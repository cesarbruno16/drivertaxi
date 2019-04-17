package com.ride.taxiDriver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ride.taxiDriver.models.restmodels.NewRidehistoryModel;
import com.ride.taxiDriver.models.restmodels.ResultStatusChecker;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.trackride.TrackRideActivity;
import com.ride.taxiDriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sam.placer.PlaceHolderView;
import com.sam.placer.annotations.Click;
import com.sam.placer.annotations.Layout;
import com.sam.placer.annotations.Resolve;

import java.util.HashMap;


public class RidesActivity extends AppCompatActivity implements  ApiManager.APIFETCHER {

    LinearLayout ll_back_rides ;
    public static Activity ridesActivity;
    SessionManager sessionManager;
    ApiManager apiManager ;
    ProgressBar progressBar ;
    PlaceHolderView place_holder ;

    public  static Activity activity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this ;
        apiManager = new ApiManager(this);
        sessionManager = new SessionManager(this);
        ridesActivity = this;
        setContentView(R.layout.activity_rides);
        getSupportActionBar().hide();
        ll_back_rides = (LinearLayout) findViewById(R.id.ll_back_rides);
        progressBar = (ProgressBar) findViewById(R.id.progress_wheel);
        place_holder = (PlaceHolderView) findViewById(R.id.place_holder);

        try{RentalTrackRideActivity.activity.finish();}catch (Exception e){}
        try{TrackRideActivity.activity.finish();}catch (Exception e){}

        ll_back_rides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        HashMap<String  , String > data = new HashMap<>();
        data.put("driver_id" , ""+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
        apiManager.execution_method_post("" +Config.ApiKeys.KEY_REST_RIDE_HISTORY, ""+ Apis.RideHistory , data);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void onAPIRunningState(int a, String APINAME) {
        if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
            place_holder.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
        if (a == ApiManager.APIFETCHER.KEY_API_IS_STOPPED) {
            place_holder.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{ GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            ResultStatusChecker rs = gson.fromJson("" +script, ResultStatusChecker.class);
            if (rs.getStatus() == 1){
                NewRidehistoryModel rideshistory_response = gson.fromJson(""+script ,NewRidehistoryModel.class);

                for(int i = 0 ; i <rideshistory_response.getDetails().size() ; i++ ){

                    if(rideshistory_response.getDetails().get(i).getRide_mode().equals("1")){   // ride type normal
                        place_holder.addView(new HolderRideHistoryNormal(this , rideshistory_response.getDetails().get(i).getNormal_Ride()));
                    }else if (rideshistory_response.getDetails().get(i).getRide_mode().equals("2")){   // ride type Rentals
                        place_holder.addView(new HolderRentalRideHistory(this , rideshistory_response.getDetails().get(i).getRental_Ride()));
                    }
                }
            }} catch (Exception e){}




    }


    @Override
    public void onFetchResultZero(String script) {

    }







    @Layout(R.layout.rides_item_new)
    public class HolderRideHistoryNormal{
        @com.sam.placer.annotations.View(R.id.tv_date_time) private TextView tv_date_time;
        @com.sam.placer.annotations.View(R.id.tv_pickup_addresss) private TextView tv_pickup_addresss;
        @com.sam.placer.annotations.View(R.id.tv_drop_addresss) private TextView tv_drop_addresss;
        @com.sam.placer.annotations.View(R.id.customer_name_txt) private TextView customer_name_txt;
        @com.sam.placer.annotations.View(R.id.customer_phone_txt) private TextView customer_phone_txt;
        @com.sam.placer.annotations.View(R.id.tv_status) private TextView tv_status;

        NewRidehistoryModel.DetailsBean.NormalRideBean mNormalRideResponse  ;

        public HolderRideHistoryNormal(Context context , NewRidehistoryModel.DetailsBean.NormalRideBean normalRideResponse  ){
            mNormalRideResponse = normalRideResponse ;
        }

        @Resolve
        private void onResolved() {
            tv_date_time.setText(""+mNormalRideResponse.getRide_date());
            tv_pickup_addresss.setText(""+mNormalRideResponse.getPickup_location());
            tv_drop_addresss.setText(""+mNormalRideResponse.getDrop_location());
            customer_name_txt.setText(""+mNormalRideResponse.getUser_name());
            customer_phone_txt.setText(""+mNormalRideResponse.getUser_phone());
            tv_status.setText(""+Config.getStatustext(""+mNormalRideResponse.getRide_status()));
        }

        @Click(R.id.root)
        private void  onclick (){
                            String date_time = mNormalRideResponse.getRide_date() + ", " + mNormalRideResponse.getRide_time();
                startActivity(new Intent(RidesActivity.this, SelectedRidesActivity.class).putExtra("ride_id", ""+mNormalRideResponse.getRide_id())
                        .putExtra("ride_status", ""+mNormalRideResponse.getRide_status())
                        .putExtra("date_time", date_time)
                        .putExtra("ride_type", ""+mNormalRideResponse.getRide_type())  // that is later or ride now type
                        .putExtra("ride_mode" , "1")
                        .putExtra("ride_id" , ""+mNormalRideResponse.getRide_id()));
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }


    @Layout(R.layout.ride_history_rental_item)
    public class HolderRentalRideHistory   {

        @com.sam.placer.annotations.View(R.id.tv_date_time) private TextView tv_date_time;
        @com.sam.placer.annotations.View(R.id.tv_pickup_addresss) private TextView tv_pickup_addresss;
        @com.sam.placer.annotations.View(R.id.tv_drop_addresss) private TextView tv_drop_addresss;
        @com.sam.placer.annotations.View(R.id.customer_name_txt) private TextView customer_name_txt;
        @com.sam.placer.annotations.View(R.id.customer_phone_txt) private TextView customer_phone_txt;
        @com.sam.placer.annotations.View(R.id.tv_status) private TextView tv_status;
        @com.sam.placer.annotations.View(R.id.drop_locatipon_layout) private LinearLayout drop_locatipon_layout ;


        NewRidehistoryModel.DetailsBean.RentalRideBean mRentalRideResponse ;


             private Context mContext;

             public HolderRentalRideHistory(Context context, NewRidehistoryModel.DetailsBean.RentalRideBean rental_ride) {
                 mContext = context;
                 mRentalRideResponse = rental_ride ;
             }

             @Resolve
             private void onResolved() {
                 tv_date_time.setText(""+mRentalRideResponse.getBooking_date());
                 tv_pickup_addresss.setText(""+mRentalRideResponse.getPickup_location());
                 tv_drop_addresss.setText(""+mRentalRideResponse.getEnd_location());
                 customer_name_txt.setText(""+mRentalRideResponse.getUser_name());
                 customer_phone_txt.setText(""+mRentalRideResponse.getUser_phone());
                 tv_status.setText(""+Config.getStatustext(""+mRentalRideResponse.getBooking_status()));

                 if(mRentalRideResponse.getEnd_location().equals("")){
                     drop_locatipon_layout.setVisibility(View.GONE);
                 }
             }


        @Click(R.id.root)
        private void  onclick (){
            String date_time = mRentalRideResponse.getBooking_date() + ", " + mRentalRideResponse.getBooking_time();
            startActivity(new Intent(RidesActivity.this, SelectedRentalRideActivity.class)
                    .putExtra("ride_id", ""+mRentalRideResponse.getRental_booking_id())
                    .putExtra("ride_status", ""+mRentalRideResponse.getBooking_status())
                    .putExtra("date_time", date_time)
                    .putExtra("ride_mode", "2"));
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }

    }



}
