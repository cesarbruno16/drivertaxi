package com.ride.taxiDriver.fcmclasses;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ride.taxiDriver.AcceptPassActivity;
import com.ride.taxiDriver.ChatActivity;
import com.ride.taxiDriver.Config;
import com.ride.taxiDriver.MainActivity;
import com.ride.taxiDriver.NotificationActivity;
import com.ride.taxiDriver.PriceFareActivity;
import com.ride.taxiDriver.ProfileActivity;
import com.ride.taxiDriver.R;
import com.ride.taxiDriver.ReAcceptpassActivity;
import com.ride.taxiDriver.ReceivePassengerActivity;
import com.ride.taxiDriver.SelectedRidesActivity;
import com.ride.taxiDriver.TripHistoryActivity;
import com.ride.taxiDriver.manager.LanguageManager;
import com.ride.taxiDriver.manager.RideSession;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.restmodels.NewRideSyncModel;
import com.ride.taxiDriver.models.newdriveraccount.ResultStatusChecker;
import com.ride.taxiDriver.models.newridesync.NewRideSync;
import com.ride.taxiDriver.models.viewrideinfodriver.ViewRideInfoDriver;
import com.ride.taxiDriver.others.ChangeLocationEvent;
import com.ride.taxiDriver.others.Constants;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.trackride.TrackRideActivity;
import com.ride.taxiDriver.urls.Apis;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.ride.taxiDriver.logger.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

public class MyFirebaseMessagingService extends FirebaseMessagingService implements ApiManager.APIFETCHER {

    private static final String TAG = "MyFirebaseMessagingService";
    Intent intent;
    String pn_message, pn_ride_id, pn_ride_status, app_id;
    String driver_id, language_id;
    SessionManager sessionManager;
    RideSession rideSession ;
    LanguageManager languageManager;
    ApiManager apiManager_new ;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        pn_message = remoteMessage.getData().get("message");
        pn_ride_id = remoteMessage.getData().get("ride_id");
        pn_ride_status = remoteMessage.getData().get("ride_status");
        app_id = remoteMessage.getData().get("app_id");
        Logger.e("pn_message       " + pn_message);
        Logger.e("pn_ride_id       " + pn_ride_id);
        Logger.e("pn_ride_status       " + pn_ride_status);
        Logger.e("app_id       " + app_id);


