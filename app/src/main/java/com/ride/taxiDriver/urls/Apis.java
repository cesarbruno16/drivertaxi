package com.ride.taxiDriver.urls;

public interface Apis {

    String BASE = "http://ridetaxi.com.br/";
    String baseDomain = BASE+"api/";
    String imageDomain = BASE + "";
    String restdomain = BASE + "Apporiotaxi/index.php/";

    String register = baseDomain + "register_driver.php";
    String registerDocs = baseDomain + "register_driver_docs.php";
    String login = baseDomain + "login_driver.php";
    String changePassword = baseDomain + "change_password_driver.php";
    String forgotPassword = baseDomain + "forgot_password_driver.php";
    String editProfile = baseDomain + "edit_profile_driver.php";
    String logout = baseDomain + "logout_driver.php";
    String viewCities = baseDomain + "city.php?";
    String viewCarByCities = baseDomain + "car_by_city.php";
    String viewCarModels = baseDomain + "car_model.php";
    String onlineOffline = baseDomain + "online_offline.php";  // //
    String updateLatLong = baseDomain + "driver_latlong.php";  //
    String ratingDriver = baseDomain + "rating_driver.php";
    String viewRideInfoDriver = baseDomain + "view_ride_info_driver.php";
    String cancelRide = baseDomain + "ride_cancel_driver.php";
    String cancelReason = baseDomain + "cancel_reason_driver.php";
    String acceptRide = baseDomain + "ride_accept.php";
    String rejectRide = baseDomain + "ride_reject.php";
    String arrivedTrip = baseDomain + "ride_arrived.php";
    String beginTrip = baseDomain + "ride_start.php";
    String endTripMeter = baseDomain +"ride_end_meter.php";
    String newRideSync = baseDomain + "new_ride_sync.php";
    String deviceid = baseDomain + "deviceid_driver.php";
    String Callsupport = baseDomain + "call_support.php";
    String update = baseDomain + "update.php";
    String aboutUs = baseDomain + "about.php";
    String tC = baseDomain + "tc.php";
    String viewDoneRide = baseDomain + "" + "view_done_ride_info.php";
    String weeklyEarnings = baseDomain + "driver_account.php?driver_id=";
    String dailyEarnings = baseDomain + "daily_amount.php?driver_id=";
    String week_amount = baseDomain+"week_amount.php?driver_id=";  //    &date=   2017-08-7
    String change_drop_location = baseDomain+"change_drop_location.php?";  //  drop_lat  drop_long  drop_location  app_id (1 for user and 2 for demotaxiappdriver) ride_id
    String heatmap = baseDomain+"heatmap.php?driver_id=";
    String PartialAccept = baseDomain+"ride_later_accept.php";
    String Sos_Request = restdomain+"/Common/SOS_Request";



    String RideSync = restdomain+"/Rental/Ride_Sync";   //  rental_booking_id      app_id= 2 for demotaxiappdriver and 1 for cutomer
    String Rideinfo = restdomain+"/Rental/Ride_Info";
    String AcceptRide = restdomain+"/Rental/Rental_Ride_Accept";
    String RejectRide = restdomain+"/Rental/Rental_Driver_Reject_Ride";
    String Arrived = restdomain+"/Rental/Rental_Driver_Arrive";
    String StartRide = restdomain+"/Rental/Rental_Driver_Start_Ride";
    String EndRide = restdomain+"/Rental/Rental_Driver_End_Ride";
    String Done_Ride_Info = restdomain+"/Rental/Done_Ride_Info"; // rental_booking_id
    String Rating = restdomain+"/Rental/Rating";  // rating_star  rental_booking_id  comment  user_id  driver_id  app_id
    String RideHistory = restdomain+"Driver/Driver_Ride_History";   //  driver_id
    String RideDetails = restdomain+"User/Ride_Details";  //  ride_mode  booking_id
    String RideCancel = restdomain+"Rental/Rental_Driver_Cancel_Ride";  //   rental_booking_id   user_id
    String CustomerSupport = restdomain+"Common/Customer_support";  //  application(1 for user and 2 for demotaxiappdriver)  name  email  phone  query  driver_id  user_id
    String DriverReport = restdomain+"Driver/Driver_Report";
    String RepostIssueDetails = baseDomain+"report_issue_email.php?";


    String DocumentList = baseDomain+"document_list.php?city_id=";
    String Document_Upload = baseDomain+"upload_document.php";
    String googleImage = "https://maps.googleapis.com/maps/api/staticmap?center=";
    String ScheduleAndunacceptedRide = baseDomain+"scheduled_and_unaccepted_rides.php";  //  driver_id
    String CheckRideTime = baseDomain+"check_ride_time.php?ride_id=" ; // &ride_mode=


    String DemoRegister = baseDomain+"demo_register_driver.php";

    //  driver_id=4&name=1&email=12&phone=&query=hello
    String Notifications = restdomain+"Common/Notification"; // application  (1 for user and 2 for demotaxiappdriver)
    String Sos = restdomain+"Common/SOS?";

    String Driver_Active_Rides = restdomain+"Driver/Driver_Active_Ride_History";  // driver_id
    String SEND_OTP = restdomain+ "Common/Send_Otp";

    String FORGOTPASS_OTP = baseDomain + "otp-sent.php";

    String FORGOTPASS_CONFIRMPASS = baseDomain + "forgot_password_driver.php";


    String AppVersions = baseDomain+"app_version.php"; // application_version  application  (1 for user and 2 for driver)   flag (1 for ios and 2 for android)

    String BackGroundAppUpdate= baseDomain+"driver_backgroud_location.php"; // driver_id  current_lat  current_long  current_location  driver_token
}
