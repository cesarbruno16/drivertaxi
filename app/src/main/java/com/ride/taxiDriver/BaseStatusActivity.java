package com.ride.taxiDriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

public class BaseStatusActivity extends AppCompatActivity {

    public static HashMap<String , String > ACTIVITY_STATUS= new HashMap<>();

    public static String ONSTART = "onStart";
    public static String ONCREATE = "onCreate";
    public static String ONRESUME = "onResume";
    public static String ONPAUSE = "onPause";
    public static String ONSTOP = "onStop";
    public static String ONDESTROY = "onDestroy";
    public static String NOT_EXSIST = "not exist";

    @Override
    protected void onStart() {
        super.onStart();
        ACTIVITY_STATUS.put(""+this.getClass().getSimpleName(),""+ONSTART);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ACTIVITY_STATUS.put(""+this.getClass().getSimpleName(),""+ONCREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ACTIVITY_STATUS.put(""+this.getClass().getSimpleName(),""+ONRESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ACTIVITY_STATUS.put(""+this.getClass().getSimpleName(),""+ONPAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ACTIVITY_STATUS.put(""+this.getClass().getSimpleName(),""+ONSTOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ACTIVITY_STATUS.put(""+this.getClass().getSimpleName(),""+ONDESTROY);
        ACTIVITY_STATUS.remove(""+this.getClass().getSimpleName());
    }


    public static String getActivityStatusbyName(String name){
        if(ACTIVITY_STATUS.get(name) == null){
            return ""+NOT_EXSIST;
        }else{
            return ACTIVITY_STATUS.get(name);
        }
    }
}
