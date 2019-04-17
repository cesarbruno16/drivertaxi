package com.ride.taxiDriver.models;

import java.util.List;

/**
 * Created by samirgoel3@gmail.com on 9/7/2017.
 */

public class ActiveRidesResponse {


    /**
     * result : 1
     * message : Active Ride History
     * details : [{"user_ride_id":"211","ride_mode":"1","user_id":"15","driver_id":"17","booking_id":"138","Normal_Ride":{"ride_id":"138","user_id":"15","coupon_code":"","pickup_lat":"28.4123073","pickup_long":"77.0433662","pickup_location":"322B, Sohna Rd, Sector 49, Gurugram, Haryana 122018, India","drop_lat":"28.4123073","drop_long":"77.0433662","drop_location":"322B, Sohna Rd, Sector 49, Gurugram, Haryana 122018, India","ride_date":"Wednesday, Nov 22","ride_time":"14:01:33","last_time_stamp":"02:01:50 PM","ride_image":"https:maps.googleapis.com/maps/api/staticmap?center=&zoom=12&size=200x200&maptype=roadmap&markers=color:green|label:S|28.412282778969523,77.04341970384121&markers=color:red|label:D|28.4592693,77.0724192&key=AIzaSyAIFe17P91Mfez3T6cqk7hfDSyvMO812Z4","later_date":"","later_time":"","driver_id":"17","car_type_id":"32","ride_type":"1","pem_file":"1","ride_status":"7","driver_rating":"0","user_rating":"1","payment_status":"1","reason_id":"0","payment_option_id":"1","card_id":"","ride_platform":"1","ride_admin_status":"1","date":"2017-11-22","car_type_name":"new demo","car_name_arabic":"","car_type_name_french":"","car_type_image":"uploads/car/car_32.png","cartype_big_image":"webstatic/img/fleet-image/prime-suv.png","cartype_image_size":"101478","car_longdescription":"The all too familiar auto ride, minus the hassle of waiting and haggling for price. A convenient way to travel everyday.","car_longdescription_arabic":"الحافلات لدينا نظيفة، أس كامل، الستائر، ومشغل دي في دي. السائقين لدينا هي المهنية ومهذبا.","car_description":"New demo ","car_description_arabic":"الحصول على سيارة أجرة الحق على عتبة داركم","ride_mode":"1","car_admin_status":"1","user_type":"1","user_name":"nope .","user_email":"er@g.com","user_phone":"+2346868285886","user_password":"123456","user_image":"","device_id":"","flag":"0","wallet_money":"0","register_date":"Wednesday, Nov 22","referral_code":"","free_rides":"0","referral_code_send":"0","phone_verified":"0","email_verified":"0","password_created":"0","facebook_id":"","facebook_mail":"","facebook_image":"","facebook_firstname":"","facebook_lastname":"","google_id":"","google_name":"","google_mail":"","google_image":"","google_token":"","facebook_token":"","token_created":"0","login_logout":"0","rating":"0","user_delete":"0","unique_number":"","user_signup_type":"1","user_signup_date":"2017-11-22","status":"1","done_ride_id":"78","total_amount":"57100"},"Rental_Ride":{"rental_booking_id":"","user_id":"","rentcard_id":"","car_type_id":"","booking_type":"","driver_id":"","pickup_lat":"","pickup_long":"","pickup_location":"","start_meter_reading":"","start_meter_reading_image":"","end_meter_reading":"","end_meter_reading_image":"","booking_date":"","booking_time":"","user_booking_date_time":"","last_update_time":"","booking_status":"","booking_admin_status":"","car_type_name":"","car_name_arabic":"","car_type_name_french":"","car_type_image":"","ride_mode":"","car_admin_status":"","user_name":"","user_email":"","user_phone":"","user_password":"","user_image":"","register_date":"","device_id":"","flag":"","referral_code":"","free_rides":"","referral_code_send":"","phone_verified":"","email_verified":"","password_created":"","facebook_id":"","facebook_mail":"","facebook_image":"","facebook_firstname":"","facebook_lastname":"","google_id":"","google_name":"","google_mail":"","google_image":"","google_token":"","facebook_token":"","token_created":"","login_logout":"","rating":"","status":"","end_lat":"","end_long":"","end_location":"","final_bill_amount":""}}]
     */

