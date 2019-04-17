package com.ride.taxiDriver;

/**
 * Created by lenovo-pc on 2/12/2018.
 */

public class EventNewRide {
    public String RideId ;
    public String RideStatus ;

    public EventNewRide(String ride_id , String ride_status){
        this.RideId = ride_id ;
        RideStatus = ride_status ;
    }
}
