package com.ride.taxiDriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.ResultCheck;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;


public class ForgotPass_ConfirmPass extends AppCompatActivity implements ApiManager.APIFETCHER {

    ApiManager apiManager ;
    GsonBuilder gsonBuilder;
    private TextView otpError_txt;
    private EditText new_pass, confirm_pass;
    private LinearLayout register_password, back;
    SessionManager sessionManager ;
    Gson gson;
    ProgressDialog pd;
    String input_newPass, input_confirmPass;
    String phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        apiManager = new ApiManager(this);
        sessionManager = new SessionManager(ForgotPass_ConfirmPass.this);
        setContentView(R.layout.activity_forgotpass_changepass);
        //progress bar
        pd = new ProgressDialog(this);
        pd.setMessage(ForgotPass_ConfirmPass.this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        new_pass = (EditText) findViewById(R.id.edit_newPass);
        confirm_pass = (EditText) findViewById(R.id.edit_confrmPass);
        register_password = (LinearLayout) findViewById(R.id.register_password1);

      //  phoneNumber = getIntent().getStringExtra("phone_number");

        register_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_newPass = new_pass.getText().toString().trim();
                input_confirmPass = confirm_pass.getText().toString().trim();

                if (input_newPass.equals("")){
                    Toast.makeText(ForgotPass_ConfirmPass.this, "Required field Empty!", Toast.LENGTH_SHORT).show();
                }else if (input_confirmPass.equals("")){
                    Toast.makeText(ForgotPass_ConfirmPass.this, "Required field Empty!", Toast.LENGTH_SHORT).show();
                }else if (!confirm_pass.getText().toString().equals(new_pass.getText().toString())){
                    Toast.makeText(ForgotPass_ConfirmPass.this, "Password dors not match. Please try again!", Toast.LENGTH_SHORT).show();
                }else {
                    changePassword(getIntent().getStringExtra("phone_number"), input_confirmPass);

                }
            }
        });

    }


    private void changePassword(String phone_number, String input_confirm_pass) {
        HashMap<String, String> bodyparameters = new HashMap<String, String>();
        bodyparameters.put("driver_phone", phone_number);
        bodyparameters.put("password", input_confirm_pass);
        bodyparameters.put("flag", "2");
        apiManager.execution_method_post(Config.ApiKeys.KEY_FORGOTPASS_CONFIRMPASS, "" + Apis.FORGOTPASS_CONFIRMPASS, bodyparameters);
    }

    /////////////// samir work
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

        if (APINAME.equals("" + Config.ApiKeys.KEY_FORGOTPASS_CONFIRMPASS)) {
            try {
                Gson gson = new GsonBuilder().create();
                ResultCheck rs = gson.fromJson("" + script, ResultCheck.class);
                if (rs.result.equals("1")) {
                    Toast.makeText(this, R.string.changed_password, Toast.LENGTH_SHORT).show();
                    sessionManager.createNewPassword(input_confirmPass);
                    Intent intent = new Intent(ForgotPass_ConfirmPass.this, LoginActivity.class);
                    startActivity(intent);
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onFetchResultZero(String script) {

    }

    }
