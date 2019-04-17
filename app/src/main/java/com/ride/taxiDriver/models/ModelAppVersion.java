package com.ride.taxiDriver.models;

/**
 * Created by lenovo-pc on 12/20/2017.
 */

public class ModelAppVersion {


    /**
     * result : 1
     * msg : Not Mandatory
     * details : {"application_version_id":"1","ios_user_current_version":"5.2","ios_user_mandantory_update":"1","ios_user_maintenance_mode":"0","android_user_current_version":"2.23.20171024","android_user_mandantory_update":"0","android_user_maintenance_mode":"0","ios_driver_current_version":"5.3","ios_driver_mandantory_update":"0","ios_driver_maintenance_mode":"0","android_driver_current_version":"219","android_driver_mandantory_update":"1","android_driver_maintenance_mode":"0"}
     * app_check : 2
     */

    private int result;
    private String msg;
    private DetailsBean details;
    private int app_check;

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

    public int getApp_check() {
        return app_check;
    }

    public void setApp_check(int app_check) {
        this.app_check = app_check;
    }

    public static class DetailsBean {
        /**
         * application_version_id : 1
         * ios_user_current_version : 5.2
         * ios_user_mandantory_update : 1
         * ios_user_maintenance_mode : 0
         * android_user_current_version : 2.23.20171024
         * android_user_mandantory_update : 0
         * android_user_maintenance_mode : 0
         * ios_driver_current_version : 5.3
         * ios_driver_mandantory_update : 0
         * ios_driver_maintenance_mode : 0
         * android_driver_current_version : 219
         * android_driver_mandantory_update : 1
         * android_driver_maintenance_mode : 0
         */

        private String application_version_id;
        private String ios_user_current_version;
        private String ios_user_mandantory_update;
        private String ios_user_maintenance_mode;
        private String android_user_current_version;
        private String android_user_mandantory_update;
        private String android_user_maintenance_mode;
        private String ios_driver_current_version;
        private String ios_driver_mandantory_update;
        private String ios_driver_maintenance_mode;
        private String android_driver_current_version;
        private String android_driver_mandantory_update;
        private String android_driver_maintenance_mode;

        public String getApplication_version_id() {
            return application_version_id;
        }

        public void setApplication_version_id(String application_version_id) {
            this.application_version_id = application_version_id;
        }

        public String getIos_user_current_version() {
            return ios_user_current_version;
        }

        public void setIos_user_current_version(String ios_user_current_version) {
            this.ios_user_current_version = ios_user_current_version;
        }

        public String getIos_user_mandantory_update() {
            return ios_user_mandantory_update;
        }

        public void setIos_user_mandantory_update(String ios_user_mandantory_update) {
            this.ios_user_mandantory_update = ios_user_mandantory_update;
        }

        public String getIos_user_maintenance_mode() {
            return ios_user_maintenance_mode;
        }

        public void setIos_user_maintenance_mode(String ios_user_maintenance_mode) {
            this.ios_user_maintenance_mode = ios_user_maintenance_mode;
        }

        public String getAndroid_user_current_version() {
            return android_user_current_version;
        }

        public void setAndroid_user_current_version(String android_user_current_version) {
            this.android_user_current_version = android_user_current_version;
        }

        public String getAndroid_user_mandantory_update() {
            return android_user_mandantory_update;
        }

        public void setAndroid_user_mandantory_update(String android_user_mandantory_update) {
            this.android_user_mandantory_update = android_user_mandantory_update;
        }

        public String getAndroid_user_maintenance_mode() {
            return android_user_maintenance_mode;
        }

        public void setAndroid_user_maintenance_mode(String android_user_maintenance_mode) {
            this.android_user_maintenance_mode = android_user_maintenance_mode;
        }

        public String getIos_driver_current_version() {
            return ios_driver_current_version;
        }

        public void setIos_driver_current_version(String ios_driver_current_version) {
            this.ios_driver_current_version = ios_driver_current_version;
        }

        public String getIos_driver_mandantory_update() {
            return ios_driver_mandantory_update;
        }

        public void setIos_driver_mandantory_update(String ios_driver_mandantory_update) {
            this.ios_driver_mandantory_update = ios_driver_mandantory_update;
        }

        public String getIos_driver_maintenance_mode() {
            return ios_driver_maintenance_mode;
        }

        public void setIos_driver_maintenance_mode(String ios_driver_maintenance_mode) {
            this.ios_driver_maintenance_mode = ios_driver_maintenance_mode;
        }

        public String getAndroid_driver_current_version() {
            return android_driver_current_version;
        }

        public void setAndroid_driver_current_version(String android_driver_current_version) {
            this.android_driver_current_version = android_driver_current_version;
        }

        public String getAndroid_driver_mandantory_update() {
            return android_driver_mandantory_update;
        }

        public void setAndroid_driver_mandantory_update(String android_driver_mandantory_update) {
            this.android_driver_mandantory_update = android_driver_mandantory_update;
        }

        public String getAndroid_driver_maintenance_mode() {
            return android_driver_maintenance_mode;
        }

        public void setAndroid_driver_maintenance_mode(String android_driver_maintenance_mode) {
            this.android_driver_maintenance_mode = android_driver_maintenance_mode;
        }
    }
}
