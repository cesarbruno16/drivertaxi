package com.ride.taxiDriver;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.ModelAppVersion;
import com.ride.taxiDriver.models.register.Register;
import com.ride.taxiDriver.others.AppUtils;
import com.ride.taxiDriver.others.FirebaseUtils;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vansuita.library.CheckNewAppVersion;

import org.json.JSONException;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;


// Hierarchy
// Check Permission --> Check GPS Connectivity --> Check Internet Connectivity --> fetch Remote Config --> Check Build variant --> Check Session Login Status --> Run Handler (2 sec )--> main Activity
public class SplashActivity extends BaseInternetCheckActivity implements ApiManager.APIFETCHER {

    private static final String TAG = "SplashActivity";
    @Bind(R.id.register_btn)
    LinearLayout registerBtn;
    @Bind(R.id.login_btn)
    LinearLayout loginBtn;
    @Bind(R.id.LoginBox)
    LinearLayout LoginBox;
    @Bind(R.id.loading_text)
    TextView loadingText;
    @Bind(R.id.demo_login)
    CardView demoLogin;
    private boolean is_gps_dialog_shown = false;
    private boolean is_internet_dialog_is_shown = false;
    private boolean is_version_dialog_is_shown = false;
    FirebaseUtils firebaseUtils ;
    ApiManager apiManager;
    String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    Gson gson;
    ModelAppVersion modelAppVersion;
    public static Activity splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseRemoteConfigSettings remoteConfigSettings = new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(BuildConfig.DEBUG).build();
        apiManager = new ApiManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        firebaseUtils = new FirebaseUtils(this);
        gson = new GsonBuilder().create();
        splash = this;
        new LanguageManager(this).createLanguageSession();

