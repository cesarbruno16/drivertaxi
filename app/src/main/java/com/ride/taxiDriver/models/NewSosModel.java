package com.ride.taxiDriver.models;

import java.util.List;

/**
 * Created by samirgoel3@gmail.com on 8/24/2017.
 */

public class NewSosModel {

    /**
     * status : 1
     * message : SOS
     * details : [{"sos_id":"1","sos_name":"Police","sos_number":"100","sos_status":"1"},{"sos_id":"3","sos_name":"keselamatan","sos_number":"999","sos_status":"1"}]
     */

    private int status;
    private String message;
    private List<DetailsBean> details;

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

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * sos_id : 1
         * sos_name : Police
         * sos_number : 100
         * sos_status : 1
         */

        private String sos_id;
        private String sos_name;
        private String sos_number;
        private String sos_status;

        public String getSos_id() {
            return sos_id;
        }

        public void setSos_id(String sos_id) {
            this.sos_id = sos_id;
        }

        public String getSos_name() {
            return sos_name;
        }

        public void setSos_name(String sos_name) {
            this.sos_name = sos_name;
        }

        public String getSos_number() {
            return sos_number;
        }

        public void setSos_number(String sos_number) {
            this.sos_number = sos_number;
        }

        public String getSos_status() {
            return sos_status;
        }

        public void setSos_status(String sos_status) {
            this.sos_status = sos_status;
        }
    }
}
