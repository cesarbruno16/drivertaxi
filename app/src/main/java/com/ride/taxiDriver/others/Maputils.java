package com.ride.taxiDriver.others;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Location;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ride.taxiDriver.Config;
import com.ride.taxiDriver.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by samirgoel3@gmail.com on 4/8/2017.
 */

public class Maputils {


    public static float getbearing(double in_lat , double in_long , double e_lat , double elong){
        Location startingLocation = new Location("starting point");
        startingLocation.setLatitude(in_lat);
        startingLocation.setLongitude(in_long);
        Location endingLocation = new Location("ending point");
        endingLocation.setLatitude(e_lat);
        endingLocation.setLongitude(elong);
        float targetBearing = startingLocation.bearingTo(endingLocation);
        return targetBearing ;
    }


    public static void moverCamera (GoogleMap googleMap , LatLng location ){
        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(Config.MainScrenZoon).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }


    public  static void setanimatedicon(LatLng current_latlong ,GoogleMap mMap , int ico_image  ){    //// 28.446890, 77.033478
        Marker currLocationMarker;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(current_latlong);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(ico_image));
        currLocationMarker = mMap.addMarker(markerOptions);

        float stroke_Width = (float) 2;
        Circle circle2 = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(current_latlong.latitude, current_latlong.longitude))
                        .radius(600)
                        .fillColor(Color.parseColor("#4D3498DB"))
                        .strokeColor(Color.parseColor("#4D3498DB"))
                        .strokeWidth(stroke_Width)
                // .strokeColor(Color.parseColor("#3498db"))
                //     .strokeWidth(stroke_Width)
        );
        circle2.setCenter(current_latlong);


        final Circle circle = mMap.addCircle(new CircleOptions().center(current_latlong)
                .strokeColor(Color.parseColor("#3498db")).radius(600).strokeWidth(stroke_Width));

        ValueAnimator vAnimator = new ValueAnimator();
        vAnimator.setRepeatCount(ValueAnimator.INFINITE);
        vAnimator.setRepeatMode(ValueAnimator.RESTART);  /* PULSE */
        vAnimator.setIntValues(0, 100);
        vAnimator.setDuration(2000);
        vAnimator.setEvaluator(new IntEvaluator());
        vAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        vAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                // Log.e("", "" + animatedFraction);
                circle.setRadius(animatedFraction * 500);
            }
        });
        vAnimator.start();



    }



    public static void slideiewToBottom(View view ){
        view.animate().translationY(view.getHeight()).alpha(1.0f);

    }


    public static void showViewToTop(View view ){
        view.animate().translationY(0);
    }



    public static Marker setDestinationmarker(Context context, GoogleMap mMap, LatLng markerLatLng, String destination_name){
        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.destination_marker_layout, null);
        TextView destination_txt = (TextView) marker.findViewById(R.id.destination_txt);
        destination_txt.setText(""+destination_name);


        Marker customMarker = mMap.addMarker(new MarkerOptions()
                .position(markerLatLng)
                .title(""+destination_name)
                .snippet("Description")
                .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(context, marker))));
        return customMarker ;

    }



    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }


}
