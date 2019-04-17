package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.newdriveraccount.RegistrationModel;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;



public class Verify_OTP extends AppCompatActivity implements ApiManager.APIFETCHER {

    ApiManager apiManager ;
    GsonBuilder gsonBuilder;
    private TextView otpError_txt;
    private EditText otp_input;
    private LinearLayout submit_otp_layout, back;
    SessionManager sessionManager ;
    RegistrationModel.OTP_Details otp_details;
    Gson gson;
    ProgressDialog pd;
    EditText phone_number;
    String input_OTP, otp, input_number, code;
    private static int KEY_REGISTER = 110;
    String phoneNumber;
    CountryCodePicker countryCodePicker;
    Button generate_otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        apiManager = new ApiManager(this );
        sessionManager = new SessionManager(Verify_OTP.this);
        otp_details = new RegistrationModel.OTP_Details();
        setContentView(R.layout.activity_verify__otp);

        //progress bar
        pd = new ProgressDialog(this);
        pd.setMessage(Verify_OTP.this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        otp_input = (EditText)findViewById(R.id.edit_verify_otp);
        submit_otp_layout = (LinearLayout)findViewById(R.id.otp_register);
        back = (LinearLayout)findViewById(R.id.otp_back_signup);
        phone_number = (EditText)findViewById(R.id.edt_enter_phone);
        countryCodePicker = (CountryCodePicker) findViewById(R.id.otp_ccp);
        generate_otp = (Button) findViewById(R.id.generate_otp);

        submit_otp_layout.setEnabled(false);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submit_otp_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_OTP = otp_input.getText().toString().trim();

                if (input_OTP.equals("")) {
             //     Toast.makeText(Verify_OTP.this, R.string.required_field_empty, Toast.LENGTH_SHORT).show();

                    SignUpAccount(code+input_number);

                 }else if (!otp_input.getText().toString().equals(otp)) {
                //  Toast.makeText(Verify_OTP.this, R.string.invalid_otp, Toast.LENGTH_SHORT).show();


                    SignUpAccount(code+input_number);
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("phone_number", "+"+countryCodePicker.getSelectedCountryCode()+ input_number);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                   // apiManager.execution_method_get(Config.ApiKeys.KEY_Driver_register, Apis.register + "?driver_name=" + name + "&driver_email=" + email + "&driver_phone=" + phone + "&driver_password=" + password + "&city_id=" + city_id + "&car_type_id=" + car_id + "&car_model_id=" + car_model_id + "&car_number=" + carNumber + "&language_id=" + languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                 }

            }
        });


        generate_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_number = phone_number.getText().toString().trim();
                Log.e("input_phone_number====", String.valueOf(input_number));
                code = countryCodePicker.getSelectedCountryCodeWithPlus();
                Log.e("COUNTRY_CODE_PICKER===", code);
                if (input_number.equals("")){
               //  Toast.makeText(Verify_OTP.this, R.string.required_field_empty, Toast.LENGTH_SHORT).show();
                    SignUpAccount(code+input_number);
                }else {
                    SignUpAccount(code+input_number);

                }
            }
        });
    }

    //generate otp from the server
    private void SignUpAccount(String number) {
        Log.e("**PHONE_NUMBER===", number);

        HashMap<String , String > bodyparameters  = new HashMap<String, String>();
        bodyparameters.put("phone" , number);
        bodyparameters.put("flag" , "2");

        Log.e("**HASHMAP_OTP", String.valueOf(bodyparameters));
        apiManager.execution_method_post(Config.ApiKeys.KEY_VERIFY_OTP,  Apis.SEND_OTP, bodyparameters);
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

        if (APINAME.equals(Config.ApiKeys.KEY_VERIFY_OTP)){
            submit_otp_layout.setEnabled(true);

                /*RegistrationModel.OTP_Details register = new RegistrationModel.OTP_Details();
                register = gson.fromJson(""+script + "", RegistrationModel.OTP_Details.class);*/
            RegistrationModel.OTP_Details otp_response = gson.fromJson("" + script, RegistrationModel.OTP_Details.class);

            ApporioLog.logE("register---model", String.valueOf(otp_response.getStatus()));
            if (otp_response.getStatus()== 1) {
                //            Toast.makeText(this, "" + otp_response.getStatus(), Toast.LENGTH_SHORT).show();
                otp = otp_response.getOtp();
                Log.e("OTP_RECEIVED--", String.valueOf(otp));
                otp_input.requestFocus();
                if(otp_response.getAuto_otp() == 1){
                    otp_input.setText(""+otp);
                }
            } else {
                Toast.makeText(this, "" + otp_response.getMessage(), Toast.LENGTH_SHORT).show();




            }
        }
}


    @Override
    public void onFetchResultZero(String script) {

    }

}

