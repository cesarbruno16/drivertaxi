package com.ride.taxiDriver.models;

/**
 * Created by lenovo-pc on 12/21/2017.
 */

public class ModelPartialRequestResponse {

    /**
     * result : 1
     * msg : Booking Accepted!!
     * details : {"ride_id":"45","user_id":"1","coupon_code":"","pickup_lat":"28.41076850299141","pickup_long":"77.04229317605495","pickup_location":"1, Sohna Rd, Block S, Uppal Southend, Sector 49, Gurugram, Haryana 122001, India","drop_lat":"28.43255017023076","drop_long":"77.05649647861719","drop_location":"1924, Huda Colony, Sector 46, Gurugram, Haryana 122018, India","ride_date":"Wednesday, Dec 20","ride_time":"11:47:59","last_time_stamp":"07:50:35","ride_image":"","later_date":"2017-12-23","later_time":"07:30","driver_id":"1","car_type_id":"30","ride_type":"2","pem_file":"1","ride_status":"22","driver_rating":"0","user_rating":"0","payment_status":"0","reason_id":"0","payment_option_id":"1","card_id":"","ride_platform":"1","ride_admin_status":"1","date":"2017-12-23","user_type":"1","user_name":"Samir Goel .","user_email":"samirgoel3@gmail.com","user_phone":"+919650923089","user_password":"123456","user_image":"","device_id":"dcw6GPmf_XI:APA91bF8o-agqQZd2ko167P1AlCtm3hv6PFyewV-dQfInXzoryMpLOo466wEcvJeZtgi9WYV6Ye7NVaIWViWXuEgVJDPmLTbC0gplDPLtIVrsVyBgrusYMbOLfOmg-1ym_VzSy3-rlBb","flag":"2","wallet_money":"0","register_date":"Friday, Dec 15","referral_code":"","free_rides":"0","referral_code_send":"0","phone_verified":"0","email_verified":"0","password_created":"0","facebook_id":"","facebook_mail":"","facebook_image":"","facebook_firstname":"","facebook_lastname":"","google_id":"","google_name":"","google_mail":"","google_image":"","google_token":"","facebook_token":"","token_created":"0","login_logout":"1","rating":"0","user_delete":"0","unique_number":"","user_signup_type":"1","user_signup_date":"2017-12-15","status":"1","car_type_name":"MustangD","car_name_arabic":"","car_type_name_french":"","car_type_image":"uploads/car/car_30.png","cartype_big_image":"webstatic/img/fleet-image/lux.png","cartype_image_size":"775","car_longdescription":"The all too familiar auto ride, minus the hassle of waiting and haggling for price. A convenient way to travel everyday.","car_longdescription_arabic":"???????? ????? ?????? ?? ????? ???????? ????? ?? ?? ??. ???????? ????? ?? ??????? ??????.","car_description":"Bus with in- cab entertainment","car_description_arabic":"?????? ??? ????? ???? ???? ??? ???? ?????","ride_mode":"1","car_admin_status":"1","company_id":"0","commission":"20","driver_name":"Samir Goel","driver_email":"samir@apporio.com","driver_phone":"+919650923089","driver_image":"","driver_password":"123456","driver_token":"ZolTb1YWVXr7mFYZ","driver_category":"1","total_payment_eraned":"34180","company_payment":"6836","driver_payment":"27344","car_model_id":"53","car_number":"DL-5575","city_id":"56","license":"","license_expire":"","rc":"","rc_expire":"","insurance":"","insurance_expire":"","other_docs":"","driver_bank_name":"Yes Bank","driver_account_name":"Samir Goel","driver_account_number":"90908079658045","total_card_payment":"0.00","total_cash_payment":"0.00","amount_transfer_pending":"0.00","current_lat":"28.4124583","current_long":"77.0438057","current_location":"","last_update":"07:48","last_update_date":"Thursday, Dec 21, 2017","completed_rides":"17","reject_rides":"2","cancelled_rides":"1","busy":"0","online_offline":"1","detail_status":"2","payment_transfer":"0","verification_date":"2017-12-15","verification_status":"1","driver_signup_date":"2017-12-15","driver_status_image":"http://www.apporiotaxi.com/superadminpanel/images/icons8-Error-80.png","driver_status_message":"Your account will be activated as soon as we review your documents. Please check back.","total_document_need":"3","driver_admin_status":"1","verfiy_document":"3"}
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
         * ride_id : 45
         * user_id : 1
         * coupon_code :
         * pickup_lat : 28.41076850299141
         * pickup_long : 77.04229317605495
         * pickup_location : 1, Sohna Rd, Block S, Uppal Southend, Sector 49, Gurugram, Haryana 122001, India
         * drop_lat : 28.43255017023076
         * drop_long : 77.05649647861719
         * drop_location : 1924, Huda Colony, Sector 46, Gurugram, Haryana 122018, India
         * ride_date : Wednesday, Dec 20
         * ride_time : 11:47:59
         * last_time_stamp : 07:50:35
         * ride_image :
         * later_date : 2017-12-23
         * later_time : 07:30
         * driver_id : 1
         * car_type_id : 30
         * ride_type : 2
         * pem_file : 1
         * ride_status : 22
         * driver_rating : 0
         * user_rating : 0
         * payment_status : 0
         * reason_id : 0
         * payment_option_id : 1
         * card_id :
         * ride_platform : 1
         * ride_admin_status : 1
         * date : 2017-12-23
         * user_type : 1
         * user_name : Samir Goel .
         * user_email : samirgoel3@gmail.com
         * user_phone : +919650923089
         * user_password : 123456
         * user_image :
         * device_id : dcw6GPmf_XI:APA91bF8o-agqQZd2ko167P1AlCtm3hv6PFyewV-dQfInXzoryMpLOo466wEcvJeZtgi9WYV6Ye7NVaIWViWXuEgVJDPmLTbC0gplDPLtIVrsVyBgrusYMbOLfOmg-1ym_VzSy3-rlBb
         * flag : 2
         * wallet_money : 0
         * register_date : Friday, Dec 15
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
         * user_signup_date : 2017-12-15
         * status : 1
         * car_type_name : MustangD
         * car_name_arabic :
         * car_type_name_french :
         * car_type_image : uploads/car/car_30.png
         * cartype_big_image : webstatic/img/fleet-image/lux.png
         * cartype_image_size : 775
         * car_longdescription : The all too familiar auto ride, minus the hassle of waiting and haggling for price. A convenient way to travel everyday.
         * car_longdescription_arabic : ???????? ????? ?????? ?? ????? ???????? ????? ?? ?? ??. ???????? ????? ?? ??????? ??????.
         * car_description : Bus with in- cab entertainment
         * car_description_arabic : ?????? ??? ????? ???? ???? ??? ???? ?????
         * ride_mode : 1
         * car_admin_status : 1
         * company_id : 0
         * commission : 20
         * driver_name : Samir Goel
         * driver_email : samir@apporio.com
         * driver_phone : +919650923089
         * driver_image :
         * driver_password : 123456
         * driver_token : ZolTb1YWVXr7mFYZ
         * driver_category : 1
         * total_payment_eraned : 34180
         * company_payment : 6836
         * driver_payment : 27344
         * car_model_id : 53
         * car_number : DL-5575
         * city_id : 56
         * license :
         * license_expire :
         * rc :
         * rc_expire :
         * insurance :
         * insurance_expire :
         * other_docs :
         * driver_bank_name : Yes Bank
         * driver_account_name : Samir Goel
         * driver_account_number : 90908079658045
         * total_card_payment : 0.00
         * total_cash_payment : 0.00
         * amount_transfer_pending : 0.00
         * current_lat : 28.4124583
         * current_long : 77.0438057
         * current_location :
         * last_update : 07:48
         * last_update_date : Thursday, Dec 21, 2017
         * completed_rides : 17
         * reject_rides : 2
         * cancelled_rides : 1
         * busy : 0
         * online_offline : 1
         * detail_status : 2
         * payment_transfer : 0
         * verification_date : 2017-12-15
         * verification_status : 1
         * driver_signup_date : 2017-12-15
         * driver_status_image : http://www.apporiotaxi.com/superadminpanel/images/icons8-Error-80.png
         * driver_status_message : Your account will be activated as soon as we review your documents. Please check back.
         * total_document_need : 3
         * driver_admin_status : 1
         * verfiy_document : 3
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
        private String company_id;
        private String commission;
        private String driver_name;
        private String driver_email;
        private String driver_phone;
        private String driver_image;
        private String driver_password;
        private String driver_token;
        private String driver_category;
        private String total_payment_eraned;
        private String company_payment;
        private String driver_payment;
        private String car_model_id;
        private String car_number;
        private String city_id;
        private String license;
        private String license_expire;
        private String rc;
        private String rc_expire;
        private String insurance;
        private String insurance_expire;
        private String other_docs;
        private String driver_bank_name;
        private String driver_account_name;
        private String driver_account_number;
        private String total_card_payment;
        private String total_cash_payment;
        private String amount_transfer_pending;
        private String current_lat;
        private String current_long;
        private String current_location;
        private String last_update;
        private String last_update_date;
        private String completed_rides;
        private String reject_rides;
        private String cancelled_rides;
        private String busy;
        private String online_offline;
        private String detail_status;
        private String payment_transfer;
        private String verification_date;
        private String verification_status;
        private String driver_signup_date;
        private String driver_status_image;
        private String driver_status_message;
        private String total_document_need;
        private String driver_admin_status;
        private String verfiy_document;

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

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
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

        public String getDriver_password() {
            return driver_password;
        }

        public void setDriver_password(String driver_password) {
            this.driver_password = driver_password;
        }

        public String getDriver_token() {
            return driver_token;
        }

        public void setDriver_token(String driver_token) {
            this.driver_token = driver_token;
        }

        public String getDriver_category() {
            return driver_category;
        }

        public void setDriver_category(String driver_category) {
            this.driver_category = driver_category;
        }

        public String getTotal_payment_eraned() {
            return total_payment_eraned;
        }

        public void setTotal_payment_eraned(String total_payment_eraned) {
            this.total_payment_eraned = total_payment_eraned;
        }

        public String getCompany_payment() {
            return company_payment;
        }

        public void setCompany_payment(String company_payment) {
            this.company_payment = company_payment;
        }

        public String getDriver_payment() {
            return driver_payment;
        }

        public void setDriver_payment(String driver_payment) {
            this.driver_payment = driver_payment;
        }

        public String getCar_model_id() {
            return car_model_id;
        }

        public void setCar_model_id(String car_model_id) {
            this.car_model_id = car_model_id;
        }

        public String getCar_number() {
            return car_number;
        }

        public void setCar_number(String car_number) {
            this.car_number = car_number;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicense_expire() {
            return license_expire;
        }

        public void setLicense_expire(String license_expire) {
            this.license_expire = license_expire;
        }

        public String getRc() {
            return rc;
        }

        public void setRc(String rc) {
            this.rc = rc;
        }

        public String getRc_expire() {
            return rc_expire;
        }

        public void setRc_expire(String rc_expire) {
            this.rc_expire = rc_expire;
        }

        public String getInsurance() {
            return insurance;
        }

        public void setInsurance(String insurance) {
            this.insurance = insurance;
        }

        public String getInsurance_expire() {
            return insurance_expire;
        }

        public void setInsurance_expire(String insurance_expire) {
            this.insurance_expire = insurance_expire;
        }

        public String getOther_docs() {
            return other_docs;
        }

        public void setOther_docs(String other_docs) {
            this.other_docs = other_docs;
        }

        public String getDriver_bank_name() {
            return driver_bank_name;
        }

        public void setDriver_bank_name(String driver_bank_name) {
            this.driver_bank_name = driver_bank_name;
        }

        public String getDriver_account_name() {
            return driver_account_name;
        }

        public void setDriver_account_name(String driver_account_name) {
            this.driver_account_name = driver_account_name;
        }

        public String getDriver_account_number() {
            return driver_account_number;
        }

        public void setDriver_account_number(String driver_account_number) {
            this.driver_account_number = driver_account_number;
        }

        public String getTotal_card_payment() {
            return total_card_payment;
        }

        public void setTotal_card_payment(String total_card_payment) {
            this.total_card_payment = total_card_payment;
        }

        public String getTotal_cash_payment() {
            return total_cash_payment;
        }

        public void setTotal_cash_payment(String total_cash_payment) {
            this.total_cash_payment = total_cash_payment;
        }

        public String getAmount_transfer_pending() {
            return amount_transfer_pending;
        }

        public void setAmount_transfer_pending(String amount_transfer_pending) {
            this.amount_transfer_pending = amount_transfer_pending;
        }

        public String getCurrent_lat() {
            return current_lat;
        }

        public void setCurrent_lat(String current_lat) {
            this.current_lat = current_lat;
        }

        public String getCurrent_long() {
            return current_long;
        }

        public void setCurrent_long(String current_long) {
            this.current_long = current_long;
        }

        public String getCurrent_location() {
            return current_location;
        }

        public void setCurrent_location(String current_location) {
            this.current_location = current_location;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public String getLast_update_date() {
            return last_update_date;
        }

        public void setLast_update_date(String last_update_date) {
            this.last_update_date = last_update_date;
        }

        public String getCompleted_rides() {
            return completed_rides;
        }

        public void setCompleted_rides(String completed_rides) {
            this.completed_rides = completed_rides;
        }

        public String getReject_rides() {
            return reject_rides;
        }

        public void setReject_rides(String reject_rides) {
            this.reject_rides = reject_rides;
        }

        public String getCancelled_rides() {
            return cancelled_rides;
        }

        public void setCancelled_rides(String cancelled_rides) {
            this.cancelled_rides = cancelled_rides;
        }

        public String getBusy() {
            return busy;
        }

        public void setBusy(String busy) {
            this.busy = busy;
        }

        public String getOnline_offline() {
            return online_offline;
        }

        public void setOnline_offline(String online_offline) {
            this.online_offline = online_offline;
        }

        public String getDetail_status() {
            return detail_status;
        }

        public void setDetail_status(String detail_status) {
            this.detail_status = detail_status;
        }

        public String getPayment_transfer() {
            return payment_transfer;
        }

        public void setPayment_transfer(String payment_transfer) {
            this.payment_transfer = payment_transfer;
        }

        public String getVerification_date() {
            return verification_date;
        }

        public void setVerification_date(String verification_date) {
            this.verification_date = verification_date;
        }

        public String getVerification_status() {
            return verification_status;
        }

        public void setVerification_status(String verification_status) {
            this.verification_status = verification_status;
        }

        public String getDriver_signup_date() {
            return driver_signup_date;
        }

        public void setDriver_signup_date(String driver_signup_date) {
            this.driver_signup_date = driver_signup_date;
        }

        public String getDriver_status_image() {
            return driver_status_image;
        }

        public void setDriver_status_image(String driver_status_image) {
            this.driver_status_image = driver_status_image;
        }

        public String getDriver_status_message() {
            return driver_status_message;
        }

        public void setDriver_status_message(String driver_status_message) {
            this.driver_status_message = driver_status_message;
        }

        public String getTotal_document_need() {
            return total_document_need;
        }

        public void setTotal_document_need(String total_document_need) {
            this.total_document_need = total_document_need;
        }

        public String getDriver_admin_status() {
            return driver_admin_status;
        }

        public void setDriver_admin_status(String driver_admin_status) {
            this.driver_admin_status = driver_admin_status;
        }

        public String getVerfiy_document() {
            return verfiy_document;
        }

        public void setVerfiy_document(String verfiy_document) {
            this.verfiy_document = verfiy_document;
        }
    }
}
