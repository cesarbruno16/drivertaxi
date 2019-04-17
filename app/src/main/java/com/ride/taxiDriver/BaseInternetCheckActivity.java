package com.ride.taxiDriver;

/**
 * Created by lenovo-pc on 12/20/2017.
 */

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.ride.taxiDriver.others.EventLocatoin;
import com.ride.taxiDriver.others.InternetEvent;
import com.devspark.appmsg.AppMsg;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BaseInternetCheckActivity extends BaseStatusActivity {

    AppMsg appMsg = null;
    Context mContext ;
    private Location mLocation  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this ;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(InternetEvent event) {
        if(appMsg == null ){
            appMsg = AppMsg.makeText(this , getString(R.string.no_internet_connection) , new AppMsg.Style(AppMsg.LENGTH_STICKY , R.color.colorPrimary));
            showHideInternetMessage (event.status);
        }else{
            showHideInternetMessage (event.status);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventLocatoin event){
        mLocation = event.location ;
    }

    private void showHideInternetMessage(boolean status) {
        if(!status){
            if(!appMsg.isShowing()){
                appMsg.show();
            }
        }else{
            if(appMsg.isShowing()){
                appMsg.cancel();
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    public Location getLatestLocation() throws Exception {
        return mLocation;
    }



}