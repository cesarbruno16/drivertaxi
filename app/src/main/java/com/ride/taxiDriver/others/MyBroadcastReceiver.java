package com.ride.taxiDriver.others;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

import com.ride.taxiDriver.Config;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";
    PowerManager pm ;
    @SuppressLint("NewApi")
    @Override
    public void onReceive(Context context, Intent intent) {

        pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);








        if(pm.isInteractive()){
            Log.d(""+TAG  , "Screen is interactive ");
            callActivities(context , intent);
        }else{
            Log.d(""+TAG  , "screen is not interactive ");
        }



    }

    private void callActivities(Context context , Intent intent) {
        String ride_status = intent.getExtras().getString("ride_status");
        String ride_id = intent.getExtras().getString("ride_id");
        Intent i = new Intent();

        if(ride_status.equals("1")){  //   ride booked for normal type
            i.setClassName("com.ride.taxiDriver", "com.ride.taxiDriver.ReceivePassengerActivity");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra(""+ Config.IntentKeys.RIDE_ID, ride_id);
            context.startActivity(i);
        }else if (ride_status.equals("10")){  //  ride booked for rental type
            i.setClassName("com.ride.taxiDriver", "com.ride.taxiDriver.ReceiveRentalPassengerActivity");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra(""+ Config.IntentKeys.RIDE_ID, ride_id);
            context.startActivity(i);
        }








    }


}
