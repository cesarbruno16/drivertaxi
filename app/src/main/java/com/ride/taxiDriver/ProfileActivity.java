package com.ride.taxiDriver;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.ResultCheck;
import com.ride.taxiDriver.models.deviceid.DeviceId;
import com.ride.taxiDriver.models.register.Register;
import com.ride.taxiDriver.others.FirebaseUtils;
import com.ride.taxiDriver.others.ImageCompressMode;
import com.ride.taxiDriver.parsing.AccountModule;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.bumptech.glide.Glide;
import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.ride.taxiDriver.logger.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.sampermissionutils.AfterPermissionGranted;
import com.sampermissionutils.EasyPermissions;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements ApiManager.APIFETCHER, Apis {
    TextView email;
    EditText account_name, bank_name, account_number;
    ImageView iv_edit_name, iv_edit_phone, iv_edit_account_number, iv_edit_account_name, iv_edit_bank_name;
    LinearLayout ll_back_profile, ll_done_profile, ll_change_password, ll_logout  ;
    EditText name, mobile , meter_ranger , location_accuracy;
    String driverid, drivername, drivermobile, drivermail, driverimage, driver_bank_name, driver_account_number, driver_account_name;
    public static Activity profileActivity;

    CircleImageView iv_profile_pic_upload;
    FirebaseUtils firebaseutil;

    Uri selectedImage;
    Bitmap bitmap1;
    String imagePath = "", imagePathCompressed = "";

    ProgressDialog pd;

    Dialog dialog;

    AccountModule accountModule;

    LanguageManager languageManager;
    String language_id;

    String driver_token;

    FirebaseUtils firebasutil ;
    SessionManager sessionManager ;
    CheckBox service_switcher ;
    ApiManager apiManager;
    private static final int RC_CAMERA_PERM = 123;
    private ContentValues values;
    private Bitmap thumbnail;
    private static final int CAMERS_PICKER = 122;
    private Uri imageUri;
    private int VERIFY_OTP = 110;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebasutil = new FirebaseUtils(this);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        profileActivity = this;

        apiManager = new ApiManager(this);
        pd = new ProgressDialog(this);
        pd.setMessage(ProfileActivity.this.getResources().getString(R.string.loading));
        firebaseutil = new FirebaseUtils(this);

        accountModule = new AccountModule(ProfileActivity.this , ProfileActivity.this);

        ll_back_profile = (LinearLayout) findViewById(R.id.ll_back_profile);
        ll_done_profile = (LinearLayout) findViewById(R.id.ll_done_profile);
        ll_change_password = (LinearLayout) findViewById(R.id.ll_change_password);
        ll_logout = (LinearLayout) findViewById(R.id.ll_logout);
        iv_profile_pic_upload = (CircleImageView) findViewById(R.id.iv_profile_pic_upload);

        meter_ranger = (EditText) findViewById(R.id.meter_ranger);
        service_switcher = (CheckBox) findViewById(R.id.service_switcher);
        location_accuracy = (EditText) findViewById(R.id.location_accuracy);

        account_name = (EditText) findViewById(R.id.account_name);
        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mob);

        name.setEnabled(false);
        mobile.setEnabled(false);

        email = (TextView) findViewById(R.id.email);
        bank_name = (EditText) findViewById(R.id.bank_name);
        account_number = (EditText) findViewById(R.id.account_number);
        iv_edit_name = (ImageView) findViewById(R.id.iv_edit_name);
        iv_edit_phone = (ImageView) findViewById(R.id.iv_edit_phone);
        iv_edit_account_number = (ImageView) findViewById(R.id.iv_edit_account_number);
        iv_edit_account_name = (ImageView) findViewById(R.id.iv_edit_account_name);
        iv_edit_bank_name = (ImageView) findViewById(R.id.iv_edit_bank_name);

        account_number.setEnabled(false);
        account_name.setEnabled(false);
        bank_name.setEnabled(false);

        sessionManager = new SessionManager(this);
        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);
        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);
        driver_bank_name = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_BANK_NAME);
        driver_account_number = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ACCOUNT_NUMBER);
        driver_account_name = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ACCOUNT_NAME);

        driverid = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID);
        drivername = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME);
        drivermobile = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_PHONE);
        drivermail = sessionManager.getUserDetails().get(SessionManager.KEY_DriverEmail);
        driverimage = sessionManager.getUserDetails().get(SessionManager.KEY_DriverImage);

        name.setText(drivername);
        mobile.setText(drivermobile);
        email.setText(drivermail);
        bank_name.setText(driver_bank_name);
        account_number.setText(driver_account_number);
        account_name.setText(driver_account_name);

        String inmage = Apis.imageDomain + driverimage ;
        Log.d("**driver_account_name===", driver_account_name);

        Log.d("***driver_bank_name" , ""+driver_bank_name);
        Log.d("***driver_account_number" , ""+driver_account_number);


        Log.d("***driver_image" , ""+inmage.replace(" " , ""));
        if(!inmage.equals("")){
            Glide.with(this).load(""+inmage.replace(" " , "")).into(iv_profile_pic_upload);
        }


        meter_ranger.setHint(""+sessionManager.getUserDetails().get(SessionManager.KEY_meter_range));
        location_accuracy.setText(""+sessionManager.getUserDetails().get(SessionManager.KEY_accuracy));
        if(sessionManager.getUserDetails().get(SessionManager.KEY_service_switcher).equals("1")){
            service_switcher.setChecked(false);
        }else{
            service_switcher.setChecked(true);
        }

        service_switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(ProfileActivity.this, "Auto update location is turned off", Toast.LENGTH_SHORT).show();
                    sessionManager.setSwitcher("0");
                }else {
                    Toast.makeText(ProfileActivity.this, "Auto update location is turned On", Toast.LENGTH_SHORT).show();
                    sessionManager.setSwitcher("1");
                }
            }
        });

        findViewById(R.id.phone_edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ProfileActivity.this, Verify_OTP.class), VERIFY_OTP);
            }
        });


        iv_profile_pic_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CFAlertDialog.Builder builder = new CFAlertDialog.Builder(ProfileActivity.this);
                builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);
                builder.setTitle(R.string.upload_profile_pic);
                builder.setItems(new String[]{getString(R.string.camera), getString(R.string.gallery)}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                      if(index == 0 ){
                          try{cameraTask();}catch (Exception e){}
                      }else if (index == 1 ){
                          Intent i1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                          i1.setType("image/*");
                          startActivityForResult(i1, 101);
                      }
                      dialogInterface.dismiss();
                    }
                });
                builder.show();


            }
        });

        ll_back_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ll_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ChangePasswordActivity.class));
            }
        });

        ll_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlogoutdialog();

            }
        });

        iv_edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                name.setClickable(true);
