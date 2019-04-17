package com.ride.taxiDriver;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.models.AboutResponse;
import com.ride.taxiDriver.urls.Apis;
import com.ride.taxiDriver.samwork.ApiManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AboutActivity extends AppCompatActivity implements ApiManager.APIFETCHER {


    public static Activity about;
    LinearLayout ll_back_about;
    TextView tv_about, tv_version;

    LanguageManager languageManager;
    String language_id;
    ApiManager apiManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide();
        about = this;
        apiManager = new ApiManager(this);

        ll_back_about = (LinearLayout) findViewById(R.id.ll_back_about);
        tv_about = (TextView) findViewById(R.id.tv_about);
        tv_version = (TextView) findViewById(R.id.tv_version);

        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        apiManager.execution_method_get(""+Config.ApiKeys.KEY_ABOUT_US , ""+ Apis.aboutUs+"?language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));

        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(AboutActivity.this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version1 = info.versionName;
        tv_version.setText(AboutActivity.this.getResources().getString(R.string.version) + version1);

        ll_back_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

            if (APINAME.equals(""+Config.ApiKeys.KEY_ABOUT_US)) {
                AboutResponse aboutResponse;
                aboutResponse = gson.fromJson(""+script, AboutResponse.class);

                if (aboutResponse.getResult()== 1) {
                    if (language_id.equals("1")) {
                        String desc = aboutResponse.getDetails().getDescription();
                        tv_about.setText(Html.fromHtml(desc , new ImageGetter(), null));
                    }
                }
            }}catch (Exception e){}
    }

    @Override
    public void onFetchResultZero(String script) {

    }


    private class ImageGetter implements Html.ImageGetter {

        public Drawable getDrawable(String source) {
            int id;
            if (source.equals("hughjackman.jpg")) {
                id = R.drawable.app_logo_100;
            }
            else {
                return null;
            }
            Drawable d = getResources().getDrawable(id);
            d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
            return d;
        }
    };


}
