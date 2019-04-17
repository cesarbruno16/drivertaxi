package com.ride.taxiDriver;

/**
 * Created by Lenovo on 11/9/2017.
 */

public class RideDetailsModel {


    /**
     * result : 1
     * msg :
     * details : {"ride_id":"6","user_id":"14","coupon_code":"","pickup_lat":"28.43042114870706","pickup_long":"77.04125247895718","pickup_location":"737, Sector 38, Islampur Village, Sector 38, Gurugram, Haryana 122018, India","drop_lat":"28.4592693","drop_long":"77.0724192","drop_location":"Huda City Centre","ride_date":"Tuesday, Nov 7","ride_time":"19:21:23","last_time_stamp":"07:23:36 PM","ride_image":"https:maps.googleapis.com/maps/api/staticmap?center=&zoom=12&size=200x200&maptype=roadmap&markers=color:green|label:S|28.43042114870706,77.04125247895718&markers=color:red|label:D|28.4592693,77.0724192&key=AIzaSyAIFe17P91Mfez3T6cqk7hfDSyvMO812Z4","later_date":"","later_time":"","driver_id":"4","car_type_id":"32","ride_type":"1","pem_file":"1","ride_status":"7","driver_rating":"1","user_rating":"1","payment_status":"1","reason_id":"0","payment_option_id":"1","card_id":"","ride_platform":"1","ride_admin_status":"1","date":"2017-11-07","payment_option_name":"Cash","user_image":"","user_name":"mehjabi .","user_phone":"+2341234568096","rating":"2.8125","arrived_time":"07:21:46 PM","begin_lat":"28.430461","begin_long":"77.0412701","begin_location":"737, Sector 38, Islampur Village, Sector 38, Gurugram, Haryana 122018, India","begin_time":"07:21:59 PM","end_lat":"28.43061","end_long":"77.0413396","end_location":"737, Sector 38, Islampur Village, Sector 38, Gurugram, Haryana 122018, India","amount":"19000.00","distance":"0.00 Miles","time":"1","waiting_time":"0 Min","waiting_price":"0.00","done_ride_time":"1 Min","ride_time_price":"00.00","end_time":"07:23:36 PM","rating_user":"0","peak_time_charge":"0.00","night_time_charge":"38000.00","coupons_code":"","coupons_price":"","total_amount":"57000"}
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
         * ride_id : 6
         * user_id : 14
         * coupon_code :
         * pickup_lat : 28.43042114870706
         * pickup_long : 77.04125247895718
         * pickup_location : 737, Sector 38, Islampur Village, Sector 38, Gurugram, Haryana 122018, India
         * drop_lat : 28.4592693
         * drop_long : 77.0724192
         * drop_location : Huda City Centre
         * ride_date : Tuesday, Nov 7
         * ride_time : 19:21:23
         * last_time_stamp : 07:23:36 PM
         * ride_image : https:maps.googleapis.com/maps/api/staticmap?center=&zoom=12&size=200x200&maptype=roadmap&markers=color:green|label:S|28.43042114870706,77.04125247895718&markers=color:red|label:D|28.4592693,77.0724192&key=AIzaSyAIFe17P91Mfez3T6cqk7hfDSyvMO812Z4
         * later_date :
         * later_time :
         * driver_id : 4
         * car_type_id : 32
         * ride_type : 1
         * pem_file : 1
         * ride_status : 7
         * driver_rating : 1
         * user_rating : 1
         * payment_status : 1
         * reason_id : 0
         * payment_option_id : 1
         * card_id :
         * ride_platform : 1
         * ride_admin_status : 1
         * date : 2017-11-07
         * payment_option_name : Cash
         * user_image :
         * user_name : mehjabi .
         * user_phone : +2341234568096
         * rating : 2.8125
         * arrived_time : 07:21:46 PM
         * begin_lat : 28.430461
         * begin_long : 77.0412701
         * begin_location : 737, Sector 38, Islampur Village, Sector 38, Gurugram, Haryana 122018, India
         * begin_time : 07:21:59 PM
         * end_lat : 28.43061
         * end_long : 77.0413396
         * end_location : 737, Sector 38, Islampur Village, Sector 38, Gurugram, Haryana 122018, India
         * amount : 19000.00
         * distance : 0.00 Miles
         * time : 1
         * waiting_time : 0 Min
         * waiting_price : 0.00
         * done_ride_time : 1 Min
         * ride_time_price : 00.00
         * end_time : 07:23:36 PM
         * rating_user : 0
         * peak_time_charge : 0.00
         * night_time_charge : 38000.00
         * coupons_code :
         * coupons_price :
         * total_amount : 57000
         */

