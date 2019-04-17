package com.ride.taxiDriver.manager;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by samirgoel3@gmail.com on 4/23/2017.
 */

public class RideSession {



    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "ridePrefrences";
    public static final String RIDE_ID = "ride_id";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PHONE = "user_phone";
    public static final String COUPON_CODE = "coupon_code";
    public static final String PICK_LATITUDE = "pick_lat";
    public static final String PICK_LONGITUDE = "pick_long";
    public static final String PICK_LOCATION = "pick_location";
    public static final String DROP_LATITUDE = "drop_lat";
    public static final String DROP_LONGITUDE = "drop_long";
    public static final String DROP_LOCATION = "drop_location";
    public static final String RIDE_DATE = "ride_date";
    public static final String RIDE_TIME = "ride_time";
    public static final String RIDE_LATER_DATE = "later_date";
    public static final String RIDE_LATER_TIME = "later_time";
    public static final String DRIVER_ID = "driver_id";
    public static final String RIDE_TYPE = "ride_type";
    public static final String RIDE_STATUS = "ride_status";
    public static final String STATUS = "status";
    public static final String IS_RIDE_ONGOING = "ride_ongoing";



    public RideSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setRideSesion( String ride_id , String user_id , String user_name , String user_phone , String coupon_code , String pick_lat , String pick_long , String pick_location , String drop_lat , String drop_long , String drop_location , String ride_date , String ride_time , String later_date , String later_time  , String driver_id , String ride_type , String ride_status , String status  ) {
        editor.putBoolean(IS_RIDE_ONGOING, true);
        editor.putString(RIDE_ID, ride_id);
        editor.putString(USER_ID, user_id);
        editor.putString(USER_NAME, user_name);
        editor.putString(USER_PHONE, user_phone);
        editor.putString(COUPON_CODE, coupon_code);
        editor.putString(PICK_LATITUDE, pick_lat);
        editor.putString(PICK_LONGITUDE, pick_long);
        editor.putString(PICK_LOCATION, pick_location);
        editor.putString(DROP_LATITUDE, drop_lat);
        editor.putString(DROP_LONGITUDE,drop_long);
        editor.putString(DROP_LOCATION, drop_location);
        editor.putString(RIDE_DATE, ride_date);
        editor.putString(RIDE_TIME, ride_time);
        editor.putString(RIDE_LATER_DATE, later_date);
        editor.putString(RIDE_LATER_TIME, later_time);
        editor.putString(DRIVER_ID, driver_id);
        editor.putString(RIDE_TYPE, ride_type);
        editor.putString(RIDE_STATUS, ride_status);
        editor.putString(STATUS, status);
        editor.commit();
    }
    
    public void setRentalRideSession (String ride_id , String user_id , String user_name , String user_phone , String coupon_code , String pick_lat , String pick_long , String pick_location , String drop_lat , String drop_long , String drop_location , String ride_date , String ride_time , String later_date , String later_time  , String driver_id , String ride_type , String ride_status , String status ){
        editor.putBoolean(IS_RIDE_ONGOING, true);
        editor.putString(RIDE_ID, ride_id);
        editor.putString(USER_ID, user_id);
        editor.putString(USER_NAME, user_name);
        editor.putString(USER_PHONE, user_phone);
        editor.putString(COUPON_CODE, coupon_code);
        editor.putString(PICK_LATITUDE, pick_lat);
        editor.putString(PICK_LONGITUDE, pick_long);
        editor.putString(PICK_LOCATION, pick_location);
        editor.putString(DROP_LATITUDE, "");
        editor.putString(DROP_LONGITUDE, "");
        editor.putString(DROP_LOCATION, "");
        editor.putString(RIDE_DATE, ride_date);
        editor.putString(RIDE_TIME, ride_time);
        editor.putString(RIDE_LATER_DATE, later_date);
        editor.putString(RIDE_LATER_TIME, later_time);
        editor.putString(DRIVER_ID, driver_id);
        editor.putString(RIDE_TYPE, ride_type);
        editor.putString(RIDE_STATUS, ""+ride_status);
        editor.putString(STATUS, status);
        editor.commit();
    }


    public void setDropLocation(String drop_location , String drop_lat , String drop_long){
        editor.putString(DROP_LOCATION, drop_location);
        editor.putString(DROP_LATITUDE, drop_lat);
        editor.putString(DROP_LONGITUDE, drop_long);
        editor.commit();
    }




    public HashMap<String, String> getCurrentRideDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(RIDE_ID , pref.getString(RIDE_ID , ""));
        user.put(USER_ID , pref.getString(USER_ID , ""));
        user.put(USER_PHONE , pref.getString(USER_PHONE , ""));
        user.put(USER_NAME , pref.getString(USER_NAME , ""));
        user.put(COUPON_CODE , pref.getString(COUPON_CODE , ""));
        user.put(PICK_LATITUDE , pref.getString(PICK_LATITUDE , ""));
        user.put(PICK_LONGITUDE , pref.getString(PICK_LONGITUDE , ""));
        user.put(PICK_LOCATION , pref.getString(PICK_LOCATION , ""));
        user.put(DROP_LATITUDE , pref.getString(DROP_LATITUDE , ""));
        user.put(DROP_LONGITUDE, pref.getString(DROP_LONGITUDE, ""));
        user.put(DROP_LOCATION , pref.getString(DROP_LOCATION , ""));
        user.put(RIDE_DATE , pref.getString(RIDE_DATE , ""));
        user.put(RIDE_TIME , pref.getString(RIDE_TIME , ""));
        user.put(RIDE_LATER_DATE, pref.getString(RIDE_LATER_DATE, ""));
        user.put(RIDE_LATER_TIME , pref.getString(RIDE_LATER_TIME , ""));
        user.put(DRIVER_ID , pref.getString(DRIVER_ID , ""));
        user.put(RIDE_TYPE , pref.getString(RIDE_TYPE , ""));
        user.put(RIDE_STATUS , pref.getString(RIDE_STATUS , ""));
        user.put(STATUS , pref.getString(STATUS , ""));
        return user;
    }




    public boolean isRideonGoing() {
        return pref.getBoolean(IS_RIDE_ONGOING, false);
    }


    public void setRideStatus (String ride_status ){
        editor.putString(RIDE_STATUS, ride_status);
        editor.commit();
    }

    public void clearRideSession(){
        editor.clear();
        editor.commit();

    }



}
