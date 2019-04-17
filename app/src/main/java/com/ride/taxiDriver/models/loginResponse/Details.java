
package com.ride.taxiDriver.models.loginResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("commission")
    @Expose
    private String commission;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("driver_email")
    @Expose
    private String driverEmail;
    @SerializedName("driver_phone")
    @Expose
    private String driverPhone;
    @SerializedName("driver_image")
    @Expose
    private String driverImage;
    @SerializedName("driver_password")
    @Expose
    private String driverPassword;
    @SerializedName("driver_token")
    @Expose
    private String driverToken;
    @SerializedName("driver_category")
    @Expose
    private String driverCategory;
    @SerializedName("total_payment_eraned")
    @Expose
    private String totalPaymentEraned;
    @SerializedName("company_payment")
    @Expose
    private String companyPayment;
    @SerializedName("driver_payment")
    @Expose
    private String driverPayment;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("car_type_id")
    @Expose
    private String carTypeId;
    @SerializedName("car_model_id")
    @Expose
    private String carModelId;
    @SerializedName("car_number")
    @Expose
    private String carNumber;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("register_date")
    @Expose
    private String registerDate;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("license_expire")
    @Expose
    private String licenseExpire;
    @SerializedName("rc")
    @Expose
    private String rc;
    @SerializedName("rc_expire")
    @Expose
    private String rcExpire;
    @SerializedName("insurance")
    @Expose
    private String insurance;
    @SerializedName("insurance_expire")
    @Expose
    private String insuranceExpire;
    @SerializedName("other_docs")
    @Expose
    private String otherDocs;
    @SerializedName("driver_bank_name")
    @Expose
    private String driverBankName;
    @SerializedName("driver_account_name")
    @Expose
    private String driverAccountName;
    @SerializedName("driver_account_number")
    @Expose
    private String driverAccountNumber;
    @SerializedName("total_card_payment")
    @Expose
    private String totalCardPayment;
    @SerializedName("total_cash_payment")
    @Expose
    private String totalCashPayment;
    @SerializedName("amount_transfer_pending")
    @Expose
    private String amountTransferPending;
    @SerializedName("current_lat")
    @Expose
    private String currentLat;
    @SerializedName("current_long")
    @Expose
    private String currentLong;
    @SerializedName("current_location")
    @Expose
    private String currentLocation;
    @SerializedName("last_update")
    @Expose
    private String lastUpdate;
    @SerializedName("last_update_date")
    @Expose
    private String lastUpdateDate;
    @SerializedName("completed_rides")
    @Expose
    private String completedRides;
    @SerializedName("reject_rides")
    @Expose
    private String rejectRides;
    @SerializedName("cancelled_rides")
    @Expose
    private String cancelledRides;
    @SerializedName("login_logout")
    @Expose
    private String loginLogout;
    @SerializedName("busy")
    @Expose
    private String busy;
    @SerializedName("online_offline")
    @Expose
    private String onlineOffline;
    @SerializedName("detail_status")
    @Expose
    private String detailStatus;
    @SerializedName("payment_transfer")
    @Expose
    private String paymentTransfer;
    @SerializedName("verification_date")
    @Expose
    private String verificationDate;
    @SerializedName("verification_status")
    @Expose
    private String verificationStatus;
    @SerializedName("unique_number")
    @Expose
    private String uniqueNumber;
    @SerializedName("driver_signup_date")
    @Expose
    private String driverSignupDate;
    @SerializedName("driver_status_image")
    @Expose
    private String driverStatusImage;
    @SerializedName("driver_status_message")
    @Expose
    private String driverStatusMessage;
    @SerializedName("total_document_need")
    @Expose
    private String totalDocumentNeed;
    @SerializedName("driver_admin_status")
    @Expose
    private String driverAdminStatus;
    @SerializedName("verfiy_document")
    @Expose
    private String verfiyDocument;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("city_latitude")
    @Expose
    private String cityLatitude;
    @SerializedName("city_longitude")
    @Expose
    private String cityLongitude;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("city_name_arabic")
    @Expose
    private String cityNameArabic;
    @SerializedName("city_name_french")
    @Expose
    private String cityNameFrench;
    @SerializedName("city_admin_status")
    @Expose
    private String cityAdminStatus;
    @SerializedName("car_type_name")
    @Expose
    private String carTypeName;
    @SerializedName("car_model_name")
    @Expose
    private String carModelName;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverImage() {
        return driverImage;
    }

    public void setDriverImage(String driverImage) {
        this.driverImage = driverImage;
    }

    public String getDriverPassword() {
        return driverPassword;
    }

    public void setDriverPassword(String driverPassword) {
        this.driverPassword = driverPassword;
    }

    public String getDriverToken() {
        return driverToken;
    }

    public void setDriverToken(String driverToken) {
        this.driverToken = driverToken;
    }

    public String getDriverCategory() {
        return driverCategory;
    }

    public void setDriverCategory(String driverCategory) {
        this.driverCategory = driverCategory;
    }

    public String getTotalPaymentEraned() {
        return totalPaymentEraned;
    }

    public void setTotalPaymentEraned(String totalPaymentEraned) {
        this.totalPaymentEraned = totalPaymentEraned;
    }

    public String getCompanyPayment() {
        return companyPayment;
    }

    public void setCompanyPayment(String companyPayment) {
        this.companyPayment = companyPayment;
    }

    public String getDriverPayment() {
        return driverPayment;
    }

    public void setDriverPayment(String driverPayment) {
        this.driverPayment = driverPayment;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public String getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(String carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(String carModelId) {
        this.carModelId = carModelId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseExpire() {
        return licenseExpire;
    }

    public void setLicenseExpire(String licenseExpire) {
        this.licenseExpire = licenseExpire;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getRcExpire() {
        return rcExpire;
    }

    public void setRcExpire(String rcExpire) {
        this.rcExpire = rcExpire;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getInsuranceExpire() {
        return insuranceExpire;
    }

    public void setInsuranceExpire(String insuranceExpire) {
        this.insuranceExpire = insuranceExpire;
    }

    public String getOtherDocs() {
        return otherDocs;
    }

    public void setOtherDocs(String otherDocs) {
        this.otherDocs = otherDocs;
    }

    public String getDriverBankName() {
        return driverBankName;
    }

    public void setDriverBankName(String driverBankName) {
        this.driverBankName = driverBankName;
    }

    public String getDriverAccountName() {
        return driverAccountName;
    }

    public void setDriverAccountName(String driverAccountName) {
        this.driverAccountName = driverAccountName;
    }

    public String getDriverAccountNumber() {
        return driverAccountNumber;
    }

    public void setDriverAccountNumber(String driverAccountNumber) {
        this.driverAccountNumber = driverAccountNumber;
    }

    public String getTotalCardPayment() {
        return totalCardPayment;
    }

    public void setTotalCardPayment(String totalCardPayment) {
        this.totalCardPayment = totalCardPayment;
    }

    public String getTotalCashPayment() {
        return totalCashPayment;
    }

    public void setTotalCashPayment(String totalCashPayment) {
        this.totalCashPayment = totalCashPayment;
    }

    public String getAmountTransferPending() {
        return amountTransferPending;
    }

    public void setAmountTransferPending(String amountTransferPending) {
        this.amountTransferPending = amountTransferPending;
    }

    public String getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(String currentLat) {
        this.currentLat = currentLat;
    }

    public String getCurrentLong() {
        return currentLong;
    }

    public void setCurrentLong(String currentLong) {
        this.currentLong = currentLong;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCompletedRides() {
        return completedRides;
    }

    public void setCompletedRides(String completedRides) {
        this.completedRides = completedRides;
    }

    public String getRejectRides() {
        return rejectRides;
    }

    public void setRejectRides(String rejectRides) {
        this.rejectRides = rejectRides;
    }

    public String getCancelledRides() {
        return cancelledRides;
    }

    public void setCancelledRides(String cancelledRides) {
        this.cancelledRides = cancelledRides;
    }

    public String getLoginLogout() {
        return loginLogout;
    }

    public void setLoginLogout(String loginLogout) {
        this.loginLogout = loginLogout;
    }

    public String getBusy() {
        return busy;
    }

    public void setBusy(String busy) {
        this.busy = busy;
    }

    public String getOnlineOffline() {
        return onlineOffline;
    }

    public void setOnlineOffline(String onlineOffline) {
        this.onlineOffline = onlineOffline;
    }

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }

    public String getPaymentTransfer() {
        return paymentTransfer;
    }

    public void setPaymentTransfer(String paymentTransfer) {
        this.paymentTransfer = paymentTransfer;
    }

    public String getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(String verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getDriverSignupDate() {
        return driverSignupDate;
    }

    public void setDriverSignupDate(String driverSignupDate) {
        this.driverSignupDate = driverSignupDate;
    }

    public String getDriverStatusImage() {
        return driverStatusImage;
    }

    public void setDriverStatusImage(String driverStatusImage) {
        this.driverStatusImage = driverStatusImage;
    }

    public String getDriverStatusMessage() {
        return driverStatusMessage;
    }

    public void setDriverStatusMessage(String driverStatusMessage) {
        this.driverStatusMessage = driverStatusMessage;
    }

    public String getTotalDocumentNeed() {
        return totalDocumentNeed;
    }

    public void setTotalDocumentNeed(String totalDocumentNeed) {
        this.totalDocumentNeed = totalDocumentNeed;
    }

    public String getDriverAdminStatus() {
        return driverAdminStatus;
    }

    public void setDriverAdminStatus(String driverAdminStatus) {
        this.driverAdminStatus = driverAdminStatus;
    }

    public String getVerfiyDocument() {
        return verfiyDocument;
    }

    public void setVerfiyDocument(String verfiyDocument) {
        this.verfiyDocument = verfiyDocument;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityLatitude() {
        return cityLatitude;
    }

    public void setCityLatitude(String cityLatitude) {
        this.cityLatitude = cityLatitude;
    }

    public String getCityLongitude() {
        return cityLongitude;
    }

    public void setCityLongitude(String cityLongitude) {
        this.cityLongitude = cityLongitude;
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

    public String getCityNameArabic() {
        return cityNameArabic;
    }

    public void setCityNameArabic(String cityNameArabic) {
        this.cityNameArabic = cityNameArabic;
    }

    public String getCityNameFrench() {
        return cityNameFrench;
    }

    public void setCityNameFrench(String cityNameFrench) {
        this.cityNameFrench = cityNameFrench;
    }

    public String getCityAdminStatus() {
        return cityAdminStatus;
    }

    public void setCityAdminStatus(String cityAdminStatus) {
        this.cityAdminStatus = cityAdminStatus;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

}
