package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.DoneRideInfo;
import com.ride.taxiDriver.models.deviceid.DeviceId;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PriceFareActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    RatingBar rating_bar;
    public static Activity pricefare;

    ProgressDialog pd;

    SessionManager sessionManager;

    String driver_token;
    LanguageManager languageManager;
    String language_id, ride_id;
    TextView  pay_mode, pick_location_txt , drop_location_txt, tv_total_time, tv_waiting_time, tv_ride_distance, fare_txt, ride_time_charges_txt, waiting_charge_txt, coupon_price_txt, coupon_code_txt, tv_ride_fare, total_payble_fare_txt_large ,  peak__charge_txt_charge_txt  , night_charge_txt;
    ApiManager apiManager ;
    DoneRideInfo doneRideInfo ;
    LinearLayout coupon_layout ;
    EditText comments ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiManager = new ApiManager(this);
        languageManager = new LanguageManager(this);
        setContentView(R.layout.price_fare_new);
        getSupportActionBar().hide();
        pricefare = this;
        pd = new ProgressDialog(this);
        pd.setMessage(""+this.getResources().getString(R.string.loading));

        apiManager.execution_method_get(Config.ApiKeys.KEY_View_done_ride_info , Apis.viewDoneRide+"?done_ride_id="+getIntent().getExtras().getString("done_ride_id")+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));


        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        sessionManager = new SessionManager(this);
        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);

        pay_mode = (TextView)findViewById(R.id.pay_mode);
        pick_location_txt = (TextView) findViewById(R.id.pick_location_txt);
        tv_ride_distance = (TextView) findViewById(R.id.tv_ride_distance);
        tv_waiting_time = (TextView) findViewById(R.id.tv_waiting_time);
        tv_total_time = (TextView) findViewById(R.id.tv_total_time);
        drop_location_txt = (TextView) findViewById(R.id.drop_location_txt);
        fare_txt = (TextView) findViewById(R.id.fare_txt);
        ride_time_charges_txt = (TextView) findViewById(R.id.ride_time_charges_txt);
        waiting_charge_txt = (TextView) findViewById(R.id.waiting_charge_txt);
        coupon_price_txt = (TextView) findViewById(R.id.coupon_price_txt);
        coupon_code_txt = (TextView) findViewById(R.id.coupon_code_txt);
        tv_ride_fare = (TextView) findViewById(R.id.tv_ride_fare);
        rating_bar = (RatingBar) findViewById(R.id.rating_bar);
        coupon_layout = (LinearLayout) findViewById(R.id.coupon_layout);
        comments = (EditText) findViewById(R.id.comments);
        total_payble_fare_txt_large = (TextView) findViewById(R.id.total_payble_fare_txt_large);

        night_charge_txt = (TextView) findViewById(R.id.night_charge_txt);
        peak__charge_txt_charge_txt = (TextView) findViewById(R.id.peak__charge_txt_charge_txt);

        ride_id = getIntent().getExtras().getString("ride_id");



        findViewById(R.id.ll_submit_rating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = String.valueOf(rating_bar.getRating());
                if (rating.equals("0.0")) {
                    Toast.makeText(PriceFareActivity.this, PriceFareActivity.this.getResources().getString(R.string.please_select_stars), Toast.LENGTH_SHORT).show();
                } else {
                    String user_id = getIntent().getExtras().getString("customerId");
                    Log.d("user_id===", user_id);
                    apiManager.execution_method_get(Config.ApiKeys.KEY_RATING_DRIVER , Apis.ratingDriver+"?ride_id="+ride_id+"&driver_id="+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)+"&user_id="+user_id+"&rating_star="+rating+"&comment="+comments.getText().toString()+"&driver_token="+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken)+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                }
            }
        });

        findViewById(R.id.pay_try).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         //       apiManager.execution_method_get(Config.ApiKeys.KEY_View_done_ride_info , Apis.baseDomain+"?done_ride_id="+getIntent().getExtras().getString("done_ride_id")+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                apiManager.execution_method_get(Config.ApiKeys.KEY_View_done_ride_info , Apis.baseDomain+"?done_ride_id="+ride_id+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));

            }
        });
    }




    @Override
    public void onAPIRunningState(int a, String APINAME) {
        if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
            pd.show();
        }
        else {
            pd.dismiss();
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            if(APINAME.equals(Config.ApiKeys.KEY_View_done_ride_info)){
                doneRideInfo = new DoneRideInfo();
                doneRideInfo = gson.fromJson(""+script, DoneRideInfo.class);

                Log.d("**OPTION_ID__PRICEFARE===", doneRideInfo.getMsg().getPayment_option_id());
                if (doneRideInfo.getResult() == 1) {

                    if (doneRideInfo.getMsg().getPayment_option_id().equals("1")){
                        pay_mode.setText("Payment Mode : CASH");
                    }else {
                        pay_mode.setText("Payment Mode : PAY WITH CARD");
                    }

                    fare_txt.setText(""+sessionManager.getCurrencyCode()+doneRideInfo.getMsg().getAmount());

                    tv_ride_fare.setText(""+sessionManager.getCurrencyCode()+doneRideInfo.getMsg().getTotal_amount());
                    waiting_charge_txt.setText(""+sessionManager.getCurrencyCode()+doneRideInfo.getMsg().getWaiting_price());
                    pick_location_txt.setText(""+doneRideInfo.getMsg().getBegin_location());
                    drop_location_txt.setText(""+doneRideInfo.getMsg().getEnd_location());
                    tv_ride_distance.setText(""+doneRideInfo.getMsg().getDistance());
                    tv_waiting_time.setText(""+doneRideInfo.getMsg().getWaiting_time());
                    tv_total_time.setText(""+doneRideInfo.getMsg().getTot_time()+"min");
                    ride_time_charges_txt.setText(""+sessionManager.getCurrencyCode()+doneRideInfo.getMsg().getRide_time_price());
                    total_payble_fare_txt_large.setText(""+sessionManager.getCurrencyCode()+doneRideInfo.getMsg().getTotal_amount());
                    night_charge_txt.setText(""+sessionManager.getCurrencyCode()+doneRideInfo.getMsg().getNight_time_charge());
                    peak__charge_txt_charge_txt.setText(""+sessionManager.getCurrencyCode()+doneRideInfo.getMsg().getPeak_time_charge());
                    if(doneRideInfo.getMsg().getCoupons_code().equals("")){
                        coupon_layout.setVisibility(View.GONE);
                    }else{
                        coupon_layout.setVisibility(View.VISIBLE);
                        coupon_code_txt.setText(getString(R.string.PRICE_FARE_coupon)+doneRideInfo.getMsg().getCoupons_code()+")");
                        coupon_price_txt.setText("-"+sessionManager.getCurrencyCode()+doneRideInfo.getMsg().getCoupons_price());
                    }

                } else {
                    Toast.makeText(PriceFareActivity.this, "" + doneRideInfo.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }if(APINAME.equals(Config.ApiKeys.KEY_RATING_DRIVER)){
                DeviceId deviceId = new DeviceId();
                deviceId = gson.fromJson(""+script, DeviceId.class);

                if (deviceId.getResult().toString().equals("1")) {
                    Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_SHORT).show();

                    finalizeOtherActivities ();
                } else if (deviceId.getResult().toString().equals("419")) {
//                    sessionManager.logoutUser();
//                    Intent intent = new Intent(this, SplashActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
//                    finish();
                } else {
                    Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }} catch (Exception e){}

    }

    @Override
    public void onFetchResultZero(String script) {

    }


    private void finalizeOtherActivities() {
        finish();
        try {
            TripHistoryActivity.activity.finish();
        }catch (Exception e){

        }
        try {
            SelectedRidesActivity.activity.finish();
        }catch (Exception e){

        }

    }
}
