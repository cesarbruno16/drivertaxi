package com.ride.taxiDriver.models.restmodels;

/**
 * Created by samirgoel3@gmail.com on 6/29/2017.
 */

public class NewRideRejectModel {

    /**
     * status : 1
     * message : Driver Reject Ride
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
