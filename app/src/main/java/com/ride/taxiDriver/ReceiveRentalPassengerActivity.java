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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.manager.RideSession;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.restmodels.NewRideAcceptmodel;
import com.ride.taxiDriver.models.restmodels.NewRideRejectModel;
import com.ride.taxiDriver.models.restmodels.ResultStatusChecker;
import com.ride.taxiDriver.others.FirebaseUtils;
import com.ride.taxiDriver.others.RideSessionEvent;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import customviews.PulsatorLayout;
import de.hdodenhof.circleimageview.CircleImageView;


public class ReceiveRentalPassengerActivity extends Activity implements ApiManager.APIFETCHER {

    private static final String TAG = "ReceiveRentalPassengerActivity";
    ApiManager apiManager;
    SessionManager sessionManager;
    RideSession rideSession;
    Gson gson;
    ProgressDialog progressDialog;
    CountDownTimer SoundTimer, ProgressTimer;
    FirebaseUtils firebaseUtils ;

    LinearLayout cash_layout, card_layout;
    public static MediaPlayer mediaPlayer;
    TextView main_layout_payment;
    long MAXTIME = 30000;
    long STARTTIME = 30000;

    @Bind(R.id.map_image)
    CircleImageView mapImage;
    @Bind(R.id.car_type_image)
    ImageView carTypeImage;
    @Bind(R.id.car_type_name_txt)
    TextView carTypeNameTxt;
    @Bind(R.id.package_txt)
    TextView packageTxt;
    @Bind(R.id._time_of_booking_txt)
    TextView TimeOfBookingTxt;
    @Bind(R.id.eta_price_txt)
    TextView etaPriceTxt;
    @Bind(R.id.pickup_address_txt)
    TextView pickupAddressTxt;
    @Bind(R.id.accept_btn)
    LinearLayout acceptBtn;
    @Bind(R.id.reject_btn)
    LinearLayout rejectBtn;
    @Bind(R.id.pulsator)
    PulsatorLayout pulsator;
    @Bind(R.id.time_txt)
    TextView timeTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiManager = new ApiManager(this);
        sessionManager = new SessionManager(this);
        rideSession = new RideSession(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("" + this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        gson = new GsonBuilder().create();
        firebaseUtils = new FirebaseUtils(this);
        setContentView(R.layout.activity_receive_rental_passenger);
        ButterKnife.bind(this);
        pulsator.start();

        //     main_layout_payment = (TextView) findViewById(R.id.main_layout_payment);

        HashMap<String, String> data = new HashMap<>();
        data.put("rental_booking_id", "" + getIntent().getExtras().getString("" + Config.IntentKeys.RIDE_ID));
        apiManager.execution_method_post(Config.ApiKeys.KEY_REST_RIDE_INFO, "" + Apis.Rideinfo, data);

        card_layout = (LinearLayout) findViewById(R.id.card_layout);
        cash_layout = (LinearLayout) findViewById(R.id.cash_layout);



        try {setMediaSound();} catch (Exception e) {}








        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundTimer.cancel();
                ProgressTimer.cancel();

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("rental_booking_id", "" + getIntent().getExtras().getString("" + Config.IntentKeys.RIDE_ID));
                data.put("driver_id", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                data.put("driver_token", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken));

                apiManager.execution_method_post(Config.ApiKeys.KEY_RESt_ACCEPT_API, "" + Apis.AcceptRide, data);
            }
        });

        rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundTimer.cancel();
                ProgressTimer.cancel();
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("rental_booking_id", "" + getIntent().getExtras().getString("" + Config.IntentKeys.RIDE_ID));
                data.put("driver_id", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                data.put("driver_token", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken));
                apiManager.execution_method_post(Config.ApiKeys.KEY_REST_REJECT_RIDE, "" + Apis.RejectRide, data);
            }
        });

    }

    private void setTimeInterval(String MaxValue , String TimeDifference) {
        try {
            MAXTIME = (Long.parseLong(MaxValue ) * 1000);
            long difference_time = (Long.parseLong(TimeDifference ) * 1000);
            STARTTIME = MAXTIME - difference_time;
            if(STARTTIME <=1){
                firebaseUtils.createRidePool("" + FirebaseUtils.NO_RIDES, "" + FirebaseUtils.NO_RIDE_STATUS);
                finish();
            }else{
                timeTxt.setText(""+(STARTTIME / 1000));
                startTimer();
            }

        } catch (Exception e) {
            ApporioLog.logE("" + TAG, "Exception Caught while taking time for progress timer -->" + e.getMessage());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Config.RentalReceivepassengerActivity = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Config.RentalReceivepassengerActivity = false;
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

        try {
            switch (APINAME) {
                case Config.ApiKeys.KEY_REST_RIDE_INFO:
                    ResultStatusChecker rs = gson.fromJson("" + script, ResultStatusChecker.class);
                    if (rs.getStatus() == 1) {
                        //  NewRideInfoModel response = gson.fromJson("" + script, NewRideInfoModel.class);
                        RentalRideInfoModel response = gson.fromJson("" + script, RentalRideInfoModel.class);
                        pickupAddressTxt.setText("" + response.getDetails().getPickup_location());
                        TimeOfBookingTxt.setText("" + response.getDetails().getBooking_time());
                        carTypeNameTxt.setText("" + response.getDetails().getCar_type_name());
                        packageTxt.setText("" + response.getDetails().getPackage_name());
                        etaPriceTxt.setText(sessionManager.getCurrencyCode() + "" + response.getDetails().getPackage_price());

                        String value = "" + response.getDetails().getPayment_option_id();

                        setTimeInterval(""+response.getDetails().getDriver_request_time() , ""+response.getDetails().getDifferenceInSeconds());
                        Glide.with(this).load("" + Apis.googleImage + "" + response.getDetails().getPickup_lat() + "," + response.getDetails().getPickup_long() + "&zoom=15&size=400x400&key=" + ReceiveRentalPassengerActivity.this.getResources().getString(R.string.google_map_key)).into(mapImage);

                        if (value.equals("1")) {
                            cash_layout.setVisibility(View.VISIBLE);
                        } else {
                            card_layout.setVisibility(View.VISIBLE);
                        }
                    } else if (rs.getStatus() == 0) {

                    } else {
                        Toast.makeText(this, "Something went wrong with API ", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Config.ApiKeys.KEY_RESt_ACCEPT_API:
                    AcceptCheck ac_check = gson.fromJson("" + script, AcceptCheck.class);
                    if (ac_check.getStatus() == 1) {
                        NewRideAcceptmodel accept_response = gson.fromJson("" + script, NewRideAcceptmodel.class);
                        new RideSession(this).setRentalRideSession(accept_response.getDetails().getRental_booking_id(), accept_response.getDetails().getUser_id(), accept_response.getDetails().getUser_name(), accept_response.getDetails().getUser_phone(), accept_response.getDetails().getReferral_code(), accept_response.getDetails().getPickup_lat(), accept_response.getDetails().getPickup_long(), accept_response.getDetails().getPickup_location(), "", "", "", accept_response.getDetails().getBooking_date(), "ride_time", accept_response.getDetails().getBooking_date(), accept_response.getDetails().getBooking_time(), accept_response.getDetails().getDriver_id(), accept_response.getDetails().getBooking_type(), "" + Config.Status.RENTAL_ACCEPTED, accept_response.getDetails().getStatus());
                        finish();
                        startActivity(new Intent(ReceiveRentalPassengerActivity.this, RentalTrackRideActivity.class));
                        Toast.makeText(this, "" + accept_response.getMessage(), Toast.LENGTH_SHORT).show();
                        FirebaseDatabase.getInstance().getReference("" + Config.RideTableReference).child("" + accept_response.getDetails().getRental_booking_id()).setValue(new RideSessionEvent("" + accept_response.getDetails().getRental_booking_id(), "" + Config.Status.RENTAL_ACCEPTED, "Not yet generated", "0"));

                    } else {
                        finish();
                        Toast.makeText(this, "" + ac_check.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    break;

                case Config.ApiKeys.KEY_REST_REJECT_RIDE:
                    AcceptCheck ac = gson.fromJson("" + script, AcceptCheck.class);
                    if (ac.getStatus() == 1) {
                        NewRideRejectModel reject_response = gson.fromJson("" + script, NewRideRejectModel.class);
                        finish();
                        Toast.makeText(this, "" + reject_response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "" + ac.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
            }
        } catch (Exception e) {
        }
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
                    if((vaaal - 1) <10){
                        timeTxt.setTextColor(Color.parseColor("#e74c3c"));
                        mapImage.setBorderColor(Color.parseColor("#e74c3c"));
                    }else{
                        timeTxt.setTextColor(Color.parseColor("#2ecc71"));
                        mapImage.setBorderColor(Color.parseColor("#2ecc71"));
                    }
                    timeTxt.setText("" + (vaaal - 1));
                }catch (Exception e){}
            }

            @Override
            public void onFinish() {
                try{
                    timeTxt.setText("0");
                    SoundTimer.cancel();
                    ProgressTimer.cancel();
                    HashMap<String, String> data = new HashMap<String, String>();
                    data.put("rental_booking_id", "" + getIntent().getExtras().getString("" + Config.IntentKeys.RIDE_ID));
                    data.put("driver_id", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                    data.put("driver_token", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken));
                    apiManager.execution_method_post(Config.ApiKeys.KEY_REST_REJECT_RIDE, "" + Apis.RejectRide, data);
                }catch (Exception e){finish();}
            }
        }.start();
    }
    private void setMediaSound() throws Exception {

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.message_pops));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
        mediaPlayer.prepare();

    }



    @Override
    public void onFetchResultZero(String script) {

    }


    public class AcceptCheck {


        /**
         * status : 0
         * message : Ride Expire
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


    @Override
    public void onBackPressed() {

    }
}