    private String result;
    private String message;
    private List<DetailsBean> details;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
         * user_ride_id : 211
         * ride_mode : 1
         * user_id : 15
         * driver_id : 17
         * booking_id : 138
         * Normal_Ride : {"ride_id":"138","user_id":"15","coupon_code":"","pickup_lat":"28.4123073","pickup_long":"77.0433662","pickup_location":"322B, Sohna Rd, Sector 49, Gurugram, Haryana 122018, India","drop_lat":"28.4123073","drop_long":"77.0433662","drop_location":"322B, Sohna Rd, Sector 49, Gurugram, Haryana 122018, India","ride_date":"Wednesday, Nov 22","ride_time":"14:01:33","last_time_stamp":"02:01:50 PM","ride_image":"https:maps.googleapis.com/maps/api/staticmap?center=&zoom=12&size=200x200&maptype=roadmap&markers=color:green|label:S|28.412282778969523,77.04341970384121&markers=color:red|label:D|28.4592693,77.0724192&key=AIzaSyAIFe17P91Mfez3T6cqk7hfDSyvMO812Z4","later_date":"","later_time":"","driver_id":"17","car_type_id":"32","ride_type":"1","pem_file":"1","ride_status":"7","driver_rating":"0","user_rating":"1","payment_status":"1","reason_id":"0","payment_option_id":"1","card_id":"","ride_platform":"1","ride_admin_status":"1","date":"2017-11-22","car_type_name":"new demo","car_name_arabic":"","car_type_name_french":"","car_type_image":"uploads/car/car_32.png","cartype_big_image":"webstatic/img/fleet-image/prime-suv.png","cartype_image_size":"101478","car_longdescription":"The all too familiar auto ride, minus the hassle of waiting and haggling for price. A convenient way to travel everyday.","car_longdescription_arabic":"الحافلات لدينا نظيفة، أس كامل، الستائر، ومشغل دي في دي. السائقين لدينا هي المهنية ومهذبا.","car_description":"New demo ","car_description_arabic":"الحصول على سيارة أجرة الحق على عتبة داركم","ride_mode":"1","car_admin_status":"1","user_type":"1","user_name":"nope .","user_email":"er@g.com","user_phone":"+2346868285886","user_password":"123456","user_image":"","device_id":"","flag":"0","wallet_money":"0","register_date":"Wednesday, Nov 22","referral_code":"","free_rides":"0","referral_code_send":"0","phone_verified":"0","email_verified":"0","password_created":"0","facebook_id":"","facebook_mail":"","facebook_image":"","facebook_firstname":"","facebook_lastname":"","google_id":"","google_name":"","google_mail":"","google_image":"","google_token":"","facebook_token":"","token_created":"0","login_logout":"0","rating":"0","user_delete":"0","unique_number":"","user_signup_type":"1","user_signup_date":"2017-11-22","status":"1","done_ride_id":"78","total_amount":"57100"}
         * Rental_Ride : {"rental_booking_id":"","user_id":"","rentcard_id":"","car_type_id":"","booking_type":"","driver_id":"","pickup_lat":"","pickup_long":"","pickup_location":"","start_meter_reading":"","start_meter_reading_image":"","end_meter_reading":"","end_meter_reading_image":"","booking_date":"","booking_time":"","user_booking_date_time":"","last_update_time":"","booking_status":"","booking_admin_status":"","car_type_name":"","car_name_arabic":"","car_type_name_french":"","car_type_image":"","ride_mode":"","car_admin_status":"","user_name":"","user_email":"","user_phone":"","user_password":"","user_image":"","register_date":"","device_id":"","flag":"","referral_code":"","free_rides":"","referral_code_send":"","phone_verified":"","email_verified":"","password_created":"","facebook_id":"","facebook_mail":"","facebook_image":"","facebook_firstname":"","facebook_lastname":"","google_id":"","google_name":"","google_mail":"","google_image":"","google_token":"","facebook_token":"","token_created":"","login_logout":"","rating":"","status":"","end_lat":"","end_long":"","end_location":"","final_bill_amount":""}
         */

        private String user_ride_id;
        private String ride_mode;
        private String user_id;
        private String driver_id;
        private String booking_id;
        private NormalRideBean Normal_Ride;
        private RentalRideBean Rental_Ride;

        public String getUser_ride_id() {
            return user_ride_id;
        }

        public void setUser_ride_id(String user_ride_id) {
            this.user_ride_id = user_ride_id;
        }

        public String getRide_mode() {
            return ride_mode;
        }

        public void setRide_mode(String ride_mode) {
            this.ride_mode = ride_mode;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
        }

        public String getBooking_id() {
            return booking_id;
        }

        public void setBooking_id(String booking_id) {
            this.booking_id = booking_id;
        }

