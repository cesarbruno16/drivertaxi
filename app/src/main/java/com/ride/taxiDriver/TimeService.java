package com.ride.taxiDriver;


import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.database.DBHelper;
import com.ride.taxiDriver.location.SamLocationRequestService;
import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.RideSession;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.restmodels.NewUpdateLatLongModel;
import com.ride.taxiDriver.others.AerialDistance;

import com.ride.taxiDriver.others.FirebaseUtils;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.trackride.EventytrackAccuracy;
import com.ride.taxiDriver.urls.Apis;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by samirgoel3@gmail.com on 4/28/2017.
 */







public class TimeService extends Service implements ApiManager.APIFETCHER{

    private static final String TAG = "TimeService";
    SamLocationRequestService sam_location ;
    LocationSession app_location_mamanger ;
    FirebaseUtils firebaseUtils ;
    SessionManager sessionManager ;
    LanguageManager languageManager ;
    RideSession rideSession ;
    DBHelper dbHelper ;
    StorageReference storageReference ;
    ApiManager apiManager ;
    GsonBuilder builder ;
    Gson gson ;
    private boolean isApiRunnign = false ;

    private Handler mHandler = new Handler();
    private Timer mTimer = null;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        app_location_mamanger = new LocationSession(this);
        sam_location = new SamLocationRequestService(this);
        apiManager = new ApiManager(this);
        firebaseUtils = new FirebaseUtils(this);
        rideSession = new RideSession(this);
        sessionManager = new SessionManager(this);
        languageManager = new LanguageManager(this);
        dbHelper = new DBHelper(this);
        builder = new GsonBuilder();
        gson = builder.create();


        storageReference = FirebaseStorage.getInstance().getReference();



        // cancel if already existed
        if(mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0,Config.LocationUpdateTimeinterval );
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {
        if(a == ApiManager.APIFETCHER.KEY_API_IS_STARTED){
            isApiRunnign = true ;
        }else{
            isApiRunnign = false ;
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {

        try{NewUpdateLatLongModel response = gson.fromJson(""+script , NewUpdateLatLongModel.class);
            sessionManager.setCurrencyCode(""+response.getCurrency_iso_code() , ""+response.getCurrency_unicode());
            sessionManager.setAccuracy(""+response.getApplication_accuracy());

        } catch (Exception e){
            ApporioLog.logE(""+TAG , "Exxception caught while parsing ==>"+e.getMessage());}
    }

    @Override
    public void onFetchResultZero(String script) {

    }



    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    if(!sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID).equals("")){
                        updateLocation();
                    }
                }

            });
        }

        private void updateLocation() {
            sam_location.executeService(new SamLocationRequestService.SamLocationListener() {
                @Override
                public void onLocationUpdate(Location location) {
                    EventBus.getDefault().post(new AccuracyEvent(""+location.getAccuracy()));
                    EventBus.getDefault().post(new EventytrackAccuracy(""+location.getAccuracy()));
                    try{
                        if(app_location_mamanger.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT).equals("")){
                            updateLocationToSession(location);
                        }else{
                            if(location.getAccuracy()<Float.parseFloat(""+sessionManager.getUserDetails().get(SessionManager.KEY_accuracy))){
                                Double distance =Double.parseDouble(""+ AerialDistance.aerialDistanceFunctionInMeters(Double.parseDouble(app_location_mamanger.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)),Double.parseDouble(app_location_mamanger.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG)), location.getLatitude(),location.getLongitude()) ) ;
                                if( distance > Double.parseDouble(""+sessionManager.getUserDetails().get(SessionManager.KEY_meter_range))){
                                    // if distance between two lat long is greater than 100 then only update on firebase and location session
                                    updateLocationToSession(location );
                                    if(!isApiRunnign){
                                        apiManager.execution_method_get(Config.ApiKeys.KEY_UPDATE_DRIVER_LAT_LONG_BACKGROUND , Apis.BackGroundAppUpdate+"?driver_id="+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)+"&current_lat="+ location.getLatitude()+"&current_long="+location.getLongitude()+"&current_location="+"&driver_token="+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken)+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                                    }
                                }
                            }
                        }
                        if(sessionManager.getUserDetails().get(SessionManager.KEY_service_switcher).equals("1")){
                            firebaseUtils.updateLocation_with_text();
                        }

                    }catch (Exception e){
                        app_location_mamanger.setLocationAddress(""+e.getMessage());
                    }
//                    LocationEvent location_event = new LocationEvent();
//                    location_event.setCurrent_speed(location.getSpeed());
//                    location_event.setCurrent_time(location.getTime());
//                    EventBus.getDefault().post(location_event);
                }
            });
        }


        private void updateLocationToSession(Location location) {
            Log.d("****"+TAG , "UpdatingLocationToSession");
            if(location.getBearing() != 0.0){
                app_location_mamanger.setBearingFactor(""+location.getBearing());
            }
            app_location_mamanger.setLocationLatLong(location);
            app_location_mamanger.setLocationAddress("----");
        }
    }


}