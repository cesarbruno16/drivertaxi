package com.ride.taxiDriver;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import com.ride.taxiDriver.others.ChatModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by samirgoel3@gmail.com on 4/1/2017.
 */

public class Config {



    public static String DriverReference = "Drivers_A";
    public static String DriverEventReference = "driver_event";
    public static String Devicetype = "2";
    public static String GeoFireReference = "geofire";
    public static String ActiveRidesRefrence = "Activeride";
    public static String RideTableReference = "RideTable";
    public static String ChatReferencetable = "Chat";
    public static boolean ReceiverPassengerActivity = false;
    public static boolean RentalReceivepassengerActivity = false ;

    public static List<ChatModel> cities = new ArrayList<ChatModel>(){{
        add(new ChatModel("NOCHAT" , "NO CHAT" , ""+System.currentTimeMillis()));
    }};

    public static float bearingfactor = 0 ;
    public static String Null_Address_Message = "Out Of Network";

    public static int MainScrenZoon = 15 ;
    public static long LocationUpdateTimeinterval = 2000 ;


    public interface ApiKeys{
        String KEY_ARRIVED = "KEY_ARRIVED";
        String KEY_BEGIN_TRIP = "KEY_BEGIN_TRIP";
        String KEY_END_TRIP = "KEY_END_TRIP";
        String KEY_CANCEL_TRIP = "KEY_CANCEL_TRIP";
        String KEY_CANCEL_REASONS = "KEY_CANCEL_REASONS";
        String KEY_View_cities = "KEY_View_cities";
        String KEY_View_car_by_city = "KEY_View_car_by_city";
        String KEY_View_car_Model = "KEY_View_car_Model";
        String KEY_Driver_register = "KEY_Driver_register";
        String KEY_View_done_ride_info = "KEY_View_done_ride_info";
        String KEY_Documents_Submit = "KEY_Documents_Submit";
        String DRIVER_ACTIVE_RIDES = "DRIVER_ACTIVE_RIDES";
        String SOS_REQUEST_NOTIFIER = "Sos_Request";


        String KEY_ONLINE_OFFLINE = "KEY_ONLINE_OFFLINE";
        String KEY_UPDATE_DRIVER_LAT_LONG = "KEY_UPDATE_DRIVER_LAT_LONG";
        String KEY_UPDATE_DEVICE_ID = "KEY_UPDATE_DEVICE_ID";
        String KEY_CALL_SUPPORT = "KEY_CALL_SUPPORT";
        String KEY_RATING_DRIVER = "KEY_RATING_DRIVER";
        String KEY_APP_CONFIG = "KEY_APP_CONFIG";
        String KEY_NEW_RIDE_SYNC = "KEY_NEW_RIDE_SYNC";
        String KEY_VIEW_RIDE_INFO_DRIVER = "KEY_VIEW_RIDE_INFO_DRIVER";

        String KEY_ACEPT_RIDE = "KEY_ACEPT_RIDE";
        String KEY_REJECT_RIDE = "KEY_REJECT_RIDE";
        String KEY_RIDES_HISTORY = "KEY_RIDES_HISTORY";
        String KEY_ABOUT_US = "KEY_ABOUT_US";
        String KEY_TERMS_AND_CONDITION = "KEY_TERMS_AND_CONDITION";
        String KEY_CUSTOMER_SUPPORT = "KEY_CUSTOMER_SUPPORT";
        String KEY_SOS = "KEY_SOS";
        String KEY_VERIFY_OTP = "KEY_VERIFY_OTP";

        /////// account module keys
        String KEY_NEW_LOGIN = "KEY_NEW_LOGIN";
        String KEY_SIGN_UP_STEP_ONE = "KEY_SIGN_UP_STEP_ONE";
        String KEY_SIGNUP_TWO = "KEY_SIGNUP_TWO";
        String KEY_SIGNUP_THREE = "KEY_SIGNUP_THREE";
        String KEY_GET_ALL = "KEY_GET_ALL";

        String KEY_DOCUMENT_LIST = "KEY_DOCUMENT_LIST";
        String KEY_DOCUMENT_UPLOAD = "KEY_DOCUMENT_UPLOAD";
        String KEY_GET_SIGNUP_STAP_FOUR = "KEY_GET_SIGNUP_STAP_FOUR";
        String KEY_DOCUMENTS_UPLOAD_LIST = "KEY_DOCUMENTS_UPLOAD_LIST";


        //////////////////   rest apis
        String KEY_REST_RIDE_SYNC = "KEY_REST_RIDE_SYNC";
        String KEY_REST_RIDE_INFO = "KEY_REST_RIDE_INFO";
        String KEY_RESt_ACCEPT_API = "KEY_RESt_ACCEPT_API";
        String KEY_REST_REJECT_RIDE = "KEY_REST_REJECT_RIDE";
        String KEY_REST_RIDE_ARRIVED = "KEY_REST_RIDE_ARRIVED";
        String KEY_REST_START_RIDE = "KEY_REST_START_RIDE";
        String KEY_REST_END_RIDE = "KEY_REST_END_RIDE";
        String KEY_REST_DONE_RIDE_INFO = "KEY_REST_DONE_RIDE_INFO";
        String KEY_REST_RATING = "KEY_REST_RATING";
        String KEY_REST_RIDE_HISTORY = "KEY_REST_RIDE_HISTORY";
        String KEY_REST_RIDE_DETAILS = "KEY_REST_RIDE_DETAILS";
        String KEY_REST_CANCEl_RIDE = "KEY_REST_CANCEl_RIDE";

