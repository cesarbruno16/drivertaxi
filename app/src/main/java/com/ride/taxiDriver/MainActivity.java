package com.ride.taxiDriver;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.fcmclasses.MyFirebaseMessagingService;
import com.ride.taxiDriver.location.SamLocationRequestService;
import com.ride.taxiDriver.manager.DeviceManager;
import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.RideSession;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.ActiveRidesResponse;
import com.ride.taxiDriver.models.CallSupportResponse;
import com.ride.taxiDriver.models.ModelReportIssue;
import com.ride.taxiDriver.models.ModelScheduleAndunacceptedRide;
import com.ride.taxiDriver.models.ResultCheck;
import com.ride.taxiDriver.models.deviceid.DeviceId;
import com.ride.taxiDriver.models.restmodels.NewHeatmapModel;
import com.ride.taxiDriver.models.restmodels.NewUpdateLatLongModel;
import com.ride.taxiDriver.others.Constants;
import com.ride.taxiDriver.others.FirebaseUtils;
import com.ride.taxiDriver.others.Maputils;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.trackride.TrackRideActivity;
import com.ride.taxiDriver.urls.Apis;
import com.bumptech.glide.Glide;
import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.ride.taxiDriver.earnings.EarningActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import com.ride.taxiDriver.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import customviews.typefacesviews.TypefaceDosisRegular;

