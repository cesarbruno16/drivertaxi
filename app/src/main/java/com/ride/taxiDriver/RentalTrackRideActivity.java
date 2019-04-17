package com.ride.taxiDriver;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.adapter.ReasonAdapter;
import com.ride.taxiDriver.fcmclasses.MyFirebaseMessagingService;
import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.RideSession;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.cancelreasoncustomer.CancelReasonCustomer;
import com.ride.taxiDriver.models.newdriveraccount.ResultStatusChecker;
import com.ride.taxiDriver.models.restmodels.NewEndRideModel;
import com.ride.taxiDriver.models.restmodels.NewRideArrivedModel;
import com.ride.taxiDriver.models.restmodels.NewbeginTripModel;
import com.ride.taxiDriver.others.ChatModel;
import com.ride.taxiDriver.others.Constants;
import com.ride.taxiDriver.others.RideSessionEvent;
import com.ride.taxiDriver.routedrawer.DrawRouteMaps;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.util.HashMap;
import java.util.List;

public class RentalTrackRideActivity extends AppCompatActivity implements OnMapReadyCallback, ApiManager.APIFETCHER {


    private static final String TAG = "RentalTrackRideActivity";
    GoogleMap mGooglemap;
    LocationSession locationSession;
    LanguageManager languageManager ;
    SessionManager sessionManager ;
    TextView customer_info_txt, pick_location_txt, trip_status_txt, your_location_txt  ,customer_phone_txt  , cancel_btn ;
    LinearLayout root  ;
    ApiManager apiManager ;
    ProgressDialog progressDialog;
    public static Activity activity ;
    RideSession rideSession ;

