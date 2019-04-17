package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.DocumentListModel;
import com.ride.taxiDriver.models.ResultCheck;
import com.ride.taxiDriver.models.register.Register;
import com.ride.taxiDriver.others.FirebaseUtils;
import com.ride.taxiDriver.parsing.AccountModule;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sam.placer.PlaceHolderView;
import com.sam.placer.annotations.Click;
import com.sam.placer.annotations.Layout;
import com.sam.placer.annotations.Position;
import com.sam.placer.annotations.Resolve;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DocumentActivity extends Activity implements ApiManager.APIFETCHER {

    @Bind(R.id.back)
    RelativeLayout back;
    @Bind(R.id.placeholder)
    PlaceHolderView placeholder;

    ApiManager apiManager;
    ProgressDialog progressDialog;
    DocumentListModel documentListModel;
    FirebaseUtils firebaseUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        ButterKnife.bind(this);
        firebaseUtils = new FirebaseUtils(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        apiManager = new ApiManager(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
/*
        String numbr = getIntent().getExtras().getString("email");
        String password = getIntent().getExtras().getString("password");
        Log.d("**phone_number=document=", numbr);
        Log.d("**password==", password);*/

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    AccountModule accountModule = new AccountModule(DocumentActivity.this, DocumentActivity.this);
                    accountModule.loginApi("" + getIntent().getExtras().getString("email"), "" + getIntent().getExtras().getString("password"), "1");
                } catch (Exception e) {
                    Toast.makeText(DocumentActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            apiManager.execution_method_get("document_list", "" + Apis.DocumentList + getIntent().getExtras().getString("city_id") + "&driver_id=" + getIntent().getExtras().getString("driver_id"));
        } catch (Exception e) {
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

        try {
            if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
                progressDialog.show();
            } else if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
        }

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{ if (APINAME.equals("document_list")) {
            try {
                Gson gson = new GsonBuilder().create();
                ResultCheck rs = gson.fromJson("" + script, ResultCheck.class);
                if (rs.result.equals("1")) {
                    documentListModel = gson.fromJson("" + script, DocumentListModel.class);
                    placeholder.removeAllViews();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < documentListModel.getMsg().size(); i++) {
                                placeholder.addView(new HolderDocumentItem(documentListModel.getMsg().get(i)));
                            }
                        }
                    }, 1000);


                } else {
                    Toast.makeText(this, R.string.no_doc_found_inthis_city, Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
            if (APINAME.equals("Login")) {
                Register register;
           //     LoginResponse loginResponse;
                Gson gson = new GsonBuilder().create();
                register = gson.fromJson("" + script, Register.class);
                String driver_id = register.getDetails().getDriver_id();
                String detail_status = register.getDetails().getDetail_status();
                if (detail_status.equals("1")) {
                    Toast.makeText(this, R.string.please_upload_all_documents, Toast.LENGTH_SHORT).show();
                } else if (detail_status.equals("2")) {
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
                            register.getDetails().getDriver_bank_name(), register.getDetails().getDriver_account_number(),
                            register.getDetails().getDriver_account_name());

                    Log.d("**driver_bank_name==document=", register.getDetails().getDriver_bank_name());
                    Log.d("**driver_account_number==document==", register.getDetails().getDriver_account_number());

                    Log.d("**driver_account_name==document=", register.getDetails().getDriver_account_name());

                    firebaseUtils.setUpDriver();
                    firebaseUtils.createRidePool(FirebaseUtils.NO_RIDES, FirebaseUtils.NO_RIDE_STATUS);
                    startActivity(new Intent(DocumentActivity.this, MainActivity.class));
                    try {
                        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                        finish();
                        SplashActivity.splash.finish();
                    } catch (Exception e) {
                    }
                } else if (detail_status.equals("3")) {
                    startActivity(new Intent(DocumentActivity.this, StatusActiity.class)
                            .putExtra("image" , ""+register.getDetails().getDriver_status_image())
                            .putExtra("message" , ""+register.getDetails().getDriver_status_message()));
                    try {
                        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                        finish();
                        SplashActivity.splash.finish();
                    } catch (Exception e) {
                    }
                }

            }
        }catch (Exception e){}

    }


    @Override
    public void onFetchResultZero(String script) {

    }


    @Layout(R.layout.item_document_list)
    private class HolderDocumentItem {

        @com.sam.placer.annotations.View(R.id.document_name_txt) private TextView document_name_txt;
        @com.sam.placer.annotations.View(R.id.document_status_txt) private TextView document_status_txt;



        @Position
        private int mPosition ;

        DocumentListModel.MsgBean mMsgBean;


        public HolderDocumentItem(DocumentListModel.MsgBean msgBean){
            mMsgBean = msgBean;
        }


        @Resolve
        private void onResolved() {
            try{
                document_name_txt.setText(""+mMsgBean.getDocument_name());
                setViewAccordingToStatus(Integer.parseInt(""+mMsgBean.getDocumnet_varification_status()));
            }catch (Exception e){}

        }


        @Click(R.id.root_element)
        private void OnRootElementClick(){
            if(mMsgBean.getDocumnet_varification_status().equals("0") || mMsgBean.getDocumnet_varification_status().equals("3") ){
                DocumentActivity.this.startActivity(new Intent(DocumentActivity.this , PhotoUploaderActivity.class)
                        .putExtra("driver_id" , ""+getIntent().getExtras().get("driver_id"))
                        .putExtra("document_id" , ""+mMsgBean.getDocument_id()));
            }
        }

        public void setViewAccordingToStatus(int status ){
            switch (status){
                case 0:  // Document not yet uploaded
                    document_status_txt.setText("Upload Document");
                    break;
                case 2:
                    document_status_txt.setText("verified");
                    break ;
                case 1 :
                    document_status_txt.setText("Verification Pending");
                    break ;
                case 3 :
                    document_status_txt.setText("Upload again");
            }

        }


    }

}
