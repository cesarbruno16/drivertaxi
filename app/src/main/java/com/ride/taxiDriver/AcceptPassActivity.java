package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.RideSession;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.ModelCheckTime;
import com.ride.taxiDriver.models.ModelPartialRequestResponse;
import com.ride.taxiDriver.models.restmodels.NewRideAcceptmodel;
import com.ride.taxiDriver.models.rideaccept.RideAccept;
import com.ride.taxiDriver.others.FirebaseUtils;
import com.ride.taxiDriver.others.RideSessionEvent;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.trackride.TrackRideActivity;
import com.ride.taxiDriver.urls.Apis;
import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import customviews.typefacesviews.TypefaceDosisRegular;
import de.hdodenhof.circleimageview.CircleImageView;

public class AcceptPassActivity extends Activity implements ApiManager.APIFETCHER {


    private final String TAG = "AcceptPassActivity";


    String RIDE_ID, RIDE_STATUS, RIDE_TYPE;
    ApiManager apiManager;
    SessionManager sessionManager;
    FirebaseUtils firebaseUtils ;
    Gson gson;
    @Bind(R.id.back)
    RelativeLayout back;
    @Bind(R.id.activity_name_txt)
    TypefaceDosisRegular activityNameTxt;
    @Bind(R.id.user_image)
    CircleImageView userImage;
    @Bind(R.id.customer_name_txt)
    TextView customerNameTxt;
    @Bind(R.id.customer_phone_txt)
    TextView customerPhoneTxt;
    @Bind(R.id.rating_selected)
    RatingBar ratingSelected;
    @Bind(R.id.ll_driver_ki_detail)
    LinearLayout llDriverKiDetail;
    @Bind(R.id.tv_start_location)
    TextView tvStartLocation;
    @Bind(R.id.tv_end_location)
    TextView tvEndLocation;
    @Bind(R.id.ll_location_module)
    LinearLayout llLocationModule;
    @Bind(R.id.booking_date_txt)
    TextView booking_date_txt;
    @Bind(R.id.requested_date_txt)
    TextView requested_date_txt;


    public static Activity activity ;


    @Bind(R.id.accept_ride_btn)
    LinearLayout acceptRideBtn;
    @Bind(R.id.request_type)
    TextView requestType;
    @Bind(R.id.loading_text)
    TextView loadingText;


