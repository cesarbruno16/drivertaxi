package com.ride.taxiDriver;


import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.manager.RideSession;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.others.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by samirgoel3@gmail.com on 9/6/2017.
 */

public class TimelySessionService extends  Service {

    private static final String TAG = "TimelySessionService";

    SessionManager sessionManager ;
    RideSession rideSession ;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    FirebaseUtils firebaseUtils;

    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    PowerManager pm;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        rideSession = new RideSession(this);
        sessionManager = new SessionManager(this);
        database = FirebaseDatabase.getInstance();
        firebaseUtils = new FirebaseUtils(this);
        myRef = database.getReference(""+Config.ActiveRidesRefrence);
        pm = (PowerManager) getSystemService(this.POWER_SERVICE);

        // cancel if already existed
        if(mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimelySessionService.TimeDisplayTimerTask(), 0, 3000 );
    }



    class TimeDisplayTimerTask extends TimerTask {
        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    if(!sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID).equals("")){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                            if(pm.isInteractive()) {
                                Log.d(""+TAG , "Device screen is interactive.");
                                fetchRideFromPool();
                            }else {
                                Log.d(""+TAG , "Device screen is not interactive.");
                            }
                        }

                    }
                }

            });
        }

        private void fetchRideFromPool(){
            ApporioLog.logD("**"+TAG , "fetching data from pool ");
            try{
                try{
                    myRef.child(""+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            try{
                                if(dataSnapshot.child("ride_status").getValue().equals("1") && !Config.ReceiverPassengerActivity  && rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID).equals("")){
                                    ApporioLog.logD("***"+TAG , "getting ride successfuly ");
                                    Config.ReceiverPassengerActivity = true ;
                                    Intent broadcastIntent = new Intent();
                                    broadcastIntent.putExtra("ride_id", ""+dataSnapshot.child("ride_id").getValue());
                                    broadcastIntent.putExtra("ride_status", ""+dataSnapshot.child("ride_status").getValue());
                                    broadcastIntent.setAction("com.ride.taxiDriver");
                                    sendBroadcast(broadcastIntent);
                                }else if (dataSnapshot.child("ride_status").getValue().equals("10")&& !Config.RentalReceivepassengerActivity && rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID).equals("")){
                                    Config.RentalReceivepassengerActivity = true ;
                                    Intent broadcastIntent_rental = new Intent();
                                    broadcastIntent_rental.putExtra("ride_id", ""+dataSnapshot.child("ride_id").getValue());
                                    broadcastIntent_rental.putExtra("ride_status", ""+dataSnapshot.child("ride_status").getValue());
                                    broadcastIntent_rental.setAction("com.ride.taxiDriver");
                                    sendBroadcast(broadcastIntent_rental);
                                }else{
                                    ApporioLog.logE(""+TAG , "After fetch no intent were called => datasnapshot ride status ="+dataSnapshot.child("ride_status").getValue()+"  ReceiverPassengerActivity="+Config.ReceiverPassengerActivity+"  Ride_Id in ridesession =>"+rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID));
                                }


                            }catch (Exception e){
                                ApporioLog.logE("" +TAG , "Exception caught while fetching data from active ride ==>"+e.getMessage());
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            ApporioLog.logI(""+TAG , "Data Fetched from firebase cancelled "+databaseError.getMessage());
                        }
                    });
                }catch (Exception e){
                    ApporioLog.logE(""+TAG , "TAXI EXCEPTION "+e.getMessage());
                }}catch (Exception e){}
        }
    }
}