//                name.setFocusable(true);
//                name.setCursorVisible(true);
//                name.setFocusableInTouchMode(true);

                name.setEnabled(true);
                name.setSelection(name.getText().length());
                name.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(name, InputMethodManager.SHOW_FORCED);
            }
        });

        iv_edit_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mobile.setClickable(true);
//                mobile.setFocusable(true);
//                mobile.setCursorVisible(true);
//                mobile.setFocusableInTouchMode(true);

                mobile.setEnabled(true);
                mobile.setSelection(mobile.getText().length());
                mobile.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(mobile, InputMethodManager.SHOW_FORCED);

            }
        });

        iv_edit_account_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             account_number.setFocusableInTouchMode(true);

                account_number.setEnabled(true);
                account_number.setSelection(account_number.getText().length());
                account_number.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(mobile, InputMethodManager.SHOW_FORCED);

            }
        });

        iv_edit_account_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account_name.setFocusableInTouchMode(true);

                account_name.setEnabled(true);
                account_name.setSelection(account_name.getText().length());
                account_name.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(mobile, InputMethodManager.SHOW_FORCED);

            }
        });

        iv_edit_bank_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bank_name.setFocusableInTouchMode(true);

                bank_name.setEnabled(true);
                bank_name.setSelection(bank_name.getText().length());
                bank_name.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(mobile, InputMethodManager.SHOW_FORCED);

            }
        });
        ll_done_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString().trim();
                String mobile1 = mobile.getText().toString().trim();
                String bank_name1 = bank_name.getText().toString().trim();
                String account_name1 = account_name.getText().toString().trim();
                String account_number1 = account_number.getText().toString().trim();

                if (name1.equals("")) {
                    Toast.makeText(ProfileActivity.this, ProfileActivity.this.getResources().getString(R.string.name_can_not_be_empty), Toast.LENGTH_SHORT).show();
                } else if (mobile1.equals("")) {
                    Toast.makeText(ProfileActivity.this, ProfileActivity.this.getResources().getString(R.string.phone_can_not_be_empty), Toast.LENGTH_SHORT).show();
                } else if (bank_name1.equals("")) {
                    Toast.makeText(ProfileActivity.this, ProfileActivity.this.getResources().getString(R.string.bank_name_empty), Toast.LENGTH_SHORT).show();
                } else if (account_name1.equals("")) {
                    Toast.makeText(ProfileActivity.this, ProfileActivity.this.getResources().getString(R.string.account_name_empty), Toast.LENGTH_SHORT).show();
                } else if (account_number1.equals("")) {
                    Toast.makeText(ProfileActivity.this, ProfileActivity.this.getResources().getString(R.string.account_number_empty), Toast.LENGTH_SHORT).show();
                } else {
                    accountModule.editProfile(driverid, name1, mobile1, imagePathCompressed, driver_token, language_id, account_name1, bank_name1, account_number1);
                }
            }
        });

         findViewById(R.id.accuracy_save).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 sessionManager.setAccuracy(""+location_accuracy.getText().toString());
                 Toast.makeText(ProfileActivity.this, "Accuracy save to "+location_accuracy.getText().toString(), Toast.LENGTH_SHORT).show();
             }
         });



        findViewById(R.id.meter_range_save_btn).setOnClickListener(new View.OnClickListener() {   // this functionality is only for development purpose
            @Override
            public void onClick(View v) {

                if(!meter_ranger.equals("")){
                    sessionManager.setMeterRange(""+meter_ranger.getText().toString());
                    Toast.makeText(ProfileActivity.this, "Range Saved", Toast.LENGTH_SHORT).show();
                }


                try{
                    View view = ProfileActivity.this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }catch (Exception e){

                }
            }
        });


        findViewById(R.id.info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ProfileActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                Window window = dialog.getWindow();
                dialog.setCancelable(true);
                window.setGravity(Gravity.CENTER);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.info_dialog);
                dialog.findViewById(R.id.ok_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }


    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask()throws Exception {
        if (EasyPermissions.hasPermissions(this, android.Manifest.permission.CAMERA)) {
            try{ // Have permission, do the thing!
                values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                imageUri = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, CAMERS_PICKER);
            }catch (Exception e){}
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camera), RC_CAMERA_PERM, android.Manifest.permission.CAMERA);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try{cameraTask();}catch (Exception e){}
    }







    @Override
    protected void onDestroy() {
        super.onDestroy();
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);
        if (res == Activity.RESULT_OK) {
            try {
                if (req == 101) {
                    selectedImage = data.getData();
                    imagePath = getPath(selectedImage);

                    ImageCompressMode imageCompressMode = new ImageCompressMode(this);
                    imagePathCompressed = imageCompressMode.compressImage(imagePath);

                    // imagePathCompressed = compressImage(imagePath);

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    // Set the Image in ImageView after decoding the String
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(filePath, options);
                    final int REQUIRED_SIZE = 300;
                    int scale = 1;
                    while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                        scale *= 2;
                    options.inSampleSize = scale;
                    options.inJustDecodeBounds = false;
                    bitmap1 = BitmapFactory.decodeFile(filePath, options);
                    iv_profile_pic_upload.setImageBitmap(bitmap1);
                }
                if(req == CAMERS_PICKER){
                    thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    iv_profile_pic_upload.setImageBitmap(thumbnail);
                    imagePath = getRealPathFromURI(imageUri);
                    ImageCompressMode imageCompressMode = new ImageCompressMode(this);
                    imagePathCompressed = imageCompressMode.compressImage(imagePath);
                }
                if(req == VERIFY_OTP){
                    mobile.setText(data.getExtras().getString("phone_number"));
                }
            } catch (Exception e) {
                Logger.e("res         " + e.toString());
            }
        }
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void showlogoutdialog() {

        dialog = new Dialog(ProfileActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_for_logout);

        LinearLayout yes = (LinearLayout) dialog.findViewById(R.id.yes);
        LinearLayout no = (LinearLayout) dialog.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accountModule.logoutApi(driverid, driver_token, language_id);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void onAPIRunningState(int a, String APINAME) {
        if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
            pd.show();
        }
        if (a == ApiManager.APIFETCHER.KEY_API_IS_STOPPED) {
            pd.dismiss();
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        if (APINAME.equals("Edit Profile")) {

            Register register = new Register();
            register = gson.fromJson(""+script, Register.class);

            if (register.getResult() == 1) {
                new SessionManager(this).createLoginSession(register.getDetails().getDriver_id(), register.getDetails().getDriver_name(),
                        register.getDetails().getDriver_phone(), register.getDetails().getDriver_email(),
                        register.getDetails().getDriver_image(), register.getDetails().getDriver_password(),
                        register.getDetails().getDriver_token(), register.getDetails().getDevice_id(),
                        Config.Devicetype, register.getDetails().getRating(), register.getDetails().getCar_type_id(),
                        register.getDetails().getCar_model_id(),
                        register.getDetails().getCar_number(), register.getDetails().getCity_id(),
                        register.getDetails().getRegister_date(), register.getDetails().getLicense(),
                        register.getDetails().getRc(), register.getDetails().getInsurance(), "other_doc", "getlast update", "last update date ",
                        register.getDetails().getCompleted_rides(), register.getDetails().getReject_rides(),
                        register.getDetails().getCancelled_rides(),
                        register.getDetails().getLogin_logout(), register.getDetails().getBusy(),
                        register.getDetails().getOnline_offline(), register.getDetails().getDetail_status(),
                        register.getDetails().getDriver_admin_status(), register.getDetails().getCar_type_name(),
                        register.getDetails().getCar_model_name(), "", "" + register.getDetails().getCity_name(),
                        register.getDetails().getDriver_account_name(), register.getDetails().getDriver_account_number(),
                        register.getDetails().getDriver_bank_name());

                  new CFAlertDialog.Builder(this)
                        .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                        .setTitle(R.string.profile_updated_successfully)
                        .addButton(getString(R.string.ok), -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.END, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        }).show();

            } else if (register.getResult()== 419) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + register.getMsg());
            } else {
             //   Toast.makeText(this, "" + register.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }

        if (APINAME.equals("Logout")) {
            DeviceId deviceId = new DeviceId();
            deviceId = gson.fromJson(""+script, DeviceId.class);

            if (deviceId.getResult().toString().equals("1")) {
                Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(ProfileActivity.this, SplashActivity.class));
                finish();
                MainActivity.mainActivity.finish();


//                firebasutil.logOutDriver();
                firebasutil.setDriverOnlineStatus(false);
                firebasutil.setDriverLoginLogoutStatus(false);
                sessionManager.logoutUser();
                dialog.dismiss();
            } else if (deviceId.getResult().toString().equals("419")) {
//                sessionManager.logoutUser();
//                Intent intent = new Intent(this, SplashActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
//                finish();
            } else {
                Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_LONG).show();
            }
        }

        if (APINAME.equals(Config.ApiKeys.KEY_ONLINE_OFFLINE)){
            try{ResultCheck resultCheck = gson.fromJson(""+script , ResultCheck.class);
                if(resultCheck.result.equals("1")){
                    switch (APINAME){
                        case Config.ApiKeys.KEY_ONLINE_OFFLINE :
                            DeviceId deviceToken = gson.fromJson(""+script, DeviceId.class);
                            if(deviceToken.getMsg().equals("Online")){
                                sessionManager.setonline_offline(true);
                                firebaseutil.setDriverOnlineStatus(true);
                            }else {
                                sessionManager.setonline_offline(false);
                                firebaseutil.setDriverOnlineStatus(false);
                            }
                            break;
                    }

                    finish();
                }
                else {
                 //   Toast.makeText(this, "Result = 0", Toast.LENGTH_SHORT).show();
                }}catch (Exception e){}
        }
    }

    @Override
    public void onFetchResultZero(String script) {

    }

}