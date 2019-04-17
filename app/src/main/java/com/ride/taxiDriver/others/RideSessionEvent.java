package com.ride.taxiDriver.others;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by samirgoel3@gmail.com on 8/30/2017.
 */

@IgnoreExtraProperties
public class RideSessionEvent {

    public String ride_id;
    public String ride_status;
    public String done_ride_id ;
    public String changed_destination ;


    public RideSessionEvent(String ride_id  , String ride_status , String done_ride_id , String changed_destination ) {
        this.ride_id = ride_id ;
        this.ride_status = ride_status ;
        this.done_ride_id = done_ride_id ;
        this.changed_destination = changed_destination ;
    }

    public RideSessionEvent(){

    }

    public String getRide_id() {
        return ride_id;
    }

    public void setRide_id(String ride_id) {
        this.ride_id = ride_id;
    }

    public String getRide_status() {
        return ride_status;
    }

    public void setRide_status(String ride_status) {
        this.ride_status = ride_status;
    }

    public String getDone_ride_id() {
        return done_ride_id;
    }

    public void setDone_ride_id(String done_ride_id) {
        this.done_ride_id = done_ride_id;
    }


    public String getChanged_destination() {
        return changed_destination;
    }

    public void setChanged_destination(String changed_destination) {
        this.changed_destination = changed_destination;
    }
}