// started with the apporio taxi branch that id initiated with main apporio code
public class MainActivity extends BaseActivity implements Apis,
        OnMapReadyCallback , ApiManager.APIFETCHER {

    DatabaseReference mDatabaseReference;

    ApiManager apiManager_new ;
    ActiveRidesResponse activeRidesResponse;


    public static Activity mainActivity;
    TextView scheduled_rides , unaccepted_ride_txt ,  tv_address, tv_car_name, tv_name, tv_car_number, tv_profile_email, tv_profile_name  , lat_txt , long_txt , driver_id  , driver_city_txt , accuracy ;
    ImageView iv_profile_pic , status_image;
    SwitchCompat rental_switch ;
    TypefaceDosisRegular status_txt ;
    String driverId, language_id, ride_id, ride_status, driver_token, driverName,
            car_type_name, car_model_name, car_type_id, driverEmail,
            driverImage, callSupport, deviceId,  android_id;
    public static String carNumber;

    GoogleMap mGooglemap;
    ProgressBar progress_wheel  ;

    String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";

    SessionManager sessionManager;
    LocationSession app_location_manager;
    DeviceManager deviceManager;

    LanguageManager languageManager;

    RideSession rideSession ;
    FirebaseUtils firebaseutil;
    FirebaseDatabase database;
    ModelReportIssue modelReportIssue ;
    GsonBuilder builder ;
    Gson gson ;
    boolean  is_location_updation_running   = false  ;

    LocationSession locationSession ;
    private boolean isLatLongUpdateAPIisRunning = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        builder = new GsonBuilder();
        gson = builder.create();
        database = FirebaseDatabase.getInstance();

        mDatabaseReference = database.getReference("Drivers_A");
        rideSession = new RideSession(this);
        firebaseutil = new FirebaseUtils(this);
        apiManager_new = new ApiManager(this);
        locationSession = new LocationSession(this);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        menuListeners();
        mainActivity = this;
        Constants.mainActivity = true;
        Constants.is_main_activity_open = true ;


        driver_id = (TextView) findViewById(R.id.driver_id);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_car_name = (TextView) findViewById(R.id.tv_car_name);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_car_number = (TextView) findViewById(R.id.tv_car_number);
        status_image = (ImageView) findViewById(R.id.status_image);
        status_txt = (TypefaceDosisRegular) findViewById(R.id.status_txt);
        iv_profile_pic = (ImageView) findViewById(R.id.iv_profile_pic);
        tv_profile_name = (TextView) findViewById(R.id.tv_profile_name);
        tv_profile_email = (TextView) findViewById(R.id.tv_profile_email);
        progress_wheel = (ProgressBar) findViewById(R.id.progress_wheel);
        rental_switch = (SwitchCompat) findViewById(R.id.rental_switch);
        lat_txt = (TextView) findViewById(R.id.lat_txt);
        long_txt = (TextView) findViewById(R.id.long_txt);
        driver_city_txt = (TextView) findViewById(R.id.driver_city_txt);
        accuracy = (TextView) findViewById(R.id.accuracy);
        scheduled_rides = (TextView) findViewById(R.id.scheduled_rides);
        unaccepted_ride_txt = (TextView) findViewById(R.id.unaccepted_ride_txt);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        languageManager = new LanguageManager(this);
        sessionManager = new SessionManager(this);
        app_location_manager = new LocationSession(this);
        deviceManager = new DeviceManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        driverId = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID);
        driverName = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME);
        driverEmail = sessionManager.getUserDetails().get(SessionManager.KEY_DriverEmail);
        driverImage = sessionManager.getUserDetails().get(SessionManager.KEY_DriverImage);
        carNumber = sessionManager.getUserDetails().get(SessionManager.KEY_Driver_CAR_Number);
        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);
        car_type_name = sessionManager.getUserDetails().get(SessionManager.KEY_CarTypeName);
        car_model_name = sessionManager.getUserDetails().get(SessionManager.KEY_CarModelName);
        car_type_id = sessionManager.getUserDetails().get(SessionManager.KEY_Driver_CarTypeId);
        deviceId = FirebaseInstanceId.getInstance().getToken();
        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);


        tv_name.setText("" + driverName);
        tv_car_name.setText("" + car_type_name + " | " + car_model_name);
        tv_car_number.setText("" + carNumber);
        tv_profile_name.setText(driverName);
        tv_profile_email.setText(driverEmail);



        apiManager_new.execution_method_get(Config.ApiKeys.KEY_UPDATE_DEVICE_ID , Apis.deviceid+"?driver_id="+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)+"&device_id="+deviceId+"&flag="+Config.Devicetype+"&driver_token="+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken)+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));

        apiManager_new.execution_method_get("" +Config.ApiKeys.KEY_REPORT_ISSUE, ""+Apis.RepostIssueDetails);

        apiManager_new.execution_method_get(Config.ApiKeys.KEY_CALL_SUPPORT ,Apis.Callsupport+"?language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID) );

        HashMap<String , String > data = new HashMap<>();
        data.put("driver_id" , ""+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
        apiManager_new.execution_method_post(Config.ApiKeys.DRIVER_ACTIVE_RIDES , ""+Apis.Driver_Active_Rides , data);

        startService(new Intent(this, TimeService.class));
        startService(new Intent(this, TimelySessionService.class));


        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApporioLog.logD("*********" , ""+Locale.getDefault().getDisplayName());
                if(Locale.getDefault().getLanguage().equals("ar")){
                    drawer.openDrawer(Gravity.RIGHT);
                }else{
                    drawer.openDrawer(Gravity.LEFT);
                }

            }
        });


        findViewById(R.id.demand_spot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiManager_new.execution_method_get("heat_map"  , ""+Apis.heatmap+""+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
            }
        });

        findViewById(R.id.curent_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    new SamLocationRequestService(MainActivity.this).executeService(new SamLocationRequestService.SamLocationListener() {
                        @Override
                        public void onLocationUpdate(Location location) {
                            Maputils.moverCamera(mGooglemap , new LatLng(location.getLatitude() ,location.getLongitude()));
                        }
                    });
                }catch (Exception e){

                }
            }
        });





        findViewById(R.id.status_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , OnlineOfflineActivity.class));
            }
        });



        if ((ContextCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        }


        try {
            onNewIntent(getIntent());
        } catch (Exception e) {
            Logger.e("Exception in OnNewIntent      " + e.toString());
        }




        rental_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();
            }
        });


        startActivityAccordingToSatatus (0, "");


        if(!sessionManager.getUserDetails().get(SessionManager.KEY_UNIQUE_ID).equals("")){
            final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);
            dialog.setContentView(R.layout.demo_main_dialog);
            dialog.setCancelable(false);

            dialog.findViewById(R.id.demo_ok_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    @Override
    protected void onResumeWithConnectionState(boolean connectivityStatus) {

    }

    private void startActivityAccordingToSatatus(int val, String donRideId) {
        ApporioLog.logD("**VAL===", String.valueOf(val));
        if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("3") ||  rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("5")  ||  rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("6")  ){
          if(!Constants.is_track_ride_activity_is_open){
              startActivity(new Intent(this, TrackRideActivity.class)
                      .putExtra("customer_name", "" + rideSession.getCurrentRideDetails().get(RideSession.USER_NAME))
                      .putExtra("customer_phone", ""  + rideSession.getCurrentRideDetails().get(RideSession.USER_PHONE)));
          }
        } else  if( rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("11")||rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("12") || rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("13") ){
           if(!Constants.is_Rental_Track_Activity_is_open){
               startActivity(new Intent(this , RentalTrackRideActivity.class));
           }
        }else if (val == 7){
            startActivity(new Intent(MainActivity.this, PriceFareActivity.class).putExtra("ride_id", activeRidesResponse.getDetails().get(0).getNormal_Ride().getRide_id()).putExtra("done_ride_id", donRideId).putExtra("customerId", activeRidesResponse.getDetails().get(0).getNormal_Ride().getUser_id()));
        }else if (val == 16){

         //   startActivity(new Intent(MainActivity.this, RentalPriceFareActiviy.class).putExtra("ride_id", activeRidesResponse.getDetails().get(0).getRental_Ride().getRental_booking_id()).putExtra("user_id", activeRidesResponse.getDetails().get(0).getRental_Ride().getUser_id()));

        }


        }


    @Override
    public void onResume() {
        super.onResume();
        setStatusViewAccordingly();
        EventBus.getDefault().register(this);
        Constants.mainActivity = true;
        driverName = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME);
        driverEmail = sessionManager.getUserDetails().get(SessionManager.KEY_DriverEmail);
        driverImage = sessionManager.getUserDetails().get(SessionManager.KEY_DriverImage);
        driver_id.setText("ID "+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
        tv_profile_name.setText(driverName);
        tv_profile_email.setText(driverEmail);
        tv_name.setText("" + driverName);
        driver_city_txt.setText("Your City "+sessionManager.getUserDetails().get(SessionManager.KEY_CITY_NAME) );


        setScrocabilityOnmap();
        setLocationonBoxex();
        apiManager_new.execution_method_get(Config.ApiKeys.KEY_UPDATE_DRIVER_LAT_LONG_BACKGROUND , Apis.BackGroundAppUpdate+"?driver_id="+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)+"&current_lat="+ locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)+"&current_long="+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG)+"&current_location="+"&driver_token="+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken)+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));


        HashMap<String , String > data = new HashMap<>();
        data.put("driver_id" , ""+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
        apiManager_new.execution_method_post(Config.ApiKeys.KEY_SCHEDULE_AND_UPDATED , ""+Apis.ScheduleAndunacceptedRide , data);



        String inmage = Apis.imageDomain + driverImage ;
        if(!inmage.equals("")){
            ApporioLog.logD("**driver_image" , ""+inmage);
            Glide.with(this).load(""+inmage.replace(" " , "")).into(iv_profile_pic);
        }




    }

    private void setScrocabilityOnmap() {
        try{
            if(sessionManager.getUserDetails().get(SessionManager.KEY_service_switcher).equals("1")){
                mGooglemap.getUiSettings().setScrollGesturesEnabled(false);

                new SamLocationRequestService(this).executeService(new SamLocationRequestService.SamLocationListener() {
                    @Override
                    public void onLocationUpdate(Location location) {
                        Maputils.moverCamera(mGooglemap , new LatLng(location.getLatitude() ,location.getLongitude()) );
                    }
                });


            }else {
                mGooglemap.getUiSettings().setScrollGesturesEnabled(true);
            }
        }catch (Exception e){
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Constants.mainActivity = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        Constants.mainActivity = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Constants.mainActivity = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Constants.mainActivity = false;
        Constants.is_main_activity_open = false ;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }


    private void menuListeners() {
        findViewById(R.id.profile_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        findViewById(R.id.my_rides_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TripHistoryActivity.class));
            }
        });

        findViewById(R.id.my_earnings_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EarningActivity.class));
            }
        });


        findViewById(R.id.customer_support_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CustomerSupportActivity.class));
            }
        });

        findViewById(R.id.terms_and_condition_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TermsAndCondition.class));
            }
        });

        findViewById(R.id.about_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });

        findViewById(R.id.notification_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });

        findViewById(R.id.ll_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showlogoutdialog();
            }
        });

        findViewById(R.id.report_issue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", ""+modelReportIssue.getDeatils(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "" + MainActivity.this.getResources().getString(R.string.report_issue));
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "" + MainActivity.this.getResources().getString(R.string.send_email)));
                emailIntent.setType("text/plain");
            }
        });



        findViewById(R.id.language_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String [] languages = new String[]{"English", "Arabic"} ;
                CFAlertDialog.Builder builder = new CFAlertDialog.Builder(MainActivity.this);
                builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);
                builder.setTitle(getString(R.string.select_language));
                builder.setItems(languages , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                        switch (languages[index]){
                            case "English":
                                sessionManager.setLanguage("en");
                                break ;
                            case "Arabic":
                                sessionManager.setLanguage("ar");
                                break ;
                        }
                        dialogInterface.dismiss();

                        startActivity(new Intent(MainActivity.this , SplashActivity.class));
                        finish();
                    }
                });
                builder.show();
            }
        });


        findViewById(R.id.today_schedule_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , TripHistoryActivity.class).putExtra("tab_number" , "0"));
            }
        });


        findViewById(R.id.unaccepted_rides_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , TripHistoryActivity.class).putExtra("tab_number" , "1"));
            }
        });


    }



    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mGooglemap = googleMap;
        MapsInitializer.initialize(this);
        try {
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.uber_theme));
        } catch (Resources.NotFoundException e) {
        }

        try{
            Maputils.moverCamera(mGooglemap , new LatLng(Double.parseDouble(app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)) , Double.parseDouble(app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG))) );
        }catch (Exception e){

            new SamLocationRequestService(this).executeService(new SamLocationRequestService.SamLocationListener() {
                @Override
                public void onLocationUpdate(Location location) {
                    Maputils.moverCamera(mGooglemap , new LatLng(location.getLatitude() ,location.getLongitude()) );
                }
            });
        }


        setScrocabilityOnmap();

        ////////  setting my location enable
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mGooglemap.setMyLocationEnabled(true);


       setLocationonBoxex();

        mGooglemap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                Location l = new Location("location");
                l.setBearing(googleMap.getCameraPosition().bearing);
                l.setLatitude(googleMap.getCameraPosition().target.latitude);
                l.setLongitude(googleMap.getCameraPosition().target.longitude);
                l.setSpeed(0);
                l.setTime(System.currentTimeMillis()/1000);
                app_location_manager.setLocationLatLong(l);
                firebaseutil.updateLocation_with_text();
            }
        });



        mGooglemap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                if(sessionManager.getUserDetails().get(SessionManager.KEY_service_switcher).equals("0")){
                    firebaseutil.updateLocationOnCameramove(""+mGooglemap.getCameraPosition().target.latitude , ""+mGooglemap.getCameraPosition().target.longitude);
                }
            }
        });


    }

    private void setheatmap(NewHeatmapModel heatMapResponse ) {
        List<LatLng> list = new ArrayList<>();

        try{
            for(int i = 0 ; i < heatMapResponse.getDetails().size() ; i++){
                list.add(new LatLng(Double.parseDouble(""+heatMapResponse.getDetails().get(i).getPickup_lat()) , Double.parseDouble(""+heatMapResponse.getDetails().get(i).getPickup_long())));
            }

        }catch (Exception e){

        }

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                .data(list)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        TileOverlay mOverlay = mGooglemap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));




        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(int i = 0 ; i < list.size() ; i++){
            builder.include(list.get(i));
        }
        LatLngBounds bounds = builder.build();
        Point displaySize = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(displaySize);
        mGooglemap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 1300, 100));

    }




    private void setLocationonBoxex() {
        try{
            new SamLocationRequestService(this).executeService(new SamLocationRequestService.SamLocationListener() {
                @Override
                public void onLocationUpdate(Location location) {
                        Maputils.moverCamera(mGooglemap , new LatLng(location.getLatitude() , location.getLongitude()) );
                    lat_txt.setText(""+location.getLatitude()); long_txt.setText(""+location.getLongitude());
                }
            });
        }catch (Exception e){

        }
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LocationEvent event){
        if(sessionManager.getUserDetails().get(SessionManager.KEY_service_switcher).equals("1")){
            Maputils.moverCamera(mGooglemap , new LatLng(Double.parseDouble(event.getlatitude_string()) , Double.parseDouble(event.getLongitude_string())) );
//        setOrAnimateMarker();
        }

        lat_txt.setText(""+event.getlatitude_string()); long_txt.setText(""+event.getLongitude_string());


        setStatusViewAccordingly();

        if(!isLatLongUpdateAPIisRunning){
            apiManager_new.execution_method_get(Config.ApiKeys.KEY_UPDATE_DRIVER_LAT_LONG_BACKGROUND , Apis.BackGroundAppUpdate+"?driver_id="+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)+"&current_lat="+ event.getlatitude_string()+"&current_long="+event.getLongitude_string()+"&current_location="+"&driver_token="+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken)+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
            is_location_updation_running = true ;
        }

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AccuracyEvent event){
       try{accuracy.setText("Acc = "+event.Accuracy);}catch (Exception e){}
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventNewRide event){
        try{
            if(event.RideStatus.equals("1")){  //   ride booked for normal type
                startActivity(new Intent(MainActivity.this , ReceivePassengerActivity.class).putExtra(""+Config.IntentKeys.RIDE_ID , ""+event.RideId));
//                Toast.makeText(mainActivity, "Open Activity", Toast.LENGTH_SHORT).show();
            }else if (event.RideStatus.equals("10")){  //  ride booked for rental type
                startActivity(new Intent(MainActivity.this , ReceiveRentalPassengerActivity.class).putExtra(""+Config.IntentKeys.RIDE_ID , ""+event.RideId));
            }
        }catch (Exception e){}
    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyFirebaseMessagingService.RideEvent event){
        rideSession.setRideStatus("18");
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.user_ride_cancel_dialog);
        dialog.setCancelable(false);

        dialog.findViewById(R.id.demo_ok_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , TripHistoryActivity.class));
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    private void setStatusViewAccordingly() {
        if(sessionManager.getUserDetails().get(SessionManager.KEY_Driver_Online_Offline_Status).equals("1")){
            status_image.setColorFilter(this.getResources().getColor(R.color.icons_8_muted_green_2_dark));
            status_txt.setText(""+this.getResources().getString(R.string.MAIN_ACTIVITY__online));
            status_txt.setTextColor(this.getResources().getColor(R.color.icons_8_muted_green_2_dark));
        }else {
            status_image.setColorFilter(this.getResources().getColor(R.color.icons_8_muted_red));
            status_txt.setText(""+this.getResources().getString(R.string.MAIN_ACTIVITY__offline));
            status_txt.setTextColor(this.getResources().getColor(R.color.icons_8_muted_red));
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    finish();
                } else if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                    mapView.getMapAsync(this);
                }
            }
        }
    }