        if (!AppUtils.hasPermissions(this, PERMISSIONS)) {
            ApporioLog.logI(TAG, "Checking Permission On Splash");
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        } else {
            startGPSCheck();
        }


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        });


        demoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDemoUserDialog();
            }
        });
    }


    private void showDemoUserDialog() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.demo_driver_dialog);
        dialog.setCancelable(false);

        final EditText demo_name = (EditText) dialog.findViewById(R.id.demo_name);
        final EditText demo_phone_email = (EditText) dialog.findViewById(R.id.demo_phone_email);

        dialog.findViewById(R.id.back_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        final   HashMap<String , String > data  = new HashMap<String, String>();
        data.put("unique_number" , ""+ Settings.Secure.getString(SplashActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID));



        dialog.findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(demo_phone_email.getText().toString().contains("@")){
                    data.put("user_name" , ""+demo_name.getText().toString());
                    data.put("user_email" , ""+demo_phone_email.getText().toString());
                    data.put("user_phone" , "");
                    apiManager.execution_method_post(Config.ApiKeys.KEY_DEMO_USER , ""+ Apis.DemoRegister , data);
                }else{
                    data.put("user_name" , ""+demo_name.getText().toString());
                    data.put("user_email" , "");
                    data.put("user_phone" , ""+demo_phone_email.getText().toString());
                    apiManager.execution_method_post(Config.ApiKeys.KEY_DEMO_USER , ""+ Apis.DemoRegister, data);
                }
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.demo_ok_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(demo_phone_email.getText().toString().contains("@")){
                    data.put("user_name" , ""+demo_name.getText().toString());
                    data.put("user_email" , ""+demo_phone_email.getText().toString());
                    data.put("user_phone" , "");
                    apiManager.execution_method_post(Config.ApiKeys.KEY_DEMO_USER , ""+ Apis.DemoRegister , data);
                }else{
                    data.put("user_name" , ""+demo_name.getText().toString());
                    data.put("user_email" , "");
                    data.put("user_phone" , ""+demo_phone_email.getText().toString());
                    apiManager.execution_method_post(Config.ApiKeys.KEY_DEMO_USER , ""+ Apis.DemoRegister , data);
                }
                dialog.dismiss();
            }
        });


        dialog.show();
    }




    private void startInternetCheckProcess() {
        ApporioLog.logI(TAG, "Now Checking net Connectivity");
        if (AppUtils.isNetworkConnected(this)) {
            ApporioLog.logI(TAG, "Internet Connectivity Status " + true);
            try {
                fetchRemoteConfig();
            } catch (Exception e) {
            }
        } else {
            ApporioLog.logI(TAG, "Internet Connectivity Status " + false + ", Now Showing Internet Dialog");
            if (!is_internet_dialog_is_shown) {
                showInternetDialog();
            }
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!AppUtils.checkGPSisOnOrNot(SplashActivity.this)) {
            showGPSDialog();
        } else {
            ApporioLog.logI(TAG, "Now GPS Status = " + true);
            startInternetCheckProcess();
        }
    }

    private void fetchRemoteConfig() throws Exception {
        ApporioLog.logI(TAG, "Fetching  Remote Config");
        long cacheExpiration = 3600;
        ApporioLog.logI("" + TAG, "Started fetching Configurations");
        HashMap<String, String> data = new HashMap<>();
        data.put("application_version", "0");
        data.put("flag", "2");
        data.put("application", "2");
        apiManager.execution_method_post("" + Config.ApiKeys.APP_VERSIONS, "" + Apis.AppVersions, data);
    }

    private void checkForVersionUpdation() throws JSONException {
        new CheckNewAppVersion(this).setOnTaskCompleteListener(new CheckNewAppVersion.ITaskComplete() {
            @Override
            public void onTaskComplete(final CheckNewAppVersion.Result result) {
                ApporioLog.logI("" + TAG, "Has new version available " + result.hasNewVersion());
                ApporioLog.logI("" + TAG, "App store version code " + result.getNewVersionCode());
                ApporioLog.logI("" + TAG, "Current version of App" + result.getOldVersionCode());


                try {
                    if (result.hasNewVersion() && modelAppVersion.getDetails().getAndroid_driver_current_version().contains("" + result.getNewVersionCode()) && modelAppVersion.getDetails().getAndroid_driver_mandantory_update().contains("1")) {
                        ApporioLog.logI(TAG, "Now Showing app update dialog with mandatory approach");
                        loadingText.setText(R.string.some_man_datory_is_available);
                        showUpdationDialog(true, result);
                    } else if (result.hasNewVersion() && modelAppVersion.getDetails().getAndroid_driver_current_version().equals("" + result.getNewVersionCode()) && modelAppVersion.getDetails().getAndroid_driver_mandantory_update().contains("")) {
                        ApporioLog.logI(TAG, "Now Showing app update dialog with Non mandatory approach");
                        loadingText.setText(R.string.non_mandatory_update);
                        showUpdationDialog(false, result);
                    } else if (result.hasNewVersion() && !modelAppVersion.getDetails().getAndroid_driver_current_version().equals("" + result.getNewVersionCode())) {
                        ApporioLog.logI(TAG, "Now Showing app update dialog with Non mandatory approach because unable to judge from back end ");
                        loadingText.setText(R.string.non_mandatory_update);
                        showUpdationDialog(false, result);
                    } else if (!result.hasNewVersion()) {
                        ApporioLog.logI(TAG, "Initiating splash process");
                        loadingText.setText(R.string.app_is_up_to_date);
                        startCheckingLoginProcedure();
                    } else {
                        loadingText.setText("Something went wrong");
                    }
                } catch (Exception e) {
                    Toast.makeText(SplashActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).execute();


    }

    private void startCheckingLoginProcedure() {
        ApporioLog.logI(TAG, "Checking login status in session");
        if (new SessionManager(this).isLoggedIn()) {
            ApporioLog.logI(TAG, "Driver is logged in and now launching MainActivity");
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        } else {
            ApporioLog.logI(TAG, "Driver is not logged in and now launching Login Screen");
            LoginBox.setVisibility(View.VISIBLE);
        }

    }

    public void showGPSDialog() {
        if (!is_gps_dialog_shown) {
            ApporioLog.logI(TAG, "Now GPS Status = " + false + ", Now Showing Dialog");
            new CFAlertDialog.Builder(this)
                    .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                    .setTitle(R.string.enable_app_location)
                    .setMessage(R.string.in_order_to_use_app_settings)
                    .addButton(SplashActivity.this.getString(R.string.open_location_settings), -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.END, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            SplashActivity.this.startActivity(myIntent);
                            dialogInterface.dismiss();
                            is_gps_dialog_shown = false;
                        }
                    }).setCancelable(false).show();
            is_gps_dialog_shown = true;
        }

    }

    private void showInternetDialog() {
        new CFAlertDialog.Builder(this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTextGravity(Gravity.CENTER)
                .setTitle(R.string.no_internet_connectivity)
                .setHeaderView(R.layout.dialog_header_no_internet)
                .setMessage(R.string.it_seems_you_are_out_of_internet_connection)
                .addButton(SplashActivity.this.getString(R.string.retry), -1, -1, CFAlertDialog.CFAlertActionStyle.DEFAULT, CFAlertDialog.CFAlertActionAlignment.END, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        is_internet_dialog_is_shown = false;
                        startInternetCheckProcess();
                    }
                }).setCancelable(false).show();
        is_internet_dialog_is_shown = true;
    }

    private void showUpdationDialog(final boolean is_maindatory, final CheckNewAppVersion.Result result) {
        if (!is_version_dialog_is_shown) {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(R.string.new_version_is_avaiable);
            dialog.setCancelable(false);
            dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    is_version_dialog_is_shown = false;
                    result.openUpdateLink();
                }
            });

            if (!is_maindatory) {
                dialog.setNegativeButton(R.string.do_it_later, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        is_version_dialog_is_shown = false;
                        startCheckingLoginProcedure();
                    }
                });
            }
            dialog.show();
            is_version_dialog_is_shown = true;
        }
    }


    private void showAppmaintainanceDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(R.string.your_app_is_in_maintainance);
        dialog.setCancelable(false);
        dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                finish();

            }
        });
        dialog.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (AppUtils.hasPermissions(SplashActivity.this, PERMISSIONS)) {
                    startGPSCheck();
                } else {
                    ApporioLog.logI("" + TAG, "Some Permissions are missing");
                }
                return;
            }
        }
    }

    private void startGPSCheck() {
        ApporioLog.logI(TAG, "Checking GPS status");
        if (!AppUtils.checkGPSisOnOrNot(SplashActivity.this)) {
            showGPSDialog();
        } else {
            ApporioLog.logI(TAG, "Now GPS Status = " + true);
            startInternetCheckProcess();
        }
    }


    @Override
    public void onAPIRunningState(int a, String APINAME) {

        if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
            loadingText.setText(R.string.fetching_versions);
        } else {
            loadingText.setText(R.string.checking_new_versions);
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {

        switch (APINAME){
            case Config.ApiKeys.KEY_DEMO_USER:
                Register register = gson.fromJson(""+script, Register.class);
                createDriverSession(register);
                break ;
            case Config.ApiKeys.APP_VERSIONS:
                ApporioLog.logI(TAG, "Successfully fetched the remote config");
                modelAppVersion = gson.fromJson("" + script, ModelAppVersion.class);

                try {
                    if (modelAppVersion.getDetails().getAndroid_driver_maintenance_mode().equals("1")) {
                        ApporioLog.logI(TAG, "Application is in under maintainence");
                        showAppmaintainanceDialog();
                    } else {
                        ApporioLog.logI(TAG, "Checking version of application.");
//                        checkForVersionUpdation();
                        ApporioLog.logI(TAG, "Initiating splash process");
                        loadingText.setText("Versão Atualizada");
                        startCheckingLoginProcedure();
                    }
                } catch (Exception e) {
                    ApporioLog.logE("" + TAG, "Exception Caught in OnFetchComplete ==>" + e.getMessage());
                }
                break;
        }
    }

    private void createDriverSession(Register register) {
        if(register.getDetails().getDetail_status().equals("1")){
            Toast.makeText(splash, "Documento do motorista não registrado", Toast.LENGTH_SHORT).show();
        }else if (register.getDetails().getDetail_status().equals("2")){
            new SessionManager(this).createLoginSession(register.getDetails().getDriver_id(),
                    register.getDetails().getDriver_name(),register.getDetails().getDriver_phone(),
                    register.getDetails().getDriver_email(),register.getDetails().getDriver_image(),
                    register.getDetails().getDriver_password(),register.getDetails().getDriver_token(),
                    register.getDetails().getDevice_id(),Config.Devicetype,register.getDetails().getRating(),
                    register.getDetails().getCar_type_id(),register.getDetails().getCar_model_id(),
                    register.getDetails().getCar_number(),register.getDetails().getCity_id(),
                    register.getDetails().getRegister_date(),register.getDetails().getLicense(),
                    register.getDetails().getRc(),register.getDetails().getInsurance(),"other_doc","getlast update","last update date ",
                    register.getDetails().getCompleted_rides(), register.getDetails().getReject_rides(),
                    register.getDetails().getCancelled_rides(),
                    register.getDetails().getLogin_logout(),register.getDetails().getBusy(),
                    register.getDetails().getOnline_offline(),register.getDetails().getDetail_status(),
                    register.getDetails().getDriver_admin_status(),register.getDetails().getCar_type_name(),
                    register.getDetails().getCar_model_name() , "" , ""+register.getDetails().getCity_name(),
                    register.getDetails().getDriver_bank_name(), register.getDetails().getDriver_account_number(),
                    register.getDetails().getDriver_account_name());

            firebaseUtils.setUpDriver();
            firebaseUtils.createRidePool(FirebaseUtils.NO_RIDES , FirebaseUtils.NO_RIDE_STATUS);
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }else if (register.getDetails().getDetail_status().equals("3")){
            Toast.makeText(splash, "Documento não verificado staus = 3", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFetchResultZero(String script) {

    }


}