        String KEY_WEEKLY_EARNING = "KEY_WEEKLY_EARNING";
        String KEY_DAILY_EARNING = "KEY_DAILY_EARNING";
        String KEY_DAILY_EARNING_RENTAL = "KEY_DAILY_EARNING_RENTAL";
        String KEY_CHANGE_DESTINATION  = "KEY_CHANGE_DESTINATION";
        String KEY_REST_NOTIFICATIONS = "KEY_REST_NOTIFICATIONS";

        String KEY_FORGOTPASS_OTP = "KEY_FORGOTPASS_OTP";
        String KEY_FORGOTPASS_CONFIRMPASS = "KEY_FORGOTPASS_CONFIRMPASS";
        String APP_VERSIONS= "APP_VERSIONS";
        String PARTIAL_ACCEPT = "PARTIAL_ACCEPT";
        String KEY_SCHEDULE_AND_UPDATED  ="KEY_SCHEDULE_AND_UPDATED";
        String CHECK_RIDE_TIME = "CHECK_RIDE_TIME";
        String KEY_UPDATE_DRIVER_LAT_LONG_BACKGROUND = "KEY_UPDATE_DRIVER_LAT_LONG_BACKGROUND";
        String KEY_REPORT_ISSUE = "KEY_REPORT_ISSUE";

        String LOGOUT = "LOGOUT";
        String KEY_DEMO_USER = "KEY_DEMO_USER";
    }


    public interface IntentKeys {
      String DOCUMENT_ID = "document_id";
        String RIDE_ID= "ride_id";
    }


//////////////////////////// config attributes

    public static int DistanceGap_tail = 100 ; //


    public static int getScreenWidth (Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return  width ;
    }


    public static int getScreenheight (Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        return  height ;
    }





    public interface Status{
        String VAL_1 = "1";  // ride booked by user
        String NORMAL_CANCEL_BY_USER = "2";  //  ride cancelled by user
        String NORMAL_ACCEPTED = "3";  // accepted by demotaxiappdriver
        String VAL_4 = "4";  // Ride cancelled by demotaxiappdriver and trying to alloting to other
        String NORMAL_ARRIVED = "5";  // demotaxiappdriver arrived on door
        String NORMAL_STARTED = "6";  //  Ride started
        String NORMAL_RIDE_END = "7";  ///  ride ended by demotaxiappdriver
        String NORMAL_LATER = "8";  // when user booked ride for later
        String NORMAL_CANCEL_BY_DRIVER = "9";  // ride cancelled by demotaxiappdriver
        String NORMAL_RIDE_CANCEl_BY_ADMIN = "17";  // normal ride cancel by admin

        // for rental approach
        String RENTAL_BOOKED = "10";  // ride booked via user
        String RENTAL_ACCEPTED = "11";  // rental booking acepted by demotaxiappdriver
        String RENTAL_ARRIVED = "12";  // rental demotaxiappdriver arrived
        String RENTAl_RIDE_STARTED = "13";  // rental ride started by demotaxiappdriver
        String RENTAL_RIDE_REJECTED = "14";  // rental ride reject by demotaxiappdriver
        String RENTAL_RIDE_CANCEL_BY_USER = "15";  // rental ride cancelled by user
        String RENTAL_RIDE_END = "16";  // rental ride end by demotaxiappdriver
        String RENTAL_RIDE_CANCEl_BY_ADMIN = "19";  // rental ride cancel by admin
        String RENTAL_RIDE_CANCELLED_BY_DRIVER = "18";  // rental ride end by demotaxiappdriver
        String PARTIAL_ACCEPTED = "22";
        String RIDE_LATER_BOOKING= "52";
        String RIDE_LATER_REASSIGNED = "54";
    }
    public static String getStatustext(String val ){
        if(val.equals(""+Status.VAL_1)){
            return  "Booked";
        } if(val.equals(""+Status.NORMAL_CANCEL_BY_USER)){
            return  "Cancelled by user";
        } if(val.equals(""+Status.NORMAL_ACCEPTED)){
            return  "Accepted";
        } if(val.equals(""+Status.VAL_4)){
            return  "Rejected";
        } if(val.equals(""+Status.NORMAL_ARRIVED)){
            return  "Arrived";
        } if(val.equals(""+Status.NORMAL_STARTED)){
            return  "Riding Now";
        } if(val.equals(""+Status.NORMAL_RIDE_END)){
            return "Completed";
        } if(val.equals(""+Status.NORMAL_LATER)){
            return  "Later Request";
        } if(val.equals(""+Status.NORMAL_CANCEL_BY_DRIVER)){
            return  "Cancelled by You";
        }if(val.equals(""+Status.RENTAL_BOOKED)){
            return  "Rental Booking";
        }if(val.equals(""+Status.RENTAL_ARRIVED)){
            return  "Arrived";
        }if(val.equals(""+Status.RENTAl_RIDE_STARTED)){
            return  "Riding Now";
        }if(val.equals(""+Status.RENTAL_ACCEPTED)){
            return  "Accepted";
        }if(val.equals(""+Status.RENTAL_RIDE_END)){
            return  "Completed";
        }if(val.equals(""+Status.RENTAL_RIDE_REJECTED)){
            return  "Rejected By You";
        }if(val.equals(""+Status.RENTAL_RIDE_CANCEL_BY_USER)){
            return  "User Cancelled";
        }if(val.equals(""+Status.RENTAL_RIDE_CANCELLED_BY_DRIVER)){
            return  "You Cancelled";
        }if(val.equals(""+Status.PARTIAL_ACCEPTED)){
            return  "Scheduled";
        }if(val.equals(""+Status.NORMAL_RIDE_CANCEl_BY_ADMIN)){
            return  "Cancelled by admin";
        }if(val.equals(""+Status.RENTAL_RIDE_CANCEl_BY_ADMIN)){
            return  "Cancelled By Admin";
        }else {
            return  "Something Went Wrong";
        }

    }


    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
