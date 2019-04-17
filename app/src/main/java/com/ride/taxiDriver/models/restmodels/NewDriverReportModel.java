package com.ride.taxiDriver.models.restmodels;

/**
 * Created by samirgoel3@gmail.com on 8/26/2017.
 */

public class NewDriverReportModel {

    /**
     * status : 1
     * message : Driver Daily Report
     * details : {"daily_acceptance_rate":"57.142857142857 %","avrage_rating":"4.805555555555555","online_time":""}
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
         * daily_acceptance_rate : 57.142857142857 %
         * avrage_rating : 4.805555555555555
         * online_time :
         */

        private String daily_acceptance_rate;
        private String avrage_rating;
        private String online_time;

        public String getDaily_acceptance_rate() {
            return daily_acceptance_rate;
        }

        public void setDaily_acceptance_rate(String daily_acceptance_rate) {
            this.daily_acceptance_rate = daily_acceptance_rate;
        }

        public String getAvrage_rating() {
            return avrage_rating;
        }

        public void setAvrage_rating(String avrage_rating) {
            this.avrage_rating = avrage_rating;
        }

        public String getOnline_time() {
            return online_time;
        }

        public void setOnline_time(String online_time) {
            this.online_time = online_time;
        }
    }
}
