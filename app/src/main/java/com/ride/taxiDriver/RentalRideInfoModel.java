package com.ride.taxiDriver;

/**
 * Created by Lenovo on 11/22/2017.
 */

public class RentalRideInfoModel {


    /**
     * status : 1
     * message : Ride Information
     * details : {"rental_booking_id":"38","user_id":"3","rentcard_id":"2","payment_option_id":"1","coupan_code":"","car_type_id":"2","booking_type":"1","driver_id":"0","pickup_lat":"28.41854696266962","pickup_long":"77.04322122037411","pickup_location":"Building A, 339, Sohna Rd, Sector 49, Gurugram, Haryana 122018, India","start_meter_reading":"0","start_meter_reading_image":"","end_meter_reading":"0","end_meter_reading_image":"","booking_date":"Wednesday, Jan 17","booking_time":"04:30 PM","ride_platform":"1","user_booking_date_time":"Wednesday, Jan 17, 04:30 PM","last_update_time":"04:30:52 PM","booking_status":"10","payment_status":"0","reason_id":"0","pem_file":"1","date":"2018-01-17","booking_admin_status":"1","user_rating":"0","driver_rating":"0","user_type":"1","user_name":"aman .","user_email":"anu@g.com","user_phone":"+23457488998","user_password":"qwerty","user_image":"","device_id":"","flag":"0","wallet_money":"0","register_date":"Saturday, Jan 6","referral_code":"","free_rides":"0","referral_code_send":"0","phone_verified":"0","email_verified":"0","password_created":"0","facebook_id":"","facebook_mail":"","facebook_image":"","facebook_firstname":"","facebook_lastname":"","google_id":"","google_name":"","google_mail":"","google_image":"","google_token":"","facebook_token":"","token_created":"0","login_logout":"1","rating":"0","user_delete":"0","unique_number":"","user_signup_type":"1","user_signup_date":"2018-01-06","status":"1","differenceInSeconds":"20","driver_request_time":"30","driver_name":"","driver_email":"","driver_phone":"","driver_image":"","car_number":"","car_type_name":"Luxury","car_type_image":"uploads/car/car_2.png","car_model_name":"","car_model_image":"","package_name":"Delhi Go","package_price":"10","payment_option_name":"Cash"}
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
         * rental_booking_id : 38
         * user_id : 3
         * rentcard_id : 2
         * payment_option_id : 1
         * coupan_code :
         * car_type_id : 2
         * booking_type : 1
         * driver_id : 0
         * pickup_lat : 28.41854696266962
         * pickup_long : 77.04322122037411
         * pickup_location : Building A, 339, Sohna Rd, Sector 49, Gurugram, Haryana 122018, India
         * start_meter_reading : 0
         * start_meter_reading_image :
         * end_meter_reading : 0
         * end_meter_reading_image :
         * booking_date : Wednesday, Jan 17
         * booking_time : 04:30 PM
         * ride_platform : 1
         * user_booking_date_time : Wednesday, Jan 17, 04:30 PM
         * last_update_time : 04:30:52 PM
         * booking_status : 10
         * payment_status : 0
         * reason_id : 0
         * pem_file : 1
         * date : 2018-01-17
         * booking_admin_status : 1
         * user_rating : 0
         * driver_rating : 0
         * user_type : 1
         * user_name : aman .
         * user_email : anu@g.com
         * user_phone : +23457488998
         * user_password : qwerty
         * user_image :
         * device_id :
         * flag : 0
         * wallet_money : 0
         * register_date : Saturday, Jan 6
         * referral_code :
         * free_rides : 0
         * referral_code_send : 0
         * phone_verified : 0
         * email_verified : 0
         * password_created : 0
         * facebook_id :
         * facebook_mail :
         * facebook_image :
         * facebook_firstname :
         * facebook_lastname :
         * google_id :
         * google_name :
         * google_mail :
         * google_image :
         * google_token :
         * facebook_token :
         * token_created : 0
         * login_logout : 1
         * rating : 0
         * user_delete : 0
         * unique_number :
         * user_signup_type : 1
         * user_signup_date : 2018-01-06
         * status : 1
         * differenceInSeconds : 20
         * driver_request_time : 30
         * driver_name :
         * driver_email :
         * driver_phone :
         * driver_image :
         * car_number :
         * car_type_name : Luxury
         * car_type_image : uploads/car/car_2.png
         * car_model_name :
         * car_model_image :
         * package_name : Delhi Go
         * package_price : 10
         * payment_option_name : Cash
         */

