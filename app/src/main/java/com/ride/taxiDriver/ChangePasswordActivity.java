package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.deviceid.DeviceId;
import com.ride.taxiDriver.parsing.AccountModule;
import com.ride.taxiDriver.samwork.ApiManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ChangePasswordActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    EditText edt_o_pass, edt_n_pass, edt_c_pass;
    LinearLayout ll_done_change_password, ll_back_change_password;
    public static Activity changepasswordactivity;
    String driverid;

    ProgressDialog pd;

    SessionManager sessionManager;
    String driver_token;

    LanguageManager languageManager;
    String language_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().hide();
        changepasswordactivity = this;

        pd = new ProgressDialog(this);
        pd.setMessage(""+this.getResources().getString(R.string.loading));

        languageManager=new LanguageManager(this);
        language_id=languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        sessionManager = new SessionManager(this);
        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);

        edt_o_pass = (EditText) findViewById(R.id.edt_o_pass);
        edt_n_pass = (EditText) findViewById(R.id.edt_n_pass);
        edt_c_pass = (EditText) findViewById(R.id.edt_c_pass);
        ll_done_change_password = (LinearLayout) findViewById(R.id.ll_done_change_password);
        ll_back_change_password = (LinearLayout) findViewById(R.id.ll_back_change_password);

        driverid = new SessionManager(ChangePasswordActivity.this).getUserDetails().get(SessionManager.KEY_DRIVER_ID);

        ll_done_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String o_p = edt_o_pass.getText().toString();
                String n_p = edt_n_pass.getText().toString();
                String c_p = edt_c_pass.getText().toString();

                if (o_p.equals("")) {
                    Toast.makeText(ChangePasswordActivity.this, R.string.old_pasword_field_is_empty, Toast.LENGTH_SHORT).show();
                } else if (n_p.equals("")) {
                    Toast.makeText(ChangePasswordActivity.this, R.string.new_password_field_is_empty, Toast.LENGTH_SHORT).show();
                } else if (c_p.equals("")) {
                    Toast.makeText(ChangePasswordActivity.this, R.string.confirm_password_field_is_empty, Toast.LENGTH_SHORT).show();
                } else if (n_p.length() < 6) {
                    Toast.makeText(ChangePasswordActivity.this, R.string.set_password_id_of_min_six_character, Toast.LENGTH_SHORT).show();
                } else if (!(c_p.equals(n_p))) {
                    Toast.makeText(ChangePasswordActivity.this, R.string.password_not_matched, Toast.LENGTH_SHORT).show();
                } else {
                    AccountModule accountModule = new AccountModule(ChangePasswordActivity.this , ChangePasswordActivity.this);
                    accountModule.cpApi(driverid, o_p, n_p, driver_token,language_id);
                }
            }
        });

        ll_back_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
        try{GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            if (APINAME.equals("Change Password")) {

                DeviceId deviceId;
                deviceId = gson.fromJson(""+script, DeviceId.class);

                if (deviceId.getResult().toString().equals("1")) {
                    Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_SHORT).show();
                    ChangePasswordActivity.changepasswordactivity.finish();
                }else {
                    Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }}catch (Exception e){}

    }

    @Override
    public void onFetchResultZero(String script) {

    }

}
