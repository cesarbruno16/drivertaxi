package com.ride.taxiDriver.models.restmodels;

/**
 * Created by samirgoel3@gmail.com on 6/30/2017.
 */

public class NewEndRideModel {

    /**
     * status : 1
     * message : Driver End Ride
     * details : {"rental_booking_id":"82","user_id":"63","rentcard_id":"3","car_type_id":"5","booking_type":"1","driver_id":"185","pickup_lat":"28.40530454398334","pickup_long":"77.07071118056774","pickup_location":"Sector 65,Gurugram, Haryana 122102,","start_meter_reading":"3639589","start_meter_reading_image":"http://apporio.net/Apporiotaxi_new/uploads/1498801300566.jpg","end_meter_reading":"369850895","end_meter_reading_image":"http://apporio.net/Apporiotaxi_new/uploads/1498802557570.jpg","booking_date":"Friday, Jun 30","booking_time":"05:41 AM","user_booking_date_time":"Friday, Jun 30, 05:41 AM","last_update_time":"06:02:49 AM","booking_status":"16","booking_admin_status":"1","user_name":"Samir Goel","user_email":"samirgoel3normal@gmail.com","user_phone":"+919650923089","user_password":"123456","user_image":"","register_date":"Monday, May 15","device_id":"eE87E4b4Ukc:APA91bGa8Yu56vFc1QnnADB851b_3V8u-XJH8VL02e6GNtdz8DkliOTpzvyIEa-ffldpXGYHAbd1QmKF4YITOZvwF_cBH-PDROEfZ1tDyDZsDWWTW4Mlr6xXp5pZiQ4dYZsJ29FiD-Jg","flag":"2","referral_code":"","free_rides":"0","referral_code_send":"0","phone_verified":"0","email_verified":"0","password_created":"0","facebook_id":"1536116859766829","facebook_mail":"samirgoel3@gmail.com","facebook_image":"facebook imgae url need to send","facebook_firstname":"Samir","facebook_lastname":"Goel","google_id":"112279197400670101492","google_name":"samir goel","google_mail":"samirgoel3@gmail.com","google_image":"google user image","google_token":"","facebook_token":"","token_created":"0","login_logout":"1","rating":"4.0414201183432","status":"1"}
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
         * rental_booking_id : 82
         * user_id : 63
         * rentcard_id : 3
         * car_type_id : 5
         * booking_type : 1
         * driver_id : 185
         * pickup_lat : 28.40530454398334
         * pickup_long : 77.07071118056774
         * pickup_location : Sector 65,Gurugram, Haryana 122102,
         * start_meter_reading : 3639589
         * start_meter_reading_image : http://apporio.net/Apporiotaxi_new/uploads/1498801300566.jpg
         * end_meter_reading : 369850895
         * end_meter_reading_image : http://apporio.net/Apporiotaxi_new/uploads/1498802557570.jpg
         * booking_date : Friday, Jun 30
         * booking_time : 05:41 AM
         * user_booking_date_time : Friday, Jun 30, 05:41 AM
         * last_update_time : 06:02:49 AM
         * booking_status : 16
         * booking_admin_status : 1
         * user_name : Samir Goel
         * user_email : samirgoel3normal@gmail.com
         * user_phone : +919650923089
         * user_password : 123456
         * user_image :
         * register_date : Monday, May 15
         * device_id : eE87E4b4Ukc:APA91bGa8Yu56vFc1QnnADB851b_3V8u-XJH8VL02e6GNtdz8DkliOTpzvyIEa-ffldpXGYHAbd1QmKF4YITOZvwF_cBH-PDROEfZ1tDyDZsDWWTW4Mlr6xXp5pZiQ4dYZsJ29FiD-Jg
         * flag : 2
         * referral_code :
         * free_rides : 0
         * referral_code_send : 0
         * phone_verified : 0
         * email_verified : 0
         * password_created : 0
         * facebook_id : 1536116859766829
         * facebook_mail : samirgoel3@gmail.com
         * facebook_image : facebook imgae url need to send
         * facebook_firstname : Samir
         * facebook_lastname : Goel
         * google_id : 112279197400670101492
         * google_name : samir goel
         * google_mail : samirgoel3@gmail.com
         * google_image : google user image
         * google_token :
         * facebook_token :
         * token_created : 0
         * login_logout : 1
         * rating : 4.0414201183432
         * status : 1
         */

