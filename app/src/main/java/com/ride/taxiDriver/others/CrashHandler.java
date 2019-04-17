package com.ride.taxiDriver.others;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by samirgoel3@gmail.com on 8/31/2017.
 */

public class CrashHandler {


    public static Double getStringToDouble(String val , String Activityname , Context context){
        Double returning_va = 0.0;
        try {
            returning_va = Double.parseDouble(""+val);
        }catch (Exception e){
            Toast.makeText(context , "Exception in "+Activityname+"  "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return returning_va;
    }


}
