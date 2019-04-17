package com.ride.taxiDriver.models.restmodels;

/**
 * Created by samirgoel3@gmail.com on 6/29/2017.
 */

public class NewRideSyncModel {

    /**
     * status : 1
     * message : New Ride Allocated
     * details : {"rental_booking_id":"37","booking_status":"10"}
     */

    private int status;
    private String message;
    private DetailsBean details;

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

    public DetailsBean getDetails() {
        return details;
    }

    public void setDetails(DetailsBean details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * rental_booking_id : 37
         * booking_status : 10
         */

        private String rental_booking_id;
        private String booking_status;

        public String getRental_booking_id() {
            return rental_booking_id;
        }

        public void setRental_booking_id(String rental_booking_id) {
            this.rental_booking_id = rental_booking_id;
        }

        public String getBooking_status() {
            return booking_status;
        }

        public void setBooking_status(String booking_status) {
            this.booking_status = booking_status;
        }
    }
}