        if (app_id.equals("2")) {
            sessionManager = new SessionManager(this);
            rideSession = new RideSession(this);
            languageManager = new LanguageManager(this);
            apiManager_new = new ApiManager(this);
            checkStatus();
        }
    }

    private void checkStatus() {
            if(!pn_ride_status.equals("20")){
                rideSession.setRideStatus(""+pn_ride_status);
            }
            if (pn_ride_status.equals("1")  || pn_ride_status.equals("8")  ) {
                if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID).equals("")){
                    apiManager_new.execution_method_get(Config.ApiKeys.KEY_NEW_RIDE_SYNC , Apis.newRideSync+"?ride_id="+pn_ride_id+"&driver_id="+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                }
            }
            if(pn_ride_status.equals("2")){
                if (Constants.is_track_ride_activity_is_open == true) {
                    EventBus.getDefault().post(new RideEvent(pn_ride_id , pn_ride_status , pn_message));
                } else if (Constants.is_track_ride_activity_is_open == false) {
                    sendNotification(pn_message);
                } else {
                    sendNotification(pn_message);
                }
                rideSession.setRideStatus("2");
            }
            if(pn_ride_status.equals("8")){

            }
            if(pn_ride_status.equals(""+Config.Status.NORMAL_RIDE_CANCEl_BY_ADMIN)){
                EventBus.getDefault().post(new RideEvent(pn_ride_id , pn_ride_status , pn_message));
            }
            else if (pn_ride_status.equals("10")){// that for rental request
                HashMap<String , String > data = new HashMap<>();
                data.put("rental_booking_id" , ""+pn_ride_id);
                data.put("app_id" , "2");
                 apiManager_new.execution_method_post(Config.ApiKeys.KEY_REST_RIDE_SYNC , ""+ Apis.RideSync , data);
            }
            else if (pn_ride_status.equals("15")){
                if (Constants.is_Rental_Track_Activity_is_open == true  || Constants.mainActivity == true) {
                    EventBus.getDefault().post(new RideEvent(pn_ride_id , pn_ride_status , pn_message));
                }else {
                    sendNotification(""+pn_message);
                }
            }
            else if (pn_ride_status.equals("20")){
                if(Constants.is_track_ride_activity_is_open){
                    EventBus.getDefault().post(new ChangeLocationEvent(""+pn_ride_status));
                }else {
                    apiManager_new.execution_method_get(Config.ApiKeys.KEY_VIEW_RIDE_INFO_DRIVER, Apis.viewRideInfoDriver + "?ride_id=" + rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID) + "&driver_token=" + sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken) + "&language_id=1" );
                }
            } else if (pn_ride_status.equals("19")){
                if(Constants.is_Rental_Track_Activity_is_open){
                    EventBus.getDefault().post(new RideEvent(pn_ride_id , pn_ride_status , pn_message));
                }else {
                    sendNotification(""+pn_message);
                }
            }
            else if (pn_ride_status.equals("51")){
                sendNotification(""+pn_message);
            }
            else if (pn_ride_status.equals(""+Config.Status.RIDE_LATER_BOOKING)){
                sendNotification(""+pn_message);
            }
            else if (pn_ride_status.equals(""+Config.Status.RIDE_LATER_REASSIGNED)){
                sendNotification(""+pn_message);
            }
            else if (pn_ride_status.equals("101")){
                sessionManager.logoutUser();
                Intent it = this.getApplicationContext().getPackageManager()
                        .getLaunchIntentForPackage( this.getApplicationContext().getPackageName() );
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(it);
                finishallOtherActivities();
            }else if (pn_ride_status.equals("17")){
                sendNotification(""+pn_message);
            }
    }

    void sendNotification(String message123) {

        if (pn_ride_status.equals("1") || pn_ride_status.equals("2") || pn_ride_status.equals("8")|| pn_ride_status.equals("10")) {
            intent = new Intent(this, MainActivity.class)
                    .putExtra("ride_id", pn_ride_id)
                    .putExtra("ride_status", pn_ride_status);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else if (pn_ride_status.equals("15")){
            intent = new Intent(this, TripHistoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else if (pn_ride_status.equals("17")){
            intent = new Intent(this, TripHistoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else if (pn_ride_status.equals("19")){
            rideSession.clearRideSession();
            intent = new Intent(this, TripHistoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        else if (pn_ride_status.equals("20")){
            intent = new Intent(this, TrackRideActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else if (pn_ride_status.equals("51")){
            intent = new Intent(this, NotificationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else if (pn_ride_status.equals(Config.Status.RIDE_LATER_BOOKING)){
            intent = new Intent(this, TripHistoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else if (pn_ride_status.equals(""+Config.Status.RIDE_LATER_REASSIGNED)){
            intent = new Intent(this, ReAcceptpassActivity.class).putExtra("ride_id" , ""+pn_ride_id);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.app_logo_100);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.app_logo_100)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(message123)
                    .setAutoCancel(true)
                    .setSound(alarmSound)
                    .setVibrate(pattern)
                    .setContentIntent(pendingIntent);
            notificationManager.notify(0, notificationBuilder.build());
        } else {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.app_logo_100);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.app_logo_100)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(message123)
                    .setAutoCancel(true)
                    .setColor(Color.parseColor("#d7ab0f"))
                    .setSound(alarmSound)
                    .setVibrate(pattern)
                    .setContentIntent(pendingIntent);
            notificationManager.notify(0, notificationBuilder.build());
        }
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            if (APINAME.equals(""+Config.ApiKeys.KEY_NEW_RIDE_SYNC)) {

                NewRideSync newRideSync = new NewRideSync();
                newRideSync = gson.fromJson(""+script, NewRideSync.class);

                if (newRideSync.getResult().toString().equals("1")) {
                    pn_message = newRideSync.getMsg();
                    pn_ride_id = newRideSync.getDetails().getRideId();
                    pn_ride_status = newRideSync.getDetails().getRideStatus();

                    if (app_id.equals("2")) {
                        switch (pn_ride_status){
//                            case "1":
//                                if(!Config.ReceiverPassengerActivity  && rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID).equals("")){
//                                    Config.ReceiverPassengerActivity = true ;
//                                    Intent broadcastIntent = new Intent();
//                                    broadcastIntent.putExtra("ride_id", ""+pn_ride_id);
//                                    broadcastIntent.putExtra("ride_status", ""+pn_ride_status);
//                                    broadcastIntent.setAction("com.ride.taxiDriver");
//                                    sendBroadcast(broadcastIntent);
//                                }
//                                break;
//                            case "8":
//                                if ( !Config.RentalReceivepassengerActivity && rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID).equals("")){
//                                    Config.RentalReceivepassengerActivity = true ;
//                                    Intent broadcastIntent_rental = new Intent();
//                                    broadcastIntent_rental.putExtra("ride_id", ""+pn_ride_id);
//                                    broadcastIntent_rental.putExtra("ride_status", ""+pn_ride_status);
//                                    broadcastIntent_rental.setAction("com.ride.taxiDriver");
//                                    sendBroadcast(broadcastIntent_rental);
//                                }
//                                break;
                        }
                    }
                }
            }
            if(APINAME.equals(""+Config.ApiKeys.KEY_REST_RIDE_SYNC)){
                ResultStatusChecker rs = gson.fromJson(""+script , ResultStatusChecker.class);
                if (rs.getStatus() == 1) {

                    NewRideSyncModel response  = gson.fromJson(""+script , NewRideSyncModel.class);
                    pn_message = response.getMessage();
                    pn_ride_id = response.getDetails().getRental_booking_id();
                    pn_ride_status = response.getDetails().getBooking_status();

                    if (app_id.equals("2")) {
                        if (Constants.mainActivity == true) {
//                            Intent broadcastIntent = new Intent();
//                            broadcastIntent.putExtra("ride_id", pn_ride_id);
//                            broadcastIntent.putExtra("ride_status", pn_ride_status);
//                            broadcastIntent.setAction("com.ride.taxiDriver");
//                            sendBroadcast(broadcastIntent);
                        } else if (Constants.mainActivity == false) {
                            sendNotification(pn_message);
                        } else {
                            sendNotification(pn_message);
                        }
                    }
                }else if (rs.getStatus() == 0){

                }
            }
            if(APINAME.equals(""+Config.ApiKeys.KEY_VIEW_RIDE_INFO_DRIVER)){
                ViewRideInfoDriver viewRideInfoDriver = gson.fromJson("" + script, ViewRideInfoDriver.class);
                rideSession.setDropLocation(viewRideInfoDriver.getDetails().getDrop_location() , ""+viewRideInfoDriver.getDetails().getDrop_lat(), ""+viewRideInfoDriver.getDetails().getDrop_long());
                sendNotification(pn_message);
            }}catch (Exception e){}

    }


    @Override
    public void onFetchResultZero(String script) {

    }


    public static class RideEvent {
        public  String  RideId ;
        public  String  RideStatus ;



        public  String  RideMessage ;
        public RideEvent(String Rideid , String RideStatus , String RideNMessage ){
            this.RideId = Rideid ;
            this.RideStatus = RideStatus ;
            this.RideMessage = RideNMessage ;
        }

        public String getRideId() {
            return RideId;
        }

        public String getRideStatus() {
            return RideStatus;
        }

        public String getRideMessage() {
            return RideMessage;
        }


    }

    private void finishallOtherActivities() {
        try {MainActivity.mainActivity.finish();}catch (Exception E){Log.e("" +TAG, "Exception caught while finish activity "+E.getMessage());}
        try {TrackRideActivity.activity.finish();}catch (Exception E){Log.e("" +TAG, "Exception caught while finish activity "+E.getMessage());}
        try {TripHistoryActivity.activity.finish();}catch (Exception E){Log.e("" +TAG, "Exception caught while finish activity "+E.getMessage());}
        try {SelectedRidesActivity.activity.finish();}catch (Exception E){Log.e("" +TAG, "Exception caught while finish activity "+E.getMessage());}
        try {ProfileActivity.profileActivity.finish();}catch (Exception E){Log.e("" +TAG, "Exception caught while finish activity "+E.getMessage());}
        try {
            ReceivePassengerActivity.activity.finish();}catch (Exception E){Log.e("" +TAG, "Exception caught while finish activity "+E.getMessage());}
        try {
            AcceptPassActivity.activity.finish();}catch (Exception E){Log.e("" +TAG, "Exception caught while finish activity "+E.getMessage());}
        try {ChatActivity.activity.finish();}catch (Exception E){Log.e("" +TAG, "Exception caught while finish activity "+E.getMessage());}
        try {
            PriceFareActivity.pricefare.finish();}catch (Exception E){Log.e("" +TAG, "Exception caught while finish activity "+E.getMessage());}
    }
}