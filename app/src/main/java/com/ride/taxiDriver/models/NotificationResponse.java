package com.ride.taxiDriver.models;

import java.util.List;

/**
 * Created by samirgoel3@gmail.com on 9/4/2017.
 */

public class NotificationResponse {

    /**
     * status : 1
     * message : All Notifications
     * details : [{"push_id":"32","push_message_heading":"Testing Headings","push_message":"This is promotional message , just for the development purpose","push_image":"uploads/notification/push_image_32.jpg","push_web_url":"","push_user_id":"0","push_driver_id":"0","push_app":"1"},{"push_id":"31","push_message_heading":"Hi","push_message":"gggggggggggggggggg","push_image":"uploads/notification/1500380956793.jpg","push_web_url":"www.google.com","push_user_id":"0","push_driver_id":"0","push_app":"1"}]
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
         * push_id : 32
         * push_message_heading : Testing Headings
         * push_message : This is promotional message , just for the development purpose
         * push_image : uploads/notification/push_image_32.jpg
         * push_web_url :
         * push_user_id : 0
         * push_driver_id : 0
         * push_app : 1
         */

        private String push_id;
        private String push_message_heading;
        private String push_message;
        private String push_image;
        private String push_web_url;
        private String push_user_id;
        private String push_driver_id;
        private String push_app;

        public String getPush_id() {
            return push_id;
        }

        public void setPush_id(String push_id) {
            this.push_id = push_id;
        }

        public String getPush_message_heading() {
            return push_message_heading;
        }

        public void setPush_message_heading(String push_message_heading) {
            this.push_message_heading = push_message_heading;
        }

        public String getPush_message() {
            return push_message;
        }

        public void setPush_message(String push_message) {
            this.push_message = push_message;
        }

        public String getPush_image() {
            return push_image;
        }

        public void setPush_image(String push_image) {
            this.push_image = push_image;
        }

        public String getPush_web_url() {
            return push_web_url;
        }

        public void setPush_web_url(String push_web_url) {
            this.push_web_url = push_web_url;
        }

        public String getPush_user_id() {
            return push_user_id;
        }

        public void setPush_user_id(String push_user_id) {
            this.push_user_id = push_user_id;
        }

        public String getPush_driver_id() {
            return push_driver_id;
        }

        public void setPush_driver_id(String push_driver_id) {
            this.push_driver_id = push_driver_id;
        }

        public String getPush_app() {
            return push_app;
        }

        public void setPush_app(String push_app) {
            this.push_app = push_app;
        }
    }
}
