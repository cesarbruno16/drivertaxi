package com.ride.taxiDriver.models.restmodels;

/**
 * Created by samirgoel3@gmail.com on 6/30/2017.
 */

public class NewDoneRidemodel {

    /**
     * status : 1
     * message : Done Ride Info
     * details : {"done_rental_booking_id":"110","rental_booking_id":"306","driver_id":"212","driver_arive_time":"01:10:36 PM","begin_lat":"28.4128877","begin_long":"77.0433134","begin_location":"322B, Sohna Road, Sector 49, Gurugram, Haryana 122018, India","begin_date":"2017-08-21","begin_time":"01:10:45 PM","end_lat":"28.4128877","end_long":"77.0433134","end_location":"322B, Sohna Road, Sector 49, Gurugram, Haryana 122018, India","end_date":"2017-08-21","end_time":"01:10:52 PM","total_distance_travel":"345 Km","total_time_travel":"0 Hr 0 Min","rental_package_price":"1000","rental_package_hours":"5","extra_hours_travel":"0","extra_hours_travel_charge":"0","rental_package_distance":"100 Km","extra_distance_travel":"245 Km","extra_distance_travel_charge":"73500","total_amount":"74500","coupan_price":"332","final_bill_amount":"74168","user_id":"323","rentcard_id":"19","payment_option_id":"4","coupan_code":"SAMIR","car_type_id":"2","booking_type":"1","pickup_lat":"28.412066328773193","pickup_long":"77.04327955842018","pickup_location":"68, Plaza St, Block S, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","start_meter_reading":"23","start_meter_reading_image":"http://www.apporiotaxi.com/Apporiotaxi/uploads/1503301242055.jpg","end_meter_reading":"368","end_meter_reading_image":"http://www.apporiotaxi.com/Apporiotaxi/uploads/1503301247532.jpg","booking_date":"Monday, Aug 21","booking_time":"01:10 PM","user_booking_date_time":"Monday, Aug 21, 01:10 PM","last_update_time":"01:10:52 PM","booking_status":"16","booking_admin_status":"1","user_name":"Samir Goel","user_email":"","user_phone":"+919650923089"}
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
         * done_rental_booking_id : 110
         * rental_booking_id : 306
         * driver_id : 212
         * driver_arive_time : 01:10:36 PM
         * begin_lat : 28.4128877
         * begin_long : 77.0433134
         * begin_location : 322B, Sohna Road, Sector 49, Gurugram, Haryana 122018, India
         * begin_date : 2017-08-21
         * begin_time : 01:10:45 PM
         * end_lat : 28.4128877
         * end_long : 77.0433134
         * end_location : 322B, Sohna Road, Sector 49, Gurugram, Haryana 122018, India
         * end_date : 2017-08-21
         * end_time : 01:10:52 PM
         * total_distance_travel : 345 Km
         * total_time_travel : 0 Hr 0 Min
         * rental_package_price : 1000
         * rental_package_hours : 5
         * extra_hours_travel : 0
         * extra_hours_travel_charge : 0
         * rental_package_distance : 100 Km
         * extra_distance_travel : 245 Km
         * extra_distance_travel_charge : 73500
         * total_amount : 74500
         * coupan_price : 332
         * final_bill_amount : 74168
         * user_id : 323
         * rentcard_id : 19
         * payment_option_id : 4
         * coupan_code : SAMIR
         * car_type_id : 2
         * booking_type : 1
         * pickup_lat : 28.412066328773193
         * pickup_long : 77.04327955842018
         * pickup_location : 68, Plaza St, Block S, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India
         * start_meter_reading : 23
         * start_meter_reading_image : http://www.apporiotaxi.com/Apporiotaxi/uploads/1503301242055.jpg
         * end_meter_reading : 368
         * end_meter_reading_image : http://www.apporiotaxi.com/Apporiotaxi/uploads/1503301247532.jpg
         * booking_date : Monday, Aug 21
         * booking_time : 01:10 PM
         * user_booking_date_time : Monday, Aug 21, 01:10 PM
         * last_update_time : 01:10:52 PM
         * booking_status : 16
         * booking_admin_status : 1
         * user_name : Samir Goel
         * user_email :
         * user_phone : +919650923089
         */

        private String done_rental_booking_id;
        private String rental_booking_id;
        private String driver_id;
        private String driver_arive_time;
        private String begin_lat;
        private String begin_long;
        private String begin_location;
        private String begin_date;
        private String begin_time;
        private String end_lat;
        private String end_long;
        private String end_location;
        private String end_date;
        private String end_time;
        private String total_distance_travel;
        private String total_time_travel;
        private String rental_package_price;
        private String rental_package_hours;
        private String extra_hours_travel;
        private String extra_hours_travel_charge;
        private String rental_package_distance;
        private String extra_distance_travel;
        private String extra_distance_travel_charge;
        private String total_amount;
        private String coupan_price;
        private String final_bill_amount;
        private String user_id;
        private String rentcard_id;
        private String payment_option_id;
        private String coupan_code;
        private String car_type_id;
        private String booking_type;
        private String pickup_lat;
        private String pickup_long;
        private String pickup_location;
        private String start_meter_reading;
        private String start_meter_reading_image;
        private String end_meter_reading;
        private String end_meter_reading_image;
        private String booking_date;
        private String booking_time;
        private String user_booking_date_time;
        private String last_update_time;
        private String booking_status;
        private String booking_admin_status;
        private String user_name;
        private String user_email;
        private String user_phone;

