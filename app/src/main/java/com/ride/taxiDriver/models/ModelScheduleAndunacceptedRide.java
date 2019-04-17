package com.ride.taxiDriver.models;

/**
 * Created by lenovo-pc on 12/21/2017.
 */

public class ModelScheduleAndunacceptedRide {

    /**
     * result : 1
     * msg : Today Schdule!!
     * details : {"scheduled_ride":0,"unaccepted_ride":2}
     */

    private int result;
    private String msg;
    private DetailsBean details;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DetailsBean getDetails() {
        return details;
    }

    public void setDetails(DetailsBean details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * scheduled_ride : 0
         * unaccepted_ride : 2
         */

        private int scheduled_ride;
        private int unaccepted_ride;

        public int getScheduled_ride() {
            return scheduled_ride;
        }

        public void setScheduled_ride(int scheduled_ride) {
            this.scheduled_ride = scheduled_ride;
        }

        public int getUnaccepted_ride() {
            return unaccepted_ride;
        }

        public void setUnaccepted_ride(int unaccepted_ride) {
            this.unaccepted_ride = unaccepted_ride;
        }
    }
}
