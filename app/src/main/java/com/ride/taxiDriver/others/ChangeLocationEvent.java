package com.ride.taxiDriver.others;

/**
 * Created by samirgoel3@gmail.com on 8/23/2017.
 */

public class ChangeLocationEvent {
    public String getRideStatus() {
        return RideStatus;
    }

    public ChangeLocationEvent(String val){
        this.RideStatus = val ;
    }

    public void setRideStatus(String rideStatus) {
        RideStatus = rideStatus;
    }

    String RideStatus ;

}
