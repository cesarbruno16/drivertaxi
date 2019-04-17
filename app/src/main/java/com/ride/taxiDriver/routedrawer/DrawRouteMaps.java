package com.ride.taxiDriver.routedrawer;

import android.content.Context;

import com.ride.taxiDriver.manager.SessionManager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ocittwo on 11/14/16.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class DrawRouteMaps {

    private static DrawRouteMaps instance;
    private Context context;
    private int route_width , color ;

    public static DrawRouteMaps getInstance(Context context , int route_width , int color) {
        instance = new DrawRouteMaps();
        instance.context = context;
        instance.route_width = route_width ;
        instance.color = color ;
        return instance;
    }

    public DrawRouteMaps draw(LatLng origin, LatLng destination, GoogleMap googleMap , SessionManager sessionManager){
        String url_route = FetchUrl.getUrl(origin, destination , context);
        DrawRoute drawRoute = new DrawRoute(googleMap , sessionManager , route_width , color);
        drawRoute.execute(url_route);
        return instance;
    }

    public static Context getContext() {
        return instance.context;
    }
}
