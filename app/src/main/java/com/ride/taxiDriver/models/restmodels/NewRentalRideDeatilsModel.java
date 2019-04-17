package com.ride.taxiDriver.models.restmodels;

/**
 * Created by samirgoel3@gmail.com on 7/3/2017.
 */

public class NewRentalRideDeatilsModel {
    /**
     * status : 1
     * message : Ride Details
     * details : {"rental_booking_id":"44","user_id":"168","rentcard_id":"74","payment_option_id":"1","coupan_code":"","car_type_id":"27","booking_type":"1","driver_id":"75","pickup_lat":"28.4122244","pickup_long":"77.0434085","pickup_location":"68, Plaza St, Block S, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","start_meter_reading":"0","start_meter_reading_image":"","end_meter_reading":"0","end_meter_reading_image":"","booking_date":"Thursday, Dec 14","booking_time":"12:42 PM","ride_platform":"1","user_booking_date_time":"Thursday, Dec 14, 12:42 PM","last_update_time":"12:42:52 PM","booking_status":"16","payment_status":"1","reason_id":"0","pem_file":"1","date":"2017-12-14","booking_admin_status":"1","user_rating":"1","driver_rating":"4.5","car_type_name":"Classic Taxi","car_name_arabic":"","car_type_name_french":"","car_type_image":"uploads/car/editcar_27.png","cartype_big_image":"webstatic/img/fleet-image/prime-play.png","cartype_image_size":"5927","car_longdescription":"Our taxis are clean with fully functional ACs. Our drivers are professional and well mannered ","car_longdescription_arabic":"","car_description":"Get a taxi right at your doorstep","car_description_arabic":"","ride_mode":"1","car_admin_status":"1","user_type":"1","user_name":"garima .","user_email":"gari@g.com","user_phone":"+919453800865","user_password":"123456","user_image":"","device_id":"","flag":"0","wallet_money":"0","register_date":"Thursday, Dec 14","referral_code":"","free_rides":"0","referral_code_send":"0","phone_verified":"0","email_verified":"0","password_created":"0","facebook_id":"","facebook_mail":"","facebook_image":"","facebook_firstname":"","facebook_lastname":"","google_id":"","google_name":"","google_mail":"","google_image":"","google_token":"","facebook_token":"","token_created":"0","login_logout":"0","rating":"4","user_delete":"0","unique_number":"","user_signup_type":"1","user_signup_date":"2017-12-14","status":"1","end_lat":"28.4122244","end_long":"77.0434085","end_location":"68, Plaza St, Block S, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","final_bill_amount":"500","driver_arive_time":"12:42:34 PM","begin_time":"12:42:44 PM","end_time":"12:42:52 PM","total_distance_travel":"0 Km","total_time_travel":"0 Hr 0 Min","rental_package_price":"500","rental_package_hours":"12","extra_hours_travel":"0","extra_hours_travel_charge":"0","rental_package_distance":"0 Km","extra_distance_travel":"0","extra_distance_travel_charge":"0","driver_name":"tryy","driver_image":"","driver_phone":"+918963644727","driver_lat":"28.412387","driver_long":"77.0438048","car_number":"ydfuug","driver_location":"----","total_amount":"500","coupan_price":"00.00","payment_option_name":"Cash","user_rating_star":"4.0","driver_rating_star":"4.5"}
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
         * rental_booking_id : 44
         * user_id : 168
         * rentcard_id : 74
         * payment_option_id : 1
         * coupan_code :
         * car_type_id : 27
         * booking_type : 1
         * driver_id : 75
         * pickup_lat : 28.4122244
         * pickup_long : 77.0434085
         * pickup_location : 68, Plaza St, Block S, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India
         * start_meter_reading : 0
         * start_meter_reading_image :
         * end_meter_reading : 0
         * end_meter_reading_image :
         * booking_date : Thursday, Dec 14
         * booking_time : 12:42 PM
         * ride_platform : 1
         * user_booking_date_time : Thursday, Dec 14, 12:42 PM
         * last_update_time : 12:42:52 PM
         * booking_status : 16
         * payment_status : 1
         * reason_id : 0
         * pem_file : 1
         * date : 2017-12-14
         * booking_admin_status : 1
         * user_rating : 1
         * driver_rating : 4.5
         * car_type_name : Classic Taxi
         * car_name_arabic :
         * car_type_name_french :
         * car_type_image : uploads/car/editcar_27.png
         * cartype_big_image : webstatic/img/fleet-image/prime-play.png
         * cartype_image_size : 5927
         * car_longdescription : Our taxis are clean with fully functional ACs. Our drivers are professional and well mannered
         * car_longdescription_arabic :
         * car_description : Get a taxi right at your doorstep
         * car_description_arabic :
         * ride_mode : 1
         * car_admin_status : 1
         * user_type : 1
         * user_name : garima .
         * user_email : gari@g.com
         * user_phone : +919453800865
         * user_password : 123456
         * user_image :
         * device_id :
         * flag : 0
         * wallet_money : 0
         * register_date : Thursday, Dec 14
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
         * login_logout : 0
         * rating : 4
         * user_delete : 0
         * unique_number :
         * user_signup_type : 1
         * user_signup_date : 2017-12-14
         * status : 1
         * end_lat : 28.4122244
         * end_long : 77.0434085
         * end_location : 68, Plaza St, Block S, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India
         * final_bill_amount : 500
         * driver_arive_time : 12:42:34 PM
         * begin_time : 12:42:44 PM
         * end_time : 12:42:52 PM
         * total_distance_travel : 0 Km
         * total_time_travel : 0 Hr 0 Min
         * rental_package_price : 500
         * rental_package_hours : 12
         * extra_hours_travel : 0
         * extra_hours_travel_charge : 0
         * rental_package_distance : 0 Km
         * extra_distance_travel : 0
         * extra_distance_travel_charge : 0
         * driver_name : tryy
         * driver_image :
         * driver_phone : +918963644727
         * driver_lat : 28.412387
         * driver_long : 77.0438048
         * car_number : ydfuug
         * driver_location : ----
         * total_amount : 500
         * coupan_price : 00.00
         * payment_option_name : Cash
         * user_rating_star : 4.0
         * driver_rating_star : 4.5
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
        private String car_type_name;
        private String car_name_arabic;
        private String car_type_name_french;
        private String car_type_image;
        private String cartype_big_image;
        private String cartype_image_size;
        private String car_longdescription;
        private String car_longdescription_arabic;
        private String car_description;
        private String car_description_arabic;
        private String ride_mode;
        private String car_admin_status;
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
        private String end_lat;
        private String end_long;
        private String end_location;
        private String final_bill_amount;
        private String driver_arive_time;
        private String begin_time;
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
        private String driver_name;
        private String driver_image;
        private String driver_phone;
        private String driver_lat;
        private String driver_long;
        private String car_number;
        private String driver_location;
        private String total_amount;
        private String coupan_price;
        private String payment_option_name;
        private String user_rating_star;
        private String driver_rating_star;

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

        public String getCar_type_name() {
            return car_type_name;
        }

        public void setCar_type_name(String car_type_name) {
            this.car_type_name = car_type_name;
        }

        public String getCar_name_arabic() {
            return car_name_arabic;
        }

        public void setCar_name_arabic(String car_name_arabic) {
            this.car_name_arabic = car_name_arabic;
        }

        public String getCar_type_name_french() {
            return car_type_name_french;
        }

        public void setCar_type_name_french(String car_type_name_french) {
            this.car_type_name_french = car_type_name_french;
        }

        public String getCar_type_image() {
            return car_type_image;
        }

        public void setCar_type_image(String car_type_image) {
            this.car_type_image = car_type_image;
        }

        public String getCartype_big_image() {
            return cartype_big_image;
        }

        public void setCartype_big_image(String cartype_big_image) {
            this.cartype_big_image = cartype_big_image;
        }

        public String getCartype_image_size() {
            return cartype_image_size;
        }

        public void setCartype_image_size(String cartype_image_size) {
            this.cartype_image_size = cartype_image_size;
        }

        public String getCar_longdescription() {
            return car_longdescription;
        }

        public void setCar_longdescription(String car_longdescription) {
            this.car_longdescription = car_longdescription;
        }

        public String getCar_longdescription_arabic() {
            return car_longdescription_arabic;
        }

        public void setCar_longdescription_arabic(String car_longdescription_arabic) {
            this.car_longdescription_arabic = car_longdescription_arabic;
        }

        public String getCar_description() {
            return car_description;
        }

        public void setCar_description(String car_description) {
            this.car_description = car_description;
        }

        public String getCar_description_arabic() {
            return car_description_arabic;
        }

        public void setCar_description_arabic(String car_description_arabic) {
            this.car_description_arabic = car_description_arabic;
        }

        public String getRide_mode() {
            return ride_mode;
        }

        public void setRide_mode(String ride_mode) {
            this.ride_mode = ride_mode;
        }

        public String getCar_admin_status() {
            return car_admin_status;
        }

        public void setCar_admin_status(String car_admin_status) {
            this.car_admin_status = car_admin_status;
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

        public String getFinal_bill_amount() {
            return final_bill_amount;
        }

        public void setFinal_bill_amount(String final_bill_amount) {
            this.final_bill_amount = final_bill_amount;
        }

        public String getDriver_arive_time() {
            return driver_arive_time;
        }

        public void setDriver_arive_time(String driver_arive_time) {
            this.driver_arive_time = driver_arive_time;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
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

        public String getDriver_name() {
            return driver_name;
        }

        public void setDriver_name(String driver_name) {
            this.driver_name = driver_name;
        }

        public String getDriver_image() {
            return driver_image;
        }

        public void setDriver_image(String driver_image) {
            this.driver_image = driver_image;
        }

        public String getDriver_phone() {
            return driver_phone;
        }

        public void setDriver_phone(String driver_phone) {
            this.driver_phone = driver_phone;
        }

        public String getDriver_lat() {
            return driver_lat;
        }

        public void setDriver_lat(String driver_lat) {
            this.driver_lat = driver_lat;
        }

        public String getDriver_long() {
            return driver_long;
        }

        public void setDriver_long(String driver_long) {
            this.driver_long = driver_long;
        }

        public String getCar_number() {
            return car_number;
        }

        public void setCar_number(String car_number) {
            this.car_number = car_number;
        }

        public String getDriver_location() {
            return driver_location;
        }

        public void setDriver_location(String driver_location) {
            this.driver_location = driver_location;
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

        public String getPayment_option_name() {
            return payment_option_name;
        }

        public void setPayment_option_name(String payment_option_name) {
            this.payment_option_name = payment_option_name;
        }

        public String getUser_rating_star() {
            return user_rating_star;
        }

        public void setUser_rating_star(String user_rating_star) {
            this.user_rating_star = user_rating_star;
        }

        public String getDriver_rating_star() {
            return driver_rating_star;
        }

        public void setDriver_rating_star(String driver_rating_star) {
            this.driver_rating_star = driver_rating_star;
        }
    }



/*
    *//**
     * status : 1
     * message : Ride Details
     * details : {"rental_booking_id":"125","user_id":"63","rentcard_id":"3","car_type_id":"5","booking_type":"1","driver_id":"185","pickup_lat":"28.412052468868172","pickup_long":"77.04336974769832","pickup_location":"68, Plaza Street,Block S, Uppal Southend, Sector 49,Gurugram, Haryana 122018,","start_meter_reading":"0","start_meter_reading_image":"","end_meter_reading":"0","end_meter_reading_image":"","booking_date":"Monday, Jul 3","booking_time":"06:56 PM","user_booking_date_time":"Monday, Jul 3, 06:56 PM","last_update_time":"06:56:50 PM","booking_status":"11","booking_admin_status":"1","car_type_name":"SUV","car_name_arabic":"","car_type_name_french":"SUV","car_type_image":"uploads/car/editcar_5.png","ride_mode":"1","car_admin_status":"1","user_name":"Samir Goel","user_email":"samirgoel3normal@gmail.com","user_phone":"+919650923089","user_password":"123456","user_image":"","register_date":"Monday, May 15","device_id":"ftWBFmFRn18:APA91bFEzBBrm6nINfHbMPcJg3WHCm-qb6EAyiZOqppztSFXaYuXyyR-wiGkNcvDqXZ7FMvBVW38deuUfxHYD7WwUsAQIRWskupA9PB0dKxfTdGRe9TjgdhuHeaEEu3fxCJX9TOeJ5Bl","flag":"2","referral_code":"","free_rides":"0","referral_code_send":"0","phone_verified":"0","email_verified":"0","password_created":"0","facebook_id":"1536116859766829","facebook_mail":"samirgoel3@gmail.com","facebook_image":"facebook imgae url need to send","facebook_firstname":"Samir","facebook_lastname":"Goel","google_id":"112279197400670101492","google_name":"samir goel","google_mail":"samirgoel3@gmail.com","google_image":"google user image","google_token":"","facebook_token":"","token_created":"0","login_logout":"1","rating":"4.0380116959064","status":"1","end_lat":"","end_long":"","end_location":"","final_bill_amount":0,"driver_arive_time":"","begin_time":"","end_time":"","total_distance_travel":"","total_time_travel":"","rental_package_price":"","rental_package_hours":"","extra_hours_travel":"","extra_hours_travel_charge":"","rental_package_distance":"","extra_distance_travel":"","extra_distance_travel_charge":"","driver_name":"","driver_image":"","driver_rating":"","driver_phone":"","driver_lat":"","driver_long":"","car_number":"","driver_location":""}
     *//*

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
        *//**
         * rental_booking_id : 125
         * user_id : 63
         * rentcard_id : 3
         * car_type_id : 5
         * booking_type : 1
         * driver_id : 185
         * pickup_lat : 28.412052468868172
         * pickup_long : 77.04336974769832
         * pickup_location : 68, Plaza Street,Block S, Uppal Southend, Sector 49,Gurugram, Haryana 122018,
         * start_meter_reading : 0
         * start_meter_reading_image :
         * end_meter_reading : 0
         * end_meter_reading_image :
         * booking_date : Monday, Jul 3
         * booking_time : 06:56 PM
         * user_booking_date_time : Monday, Jul 3, 06:56 PM
         * last_update_time : 06:56:50 PM
         * booking_status : 11
         * booking_admin_status : 1
         * car_type_name : SUV
         * car_name_arabic :
         * car_type_name_french : SUV
         * car_type_image : uploads/car/editcar_5.png
         * ride_mode : 1
         * car_admin_status : 1
         * user_name : Samir Goel
         * user_email : samirgoel3normal@gmail.com
         * user_phone : +919650923089
         * user_password : 123456
         * user_image :
         * register_date : Monday, May 15
         * device_id : ftWBFmFRn18:APA91bFEzBBrm6nINfHbMPcJg3WHCm-qb6EAyiZOqppztSFXaYuXyyR-wiGkNcvDqXZ7FMvBVW38deuUfxHYD7WwUsAQIRWskupA9PB0dKxfTdGRe9TjgdhuHeaEEu3fxCJX9TOeJ5Bl
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
         * rating : 4.0380116959064
         * status : 1
         * end_lat :
         * end_long :
         * end_location :
         * final_bill_amount : 0
         * driver_arive_time :
         * begin_time :
         * end_time :
         * total_distance_travel :
         * total_time_travel :
         * rental_package_price :
         * rental_package_hours :
         * extra_hours_travel :
         * extra_hours_travel_charge :
         * rental_package_distance :
         * extra_distance_travel :
         * extra_distance_travel_charge :
         * driver_name :
         * driver_image :
         * driver_rating :
         * driver_phone :
         * driver_lat :
         * driver_long :
         * car_number :
         * driver_location :
         *//*

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
        private String car_type_name;
        private String car_name_arabic;
        private String car_type_name_french;
        private String car_type_image;
        private String ride_mode;
        private String car_admin_status;
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
        private String end_lat;
        private String end_long;
        private String end_location;
        private int final_bill_amount;
        private String driver_arive_time;
        private String begin_time;
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
        private String driver_name;
        private String driver_image;
        private String driver_rating;
        private String driver_phone;
        private String driver_lat;
        private String driver_long;
        private String car_number;
        private String driver_location;

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

        public String getCar_type_name() {
            return car_type_name;
        }

        public void setCar_type_name(String car_type_name) {
            this.car_type_name = car_type_name;
        }

        public String getCar_name_arabic() {
            return car_name_arabic;
        }

        public void setCar_name_arabic(String car_name_arabic) {
            this.car_name_arabic = car_name_arabic;
        }

        public String getCar_type_name_french() {
            return car_type_name_french;
        }

        public void setCar_type_name_french(String car_type_name_french) {
            this.car_type_name_french = car_type_name_french;
        }

        public String getCar_type_image() {
            return car_type_image;
        }

        public void setCar_type_image(String car_type_image) {
            this.car_type_image = car_type_image;
        }

        public String getRide_mode() {
            return ride_mode;
        }

        public void setRide_mode(String ride_mode) {
            this.ride_mode = ride_mode;
        }

        public String getCar_admin_status() {
            return car_admin_status;
        }

        public void setCar_admin_status(String car_admin_status) {
            this.car_admin_status = car_admin_status;
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

        public int getFinal_bill_amount() {
            return final_bill_amount;
        }

        public void setFinal_bill_amount(int final_bill_amount) {
            this.final_bill_amount = final_bill_amount;
        }

        public String getDriver_arive_time() {
            return driver_arive_time;
        }

        public void setDriver_arive_time(String driver_arive_time) {
            this.driver_arive_time = driver_arive_time;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
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

        public String getDriver_name() {
            return driver_name;
        }

        public void setDriver_name(String driver_name) {
            this.driver_name = driver_name;
        }

        public String getDriver_image() {
            return driver_image;
        }

        public void setDriver_image(String driver_image) {
            this.driver_image = driver_image;
        }

        public String getDriver_rating() {
            return driver_rating;
        }

        public void setDriver_rating(String driver_rating) {
            this.driver_rating = driver_rating;
        }

        public String getDriver_phone() {
            return driver_phone;
        }

        public void setDriver_phone(String driver_phone) {
            this.driver_phone = driver_phone;
        }

        public String getDriver_lat() {
            return driver_lat;
        }

        public void setDriver_lat(String driver_lat) {
            this.driver_lat = driver_lat;
        }

        public String getDriver_long() {
            return driver_long;
        }

        public void setDriver_long(String driver_long) {
            this.driver_long = driver_long;
        }

        public String getCar_number() {
            return car_number;
        }

        public void setCar_number(String car_number) {
            this.car_number = car_number;
        }

        public String getDriver_location() {
            return driver_location;
        }

        public void setDriver_location(String driver_location) {
            this.driver_location = driver_location;
        }
    }*/
}
