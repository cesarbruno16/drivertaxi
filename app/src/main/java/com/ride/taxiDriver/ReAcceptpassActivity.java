package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.ModelPartialRequestResponse;
import com.ride.taxiDriver.models.viewrideinfodriver.ViewRideInfoDriver;
import com.ride.taxiDriver.others.FirebaseUtils;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import customviews.PulsatorLayout;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReAcceptpassActivity extends Activity implements ApiManager.APIFETCHER {


    public static MediaPlayer mediaPlayer;

    long MAXTIME = 30000;
    long STARTTIME = 30000;
    int maxProgress = 360;
    int progress_quadrant;
    ApiManager apiManager;
    SessionManager sessionManager;

    GsonBuilder builder;
    Gson gson;
    ProgressDialog progressDialog;
    FirebaseUtils firebaseUtils;
    ViewRideInfoDriver viewRideInfoDriver;

    CountDownTimer SoundTimer, ProgressTimer;
    LanguageManager languageManager;
    @Bind(R.id.time_txt)
    TextView timeTxt;
    @Bind(R.id.cancel_btn)
    LinearLayout cancelBtn;
    @Bind(R.id.cancel_layout)
    LinearLayout cancelLayout;
    @Bind(R.id.map_image)
    CircleImageView mapImage;
    @Bind(R.id.main_layout_pickup_txt)
    TextView mainLayoutPickupTxt;
    @Bind(R.id.main_layout)
    LinearLayout mainLayout;
    @Bind(R.id.accept_ride_btn)
    LinearLayout acceptRideBtn;
    @Bind(R.id.pulsator)
    PulsatorLayout pulsator;
    @Bind(R.id.booking_date_txt)
    TextView booking_date_txt;
    @Bind(R.id.requested_date_txt)
    TextView requested_date_txt;
    @Bind(R.id.drop_location_txt)
    TextView drop_location_txt;









    private String TAG = "ReceivePassengerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_acceptpass);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("" + this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        firebaseUtils = new FirebaseUtils(this);
        builder = new GsonBuilder();
        gson = builder.create();
        apiManager = new ApiManager(this);
        sessionManager = new SessionManager(this);
        languageManager = new LanguageManager(this);

        try {
            setMediaSound();
        } catch (Exception e) {
        }

        apiManager.execution_method_get(Config.ApiKeys.KEY_VIEW_RIDE_INFO_DRIVER, Apis.viewRideInfoDriver + "?ride_id=" + super.getIntent().getExtras().getString("" + Config.IntentKeys.RIDE_ID) + "&driver_token=" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken) + "&language_id=" + languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));




        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundTimer.cancel();
                ProgressTimer.cancel();
                finish();
            }
        });


        acceptRideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SoundTimer.cancel();
                ProgressTimer.cancel();
                HashMap<String, String> data = new HashMap<>();
                data.put("ride_id", "" + getIntent().getExtras().getString("ride_id"));
                data.put("ride_mode", "1" );
                data.put("driver_id", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                data.put("driver_token", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken));
                apiManager.execution_method_post("" + Config.ApiKeys.PARTIAL_ACCEPT, "" + Apis.PartialAccept, data);
            }
        });


    }

    private void startTimer() {
        SoundTimer = new CountDownTimer(STARTTIME, 2000) {
            @Override
            public void onTick(long l) {
                mediaPlayer.start();
                pulsator.start();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        ProgressTimer = new CountDownTimer(STARTTIME, 1000) {
            @Override
            public void onTick(long l) {

                try{
                    int vaaal = Integer.parseInt(""+timeTxt.getText().toString());
                    if((vaaal - 1) <10){timeTxt.setTextColor(Color.parseColor("#e74c3c"));
                        mapImage.setBorderColor(Color.parseColor("#e74c3c"));}else{timeTxt.setTextColor(Color.parseColor("#2ecc71"));}
                    timeTxt.setText("" + (vaaal - 1));
                }catch (Exception e){}

                maxProgress = maxProgress - progress_quadrant;
            }

            @Override
            public void onFinish() {

                if (Config.ReceiverPassengerActivity) {
                    finish();
                }
            }
        }.start();
    }

    private void setMediaSound() throws Exception {

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.message_pops));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
        mediaPlayer.prepare();

    }

    private void setprogressQuadAndMaxProgress(long maxtime, long startTime) {
        progress_quadrant = (int) (6 * (60000 / maxtime));
        int val = (int) (maxtime / startTime);
        maxProgress = 360 / val;
        startTimer();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Config.ReceiverPassengerActivity = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Config.ReceiverPassengerActivity = false;
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

        if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
            progressDialog.show();
        } else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }

        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try {
            if (APINAME.equals("" + Config.ApiKeys.KEY_VIEW_RIDE_INFO_DRIVER)) {
                viewRideInfoDriver = gson.fromJson("" + script, ViewRideInfoDriver.class);
                mainLayoutPickupTxt.setText("" + viewRideInfoDriver.getDetails().getPickup_location());
                Glide.with(this).load("" + Apis.googleImage + "" + viewRideInfoDriver.getDetails().getPickup_lat() + "," + viewRideInfoDriver.getDetails().getPickup_long() + "&zoom=15&size=400x400&key=" + ReAcceptpassActivity.this.getResources().getString(R.string.google_map_key)).into(mapImage);

                String value = viewRideInfoDriver.getDetails().getPayment_option_name();
                Log.d("**value==", viewRideInfoDriver.getDetails().getPayment_option_name());

                try {

                    drop_location_txt.setText(""+viewRideInfoDriver.getDetails().getDrop_location());
                    requested_date_txt.setText(""+viewRideInfoDriver.getDetails().getRide_date());
                    booking_date_txt.setText(""+viewRideInfoDriver.getDetails().getLater_date());

                    MAXTIME = (60* 1000);
                    long difference_time = (0 * 1000);
                    STARTTIME = MAXTIME - difference_time;
                    Log.d("" + TAG, "MAX TIME" + MAXTIME);
                    Log.d("" + TAG, "START TIME " + STARTTIME);
                    timeTxt.setText(""+(STARTTIME / 1000));
                    setprogressQuadAndMaxProgress(MAXTIME, STARTTIME);
                } catch (Exception e) {
                    ApporioLog.logE("" + TAG, "Exception Caught while taking time for progress timer -->" + e.getMessage());
                }


            }

            if (APINAME.equals("" + Config.ApiKeys.PARTIAL_ACCEPT)) {
                ModelPartialRequestResponse modelPartialRequestResponse = gson.fromJson("" + script, ModelPartialRequestResponse.class);
                Toast.makeText(this, "" + modelPartialRequestResponse.getMsg(), Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onFetchResultZero(String script) {

    }



    @Override
    public void onBackPressed() {

    }


}