        private String ride_id;
        private String user_id;
        private String coupon_code;
        private String pickup_lat;
        private String pickup_long;
        private String pickup_location;
        private String drop_lat;
        private String drop_long;
        private String drop_location;
        private String ride_date;
        private String ride_time;
        private String last_time_stamp;
        private String ride_image;
        private String later_date;
        private String later_time;
        private String driver_id;
        private String car_type_id;
        private String ride_type;
        private String pem_file;
        private String ride_status;
        private String driver_rating;
        private String user_rating;
        private String payment_status;
        private String reason_id;
        private String payment_option_id;
        private String card_id;
        private String ride_platform;
        private String ride_admin_status;
        private String date;
        private String payment_option_name;
        private String user_image;
        private String user_name;
        private String user_phone;
        private String rating;
        private String arrived_time;
        private String begin_lat;
        private String begin_long;
        private String begin_location;
        private String begin_time;
        private String end_lat;
        private String end_long;
        private String end_location;
        private String amount;
        private String distance;
        private String time;
        private String waiting_time;
        private String waiting_price;
        private String done_ride_time;
        private String ride_time_price;
        private String end_time;
        private String rating_user;
        private String peak_time_charge;
        private String night_time_charge;
        private String coupons_code;
        private String coupons_price;
        private String total_amount;

        public String getRide_id() {
            return ride_id;
        }

        public void setRide_id(String ride_id) {
            this.ride_id = ride_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCoupon_code() {
            return coupon_code;
        }

        public void setCoupon_code(String coupon_code) {
            this.coupon_code = coupon_code;
        }

        public String getPickup_lat() {
            return pickup_lat;
        }

        public void setPickup_lat(String pickup_lat) {
            this.pickup_lat = pickup_lat;
        }

        public String getPickup_long() {
            return pickup_long;
        }

        public void setPickup_long(String pickup_long) {
            this.pickup_long = pickup_long;
        }

        public String getPickup_location() {
            return pickup_location;
        }

        public void setPickup_location(String pickup_location) {
            this.pickup_location = pickup_location;
        }

        public String getDrop_lat() {
            return drop_lat;
        }

        public void setDrop_lat(String drop_lat) {
            this.drop_lat = drop_lat;
        }

        public String getDrop_long() {
            return drop_long;
        }

        public void setDrop_long(String drop_long) {
            this.drop_long = drop_long;
        }

        public String getDrop_location() {
            return drop_location;
        }

        public void setDrop_location(String drop_location) {
            this.drop_location = drop_location;
        }

        public String getRide_date() {
            return ride_date;
        }

        public void setRide_date(String ride_date) {
            this.ride_date = ride_date;
        }

        public String getRide_time() {
            return ride_time;
        }

        public void setRide_time(String ride_time) {
            this.ride_time = ride_time;
        }

        public String getLast_time_stamp() {
            return last_time_stamp;
        }

        public void setLast_time_stamp(String last_time_stamp) {
            this.last_time_stamp = last_time_stamp;
        }

        public String getRide_image() {
            return ride_image;
        }

        public void setRide_image(String ride_image) {
            this.ride_image = ride_image;
        }

        public String getLater_date() {
            return later_date;
        }

        public void setLater_date(String later_date) {
            this.later_date = later_date;
        }

        public String getLater_time() {
            return later_time;
        }

        public void setLater_time(String later_time) {
            this.later_time = later_time;
        }

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
        }

        public String getCar_type_id() {
            return car_type_id;
        }

        public void setCar_type_id(String car_type_id) {
            this.car_type_id = car_type_id;
        }

        public String getRide_type() {
            return ride_type;
        }

        public void setRide_type(String ride_type) {
            this.ride_type = ride_type;
        }

