package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.models.deviceid.DeviceId;
import com.ride.taxiDriver.parsing.AccountModule;
import com.ride.taxiDriver.samwork.ApiManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ForgotPasswordActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    Button submit;
    LinearLayout ll_back_forgot;
    EditText email;
    public static Activity forgotpassword;

    ProgressDialog pd;

    LanguageManager languageManager;
    String language_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
        forgotpassword = this;

        pd = new ProgressDialog(this);
        pd.setMessage(ForgotPasswordActivity.this.getResources().getString(R.string.loading));

        languageManager=new LanguageManager(this);
        language_id=languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        submit = (Button) findViewById(R.id.forgotdone);
        email = (EditText) findViewById(R.id.forgotemail);
        ll_back_forgot = (LinearLayout) findViewById(R.id.ll_back_forgot);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString().trim();
                if (email.equals("")) {
                    Toast.makeText(ForgotPasswordActivity.this,ForgotPasswordActivity.this.getResources().getString(R.string.please_enter_email) , Toast.LENGTH_SHORT).show();
                } else {
                    AccountModule accountModule = new AccountModule(ForgotPasswordActivity.this , ForgotPasswordActivity.this);
                    accountModule.fpApi(email1,language_id);
                }

            }
        });

        ll_back_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        if (APINAME.equals("Forgot Password")) {

            DeviceId deviceId;
            deviceId = gson.fromJson(""+script, DeviceId.class);

            if (deviceId.getResult().toString().equals("1")) {
                Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onFetchResultZero(String script) {

    }

}