        public NormalRideBean getNormal_Ride() {
            return Normal_Ride;
        }

        public void setNormal_Ride(NormalRideBean Normal_Ride) {
            this.Normal_Ride = Normal_Ride;
        }

        public RentalRideBean getRental_Ride() {
            return Rental_Ride;
        }

        public void setRental_Ride(RentalRideBean Rental_Ride) {
            this.Rental_Ride = Rental_Ride;
        }

        public static class NormalRideBean {
            /**
             * ride_id : 138
             * user_id : 15
             * coupon_code :
             * pickup_lat : 28.4123073
             * pickup_long : 77.0433662
             * pickup_location : 322B, Sohna Rd, Sector 49, Gurugram, Haryana 122018, India
             * drop_lat : 28.4123073
             * drop_long : 77.0433662
             * drop_location : 322B, Sohna Rd, Sector 49, Gurugram, Haryana 122018, India
             * ride_date : Wednesday, Nov 22
             * ride_time : 14:01:33
             * last_time_stamp : 02:01:50 PM
             * ride_image : https:maps.googleapis.com/maps/api/staticmap?center=&zoom=12&size=200x200&maptype=roadmap&markers=color:green|label:S|28.412282778969523,77.04341970384121&markers=color:red|label:D|28.4592693,77.0724192&key=AIzaSyAIFe17P91Mfez3T6cqk7hfDSyvMO812Z4
             * later_date :
             * later_time :
             * driver_id : 17
             * car_type_id : 32
             * ride_type : 1
             * pem_file : 1
             * ride_status : 7
             * driver_rating : 0
             * user_rating : 1
             * payment_status : 1
             * reason_id : 0
             * payment_option_id : 1
             * card_id :
             * ride_platform : 1
             * ride_admin_status : 1
             * date : 2017-11-22
             * car_type_name : new demo
             * car_name_arabic :
             * car_type_name_french :
             * car_type_image : uploads/car/car_32.png
             * cartype_big_image : webstatic/img/fleet-image/prime-suv.png
             * cartype_image_size : 101478
             * car_longdescription : The all too familiar auto ride, minus the hassle of waiting and haggling for price. A convenient way to travel everyday.
             * car_longdescription_arabic : الحافلات لدينا نظيفة، أس كامل، الستائر، ومشغل دي في دي. السائقين لدينا هي المهنية ومهذبا.
             * car_description : New demo
             * car_description_arabic : الحصول على سيارة أجرة الحق على عتبة داركم
             * ride_mode : 1
             * car_admin_status : 1
             * user_type : 1
             * user_name : nope .
             * user_email : er@g.com
             * user_phone : +2346868285886
             * user_password : 123456
             * user_image :
             * device_id :
             * flag : 0
             * wallet_money : 0
             * register_date : Wednesday, Nov 22
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
             * rating : 0
             * user_delete : 0
             * unique_number :
             * user_signup_type : 1
             * user_signup_date : 2017-11-22
             * status : 1
             * done_ride_id : 78
             * total_amount : 57100
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
            private String done_ride_id;
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

            public String getDone_ride_id() {
                return done_ride_id;
            }

            public void setDone_ride_id(String done_ride_id) {
                this.done_ride_id = done_ride_id;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }
        }

        public static class RentalRideBean {
            /**
             * rental_booking_id :
             * user_id :
             * rentcard_id :
             * car_type_id :
             * booking_type :
             * driver_id :
             * pickup_lat :
             * pickup_long :
             * pickup_location :
             * start_meter_reading :
             * start_meter_reading_image :
             * end_meter_reading :
             * end_meter_reading_image :
             * booking_date :
             * booking_time :
             * user_booking_date_time :
             * last_update_time :
             * booking_status :
             * booking_admin_status :
             * car_type_name :
             * car_name_arabic :
             * car_type_name_french :
             * car_type_image :
             * ride_mode :
             * car_admin_status :
             * user_name :
             * user_email :
             * user_phone :
             * user_password :
             * user_image :
             * register_date :
             * device_id :
             * flag :
             * referral_code :
             * free_rides :
             * referral_code_send :
             * phone_verified :
             * email_verified :
             * password_created :
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
             * token_created :
             * login_logout :
             * rating :
             * status :
             * end_lat :
             * end_long :
             * end_location :
             * final_bill_amount :
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
            private String final_bill_amount;

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

            public String getFinal_bill_amount() {
                return final_bill_amount;
            }

            public void setFinal_bill_amount(String final_bill_amount) {
                this.final_bill_amount = final_bill_amount;
            }
        }
    }
}