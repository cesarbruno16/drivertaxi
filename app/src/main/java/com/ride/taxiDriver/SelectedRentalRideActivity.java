package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.restmodels.NewRentalRideDeatilsModel;
import com.ride.taxiDriver.models.restmodels.ResultStatusChecker;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.typeface.TypefaceTextView;
import com.ride.taxiDriver.urls.Apis;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SelectedRentalRideActivity extends Activity implements ApiManager.APIFETCHER {

    LanguageManager languageManager;
    SessionManager sessionManager;
    ApiManager apiManager;
    ProgressDialog pd;
    String RIDE_ID, RIDE_STATUS, RIDE_MODE;
    NewRentalRideDeatilsModel response;

    Gson gson;
    public static Activity activity;

    @Bind(R.id.ll_back_ride_select)
    LinearLayout llBackRideSelect;
    @Bind(R.id.tv_date_time1)
    TypefaceTextView tvDateTime1;
    @Bind(R.id.iv_image_driver)
    CircleImageView ivImageDriver;
    @Bind(R.id.customer_name_txt)
    TextView customerNameTxt;
    @Bind(R.id.customer_phone_txt)
    TextView customerPhoneTxt;
    @Bind(R.id.rating_selected)
    RatingBar ratingSelected;
    @Bind(R.id.ll_driver_ki_detail)
    LinearLayout llDriverKiDetail;
    @Bind(R.id.tv_car_ima)
    CircleImageView tvCarIma;
    @Bind(R.id.tv_rupee)
    TextView tvRupee;
    @Bind(R.id.tv_dis)
    TextView tvDis;
    @Bind(R.id.tv_time1)
    TextView tvTime1;
    @Bind(R.id.ll_bill)
    LinearLayout llBill;
    @Bind(R.id.start_time_txt)
    TextView startTimeTxt;
    @Bind(R.id.tv_start_location)
    TextView tvStartLocation;
    @Bind(R.id.drop_time_txt)
    TextView dropTimeTxt;
    @Bind(R.id.tv_end_location)
    TextView tvEndLocation;
    @Bind(R.id.ll_location_module)
    LinearLayout llLocationModule;
    @Bind(R.id.tv_ride_distance)
    TextView tvRideDistance;
    @Bind(R.id.textView6)
    TextView textView6;
    @Bind(R.id.total_hours_txt)
    TextView totalHoursTxt;
    @Bind(R.id.base_package_txt)
    TextView basePackageTxt;
    @Bind(R.id.base_package_price)
    TextView basePackagePrice;
    @Bind(R.id.extra_distance_txt)
    TextView extraDistanceTxt;
    @Bind(R.id.extra_distance_price_txt)
    TextView extraDistancePriceTxt;
    @Bind(R.id.extra_time_txt)
    TextView extraTimeTxt;
    @Bind(R.id.extra_time_price_txt)
    TextView extraTimePriceTxt;
    @Bind(R.id.total_price_txt)
    TextView totalPriceTxt;
    @Bind(R.id.coupon_txt)
    TextView couponTxt;
    @Bind(R.id.coupon_price_txt)
    TextView couponPriceTxt;
    @Bind(R.id.total_payble_bottom)
    TextView totalPaybleBottom;
    @Bind(R.id.ll_track_ride)
    LinearLayout llTrackRide;
    @Bind(R.id.activity_selected_rides)
    LinearLayout activitySelectedRides;
    @Bind(R.id.bill_layout)
    LinearLayout bill_layout;
    @Bind(R.id.drop_layout)
    LinearLayout drop_layout;
    @Bind(R.id.night_time_txt)
    TextView nightTimeTxt;
    @Bind(R.id.night_time_price_txt)
    TextView nightTimePriceTxt;
    @Bind(R.id.peak_time_txt)
    TextView peakTimeTxt;
    @Bind(R.id.peak_time_price_txt)
    TextView peakTimePriceTxt;
    @Bind(R.id.payMode)
    TextView payMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        gson = new GsonBuilder().create();
        apiManager = new ApiManager(this);
        pd = new ProgressDialog(this);
        pd.setMessage("" + this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        sessionManager = new SessionManager(this);
        languageManager = new LanguageManager(this);

        setContentView(R.layout.activity_selected_rental_ride);
        ButterKnife.bind(this);
        RIDE_ID = super.getIntent().getExtras().getString("ride_id");
        RIDE_STATUS = super.getIntent().getExtras().getString("ride_status");
        RIDE_MODE = super.getIntent().getExtras().getString("ride_mode");


        HashMap<String, String> data = new HashMap<>();
        data.put("ride_mode", "" + RIDE_MODE);
        data.put("booking_id", "" + RIDE_ID);
        apiManager.execution_method_post("" + Config.ApiKeys.KEY_REST_RIDE_DETAILS, "" + Apis.RideDetails, data);


        llTrackRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectedRentalRideActivity.this, RentalTrackRideActivity.class));
            }
        });


        llBackRideSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

        if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
            pd.show();
        } else if (pd.isShowing()) {
            pd.dismiss();
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{ResultStatusChecker rs = gson.fromJson("" + script, ResultStatusChecker.class);
            if (rs.getStatus() == 1) {
                response = gson.fromJson("" + script, NewRentalRideDeatilsModel.class);
                setView();
            } else if (rs.getStatus() == 0) {
                Toast.makeText(this, "" + rs.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.SELECTED_RENTAL_RIDE_ACTIVITY__something_went_wrong_in_api, Toast.LENGTH_SHORT).show();
            }} catch (Exception e){}
    }

    @Override
    public void onFetchResultZero(String script) {

    }


    private void setView() {
        if (response.getDetails().getFinal_bill_amount().equals("0")) {
            bill_layout.setVisibility(View.GONE);
            llBill.setVisibility(View.GONE
            );
        }

        if (response.getDetails().getEnd_location().equals("")) {
            drop_layout.setVisibility(View.GONE);
        }
        RIDE_STATUS = ""+response.getDetails().getBooking_status();
        tvRupee.setText(""+sessionManager.getCurrencyCode() + response.getDetails().getFinal_bill_amount());
        tvDis.setText("" + response.getDetails().getTotal_distance_travel());
        tvTime1.setText("" + response.getDetails().getTotal_time_travel());
        startTimeTxt.setText("" + response.getDetails().getBegin_time());
        dropTimeTxt.setText("" + response.getDetails().getEnd_time());
        tvStartLocation.setText("" + response.getDetails().getPickup_location());
        tvEndLocation.setText("" + response.getDetails().getEnd_location());

        Glide.with(SelectedRentalRideActivity.this).load(""+response.getDetails().getUser_image()).into(ivImageDriver);

        tvRideDistance.setText("" + response.getDetails().getTotal_distance_travel());
        totalHoursTxt.setText("" + response.getDetails().getTotal_time_travel());
        basePackageTxt.setText(getString(R.string.SELECTED_RENTAL_RIDE_ACTIVITY__base_package_txt_package) + response.getDetails().getRental_package_distance() + " " + getString(R.string.SELECTED_RENTAL_RIDE_ACTIVITY__for) + " " + response.getDetails().getRental_package_hours() + getString(R.string.SELECTED_RENTAL_RIDE_ACTIVITY__hours));
        basePackagePrice.setText("" +sessionManager.getCurrencyCode()+ response.getDetails().getRental_package_price());
        extraDistanceTxt.setText(getString(R.string.SELECTED_RENTAL_RIDE_ACTIVITY__extra_distance_travel) + response.getDetails().getExtra_distance_travel() + this.getResources().getString(R.string.distance_symbol) + " )");
        extraDistancePriceTxt.setText(""+sessionManager.getCurrencyCode() + response.getDetails().getExtra_distance_travel_charge());
        extraTimePriceTxt.setText(getString(R.string.SELECTED_RENTAL_RIDE_ACTIVITY__extra_time) + response.getDetails().getExtra_hours_travel() + ")");
        extraTimePriceTxt.setText(""+sessionManager.getCurrencyCode() + response.getDetails().getExtra_hours_travel_charge());
        totalPriceTxt.setText(""+sessionManager.getCurrencyCode() + response.getDetails().getFinal_bill_amount());  /// need to be changes later
        totalPaybleBottom.setText("" +sessionManager.getCurrencyCode()+ response.getDetails().getFinal_bill_amount());
        try{ratingSelected.setRating(Float.parseFloat(""+response.getDetails().getDriver_rating()));}catch (Exception e){}

        if (response.getDetails().getPayment_option_id().equals("1")){
            payMode.setText("CASH");
        }else {
            payMode.setText("PAY WITH CARD");
        }

        customerNameTxt.setText("" + response.getDetails().getUser_name());
        customerPhoneTxt.setText("" + response.getDetails().getUser_phone());

        if (RIDE_STATUS.equals("" + Config.Status.RENTAL_RIDE_END) || RIDE_STATUS.equals(""+Config.Status.RENTAL_RIDE_CANCEl_BY_ADMIN ) || RIDE_STATUS.equals(""+Config.Status.RENTAL_RIDE_CANCELLED_BY_DRIVER ) || RIDE_STATUS.equals(""+Config.Status.RENTAL_RIDE_CANCEL_BY_USER ) || RIDE_STATUS.equals(""+Config.Status.NORMAL_RIDE_CANCEl_BY_ADMIN) || RIDE_STATUS.equals(""+Config.Status.RENTAL_RIDE_CANCEl_BY_ADMIN )) {
            llTrackRide.setVisibility(View.GONE);
        } else {
            llTrackRide.setVisibility(View.VISIBLE);
        }
    }
}
