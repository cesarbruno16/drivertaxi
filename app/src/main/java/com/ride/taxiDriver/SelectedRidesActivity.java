package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.logger.Logger;
import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.trackride.TrackRideActivity;
import com.ride.taxiDriver.typeface.TypefaceTextView;
import com.ride.taxiDriver.urls.Apis;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SelectedRidesActivity extends AppCompatActivity implements ApiManager.APIFETCHER {


    LanguageManager languageManager;
    SessionManager sessionManager;
    ApiManager apiManager ;
    ProgressDialog pd;
    String  ride_id;
    RideDetailsModel viewRideInfoDriver;
    public  static Activity activity ;
    RideDetailsModel rideDetailsModel;

    @Bind(R.id.ll_back_ride_select) LinearLayout llBackRideSelect;
    @Bind(R.id.ll_driver_ki_detail) LinearLayout llDriverKiDetail;
    @Bind(R.id.ll_bill) LinearLayout llBill;
    @Bind(R.id.ll_location_module) LinearLayout llLocationModule;
    @Bind(R.id.ll_total_bill) LinearLayout llTotalBill;
    @Bind(R.id.ll_track_ride) LinearLayout llTrackRide;
    @Bind(R.id.activity_selected_rides) LinearLayout activitySelectedRides;
    @Bind(R.id.bill_layout) LinearLayout bill_layout;
    @Bind(R.id.tv_rupee) TextView tvRupee;
    @Bind(R.id.tv_dis) TextView tvDis;
    @Bind(R.id.tv_time1) TextView tvTime1;
    @Bind(R.id.start_time_txt) TextView startTimeTxt;
    @Bind(R.id.tv_start_location) TextView tvStartLocation;
    @Bind(R.id.drop_time_txt) TextView dropTimeTxt;
    @Bind(R.id.tv_end_location) TextView tvEndLocation;
    @Bind(R.id.total_distance_txt) TextView totalDistanceTxt;
    @Bind(R.id.total_fare_txt) TextView totalFareTxt;
    @Bind(R.id.ride_time_charges_txt) TextView rideTimeChargesTxt;
    @Bind(R.id.wating_charge_txt) TextView watingChargeTxt;
    @Bind(R.id.coupon_code_txt) TextView couponCodeTxt;
    @Bind(R.id.coupon_value_txt) TextView couponValueTxt;
    @Bind(R.id.total_gross_bill_txt) TextView totalGrossBillTxt;
    @Bind(R.id.payment_mode_txt) TextView paymentModeTxt;
    @Bind(R.id.payment_amount_done_txt) TextView paymentAmountDoneTxt;
    @Bind(R.id.tv_date_time1)
    TypefaceTextView tvDateTime1;
    @Bind(R.id.customer_name_txt) TextView customer_name_txt;
    @Bind(R.id.customer_phone_txt) TextView customer_phone_txt;
    @Bind(R.id.iv_image_driver) CircleImageView ivImageDriver;


    @Bind(R.id.night_time_charge_txt) TextView night_time_charge_txt;

    @Bind(R.id.peak__charge_txt) TextView peak__charge_txt;
    @Bind(R.id.rating_selected)
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiManager = new ApiManager(this);
        activity = this ;
        pd = new ProgressDialog(this);
        pd.setMessage("" + this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        sessionManager = new SessionManager(this);
        languageManager = new LanguageManager(this);
        setContentView(R.layout.activity_selected_rides);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        ride_id = super.getIntent().getExtras().getString("ride_id");
//        setViewVisibility(""+super.getIntent().getExtras().getString("ride_status"));


                String ride_status  = super.getIntent().getExtras().getString("ride_status") ;
        if(ride_status.equals(Config.Status.NORMAL_CANCEL_BY_USER) || ride_status.equals(Config.Status.VAL_4) || ride_status.equals(Config.Status.NORMAL_RIDE_END) || ride_status.equals(Config.Status.NORMAL_CANCEL_BY_DRIVER) || ride_status.equals(Config.Status.NORMAL_RIDE_CANCEl_BY_ADMIN)){

            llTrackRide.setVisibility(View.GONE);
        }


        if(ride_status.equals("1")){
            tvDateTime1.setText(R.string.SELECTED_RIDE_ACTIVITY__new_ride_allocated);
        } if(ride_status.equals("2")){
            tvDateTime1.setText(R.string.SELECTED_RIDE_ACTIVITY__user_cancelled_ride);
        } if(ride_status.equals("3")){
            tvDateTime1.setText(R.string.SELECTED_RIDE_ACTIVITY__accepted);
        } if(ride_status.equals("4")){
            tvDateTime1.setText(R.string.SELECTED_RIDE_ACTIVITY__alloting_to_other);
        } if(ride_status.equals("5")){
            tvDateTime1.setText(R.string.SELECTED_RIDE_ACTIVITY__arrived);
        } if(ride_status.equals("6")){
            tvDateTime1.setText(R.string.SELECTED_RIDE_ACTIVITY__ride_on_going);
        } if(ride_status.equals("7")){
            tvDateTime1.setText(R.string.SELECTED_RIDE_ACTIVITY__ride_completed);
        } if(ride_status.equals("8")){
            tvDateTime1.setText(R.string.SELECTED_RIDE_ACTIVITY__later_request);
        } if(ride_status.equals("9")){
            tvDateTime1.setText(R.string.SELECTED_RIDE_ACTIVITY__cancelled_by_you);
        }


        apiManager.execution_method_get(Config.ApiKeys.KEY_VIEW_RIDE_INFO_DRIVER , ""+ Apis.viewRideInfoDriver +"?ride_id="+ride_id+"&driver_token="+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken)+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));



        findViewById(R.id.ll_back_ride_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        findViewById(R.id.ll_back_ride_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.ll_track_ride).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectedRidesActivity.this, TrackRideActivity.class)
                        .putExtra("customer_name", "" + viewRideInfoDriver.getDetails().getUser_name())
                        .putExtra("customer_phone", "" + viewRideInfoDriver.getDetails().getUser_phone()));
                finish();
            }
        });
    }



    @Override
    public void onAPIRunningState(int a, String APINAME) {
        try{  if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
            pd.show();
        }
            if (a == ApiManager.APIFETCHER.KEY_API_IS_STOPPED) {
                pd.dismiss();
            }}catch (Exception e){}

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            if (APINAME.equals(""+Config.ApiKeys.KEY_VIEW_RIDE_INFO_DRIVER)) {

                viewRideInfoDriver = gson.fromJson(""+script, RideDetailsModel.class);

                if (viewRideInfoDriver.getResult() == 1 ) {
                    if(viewRideInfoDriver.getDetails().getDistance().equals("")){
                        bill_layout.setVisibility(View.GONE);
                        llBill.setVisibility(View.GONE
                        );
                    }
                    tvRupee.setText(""+sessionManager.getCurrencyCode()+viewRideInfoDriver.getDetails().getTotal_amount());
                    tvDis.setText(""+viewRideInfoDriver.getDetails().getDistance());
                    tvTime1.setText(""+viewRideInfoDriver.getDetails().getDone_ride_time());
                    startTimeTxt.setText(""+viewRideInfoDriver.getDetails().getBegin_time());
                    dropTimeTxt.setText(""+viewRideInfoDriver.getDetails().getEnd_time());
                    tvStartLocation.setText(""+viewRideInfoDriver.getDetails().getBegin_location());
                    tvEndLocation.setText(""+viewRideInfoDriver.getDetails().getEnd_location());
                    totalDistanceTxt.setText(""+viewRideInfoDriver.getDetails().getDistance());
                    totalFareTxt.setText(""+sessionManager.getCurrencyCode()+viewRideInfoDriver.getDetails().getAmount());
                    rideTimeChargesTxt.setText(""+sessionManager.getCurrencyCode()+viewRideInfoDriver.getDetails().getRide_time_price());
                    watingChargeTxt.setText(""+sessionManager.getCurrencyCode()+viewRideInfoDriver.getDetails().getWaiting_price());
                    totalGrossBillTxt.setText(""+sessionManager.getCurrencyCode()+viewRideInfoDriver.getDetails().getTotal_amount());
                    paymentModeTxt.setText(""+viewRideInfoDriver.getDetails().getPayment_option_name());
                    paymentAmountDoneTxt.setText(""+sessionManager.getCurrencyCode()+viewRideInfoDriver.getDetails().getTotal_amount());
                    customer_name_txt.setText(""+viewRideInfoDriver.getDetails().getUser_name());
                    customer_phone_txt.setText(""+viewRideInfoDriver.getDetails().getUser_phone());
                    night_time_charge_txt.setText(""+sessionManager.getCurrencyCode()+viewRideInfoDriver.getDetails().getNight_time_charge());
                    peak__charge_txt.setText(""+sessionManager.getCurrencyCode()+viewRideInfoDriver.getDetails().getPeak_time_charge());
                    Glide.with(SelectedRidesActivity.this).load(""+viewRideInfoDriver.getDetails().getUser_image()).into(ivImageDriver);

                    try{ratingBar.setRating(Float.parseFloat(""+viewRideInfoDriver.getDetails().getRating_user()));}catch (Exception e){}
                    if(viewRideInfoDriver.getDetails().getCoupons_price().equals("")){
                        couponCodeTxt.setVisibility(View.GONE);
                        couponValueTxt.setVisibility(View.GONE);
                    }else{
                        couponCodeTxt.setVisibility(View.VISIBLE);
                        couponValueTxt.setVisibility(View.VISIBLE);
                        couponCodeTxt.setText(SelectedRidesActivity.this.getResources().getString(R.string.SELECTED_RIDE_ACTIVITY__coupon_applied)+" ("+viewRideInfoDriver.getDetails().getCoupon_code()+")");
                        couponValueTxt.setText("-"+sessionManager.getCurrencyCode()+viewRideInfoDriver.getDetails().getCoupons_price());
                    }
                } else if (viewRideInfoDriver.getResult() == 419) {
                    sessionManager.logoutUser();
                    Intent intent = new Intent(this, SplashActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                    finish();
                    Logger.e("lat long update       " + viewRideInfoDriver.getMsg());
                } else {
                    Toast.makeText(SelectedRidesActivity.this, "" + this.getResources().getString(R.string.ride_selected_no_data_found), Toast.LENGTH_SHORT).show();
                }
            }}catch (Exception e){}

    }

    @Override
    public void onFetchResultZero(String script) {

    }




}
