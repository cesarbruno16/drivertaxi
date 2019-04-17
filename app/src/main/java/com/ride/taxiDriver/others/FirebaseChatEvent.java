package com.ride.taxiDriver.others;

/**
 * Created by lenovo-pc on 10/10/2017.
 */

public class FirebaseChatEvent{
    public String message ;
    public String send_via;
    public String timestamp ;

    FirebaseChatEvent(String message , String send_via , String timestamp){
        this.message = message ;
        this.send_via = send_via ;
        this.timestamp = timestamp;
    }
}