package com.ride.taxiDriver;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.models.TremsAndConditionResponse;
import com.ride.taxiDriver.urls.Apis;
import com.ride.taxiDriver.samwork.ApiManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TermsAndCondition extends AppCompatActivity implements ApiManager.APIFETCHER {

    LinearLayout bck;
    TextView tv_desc;
    public static Activity tca;

    LanguageManager languageManager;
    String language_id;
    ApiManager apiManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tc);
        getSupportActionBar().hide();
        tca = this;
        apiManager = new ApiManager(this);

        bck = (LinearLayout) findViewById(R.id.bck);
        tv_desc = (TextView) findViewById(R.id.tc);

        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        apiManager.execution_method_get(Config.ApiKeys.KEY_TERMS_AND_CONDITION , ""+ Apis.tC+"?language_id="+language_id);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            if (APINAME.equals(""+Config.ApiKeys.KEY_TERMS_AND_CONDITION)) {
                TremsAndConditionResponse terms_response;
                terms_response = gson.fromJson(""+script, TremsAndConditionResponse.class);

                if (terms_response.getResult() == 1) {
                    String desc = terms_response.getDetails().getDescription();
                    tv_desc.setText(Html.fromHtml(""+desc));
                }
            }}catch (Exception e){}

    }


    @Override
    public void onFetchResultZero(String script) {

    }

}