    final Handler mHandeler = new Handler();
    Runnable mRunnable ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiManager = new ApiManager(this);
        super.onCreate(savedInstanceState);
        locationSession = new LocationSession(this);
        sessionManager = new SessionManager(this);
        languageManager = new LanguageManager(this);
        progressDialog = new ProgressDialog(this);
        rideSession = new RideSession(this);
        activity = this;
        progressDialog.setMessage(""+this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        setContentView(R.layout.activity_track_ride_rental);

        customer_info_txt = (TextView) findViewById(R.id.customer_info_txt);
        pick_location_txt = (TextView) findViewById(R.id.pick_location_txt);
        trip_status_txt = (TextView) findViewById(R.id.trip_status_txt);
        your_location_txt = (TextView) findViewById(R.id.your_location_txt);
        customer_phone_txt = (TextView) findViewById(R.id.customer_phone_txt);
        cancel_btn = (TextView) findViewById(R.id.cancel_btn);
        root = (LinearLayout) findViewById(R.id.root);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getSupportActionBar().hide();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

                if(!Constants.is_main_activity_open){
                    startActivity(new Intent(RentalTrackRideActivity.this , SplashActivity.class));
                }
            }
        });


        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String , String > data = new HashMap<String, String>();
                data .put("rental_booking_id" , ""+rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID));
                data .put("driver_id" , ""+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                apiManager.execution_method_post(""+Config.ApiKeys.KEY_REST_CANCEl_RIDE , ""+ Apis.RideCancel , data );
            }
        });
        findViewById(R.id.call_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+rideSession.getCurrentRideDetails().get(RideSession.USER_PHONE)));
                if (ActivityCompat.checkSelfPermission(RentalTrackRideActivity.this,
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

        trip_status_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("11")){  // run arrived API
                    HashMap<String , String >data = new HashMap<String, String>();
                    data.put("rental_booking_id" , ""+rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID));
                    data.put("driver_id" , ""+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                    data.put("driver_token" , ""+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken));
                    apiManager.execution_method_post(""+Config.ApiKeys.KEY_REST_RIDE_ARRIVED , ""+Apis.Arrived , data);
                }if (rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("12")){  // run begin trip API
                    startActivityForResult(new Intent(RentalTrackRideActivity.this , MeterImageActivity.class), 888);
                }if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("13")){
                    startActivityForResult(new Intent(RentalTrackRideActivity.this , MeterImageActivity.class), 888);
                }
            }
        });
    }


    private void setView() {
        customer_info_txt.setText("" + rideSession.getCurrentRideDetails().get(RideSession.USER_NAME));
        customer_phone_txt.setText("" + rideSession.getCurrentRideDetails().get(RideSession.USER_PHONE));
        pick_location_txt.setText("" + rideSession.getCurrentRideDetails().get(RideSession.PICK_LOCATION));
        setviewAccordingToStatus();
    }


    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        Constants.is_Rental_Track_Activity_is_open = true  ;
        if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals(Config.Status.RENTAL_RIDE_CANCEL_BY_USER)){showDialogForCancelation();}
        if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals(Config.Status.RENTAL_RIDE_CANCEl_BY_ADMIN)){showDialogForCancelationByAdmin();}
        if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals(Config.Status.RENTAL_RIDE_CANCELLED_BY_DRIVER)){showDialogForCancelation();}
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        mHandeler.removeCallbacks(mRunnable);
        Constants.is_Rental_Track_Activity_is_open = false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGooglemap = googleMap;
        mGooglemap.setMaxZoomPreference(18);
        try {
//            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.midnight_commander_theme));
        } catch (Resources.NotFoundException e) {
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mGooglemap.setMyLocationEnabled(true);
        if(!locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT).equals("")){
            your_location_txt.setText(""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT));
        }
        setView();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LocationEvent event){
        if(event.getFullAddress().equals("null")  || event.getFullAddress()==null){
            your_location_txt.setText(""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT));
        }
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyFirebaseMessagingService.RideEvent event){
        if(event.getRideStatus().equals(Config.Status.RENTAL_RIDE_CANCEL_BY_USER)){
            showDialogForCancelation();
        }if(event.getRideStatus().equals(Config.Status.RENTAL_RIDE_CANCEl_BY_ADMIN)){
            showDialogForCancelationByAdmin();
        }if(event.getRideStatus().equals(Config.Status.RENTAL_RIDE_CANCELLED_BY_DRIVER)){
            showDialogForCancelation();
        }
    }

    private void showDialogForCancelation() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_for_cancel_via_customer);
        dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finaliseAftercancelation();
            }
        });

        dialog.show();
    }

    private void showDialogForCancelationByAdmin() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_for_cancel_via_admin);
        dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finaliseAftercancelation();
            }
        });

        dialog.show();
    }




    public void setviewAccordingToStatus (){
        ////  set view when driver needs to reach over pick up point
        if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("11")){
            cancel_btn.setVisibility(View.VISIBLE);
            trip_status_txt.setText(""+this.getResources().getString(R.string.TRACK_RIDE_RENTAL_ACTIVITY__located));
            drawRoute(new LatLng(Double.parseDouble(""+rideSession.getCurrentRideDetails().get(RideSession.PICK_LATITUDE)) , Double.parseDouble(""+rideSession.getCurrentRideDetails().get(RideSession.PICK_LONGITUDE))) ,  new LatLng(Double.parseDouble(locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)) , Double.parseDouble(locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG))) ,mGooglemap ,R.drawable.ic_contact_green , R.drawable.ic_very_small );
        }if (rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("12")){
            cancel_btn.setVisibility(View.VISIBLE);
            trip_status_txt.setText(""+this.getResources().getString(R.string.TRACK_RIDE_RENTAL_ACTIVITY__begin));
            mGooglemap.clear();
            LatLng mlat = new LatLng(Double.parseDouble(""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)) , Double.parseDouble(""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG)));
            mGooglemap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(mlat).zoom(15).build()));
        }if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("13")){
            cancel_btn.setVisibility(View.GONE);
            trip_status_txt.setText(""+this.getResources().getString(R.string.TRACK_RIDE_RENTAL_ACTIVITY__end));
            CameraPosition position = mGooglemap.getCameraPosition() ;
            int mCameraTilt = (position.zoom < 15) ? 0 : 60;
            mGooglemap.animateCamera(CameraUpdateFactory.newCameraPosition(
                    new CameraPosition.Builder()
                            .target(position.target)
                            .tilt(mCameraTilt)
                            .zoom(16)
                            .build()));
        }
    }



    public void drawRoute (LatLng origin  , LatLng destination , GoogleMap mMap , int origin_icon  , int destination_icon ){
        mGooglemap.clear();
        DrawRouteMaps.getInstance(this , 6 , R.color.icons_8_muted_green_1).draw(origin, destination, mMap , sessionManager);
//        DrawMarker.getInstance(this).draw(mMap, origin, origin_icon, ""+rideSession.getCurrentRideDetails().get(RideSession.PICK_LOCATION));
//        DrawMarker.getInstance(this).draw(mMap, destination, destination_icon, ""+rideSession.getCurrentRideDetails().get(RideSession.DROP_LOCATION));

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(origin)
                .include(destination).build();
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 500, 60));
    }




    @Override
    public void onAPIRunningState(int a, String APINAME) {

        if(a == ApiManager.APIFETCHER.KEY_API_IS_STARTED){
            progressDialog.show();
        }else if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{ GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            ResultStatusChecker rs = gson.fromJson(""+script , ResultStatusChecker.class);
            if(rs.getStatus() == 1){
                switch (APINAME){
                    case Config.ApiKeys.KEY_REST_RIDE_ARRIVED :
                        rideSession.setRideStatus("12");
                        NewRideArrivedModel arrived_response = gson.fromJson(""+script , NewRideArrivedModel.class);
                        updateFirebaseEvent(Config.Status.RENTAL_ARRIVED, rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID));
                        setviewAccordingToStatus();
                        break;
                    case Config.ApiKeys.KEY_REST_START_RIDE :

                        NewbeginTripModel begin_response = gson.fromJson(""+script , NewbeginTripModel.class);
                        if(begin_response.getStatus() == 1 ){
                            rideSession.setRideStatus("13");
                            updateFirebaseEvent(Config.Status.RENTAl_RIDE_STARTED , rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID));
                            setviewAccordingToStatus();
                        }else {
                            Toast.makeText(this, ""+begin_response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case Config.ApiKeys.KEY_REST_END_RIDE:
                        NewEndRideModel end_ride_response = gson.fromJson(""+script , NewEndRideModel.class);
                        if(end_ride_response.getStatus() == 1){
                            updateFirebaseEventAtRideEnd(Config.Status.RENTAL_RIDE_END , end_ride_response.getDetails().getRental_booking_id() , rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID));
                            rideSession.setRideStatus("16");
                            rideSession.clearRideSession();
                            finish();
                            startActivity(new Intent(RentalTrackRideActivity.this , RentalPriceFareActiviy.class).putExtra("ride_id" , ""+end_ride_response.getDetails().getRental_booking_id()));
                        }else {
                            Toast.makeText(this, ""+end_ride_response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case Config.ApiKeys.KEY_REST_CANCEl_RIDE :
                        updateFirebaseEvent(Config.Status.RENTAL_RIDE_CANCELLED_BY_DRIVER  , rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID));
                        finaliseAftercancelation();
                        break ;
                }
            }else if (rs.getStatus() == 0 ) {
                Toast.makeText(this, ""+rs.getMessage(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, R.string.TRACK_RIDE_RENTAL_ACTIVITY__something_bad_happen_with_api, Toast.LENGTH_SHORT).show();
            }}catch (Exception e){}

    }

    @Override
    public void onFetchResultZero(String script) {

    }


    private void finaliseAftercancelation() {
        rideSession.clearRideSession();
        try{
            TripHistoryActivity.activity.finish();
        }catch (Exception e){

        }
        try{
            SelectedRentalRideActivity.activity.finish();
        }catch (Exception e ){

        }
        rideSession.setRideStatus("18");
        finish();
        startActivity(new Intent(RentalTrackRideActivity.this , TripHistoryActivity.class ).putExtra("tab_number" , "2"));
    }


    /////////////////// dialog
    public void showReasonDialog(final CancelReasonCustomer cancelReasonCustomer) {

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_for_cancel_reason);

        ListView lv_reasons = (ListView) dialog.findViewById(R.id.lv_reasons);
        lv_reasons.setAdapter(new ReasonAdapter(this, cancelReasonCustomer));

//        lv_reasons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                apiManager.execution_method_get(Config.ApiKeys.KEY_CANCEL_TRIP , Apis.cancelRide+"?ride_id="+rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID)+"&reason_id="+cancelReasonCustomer.getMsg().get(position).getReasonId());
//                dialog.dismiss();
//            }
//        });
        dialog.show();
    }



    @Override
    public void onBackPressed() {
        if(!Constants.is_main_activity_open){
            startActivity(new Intent(RentalTrackRideActivity.this , SplashActivity.class));
        }
        super.onBackPressed();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 888){
                if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("12")){
                    HashMap<String , String >data_hash = new HashMap<String, String>();
                    data_hash.put("rental_booking_id" , ""+rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID));
                    data_hash.put("driver_id" , ""+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                    data_hash.put("driver_token" , ""+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken));
                    data_hash.put("meter_reading" , ""+data.getExtras().getString("meter"));
                    data_hash.put("begin_lat" , ""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT));
                    data_hash.put("begin_long" , ""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG));
                    data_hash.put("begin_location" , ""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT));
                    apiManager.execution_method_multipart(""+Config.ApiKeys.KEY_REST_START_RIDE , ""+Apis.StartRide , data_hash , "meter_reading_image" , ""+data.getExtras().getString("image"));
                }if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("13")){
                    HashMap<String , String >data_hash = new HashMap<String, String>();
                    data_hash.put("rental_booking_id" , ""+rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID));
                    data_hash.put("driver_id" , ""+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                    data_hash.put("driver_token" , ""+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken));
                    data_hash.put("meter_reading" , ""+data.getExtras().getString("meter"));
                    data_hash.put("end_lat" , ""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT));
                    data_hash.put("end_long" , ""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG));
                    data_hash.put("end_location" , ""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT));
                    apiManager.execution_method_multipart(""+Config.ApiKeys.KEY_REST_END_RIDE , ""+Apis.EndRide , data_hash , "meter_reading_image" , ""+data.getExtras().getString("image"));

                }
            }
        }
    }





    private void updateFirebaseEvent(final String status_value , final String Ride_id ) throws  Exception{

        FirebaseDatabase.getInstance().getReference(Config.RideTableReference).child(""+Ride_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<ChatModel>> t = new GenericTypeIndicator<List<ChatModel>>() {};
                List<ChatModel> yourStringArray = dataSnapshot.child("Chat").getValue(t);
                try{FirebaseDatabase.getInstance().getReference(""+Config.RideTableReference).child(""+Ride_id).setValue(new RideSessionEvent(""+Ride_id , ""+status_value , "Not yet generated" , "0"));}catch (Exception e){}
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                ApporioLog.logI(""+TAG , "Data Fetched from firebase cancelled "+databaseError.getMessage());
            }
        });
    }


    private void updateFirebaseEventAtRideEnd(final String status_value , final String RideEnd_val  , final String ride_id) throws  Exception{

        FirebaseDatabase.getInstance().getReference(Config.RideTableReference).child(""+ride_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<ChatModel>> t = new GenericTypeIndicator<List<ChatModel>>() {};
                List<ChatModel> yourStringArray = dataSnapshot.child("Chat").getValue(t);
                try{FirebaseDatabase.getInstance().getReference(""+Config.RideTableReference).child(""+ride_id).setValue(new RideSessionEvent(""+ride_id , ""+status_value , ""+RideEnd_val , "0"));}catch (Exception e){}
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                ApporioLog.logI(""+TAG , "Data Fetched from firebase cancelled "+databaseError.getMessage());
            }
        });
    }



}
