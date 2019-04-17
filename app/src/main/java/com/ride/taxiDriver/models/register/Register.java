
package com.ride.taxiDriver.models.register;

public class Register {


    /**
     * result : 1
     * msg : Login Successful
     * details : {"driver_id":"20","company_id":"0","commission":"56","driver_name":"samir","driver_email":"samir@gmail.com","driver_phone":"+569650923089","driver_image":"","driver_password":"123456","driver_token":"fyzRwhC6uQyIPds5","driver_category":"1","total_payment_eraned":"0","company_payment":"0","driver_payment":"","device_id":"fyFqcvsupzU:APA91bHFAPE-Fp4lWHz_NfnJZCmWcZrhPA1FrIfrPYQz6w6VOBPdZD8YWatWbHDoRBNYRFNL7jFvv6kj_wru649_rmZI5S4Eg1RWec5RW6Zy-u1BSdgLW6Y-DtxfHU_6S52mTldhbnYK","flag":"2","rating":"0","car_type_id":"32","car_model_id":"52","car_number":"DDFV","city_id":"56","register_date":"Monday, Nov 6","license":"","license_expire":"","rc":"","rc_expire":"","insurance":"","insurance_expire":"","other_docs":"","driver_bank_name":"yes","driver_account_name":"bank name first here","driver_account_number":"585898098525886","total_card_payment":"0.00","total_cash_payment":"0.00","amount_transfer_pending":"0.00","current_lat":"28.4123461","current_long":"77.0434171","current_location":"----","last_update":"10:30","last_update_date":"Monday, Nov 6, 2017","completed_rides":"0","reject_rides":"0","cancelled_rides":"0","login_logout":"1","busy":"0","online_offline":"2","detail_status":"2","payment_transfer":"0","verification_date":"2017-11-06","verification_status":"1","unique_number":"","driver_signup_date":"2017-11-06","driver_status_image":"http://www.apporiotaxi.com/superadminpanel/images/icons8-Error-80.png","driver_status_message":"Your account will be activated as soon as we review your documents. Please check back.","total_document_need":"3","driver_admin_status":"1","verfiy_document":"3","city_name":"Gurugram","city_latitude":"","city_longitude":"","currency":"$","distance":"Km","city_name_arabic":"","city_name_french":"","city_admin_status":"1","car_type_name":"new demo","car_model_name":"pooja"}
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
         * driver_id : 20
         * company_id : 0
         * commission : 56
         * driver_name : samir
         * driver_email : samir@gmail.com
         * driver_phone : +569650923089
         * driver_image :
         * driver_password : 123456
         * driver_token : fyzRwhC6uQyIPds5
         * driver_category : 1
         * total_payment_eraned : 0
         * company_payment : 0
         * driver_payment :
         * device_id : fyFqcvsupzU:APA91bHFAPE-Fp4lWHz_NfnJZCmWcZrhPA1FrIfrPYQz6w6VOBPdZD8YWatWbHDoRBNYRFNL7jFvv6kj_wru649_rmZI5S4Eg1RWec5RW6Zy-u1BSdgLW6Y-DtxfHU_6S52mTldhbnYK
         * flag : 2
         * rating : 0
         * car_type_id : 32
         * car_model_id : 52
         * car_number : DDFV
         * city_id : 56
         * register_date : Monday, Nov 6
         * license :
         * license_expire :
         * rc :
         * rc_expire :
         * insurance :
         * insurance_expire :
         * other_docs :
         * driver_bank_name : yes
         * driver_account_name : bank name first here
         * driver_account_number : 585898098525886
         * total_card_payment : 0.00
         * total_cash_payment : 0.00
         * amount_transfer_pending : 0.00
         * current_lat : 28.4123461
         * current_long : 77.0434171
         * current_location : ----
         * last_update : 10:30
         * last_update_date : Monday, Nov 6, 2017
         * completed_rides : 0
         * reject_rides : 0
         * cancelled_rides : 0
         * login_logout : 1
         * busy : 0
         * online_offline : 2
         * detail_status : 2
         * payment_transfer : 0
         * verification_date : 2017-11-06
         * verification_status : 1
         * unique_number :
         * driver_signup_date : 2017-11-06
         * driver_status_image : http://www.apporiotaxi.com/superadminpanel/images/icons8-Error-80.png
         * driver_status_message : Your account will be activated as soon as we review your documents. Please check back.
         * total_document_need : 3
         * driver_admin_status : 1
         * verfiy_document : 3
         * city_name : Gurugram
         * city_latitude :
         * city_longitude :
         * currency : $
         * distance : Km
         * city_name_arabic :
         * city_name_french :
         * city_admin_status : 1
         * car_type_name : new demo
         * car_model_name : pooja
         */

        private String driver_id;
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
        private String device_id;
        private String flag;
        private String rating;
        private String car_type_id;
        private String car_model_id;
        private String car_number;
        private String city_id;
        private String register_date;
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
        private String login_logout;
        private String busy;
        private String online_offline;
        private String detail_status;
        private String payment_transfer;
        private String verification_date;
        private String verification_status;
        private String unique_number;
        private String driver_signup_date;
        private String driver_status_image;
        private String driver_status_message;
        private String total_document_need;
        private String driver_admin_status;
        private String verfiy_document;
        private String city_name;
        private String city_latitude;
        private String city_longitude;
        private String currency;
        private String distance;
        private String city_name_arabic;
        private String city_name_french;
        private String city_admin_status;
        private String car_type_name;
        private String car_model_name;

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
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

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getCar_type_id() {
            return car_type_id;
        }

        public void setCar_type_id(String car_type_id) {
            this.car_type_id = car_type_id;
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

        public String getRegister_date() {
            return register_date;
        }

        public void setRegister_date(String register_date) {
            this.register_date = register_date;
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

        public String getLogin_logout() {
            return login_logout;
        }

        public void setLogin_logout(String login_logout) {
            this.login_logout = login_logout;
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

        public String getUnique_number() {
            return unique_number;
        }

        public void setUnique_number(String unique_number) {
            this.unique_number = unique_number;
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

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getCity_latitude() {
            return city_latitude;
        }

        public void setCity_latitude(String city_latitude) {
            this.city_latitude = city_latitude;
        }

        public String getCity_longitude() {
            return city_longitude;
        }

        public void setCity_longitude(String city_longitude) {
            this.city_longitude = city_longitude;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getCity_name_arabic() {
            return city_name_arabic;
        }

        public void setCity_name_arabic(String city_name_arabic) {
            this.city_name_arabic = city_name_arabic;
        }

        public String getCity_name_french() {
            return city_name_french;
        }

        public void setCity_name_french(String city_name_french) {
            this.city_name_french = city_name_french;
        }

        public String getCity_admin_status() {
            return city_admin_status;
        }

        public void setCity_admin_status(String city_admin_status) {
            this.city_admin_status = city_admin_status;
        }

        public String getCar_type_name() {
            return car_type_name;
        }

        public void setCar_type_name(String car_type_name) {
            this.car_type_name = car_type_name;
        }

        public String getCar_model_name() {
            return car_model_name;
        }

        public void setCar_model_name(String car_model_name) {
            this.car_model_name = car_model_name;
        }
    }
}
