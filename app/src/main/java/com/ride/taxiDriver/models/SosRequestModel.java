package com.ride.taxiDriver.models;

/**
 * Created by lenovo-pc on 10/17/2017.
 */

public class SosRequestModel {

    /**
     * status : 1
     * message : SOS Request Successfully Save
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