        private String rental_booking_id;
        private String user_id;
        private String rentcard_id;
        private String car_type_id;
        private String booking_type;
        private String driver_id;
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
        private String user_password;
        private String user_image;
        private String register_date;
        private String device_id;
        private String flag;
        private String referral_code;
        private String free_rides;
        private String referral_code_send;
        private String phone_verified;
        private String email_verified;
        private String password_created;
        private String facebook_id;
        private String facebook_mail;
        private String facebook_image;
        private String facebook_firstname;
        private String facebook_lastname;
        private String google_id;
        private String google_name;
        private String google_mail;
        private String google_image;
        private String google_token;
        private String facebook_token;
        private String token_created;
        private String login_logout;
        private String rating;
        private String status;

        public String getRental_booking_id() {
            return rental_booking_id;
        }

        public void setRental_booking_id(String rental_booking_id) {
            this.rental_booking_id = rental_booking_id;
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

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
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

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public String getUser_image() {
            return user_image;
        }

        public void setUser_image(String user_image) {
            this.user_image = user_image;
        }

        public String getRegister_date() {
            return register_date;
        }

        public void setRegister_date(String register_date) {
            this.register_date = register_date;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getReferral_code() {
            return referral_code;
        }

        public void setReferral_code(String referral_code) {
            this.referral_code = referral_code;
        }

        public String getFree_rides() {
            return free_rides;
        }

        public void setFree_rides(String free_rides) {
            this.free_rides = free_rides;
        }

        public String getReferral_code_send() {
            return referral_code_send;
        }

        public void setReferral_code_send(String referral_code_send) {
            this.referral_code_send = referral_code_send;
        }

        public String getPhone_verified() {
            return phone_verified;
        }

        public void setPhone_verified(String phone_verified) {
            this.phone_verified = phone_verified;
        }

        public String getEmail_verified() {
            return email_verified;
        }

        public void setEmail_verified(String email_verified) {
            this.email_verified = email_verified;
        }

        public String getPassword_created() {
            return password_created;
        }

        public void setPassword_created(String password_created) {
            this.password_created = password_created;
        }

        public String getFacebook_id() {
            return facebook_id;
        }

        public void setFacebook_id(String facebook_id) {
            this.facebook_id = facebook_id;
        }

        public String getFacebook_mail() {
            return facebook_mail;
        }

        public void setFacebook_mail(String facebook_mail) {
            this.facebook_mail = facebook_mail;
        }

        public String getFacebook_image() {
            return facebook_image;
        }

        public void setFacebook_image(String facebook_image) {
            this.facebook_image = facebook_image;
        }

        public String getFacebook_firstname() {
            return facebook_firstname;
        }

        public void setFacebook_firstname(String facebook_firstname) {
            this.facebook_firstname = facebook_firstname;
        }

        public String getFacebook_lastname() {
            return facebook_lastname;
        }

        public void setFacebook_lastname(String facebook_lastname) {
            this.facebook_lastname = facebook_lastname;
        }

        public String getGoogle_id() {
            return google_id;
        }

        public void setGoogle_id(String google_id) {
            this.google_id = google_id;
        }

        public String getGoogle_name() {
            return google_name;
        }

        public void setGoogle_name(String google_name) {
            this.google_name = google_name;
        }

        public String getGoogle_mail() {
            return google_mail;
        }

        public void setGoogle_mail(String google_mail) {
            this.google_mail = google_mail;
        }

        public String getGoogle_image() {
            return google_image;
        }

        public void setGoogle_image(String google_image) {
            this.google_image = google_image;
        }

        public String getGoogle_token() {
            return google_token;
        }

        public void setGoogle_token(String google_token) {
            this.google_token = google_token;
        }

        public String getFacebook_token() {
            return facebook_token;
        }

        public void setFacebook_token(String facebook_token) {
            this.facebook_token = facebook_token;
        }

        public String getToken_created() {
            return token_created;
        }

        public void setToken_created(String token_created) {
            this.token_created = token_created;
        }

        public String getLogin_logout() {
            return login_logout;
        }

        public void setLogin_logout(String login_logout) {
            this.login_logout = login_logout;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