        public String getDone_rental_booking_id() {
            return done_rental_booking_id;
        }

        public void setDone_rental_booking_id(String done_rental_booking_id) {
            this.done_rental_booking_id = done_rental_booking_id;
        }

        public String getRental_booking_id() {
            return rental_booking_id;
        }

        public void setRental_booking_id(String rental_booking_id) {
            this.rental_booking_id = rental_booking_id;
        }

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
        }

        public String getDriver_arive_time() {
            return driver_arive_time;
        }

        public void setDriver_arive_time(String driver_arive_time) {
            this.driver_arive_time = driver_arive_time;
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

        public String getBegin_date() {
            return begin_date;
        }

        public void setBegin_date(String begin_date) {
            this.begin_date = begin_date;
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

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getTotal_distance_travel() {
            return total_distance_travel;
        }

        public void setTotal_distance_travel(String total_distance_travel) {
            this.total_distance_travel = total_distance_travel;
        }

        public String getTotal_time_travel() {
            return total_time_travel;
        }

        public void setTotal_time_travel(String total_time_travel) {
            this.total_time_travel = total_time_travel;
        }

        public String getRental_package_price() {
            return rental_package_price;
        }

        public void setRental_package_price(String rental_package_price) {
            this.rental_package_price = rental_package_price;
        }

        public String getRental_package_hours() {
            return rental_package_hours;
        }

        public void setRental_package_hours(String rental_package_hours) {
            this.rental_package_hours = rental_package_hours;
        }

        public String getExtra_hours_travel() {
            return extra_hours_travel;
        }

        public void setExtra_hours_travel(String extra_hours_travel) {
            this.extra_hours_travel = extra_hours_travel;
        }

        public String getExtra_hours_travel_charge() {
            return extra_hours_travel_charge;
        }

        public void setExtra_hours_travel_charge(String extra_hours_travel_charge) {
            this.extra_hours_travel_charge = extra_hours_travel_charge;
        }

        public String getRental_package_distance() {
            return rental_package_distance;
        }

        public void setRental_package_distance(String rental_package_distance) {
            this.rental_package_distance = rental_package_distance;
        }

        public String getExtra_distance_travel() {
            return extra_distance_travel;
        }

        public void setExtra_distance_travel(String extra_distance_travel) {
            this.extra_distance_travel = extra_distance_travel;
        }

        public String getExtra_distance_travel_charge() {
            return extra_distance_travel_charge;
        }

        public void setExtra_distance_travel_charge(String extra_distance_travel_charge) {
            this.extra_distance_travel_charge = extra_distance_travel_charge;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getCoupan_price() {
            return coupan_price;
        }

        public void setCoupan_price(String coupan_price) {
            this.coupan_price = coupan_price;
        }

        public String getFinal_bill_amount() {
            return final_bill_amount;
        }

        public void setFinal_bill_amount(String final_bill_amount) {
            this.final_bill_amount = final_bill_amount;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getRentcard_id() {
            return rentcard_id;
        }

        public void setRentcard_id(String rentcard_id) {
            this.rentcard_id = rentcard_id;
        }

        public String getPayment_option_id() {
            return payment_option_id;
        }

        public void setPayment_option_id(String payment_option_id) {
            this.payment_option_id = payment_option_id;
        }

        public String getCoupan_code() {
            return coupan_code;
        }

        public void setCoupan_code(String coupan_code) {
            this.coupan_code = coupan_code;
        }

        public String getCar_type_id() {
            return car_type_id;
        }

        public void setCar_type_id(String car_type_id) {
            this.car_type_id = car_type_id;
        }

        public String getBooking_type() {
            return booking_type;
        }

        public void setBooking_type(String booking_type) {
            this.booking_type = booking_type;
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

        public String getStart_meter_reading() {
            return start_meter_reading;
        }

        public void setStart_meter_reading(String start_meter_reading) {
            this.start_meter_reading = start_meter_reading;
        }

        public String getStart_meter_reading_image() {
            return start_meter_reading_image;
        }

        public void setStart_meter_reading_image(String start_meter_reading_image) {
            this.start_meter_reading_image = start_meter_reading_image;
        }

        public String getEnd_meter_reading() {
            return end_meter_reading;
        }

        public void setEnd_meter_reading(String end_meter_reading) {
            this.end_meter_reading = end_meter_reading;
        }

        public String getEnd_meter_reading_image() {
            return end_meter_reading_image;
        }

        public void setEnd_meter_reading_image(String end_meter_reading_image) {
            this.end_meter_reading_image = end_meter_reading_image;
        }

        public String getBooking_date() {
            return booking_date;
        }

        public void setBooking_date(String booking_date) {
            this.booking_date = booking_date;
        }

        public String getBooking_time() {
            return booking_time;
        }

        public void setBooking_time(String booking_time) {
            this.booking_time = booking_time;
        }

        public String getUser_booking_date_time() {
            return user_booking_date_time;
        }

        public void setUser_booking_date_time(String user_booking_date_time) {
            this.user_booking_date_time = user_booking_date_time;
        }

        public String getLast_update_time() {
            return last_update_time;
        }

        public void setLast_update_time(String last_update_time) {
            this.last_update_time = last_update_time;
        }

        public String getBooking_status() {
            return booking_status;
        }

        public void setBooking_status(String booking_status) {
            this.booking_status = booking_status;
        }

        public String getBooking_admin_status() {
            return booking_admin_status;
        }

        public void setBooking_admin_status(String booking_admin_status) {
            this.booking_admin_status = booking_admin_status;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }
    }
}