    ProgressDialog progressDialog ;
    ModelCheckTime modelCheckTime ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_pass);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(""+this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        activity = this ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        activityNameTxt.setText(R.string.your_trips);
        apiManager = new ApiManager(this);
        firebaseUtils = new FirebaseUtils(this);
        sessionManager = new SessionManager(this);
        gson = new GsonBuilder().create();

        RIDE_ID = "" + getIntent().getExtras().getString("ride_id");
        RIDE_STATUS = "" + getIntent().getExtras().getString("ride_status");
        RIDE_TYPE = "" + getIntent().getExtras().getString("ride_type");
        customerNameTxt.setText("" + getIntent().getExtras().getString("user_name"));
        customerPhoneTxt.setText("" + getIntent().getExtras().getString("user_phone"));
        tvStartLocation.setText("" + getIntent().getExtras().getString("pick_up_location"));
        tvEndLocation.setText("" + getIntent().getExtras().getString("drop_off_location"));
        requested_date_txt.setText("" + getIntent().getExtras().getString("later_date") + " " + getIntent().getExtras().getString("later_time"));
        booking_date_txt.setText("" + getIntent().getExtras().getString("booking_date") + " " + getIntent().getExtras().getString("booking_time"));
        if (RIDE_TYPE.equals("1")) {
            requestType.setText(R.string.normal_type);
        } else if (RIDE_TYPE.equals("2")) {
            requestType.setText(R.string.rental_request);
        }
        try {

            Glide.with(this).load("" +getIntent().getExtras().getString("user_image")).into(userImage);
        } catch (Exception e) {
            ApporioLog.logE("" + TAG, "Exception caught while loading user image ==>" + e.getMessage());
        }
        try {
            ratingSelected.setRating(Float.parseFloat("" + getIntent().getExtras().getString("user_rating")));
        } catch (Exception e) {
        }


        acceptRideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RIDE_STATUS.equals(""+Config.Status.PARTIAL_ACCEPTED)){
                    if(RIDE_TYPE.equals("1") && modelCheckTime.getResult() == 1 ){  // NORMAL
                        // call normal accept API
                        apiManager.execution_method_get(Config.ApiKeys.KEY_ACEPT_RIDE, Apis.acceptRide + "?ride_id=" + RIDE_ID + "&driver_id=" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID) + "&driver_token=" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken) + "&language_id=" +new LanguageManager(AcceptPassActivity.this).getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                    }else if(RIDE_TYPE.equals("2") && modelCheckTime.getResult() == 1){  // RENTAL
                        // call rental accept API
                        HashMap<String, String> data = new HashMap<String, String>();
                        data.put("rental_booking_id", "" +RIDE_ID);
                        data.put("driver_id", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                        data.put("driver_token", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken));
                        apiManager.execution_method_post(Config.ApiKeys.KEY_RESt_ACCEPT_API, "" + Apis.AcceptRide, data);
                    }
                }else{
                    HashMap<String, String> data = new HashMap<>();
                    data.put("ride_id", "" + RIDE_ID);
                    data.put("ride_mode", "" + RIDE_TYPE);
                    data.put("driver_id", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                    data.put("driver_token", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken));
                    apiManager.execution_method_post("" + Config.ApiKeys.PARTIAL_ACCEPT, "" + Apis.PartialAccept, data);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(RIDE_STATUS.equals("22")){
            apiManager.execution_method_get("" + Config.ApiKeys.CHECK_RIDE_TIME, "" + Apis.CheckRideTime + RIDE_ID + "&ride_mode=" + RIDE_TYPE);
        }else{
            loadingText.setText(AcceptPassActivity.this.getResources().getString(R.string.accept));
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

        if(a == ApiManager.APIFETCHER.KEY_API_IS_STARTED){
            progressDialog.show();
        }else if (progressDialog.isShowing()){
            try{progressDialog.dismiss();} catch ( Exception e){}
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{
            switch (APINAME){
                case Config.ApiKeys.CHECK_RIDE_TIME:
                     modelCheckTime = gson.fromJson("" +script, ModelCheckTime.class);
                    if(modelCheckTime.getResult() == 0 ){
                        loadingText.setText(""+modelCheckTime.getMsg());
                        loadingText.setTypeface(null , Typeface.NORMAL);
                        loadingText.setTextColor(Color.parseColor("#ffffff"));
                        acceptRideBtn.setBackgroundColor(Color.parseColor("#3498db"));
                    }else if (modelCheckTime.getResult() == 1){
                        loadingText.setText(AcceptPassActivity.this.getResources().getString(R.string.start_for_customer_pickup));
                    }
                    break;
                case Config.ApiKeys.PARTIAL_ACCEPT:
                    ModelPartialRequestResponse modelPartialRequestResponse = gson.fromJson("" + script, ModelPartialRequestResponse.class);
                    Toast.makeText(this, "" + modelPartialRequestResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case Config.ApiKeys.KEY_ACEPT_RIDE:
                    ReceivePassengerActivity.AcceptCheck ac = gson.fromJson(""+script , ReceivePassengerActivity.AcceptCheck.class);
                    if(ac.getResult() == 1){
                        RideAccept rideAccept = gson.fromJson("" + script, RideAccept.class);
                        if (rideAccept.getResult() == 1) {
                            new RideSession(this).setRideSesion(rideAccept.getDetails().getRide_id(),rideAccept.getDetails().getUser_id(),rideAccept.getDetails().getUser_name(),rideAccept.getDetails().getUser_phone(),rideAccept.getDetails().getCoupon_code(),rideAccept.getDetails().getPickup_lat(),rideAccept.getDetails().getPickup_long(),rideAccept.getDetails().getPickup_location(),rideAccept.getDetails().getDrop_lat(),rideAccept.getDetails().getDrop_long(),rideAccept.getDetails().getDrop_location(),rideAccept.getDetails().getRide_date(),rideAccept.getDetails().getRide_time(),rideAccept.getDetails().getLater_date(),rideAccept.getDetails().getLater_time(),rideAccept.getDetails().getDriver_id(),rideAccept.getDetails().getRide_type(),rideAccept.getDetails().getRide_status(),rideAccept.getDetails().getStatus());
                            FirebaseDatabase.getInstance().getReference(""+Config.RideTableReference).child(""+rideAccept.getDetails().getRide_id()).setValue(new RideSessionEvent(""+rideAccept.getDetails().getRide_id() , ""+Config.Status.NORMAL_ACCEPTED, "Not yet generated" , "0"));
                            startActivity(new Intent(this, TrackRideActivity.class)
                                    .putExtra("customer_name", "" + rideAccept.getDetails().getUser_name())
                                    .putExtra("customer_phone", "" + rideAccept.getDetails().getUser_phone()));
                            firebaseUtils.createRidePool(""+ FirebaseUtils.NO_RIDES , ""+FirebaseUtils.NO_RIDE_STATUS);
                            finish();
                        }
                    }else{
                        Toast.makeText(this, R.string.your_ride_is_expired, Toast.LENGTH_SHORT).show();
                    }
                    break ;
                case Config.ApiKeys.KEY_RESt_ACCEPT_API:
                    ReceiveRentalPassengerActivity.AcceptCheck ac_check = gson.fromJson(""+script , ReceiveRentalPassengerActivity.AcceptCheck.class);
                    if(ac_check.getStatus() == 1){
                        NewRideAcceptmodel accept_response = gson.fromJson(""+script , NewRideAcceptmodel.class);
                        new RideSession(this).setRentalRideSession(accept_response.getDetails().getRental_booking_id(),accept_response.getDetails().getUser_id(),accept_response.getDetails().getUser_name(),accept_response.getDetails().getUser_phone(),accept_response.getDetails().getReferral_code(),accept_response.getDetails().getPickup_lat(),accept_response.getDetails().getPickup_long(),accept_response.getDetails().getPickup_location(),"" , "" , "",accept_response.getDetails().getBooking_date(),"ride_time",accept_response.getDetails().getBooking_date(),accept_response.getDetails().getBooking_time(),accept_response.getDetails().getDriver_id(),accept_response.getDetails().getBooking_type(),""+Config.Status.RENTAL_ACCEPTED,accept_response.getDetails().getStatus());
                        finish();
                        startActivity(new Intent(AcceptPassActivity.this , RentalTrackRideActivity.class));
                        Toast.makeText(this, ""+accept_response.getMessage(), Toast.LENGTH_SHORT).show();
                        FirebaseDatabase.getInstance().getReference(""+Config.RideTableReference).child(""+accept_response.getDetails().getRental_booking_id()).setValue(new RideSessionEvent(""+accept_response.getDetails().getRental_booking_id() , ""+Config.Status.RENTAL_ACCEPTED , "Not yet generated" , "0"));

                    }else{
                        finish();
                        Toast.makeText(this, ""+ac_check.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }catch (Exception e){
            ApporioLog.logE("" + TAG, "Exception caught while parsing ==>" + e.getMessage());
        }
    }

    @Override
    public void onFetchResultZero(String script) {

    }
}
