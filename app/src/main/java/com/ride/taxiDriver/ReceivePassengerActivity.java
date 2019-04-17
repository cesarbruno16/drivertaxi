package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.RideSession;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.deviceid.DeviceId;
import com.ride.taxiDriver.models.newridesync.NewRideSync;
import com.ride.taxiDriver.models.rideaccept.RideAccept;
import com.ride.taxiDriver.models.viewrideinfodriver.ViewRideInfoDriver;
import com.ride.taxiDriver.others.FirebaseUtils;
import com.ride.taxiDriver.others.RideSessionEvent;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.trackride.TrackRideActivity;
import com.ride.taxiDriver.urls.Apis;
import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import customviews.PulsatorLayout;
import customviews.progresswheel.ProgressWheel;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReceivePassengerActivity extends Activity implements ApiManager.APIFETCHER {

    // private String TAG = "ReceivePassengerActivity";
    public static  Activity activity;
    private static final String TAG = "ReceivePassengerActivity";

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


    @Bind(R.id.activity_countdown_timer_days)
    ProgressWheel progressWheel;
    @Bind(R.id.time_txt)
    TextView timeTxt;
    @Bind(R.id.cancel_btn)
    LinearLayout cancelBtn;
    @Bind(R.id.cancel_layout)
    LinearLayout cancelLayout;
    @Bind(R.id.main_layout_payment_mode)
    TextView mainLayoutPaymentMode;
    @Bind(R.id.main_layout_payment)
    TextView mainLayoutPayment;
    @Bind(R.id.pay_with_card_mode)
    TextView payWithCardMode;
    @Bind(R.id.pay_with_card)
    TextView payWithCard;
    @Bind(R.id.map_image)
    CircleImageView mapImage;
    @Bind(R.id.main_layout_pickup_txt)
    TextView mainLayoutPickupTxt;
    @Bind(R.id.main_layout)
    LinearLayout mainLayout;
    @Bind(R.id.ride_expire_pick_address_txt)
    TextView rideExpirePickAddressTxt;
    @Bind(R.id.ride_expire_drop_address_txt)
    TextView rideExpireDropAddressTxt;
    @Bind(R.id.expire_msg)
    TextView expireMsg;
    @Bind(R.id.ride_expire_ok_btn)
    LinearLayout rideExpireOkBtn;
    @Bind(R.id.ride_expire_layout)
    LinearLayout rideExpireLayout;
    @Bind(R.id.accept_ride_btn)
    LinearLayout acceptRideBtn;
    @Bind(R.id.cash_layout)
    LinearLayout cash_Layout;
    @Bind(R.id.card_layout)
    LinearLayout card_Layout;
    @Bind(R.id.pulsator)
    PulsatorLayout pulsator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_passenger);
        ButterKnife.bind(this);
        activity = this ;
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
        apiManager.execution_method_get(Config.ApiKeys.KEY_NEW_RIDE_SYNC, Apis.newRideSync + "?ride_id=" + super.getIntent().getExtras().getString("" + Config.IntentKeys.RIDE_ID) + "&driver_id=" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID) + "&language_id=" + languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));





        rideExpireOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    firebaseUtils.createRidePool("" + FirebaseUtils.NO_RIDES, "" + FirebaseUtils.NO_RIDE_STATUS);
                } catch (Exception e) {
                }
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiManager.execution_method_get(Config.ApiKeys.KEY_REJECT_RIDE, Apis.rejectRide
                        + "?ride_id=" + getIntent().getExtras().getString("" + Config.IntentKeys.RIDE_ID) + "&driver_id=" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID) + "&ride_status=4" + "&driver_token=" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken) + "&language_id=" + languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));

                SoundTimer.cancel();
                ProgressTimer.cancel();
            }
        });


        acceptRideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SoundTimer.cancel();
                ProgressTimer.cancel();
                apiManager.execution_method_get(Config.ApiKeys.KEY_ACEPT_RIDE, Apis.acceptRide + "?ride_id=" + getIntent().getExtras().getString("" + Config.IntentKeys.RIDE_ID) + "&driver_id=" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID) + "&driver_token=" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken) + "&language_id=" + languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));

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
                    if((vaaal - 1) <10){timeTxt.setTextColor(Color.parseColor("#e74c3c"));}else{timeTxt.setTextColor(Color.parseColor("#2ecc71"));}
                    timeTxt.setText("" + (vaaal - 1));
                }catch (Exception e){}

                maxProgress = maxProgress - progress_quadrant;
                progressWheel.setProgress(maxProgress);
            }

            @Override
            public void onFinish() {

                if (Config.ReceiverPassengerActivity) {
                    apiManager.execution_method_get(Config.ApiKeys.KEY_REJECT_RIDE, Apis.rejectRide + "?ride_id=" + getIntent().getExtras().getString("" + Config.IntentKeys.RIDE_ID) + "&driver_id=" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID) + "&ride_status=4" + "&driver_token=" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken) + "&language_id=" + languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
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
        progressWheel.setProgress(maxProgress);
        startTimer();
    }


    private void setTimeInterval(String maxtime, String differencetime) {
        try {
            MAXTIME = (Long.parseLong(""+maxtime ) * 1000);
            long difference_time = (Long.parseLong(""+differencetime) * 1000);
            STARTTIME = MAXTIME - difference_time;
            if(STARTTIME <= 1 ){
                apiManager.execution_method_get(Config.ApiKeys.KEY_REJECT_RIDE, Apis.rejectRide
                        + "?ride_id=" + getIntent().getExtras().getString("" + Config.IntentKeys.RIDE_ID) + "&driver_id=" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID) + "&ride_status=4" + "&driver_token=" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken) + "&language_id=" + languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
            }else{
                timeTxt.setText(""+(STARTTIME / 1000));
                setprogressQuadAndMaxProgress(MAXTIME, STARTTIME);
            }
        } catch (Exception e) {
            ApporioLog.logE("" + TAG, "Exception Caught while taking time for progress timer -->" + e.getMessage());
        }
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

    public void setViewAccordingToRideStatus(String ride_status, String message) {

        if (ride_status.equals("1")) {
            cancelLayout.setVisibility(View.VISIBLE);
            mainLayout.setVisibility(View.VISIBLE);
            acceptRideBtn.setVisibility(View.VISIBLE);
            rideExpireLayout.setVisibility(View.GONE);


        } // Ride is still live for demotaxiappdriver
        else {  // some thing went wrong
            cancelLayout.setVisibility(View.GONE);
            mainLayout.setVisibility(View.GONE);
            acceptRideBtn.setVisibility(View.GONE);
            rideExpireLayout.setVisibility(View.VISIBLE);
            expireMsg.setText("" + message);
        }
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
                rideExpirePickAddressTxt.setText("" + viewRideInfoDriver.getDetails().getPickup_location());
                rideExpireDropAddressTxt.setText("" + viewRideInfoDriver.getDetails().getDrop_location());
                Glide.with(this).load("" + Apis.googleImage + "" + viewRideInfoDriver.getDetails().getPickup_lat() + "," + viewRideInfoDriver.getDetails().getPickup_long() + "&zoom=15&size=400x400&key=" + ReceivePassengerActivity.this.getResources().getString(R.string.google_map_key)).into(mapImage);

                String value = viewRideInfoDriver.getDetails().getPayment_option_name();
                Log.d("**value==", viewRideInfoDriver.getDetails().getPayment_option_name());

                if (value.equals("Cash")) {
                    cash_Layout.setVisibility(View.VISIBLE);
                } else {
                    card_Layout.setVisibility(View.VISIBLE);
                }

                setTimeInterval(""+viewRideInfoDriver.getDetails().getDriver_request_time() , ""+viewRideInfoDriver.getDetails().getDifferenceInSeconds() );




            }

            if (APINAME.equals(Config.ApiKeys.KEY_NEW_RIDE_SYNC)) {
                AcceptCheck ac_one = gson.fromJson("" + script, AcceptCheck.class);
                if (ac_one.getResult() == 1) {
                    NewRideSync newRideSync = gson.fromJson("" + script, NewRideSync.class);
                    setViewAccordingToRideStatus(newRideSync.getResult().toString(), "" + newRideSync.getMsg());
                } else {
                    setViewAccordingToRideStatus("0", "" + ac_one.getMsg());
                }
            }


            if (APINAME.equals("" + Config.ApiKeys.KEY_REJECT_RIDE)) {
                DeviceId deviceId = gson.fromJson("" + script, DeviceId.class);
                if (deviceId.getResult().toString().equals("1")) {
                    Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_SHORT).show();
                    finish();
                    firebaseUtils.createRidePool("" + FirebaseUtils.NO_RIDES, "" + FirebaseUtils.NO_RIDE_STATUS);
                } else {
                    setViewAccordingToRideStatus("0", "" + deviceId.getMsg());
                }
            }


            if (APINAME.equals("" + Config.ApiKeys.KEY_ACEPT_RIDE)) {
                AcceptCheck ac = gson.fromJson("" + script, AcceptCheck.class);
                if (ac.getResult() == 1) {
                    RideAccept rideAccept = gson.fromJson("" + script, RideAccept.class);
                    Log.e("**script--trialreceivepassener", String.valueOf(script));

                    if (rideAccept.getResult() == 1) {
                        new RideSession(this).setRideSesion(rideAccept.getDetails().getRide_id(), rideAccept.getDetails().getUser_id(), rideAccept.getDetails().getUser_name(), rideAccept.getDetails().getUser_phone(), rideAccept.getDetails().getCoupon_code(), rideAccept.getDetails().getPickup_lat(), rideAccept.getDetails().getPickup_long(), rideAccept.getDetails().getPickup_location(), rideAccept.getDetails().getDrop_lat(), rideAccept.getDetails().getDrop_long(), rideAccept.getDetails().getDrop_location(), rideAccept.getDetails().getRide_date(), rideAccept.getDetails().getRide_time(), rideAccept.getDetails().getLater_date(), rideAccept.getDetails().getLater_time(), rideAccept.getDetails().getDriver_id(), rideAccept.getDetails().getRide_type(), rideAccept.getDetails().getRide_status(), rideAccept.getDetails().getStatus());
                        FirebaseDatabase.getInstance().getReference("" + Config.RideTableReference).child("" + rideAccept.getDetails().getRide_id()).setValue(new RideSessionEvent("" + rideAccept.getDetails().getRide_id(), "" + Config.Status.NORMAL_ACCEPTED, "Not yet generated", "0"));
                        startActivity(new Intent(this, TrackRideActivity.class)
                                .putExtra("customer_name", "" + rideAccept.getDetails().getUser_name())
                                .putExtra("customer_phone", "" + rideAccept.getDetails().getUser_phone()));
                        firebaseUtils.createRidePool("" + FirebaseUtils.NO_RIDES, "" + FirebaseUtils.NO_RIDE_STATUS);
                        finish();
                    }
                } else {
                    setViewAccordingToRideStatus("0", "" + ac.getMsg());
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public void onFetchResultZero(String script) {

    }


    public class AcceptCheck {

        /**
         * result : 0
         * msg : Ride Expire
         */

        private int result;
        private String msg;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }


    @Override
    public void onBackPressed() {

    }


}
