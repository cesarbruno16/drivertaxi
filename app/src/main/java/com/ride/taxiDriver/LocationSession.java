package com.ride.taxiDriver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.database.DBHelper;
import com.ride.taxiDriver.manager.RideSession;
import com.ride.taxiDriver.others.AerialDistance;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * Created by samirgoel3@gmail.com on 4/7/2017.
 */

public class LocationSession {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LocationPrefrences";
    LocationEvent mLocationEvent ;
    RideSession rideSession ;
    DBHelper dbHelper ;

    public static String TAG = "LocationSession";


    public static final String KEY_CURRENT_LAT = "current_lat";
    public static final String KEY_CURRENT_LONG = "current_long";
    public static final String KEY_CURRENT_LOCATION_TEXT = "current_location_text";
    public static final String KEY_BEARING_FACTOR  = "bearing_factor";
    public static final String KEY_LOCATION_SERVICE_STARTED  = "location_service_started";
    public static final String KEY_METER_VALUE = "meter+value";
    public static final String KEY_ACCURACY = "accuracy";




    public void setKeyMeterValuelue (String meter_value){
        if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("3") && (Double.parseDouble(pref.getString(KEY_METER_VALUE,"0.0")) > Config.DistanceGap_tail) ){
            // empty the meter value else update meter value
            ApporioLog.logD(""+TAG , "RIDE STATUS = 3 and meter exceeds From"+Config.DistanceGap_tail+" meter");
            clearMeterValue();
        }else if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("6")){
            ApporioLog.logD(""+TAG , "RIDE STATUS = 6 and meter value = "+meter_value);
            editor.putString(KEY_METER_VALUE , meter_value);
            editor.commit();
            mLocationEvent.setIs_meter_value_cleared(false);
            mLocationEvent.setMeter_value(Double.parseDouble(meter_value));
            EventBus.getDefault().post(mLocationEvent);
        }else {
//            ApporioLog.logd(""+TAG , "Ride status = 3 and meter value =>"+meter_value);
//            editor.putString(KEY_METER_VALUE , meter_value);
//            editor.commit();
//            mLocationEvent.setIs_meter_value_cleared(false);
//            mLocationEvent.setMeter_value(Double.parseDouble(meter_value));
//            EventBus.getDefault().post(mLocationEvent);
        }
    }

    public void clearMeterValue (){
        ApporioLog.logD(""+TAG , "Clearing meter value ");
        editor.putString(KEY_METER_VALUE , "0.0");
        editor.commit();
        mLocationEvent.setMeter_value(0.0);
        mLocationEvent.setIs_meter_value_cleared(true);
        EventBus.getDefault().post(mLocationEvent);

    }

    public void startLocationService(){
        if(!isLocationserviceStarted()){
            _context.startService(new Intent(_context, TimeService.class));
            editor.putBoolean(KEY_LOCATION_SERVICE_STARTED , true);
            editor.commit();
        }
    }

    public boolean isLocationserviceStarted (){
        return pref.getBoolean(KEY_LOCATION_SERVICE_STARTED, false);
    }


    public LocationSession(Context context) {
        mLocationEvent = new LocationEvent();
        rideSession = new RideSession(context);
        dbHelper = new DBHelper(context);
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLocationLatLong(Location location) {
        if(!rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("")){   /// that is when ride is accepted
            Double d = Double.parseDouble(getLocationDetails().get(KEY_METER_VALUE))+ AerialDistance.aerialDistanceFunctionInMeters(Double.parseDouble(pref.getString(KEY_CURRENT_LAT, "0.0")),Double.parseDouble(pref.getString(KEY_CURRENT_LONG, "0.0")),location.getLatitude(),location.getLongitude());
             d = Math.round(d * 100D) / 100D;
            setKeyMeterValuelue("" +d);
        }
        if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("6")){
            dbHelper.insertLocation(location.getBearing(),location.getLatitude() , location.getLongitude(),location.getSpeed(),location.getTime(),""+rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID));
        }
        editor.putString(KEY_CURRENT_LAT, ""+location.getLatitude());
        editor.putString(KEY_CURRENT_LONG, ""+location.getLongitude());
        editor.putString(KEY_ACCURACY, ""+location.getAccuracy());

        Log.d("" +TAG, "Recent updated in session lat:"+location.getLatitude()+" longitude:"+location.getLongitude()+"   Accuracy:"+location.getAccuracy());
        editor.commit();
    }



    public void setLocationAddress(String current_location_txt  ) {
        if(current_location_txt == null || current_location_txt.equals("null")){
            mLocationEvent.setLocationWithAddress(getLocationDetails().get(KEY_CURRENT_LAT), getLocationDetails().get(KEY_CURRENT_LONG) , ""+_context.getResources().getString(R.string.no_internet_connection), Float.parseFloat(getLocationDetails().get(LocationSession.KEY_BEARING_FACTOR)));
            EventBus.getDefault().post(mLocationEvent);
           }
        else{
            mLocationEvent.setLocationWithAddress(getLocationDetails().get(KEY_CURRENT_LAT), getLocationDetails().get(KEY_CURRENT_LONG) , current_location_txt, Float.parseFloat(getLocationDetails().get(LocationSession.KEY_BEARING_FACTOR)));
            EventBus.getDefault().post(mLocationEvent);
            editor.putString(KEY_CURRENT_LOCATION_TEXT, current_location_txt);
            editor.commit();
        }
    }


    public void setBearingFactor(String bearing_factor  ) {
        editor.putString(KEY_BEARING_FACTOR, bearing_factor);
        editor.commit();
    }



    public HashMap<String, String> getLocationDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_CURRENT_LAT , pref.getString(KEY_CURRENT_LAT , ""));
        user.put(KEY_CURRENT_LONG , pref.getString(KEY_CURRENT_LONG , ""));
        user.put(KEY_CURRENT_LOCATION_TEXT , pref.getString(KEY_CURRENT_LOCATION_TEXT , "Not yet fetched"));
        user.put(KEY_BEARING_FACTOR , pref.getString(KEY_BEARING_FACTOR , "0.0"));
        user.put(KEY_ACCURACY , pref.getString(KEY_ACCURACY , "0.0"));
        user.put(KEY_METER_VALUE , pref.getString(KEY_METER_VALUE , "0.0"));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }




}