//    @Override
//    protected void onNewIntent(Intent intent) {
//
//        Bundle extras = intent.getExtras();
//        if (extras != null) {
//            ride_status = extras.getString("ride_status");
//            ride_id = extras.getString("ride_id");
//
//            if (ride_status.equals("1")) {
//                Intent i = new Intent();
//                i.setClassName("com.ride.taxiDriver", "TrialReceivePassengerActivity");
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra(""+Config.IntentKeys.RIDE_ID, ride_id);
//                startActivity(i);
//            } else if (ride_status.equals("2")) {
//                Intent i = new Intent();
//                i.setClassName("com.ride.taxiDriver", "RidesActivity");
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//            } else if (ride_status.equals("8")) {
//                Intent i = new Intent();
//                i.setClassName("com.ride.taxiDriver", "TrialReceivePassengerActivity");
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra(""+Config.IntentKeys.RIDE_ID, ride_id);
//                startActivity(i);
//            }else if (ride_status.equals("10")) {
//                Intent i = new Intent();
//                i.setClassName("com.ride.taxiDriver", "ReceiveRentalPassengerActivity");
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra(""+Config.IntentKeys.RIDE_ID, ride_id);
//                startActivity(i);
//            }
//        }
//    }



/////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onAPIRunningState(int a, String APINAME) {
        if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
            progress_wheel.setVisibility(View.VISIBLE);
            is_location_updation_running = true ;
        }else{
            progress_wheel.setVisibility(View.GONE);
            is_location_updation_running  = false ;
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{ResultCheck resultCheck = gson.fromJson(""+script , ResultCheck.class);
            if(resultCheck.result.equals("1")){
                switch (APINAME){
                    case Config.ApiKeys.KEY_ONLINE_OFFLINE :
                        DeviceId deviceToken = gson.fromJson(""+script, DeviceId.class);
                        if(deviceToken.getMsg().equals("Online")){
                            sessionManager.setonline_offline(true);
                            firebaseutil.setDriverOnlineStatus(true);
                        }else {
                            sessionManager.setonline_offline(false);
                            firebaseutil.setDriverOnlineStatus(false);
                        }
                        break;
                    case  Config.ApiKeys.KEY_CALL_SUPPORT :
                        CallSupportResponse call_response = gson.fromJson(""+script, CallSupportResponse.class);
                        if (call_response.getResult() == 1) {
                            callSupport = call_response.getDetails().getDescription();
                        }break;
                    case "heat_map":
                        NewHeatmapModel heat_map_response = gson.fromJson(""+script ,NewHeatmapModel.class);
                        setheatmap(heat_map_response);
                        break ;
                    case Config.ApiKeys.DRIVER_ACTIVE_RIDES:
                        activeRidesResponse = gson.fromJson(""+script , ActiveRidesResponse.class);
                        if(activeRidesResponse.getDetails().get(0).getRide_mode().equals("1")){ // normal type ride
                            if(activeRidesResponse.getDetails().get(0).getNormal_Ride().getRide_status().equals("1")&& !Config.ReceiverPassengerActivity  ){
                                startActivity(new Intent(MainActivity.this , ReceivePassengerActivity.class).putExtra(""+Config.IntentKeys.RIDE_ID ,""+activeRidesResponse.getDetails().get(0).getNormal_Ride().getRide_id()));
                            }else{
                                rideSession.setRideSesion(activeRidesResponse.getDetails().get(0).getNormal_Ride().getRide_id(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getUser_id(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getUser_name(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getUser_phone(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getCoupon_code(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getPickup_lat(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getPickup_long(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getPickup_location(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getDrop_lat(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getDrop_long(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getDrop_location(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getRide_date(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getRide_time(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getLater_date(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getLater_time(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getDriver_id(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getRide_type(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getRide_status(),
                                        activeRidesResponse.getDetails().get(0).getNormal_Ride().getStatus());
                                startActivityAccordingToSatatus(Integer.parseInt(activeRidesResponse.getDetails().get(0).getNormal_Ride().getRide_status()), activeRidesResponse.getDetails().get(0).getNormal_Ride().getDone_ride_id());
                            }
                        }else if (activeRidesResponse.getDetails().get(0).getRide_mode().equals("2")){ // rental ride types
                            if(activeRidesResponse.getDetails().get(0).getRental_Ride().getBooking_status().equals("10")&& !Config.RentalReceivepassengerActivity){
                                startActivity(new Intent(MainActivity.this , ReceiveRentalPassengerActivity.class).putExtra(""+Config.IntentKeys.RIDE_ID ,""+activeRidesResponse.getDetails().get(0).getRental_Ride().getRental_booking_id()));
                            }else{
                                rideSession.setRideSesion(activeRidesResponse.getDetails().get(0).getRental_Ride().getRental_booking_id(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getUser_id(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getUser_name(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getUser_phone(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getReferral_code(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getPickup_lat(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getPickup_long(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getPickup_location(),
                                        "" , "" , "",
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getBooking_date(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getBooking_time(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getBooking_date(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getBooking_time(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getDriver_id(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getBooking_type(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getBooking_status(),
                                        activeRidesResponse.getDetails().get(0).getRental_Ride().getStatus());
                                startActivity(new Intent(MainActivity.this, RentalPriceFareActiviy.class).putExtra("ride_id", activeRidesResponse.getDetails().get(0).getRental_Ride().getRental_booking_id()).putExtra("user_id", activeRidesResponse.getDetails().get(0).getRental_Ride().getUser_id()));
                            }
                        }
                        break;
                    case Config.ApiKeys.KEY_UPDATE_DRIVER_LAT_LONG_BACKGROUND :
                        isLatLongUpdateAPIisRunning = false ;
                        NewUpdateLatLongModel response = gson.fromJson(""+script , NewUpdateLatLongModel.class);
                        sessionManager.setCurrencyCode(""+response.getCurrency_iso_code() , ""+response.getCurrency_unicode());
                        break ;
                    case Config.ApiKeys.KEY_SCHEDULE_AND_UPDATED :
                        ModelScheduleAndunacceptedRide modelScheduleAndunacceptedRide = gson.fromJson(""+script ,ModelScheduleAndunacceptedRide.class);
                        unaccepted_ride_txt.setText(""+modelScheduleAndunacceptedRide.getDetails().getUnaccepted_ride());
                        scheduled_rides.setText(""+modelScheduleAndunacceptedRide.getDetails().getScheduled_ride());
                        break;
                    case Config.ApiKeys.KEY_REPORT_ISSUE :
                        modelReportIssue = gson.fromJson("" +script, ModelReportIssue.class);
                        break;
                    case Config.ApiKeys.LOGOUT:
                        firebaseutil.setDriverOnlineStatus(false);
                        firebaseutil.setDriverLoginLogoutStatus(false);
                        sessionManager.logoutUser();
                        startActivity(new Intent(MainActivity.this, SplashActivity.class));
                        finish();
                        break;
                }
            } else if(resultCheck.result.equals("0")){
                if(APINAME.equals(Config.ApiKeys.DRIVER_ACTIVE_RIDES)){
                    rideSession.clearRideSession();
                }
            }}catch (Exception e){}

    }

    @Override
    public void onFetchResultZero(String script) {

    }




    public void showlogoutdialog() {

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET)
                .setTitle(MainActivity.this.getResources().getString(R.string.PROFILE_ACTIVITY__logout))
                .setMessage(MainActivity.this.getResources().getString(R.string.are_you_sure_to_log_out))
                .addButton(MainActivity.this.getResources().getString(R.string.PROFILE_ACTIVITY__logout), -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        apiManager_new.execution_method_get(""+Config.ApiKeys.LOGOUT , ""+Apis.logout+"?driver_id="+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)+"&driver_token="+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken));
                        dialogInterface.dismiss();
                    }
                }).addButton(MainActivity.this.getResources().getString(R.string.TRACK_RIDE_ACTIVITY__cancel), -1, -1, CFAlertDialog.CFAlertActionStyle.NEGATIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.show();


    }


}
