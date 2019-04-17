package com.ride.taxiDriver.routedrawer;

import android.content.Context;

import com.ride.taxiDriver.R;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ocittwo on 11/14/16.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class FetchUrl {
    public static String getUrl(LatLng origin, LatLng dest , Context context) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String key = "key="+context.getResources().getString(R.string.google_map_key);
        String parameters = str_origin+"&"+str_dest+"&"+sensor+"&"+key;
        String output = "json";
        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
    }
}
