package com.ride.taxiDriver;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomerSupportActivity extends AppCompatActivity implements  ApiManager.APIFETCHER{


    @Bind(R.id.user_name_edt)EditText user_name_edt;
    @Bind(R.id.email_edt) EditText email_edt;
    @Bind(R.id.pone_edt) EditText pone_edt;
    @Bind(R.id.query_edt) EditText query_edt;
    @Bind(R.id.root) LinearLayout root;

    SessionManager sessionManager ;
    ProgressDialog progress_dialoge ;
    ApiManager apiManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManager = new SessionManager(this);
        progress_dialoge = new ProgressDialog(this);
        progress_dialoge.setMessage(""+this.getResources().getString(R.string.loading));
        apiManager = new ApiManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_support);
        ButterKnife.bind(this);
        getSupportActionBar().hide();


        findViewById(R.id.ll_back_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        findViewById(R.id.send_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_name_edt.getText().toString().equals("")  || email_edt.getText().toString().equals("") || query_edt.getText().toString().equals("") ){
                    Snackbar.make(root , R.string.please_enter_the_maindatory_details , Snackbar.LENGTH_SHORT).show();
                }else {
                    HashMap<String , String > data = new HashMap<String, String>();
                    data.put("application" , "2");
                    data.put("name" , ""+user_name_edt.getText().toString());
                    data.put("email" , ""+email_edt.getText().toString());
                    data.put("phone" , ""+pone_edt.getText().toString());
                    data.put("query" , ""+query_edt.getText().toString());
                    data.put("driver_id" , ""+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                    data.put("user_id" , "");
                    apiManager.execution_method_post(""+Config.ApiKeys.KEY_CUSTOMER_SUPPORT , ""+ Apis.CustomerSupport , data);
                }
            }
        });

    }


    public void dialogForQueryComplete(String message ) {

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.dialog_for_query_complete);
        dialog.setCancelable(false);
        TextView quer_response_message  = (TextView) dialog.findViewById(R.id.quer_response_message);
        quer_response_message.setText(""+message);

        LinearLayout ll_update = (LinearLayout) dialog.findViewById(R.id.ll_ok);
        ll_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    //////////////////////////////

    @Override
    public void onAPIRunningState(int a, String APINAME) {
        if(a == ApiManager.APIFETCHER.KEY_API_IS_STARTED){
            progress_dialoge.show();
        }else {
            progress_dialoge.dismiss();
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{ GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            if(APINAME.equals(""+Config.ApiKeys.KEY_CUSTOMER_SUPPORT)){
                QueryResponseModel data_response;
                data_response = gson.fromJson(""+script, QueryResponseModel.class);
                if(data_response.getStatus() == 1){
                    dialogForQueryComplete(""+data_response.getMessage());
                }
            }}catch (Exception e){}

    }

    @Override
    public void onFetchResultZero(String script) {

    }




    public class QueryResponseModel{

        /**
         * status : 1
         * message : Thanks For Query
         */

        private int status;
        private String message;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


}