        public String getPem_file() {
            return pem_file;
        }

        public void setPem_file(String pem_file) {
            this.pem_file = pem_file;
        }

        public String getRide_status() {
            return ride_status;
        }

        public void setRide_status(String ride_status) {
            this.ride_status = ride_status;
        }

        public String getDriver_rating() {
            return driver_rating;
        }

        public void setDriver_rating(String driver_rating) {
            this.driver_rating = driver_rating;
        }

        public String getUser_rating() {
            return user_rating;
        }

        public void setUser_rating(String user_rating) {
            this.user_rating = user_rating;
        }

        public String getPayment_status() {
            return payment_status;
        }

        public void setPayment_status(String payment_status) {
            this.payment_status = payment_status;
        }

        public String getReason_id() {
            return reason_id;
        }

        public void setReason_id(String reason_id) {
            this.reason_id = reason_id;
        }

        public String getPayment_option_id() {
            return payment_option_id;
        }

        public void setPayment_option_id(String payment_option_id) {
            this.payment_option_id = payment_option_id;
        }

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getRide_platform() {
            return ride_platform;
        }

        public void setRide_platform(String ride_platform) {
            this.ride_platform = ride_platform;
        }

        public String getRide_admin_status() {
            return ride_admin_status;
        }

        public void setRide_admin_status(String ride_admin_status) {
            this.ride_admin_status = ride_admin_status;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPayment_option_name() {
            return payment_option_name;
        }

        public void setPayment_option_name(String payment_option_name) {
            this.payment_option_name = payment_option_name;
        }

        public String getUser_image() {
            return user_image;
        }

        public void setUser_image(String user_image) {
            this.user_image = user_image;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getArrived_time() {
            return arrived_time;
        }

        public void setArrived_time(String arrived_time) {
            this.arrived_time = arrived_time;
        }

        public String getBegin_lat() {
            return begin_lat;
        }

        public void setBegin_lat(String begin_lat) {
            this.begin_lat = begin_lat;
        }

        public String getBegin_long() {
            return begin_long;
        }

        public void setBegin_long(String begin_long) {
            this.begin_long = begin_long;
        }

        public String getBegin_location() {
            return begin_location;
        }

        public void setBegin_location(String begin_location) {
            this.begin_location = begin_location;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
        }

        public String getEnd_lat() {
            return end_lat;
        }

        public void setEnd_lat(String end_lat) {
            this.end_lat = end_lat;
        }

        public String getEnd_long() {
            return end_long;
        }

        public void setEnd_long(String end_long) {
            this.end_long = end_long;
        }

        public String getEnd_location() {
            return end_location;
        }

        public void setEnd_location(String end_location) {
            this.end_location = end_location;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWaiting_time() {
            return waiting_time;
        }

        public void setWaiting_time(String waiting_time) {
            this.waiting_time = waiting_time;
        }

        public String getWaiting_price() {
            return waiting_price;
        }

        public void setWaiting_price(String waiting_price) {
            this.waiting_price = waiting_price;
        }

        public String getDone_ride_time() {
            return done_ride_time;
        }

        public void setDone_ride_time(String done_ride_time) {
            this.done_ride_time = done_ride_time;
        }

        public String getRide_time_price() {
            return ride_time_price;
        }

        public void setRide_time_price(String ride_time_price) {
            this.ride_time_price = ride_time_price;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getRating_user() {
            return rating_user;
        }

        public void setRating_user(String rating_user) {
            this.rating_user = rating_user;
        }

        public String getPeak_time_charge() {
            return peak_time_charge;
        }

        public void setPeak_time_charge(String peak_time_charge) {
            this.peak_time_charge = peak_time_charge;
        }

        public String getNight_time_charge() {
            return night_time_charge;
        }

        public void setNight_time_charge(String night_time_charge) {
            this.night_time_charge = night_time_charge;
        }

        public String getCoupons_code() {
            return coupons_code;
        }

        public void setCoupons_code(String coupons_code) {
            this.coupons_code = coupons_code;
        }

        public String getCoupons_price() {
            return coupons_price;
        }

        public void setCoupons_price(String coupons_price) {
            this.coupons_price = coupons_price;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }
    }
}
