package com.ride.taxiDriver;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.content.Context;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;

import com.ride.taxiDriver.others.NoInterNetDialogFragment;


public abstract  class BaseActivity extends FragmentActivity implements NoInterNetDialogFragment.InternetDialogListener {

    FragmentManager manager ;
    NoInterNetDialogFragment internetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
         manager = getFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_edit_name");
        internetDialog = new NoInterNetDialogFragment();
        internetDialog.setCancelable(false);

        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }


    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        checkGPSisOnOrNot();

    }

    protected abstract void onResumeWithConnectionState(boolean connectivityStatus);

    @Override
    public void onInternetDialogFinish() {
        onResumeWithConnectionState(Connectivity.isConnected(this));
    }







    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        onResumeWithConnectionState(Connectivity.isConnected(this));
        if(!Connectivity.isConnected(this)){
            if(!internetDialog.isAdded()){
                internetDialog.show(manager, "fragment_edit_name");
            }
        }else {
            if (internetDialog.isAdded()) {
                internetDialog.dismiss();
            }
        }
    }




    public static  class Connectivity{

        public static NetworkInfo getNetworkInfo(Context context){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo();
        }


        public static boolean isConnected(Context context){
            NetworkInfo info = Connectivity.getNetworkInfo(context);
            return (info != null && info.isConnected());
        }


        public static boolean isConnectedWifi(Context context){
            NetworkInfo info = Connectivity.getNetworkInfo(context);
            return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
        }


        public static boolean isConnectedMobile(Context context){
            NetworkInfo info = Connectivity.getNetworkInfo(context);
            return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
        }


        public static boolean isConnectedFast(Context context){
            NetworkInfo info = Connectivity.getNetworkInfo(context);
            return (info != null && info.isConnected() && Connectivity.isConnectionFast(info.getType(),info.getSubtype()));
        }


        public static boolean isConnectionFast(int type, int subType){
            if(type==ConnectivityManager.TYPE_WIFI){
                return true;
            }else if(type==ConnectivityManager.TYPE_MOBILE){
                switch(subType){
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                        return false; // ~ 50-100 kbps
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                        return false; // ~ 14-64 kbps
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                        return false; // ~ 50-100 kbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        return true; // ~ 400-1000 kbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        return true; // ~ 600-1400 kbps
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                        return false; // ~ 100 kbps
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                        return true; // ~ 2-14 Mbps
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                        return true; // ~ 700-1700 kbps
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                        return true; // ~ 1-23 Mbps
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                        return true; // ~ 400-7000 kbps
            /*
             * Above API level 7, make sure to set android:targetSdkVersion
             * to appropriate level to use these
             */
                    case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                        return true; // ~ 1-2 Mbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                        return true; // ~ 5 Mbps
                    case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                        return true; // ~ 10-20 Mbps
                    case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                        return false; // ~25 kbps
                    case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                        return true; // ~ 10+ Mbps
                    // Unknown
                    case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    default:
                        return false;
                }
            }else{
                return false;
            }
        }
    }




    private boolean checkGPSisOnOrNot(){
        LocationManager lm = (LocationManager)this.getSystemService(this.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
            // notify user
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(R.string.enable_app_location);
            dialog.setPositiveButton(R.string.open_location_settings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                }
            });
            dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    finish();
                }
            });
            dialog.show();

        }
        if(!network_enabled&& !gps_enabled){
            return false;
        }else return true;
    }

}