        private String rental_booking_id;
        private String user_id;
        private String rentcard_id;
        private String payment_option_id;
        private String coupan_code;
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
        private String ride_platform;
        private String user_booking_date_time;
        private String last_update_time;
        private String booking_status;
        private String payment_status;
        private String reason_id;
        private String pem_file;
        private String date;
        private String booking_admin_status;
        private String user_rating;
        private String driver_rating;
        private String user_type;
        private String user_name;
        private String user_email;
        private String user_phone;
        private String user_password;
        private String user_image;
        private String device_id;
        private String flag;
        private String wallet_money;
        private String register_date;
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
        private String user_delete;
        private String unique_number;
        private String user_signup_type;
        private String user_signup_date;
        private String status;
        private String differenceInSeconds;
        private String driver_request_time;
        private String driver_name;
        private String driver_email;
        private String driver_phone;
        private String driver_image;
        private String car_number;
        private String car_type_name;
        private String car_type_image;
        private String car_model_name;
        private String car_model_image;
        private String package_name;
        private String package_price;
        private String payment_option_name;

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

        public String getRide_platform() {
            return ride_platform;
        }

        public void setRide_platform(String ride_platform) {
            this.ride_platform = ride_platform;
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

        public String getPem_file() {
            return pem_file;
        }

        public void setPem_file(String pem_file) {
            this.pem_file = pem_file;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getBooking_admin_status() {
            return booking_admin_status;
        }

        public void setBooking_admin_status(String booking_admin_status) {
            this.booking_admin_status = booking_admin_status;
        }

        public String getUser_rating() {
            return user_rating;
        }

        public void setUser_rating(String user_rating) {
            this.user_rating = user_rating;
        }

        public String getDriver_rating() {
            return driver_rating;
        }

        public void setDriver_rating(String driver_rating) {
            this.driver_rating = driver_rating;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
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

        public String getWallet_money() {
            return wallet_money;
        }

        public void setWallet_money(String wallet_money) {
            this.wallet_money = wallet_money;
        }

        public String getRegister_date() {
            return register_date;
        }

        public void setRegister_date(String register_date) {
            this.register_date = register_date;
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

        public String getUser_delete() {
            return user_delete;
        }

        public void setUser_delete(String user_delete) {
            this.user_delete = user_delete;
        }

        public String getUnique_number() {
            return unique_number;
        }

        public void setUnique_number(String unique_number) {
            this.unique_number = unique_number;
        }

        public String getUser_signup_type() {
            return user_signup_type;
        }

        public void setUser_signup_type(String user_signup_type) {
            this.user_signup_type = user_signup_type;
        }

        public String getUser_signup_date() {
            return user_signup_date;
        }

        public void setUser_signup_date(String user_signup_date) {
            this.user_signup_date = user_signup_date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDifferenceInSeconds() {
            return differenceInSeconds;
        }

        public void setDifferenceInSeconds(String differenceInSeconds) {
            this.differenceInSeconds = differenceInSeconds;
        }

        public String getDriver_request_time() {
            return driver_request_time;
        }

        public void setDriver_request_time(String driver_request_time) {
            this.driver_request_time = driver_request_time;
        }

        public String getDriver_name() {
            return driver_name;
        }

        public void setDriver_name(String driver_name) {
            this.driver_name = driver_name;
        }

        public String getDriver_email() {
            return driver_email;
        }

        public void setDriver_email(String driver_email) {
            this.driver_email = driver_email;
        }

        public String getDriver_phone() {
            return driver_phone;
        }

        public void setDriver_phone(String driver_phone) {
            this.driver_phone = driver_phone;
        }

        public String getDriver_image() {
            return driver_image;
        }

        public void setDriver_image(String driver_image) {
            this.driver_image = driver_image;
        }

        public String getCar_number() {
            return car_number;
        }

        public void setCar_number(String car_number) {
            this.car_number = car_number;
        }

        public String getCar_type_name() {
            return car_type_name;
        }

        public void setCar_type_name(String car_type_name) {
            this.car_type_name = car_type_name;
        }

        public String getCar_type_image() {
            return car_type_image;
        }

        public void setCar_type_image(String car_type_image) {
            this.car_type_image = car_type_image;
        }

        public String getCar_model_name() {
            return car_model_name;
        }

        public void setCar_model_name(String car_model_name) {
            this.car_model_name = car_model_name;
        }

        public String getCar_model_image() {
            return car_model_image;
        }

        public void setCar_model_image(String car_model_image) {
            this.car_model_image = car_model_image;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public String getPackage_price() {
            return package_price;
        }

        public void setPackage_price(String package_price) {
            this.package_price = package_price;
        }

        public String getPayment_option_name() {
            return payment_option_name;
        }

        public void setPayment_option_name(String payment_option_name) {
            this.payment_option_name = payment_option_name;
        }
    }
}
