package com.ride.taxiDriver.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Locale;

public class SessionManager {
    private String TAG  = "SessionManager";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LoginPrefrences";
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_DRIVER_ACCOUNT_NAME = "driver_account_name";
    public static final String KEY_DRIVER_BANK_NAME = "driver_bank_name";
    public static final String KEY_DRIVER_ACCOUNT_NUMBER = "driver_account_number";
    public static final String KEY_DRIVER_ID = "h";
    public static final String KEY_DRIVER_NAME = "driver_name";
    public static final String KEY_DRIVER_PHONE = "driver_phone_number";
    public static final String KEY_DriverEmail = "driver_email";
    public static final String KEY_DriverImage = "driver_image";
    public static final String KEY_DriverPassword = "driver_password";
    public static final String KEY_DriverToken = "driver_token";
    public static final String KEY_Driver_Device_id= "driver_device_id";
    public static final String KEY_Driver_flag = "driver_flag";
    public static final String KEY_Driver_rating = "driver_rating";
    public static final String KEY_Driver_CarTypeId = "driver_car_type_id";
    public static final String KEY_Driver_ModelID = "driver_model_id";
    public static final String KEY_Driver_CAR_Number = "driver_car_number";
    public static final String KEY_Driver_CityId = "driver_city_id";
    public static final String KEY_Driver_registeration_date = "drievr_registration_date";
    public static final String KEY_Driver_lisence = "driver_lisence";
    public static final String KEY_Driver_RC = "driver_rc";
    public static final String KEY_Driver_insurence = "driver_insurance";
    public static final String KEY_Driver_Other_DOC = "otherdoc";
    public static final String KEY_LastUpdate = "last_update";
    public static final String KEY_LastUpdateDate = "last_update_date";
    public static final String KEY_Completed_Rides = "completed_rides";
    public static final String KEY_Rejected_Rides  = "Rejected_Rides";
    public static final String KEY_Cancelled_Rides = "cancelled_rides";
    public static final String KEY_Driver_login_logout = "login_logout";
    public static final String KEY_Driver_Busy_Status = "busy_status";
    public static final String KEY_Driver_Online_Offline_Status = "offline_online_status";
    public static final String KEY_Detail_Status = "details_status";
    public static final String KEY_Admin_Status  = "admin_status";
    public static final String KEY_CarTypeName = "car_type_name";
    public static final String KEY_CarModelName = "car_model_name";
    public static final String KEY_UNIQUE_ID = "unique_id";
    public static final String KEY_TAIL = "tail";
    public static final String KEY_CITY_NAME = "city_name";

    public static final String KEY_meter_range = "meter_range";
    public static final String KEY_service_switcher = "service_switcher";
    public static final String KEY_accuracy = "KEY_accuracy";
    public static final String LANGUAGE = "Languagae";

    public static final String Currency_symbol = "currency_symbol";
    public static final String Currency_ISO_Code = "currency_iso_code";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }




    public String getLanguage(){
        HashMap<String, String> data = new HashMap<>();
        Log.d("" +TAG, "Getting language = "+pref.getString(LANGUAGE, ""));
        return pref.getString(LANGUAGE, "");
    }


    public void setLanguage (String locale ){
        Log.d("" +TAG, "Setting languge = "+locale);
        editor.putString(LANGUAGE,""+ locale);
        editor.commit();

        Locale myLocale = new Locale(""+locale);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        _context.getResources().updateConfiguration(config, _context.getResources().getDisplayMetrics());

    }

    public void createNewPassword(String password){
        editor.putString(KEY_DriverPassword, password);
        editor.commit();
    }

    public void createLoginSession(String DriverId,
                                   String DriverName ,
                                   String DriverPhoneNumber ,
                                   String DriverEmail ,
                                   String DriverImage ,
                                   String DriverPassword ,
                                   String DriverToken ,
                                   String Driver_Device_id,
                                   String Driver_flag ,
                                   String Driver_rating ,
                                   String Driver_CarTypeId ,
                                   String Driver_ModelID ,
                                   String Driver_CAR_Number ,
                                   String Driver_CityId ,
                                   String Driver_registeration_date ,
                                   String Driver_lisence ,
                                   String Driver_RC ,
                                   String Driver_insurence ,
                                   String Driver_Other_DOC ,
                                   String LastUpdate ,
                                   String LastUpdateDate ,
                                   String Completed_Rides ,
                                   String Rejected_Rides  ,
                                   String Cancelled_Rides ,
                                   String Driver_login_logout ,
                                   String Driver_Busy_Status ,
                                   String Driver_Online_Offline_Status ,
                                   String Detail_Status ,
                                   String Admin_Status  ,
                                   String CarTypeName ,
                                   String CarModelName  ,
                                   String Unique_id ,
                                   String city_name,
                                   String Driver_bank_name,
                                   String Driver_account_number,
                                   String Driver_bank) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_DRIVER_ID, DriverId);
        editor.putString(KEY_DRIVER_NAME, DriverName);
        editor.putString(KEY_DRIVER_PHONE, DriverPhoneNumber);
        editor.putString(KEY_DriverEmail, DriverEmail);
        editor.putString(KEY_DriverImage, DriverImage);
        editor.putString(KEY_DriverPassword, DriverPassword);
        editor.putString(KEY_DriverToken, DriverToken);
        editor.putString(KEY_Driver_Device_id, Driver_Device_id);
        editor.putString(KEY_Driver_flag, Driver_flag);
        editor.putString(KEY_Driver_rating, Driver_rating);
        editor.putString(KEY_Driver_CarTypeId, Driver_CarTypeId);
        editor.putString(KEY_Driver_ModelID, Driver_ModelID);
        editor.putString(KEY_Driver_CAR_Number, Driver_CAR_Number);
        editor.putString(KEY_Driver_CityId, Driver_CityId);
        editor.putString(KEY_Driver_registeration_date, Driver_registeration_date);
        editor.putString(KEY_Driver_lisence, Driver_lisence);
        editor.putString(KEY_Driver_RC, Driver_RC);
        editor.putString(KEY_Driver_insurence, Driver_insurence);
        editor.putString(KEY_Driver_Other_DOC, Driver_Other_DOC);
        editor.putString(KEY_LastUpdate, LastUpdate);
        editor.putString(KEY_LastUpdateDate, LastUpdateDate);
        editor.putString(KEY_Completed_Rides, Completed_Rides);
        editor.putString(KEY_Rejected_Rides, Rejected_Rides);
        editor.putString(KEY_Cancelled_Rides, Cancelled_Rides);
        editor.putString(KEY_Driver_login_logout, Driver_login_logout);
        editor.putString(KEY_Driver_Busy_Status, Driver_Busy_Status);
        editor.putString(KEY_Driver_Online_Offline_Status, Driver_Online_Offline_Status);
        editor.putString(KEY_Detail_Status, Detail_Status);
        editor.putString(KEY_Admin_Status, Admin_Status);
        editor.putString(KEY_CarTypeName, CarTypeName);
        editor.putString(KEY_CarModelName, CarModelName);
        editor.putString(KEY_UNIQUE_ID, Unique_id);
        editor.putString(KEY_CITY_NAME, city_name);
        editor.putString(KEY_DRIVER_BANK_NAME, Driver_bank_name);
        editor.putString(KEY_DRIVER_ACCOUNT_NUMBER, Driver_account_number);
        editor.putString(KEY_DRIVER_ACCOUNT_NAME, Driver_bank);
        editor.commit();
    }




    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_DRIVER_ID , pref.getString(KEY_DRIVER_ID , ""));
        user.put(KEY_DRIVER_NAME , pref.getString(KEY_DRIVER_NAME , ""));
        user.put(KEY_DRIVER_PHONE , pref.getString(KEY_DRIVER_PHONE , ""));
        user.put(KEY_DriverEmail , pref.getString(KEY_DriverEmail , ""));
        user.put(KEY_DriverImage , pref.getString(KEY_DriverImage , ""));
        user.put(KEY_DriverPassword , pref.getString(KEY_DriverPassword , ""));
        user.put(KEY_DriverToken , pref.getString(KEY_DriverToken , ""));
        user.put(KEY_Driver_Device_id, pref.getString(KEY_Driver_Device_id, ""));
        user.put(KEY_Driver_flag , pref.getString(KEY_Driver_flag , ""));
        user.put(KEY_Driver_rating , pref.getString(KEY_Driver_rating , ""));
        user.put(KEY_Driver_CarTypeId , pref.getString(KEY_Driver_CarTypeId , ""));
        user.put(KEY_Driver_ModelID, pref.getString(KEY_Driver_ModelID, ""));
        user.put(KEY_Driver_CAR_Number , pref.getString(KEY_Driver_CAR_Number , ""));
        user.put(KEY_Driver_CityId , pref.getString(KEY_Driver_CityId , ""));
        user.put(KEY_Driver_registeration_date , pref.getString(KEY_Driver_registeration_date , ""));
        user.put(KEY_Driver_lisence , pref.getString(KEY_Driver_lisence , ""));
        user.put(KEY_Driver_RC , pref.getString(KEY_Driver_RC , ""));
        user.put(KEY_Driver_insurence , pref.getString(KEY_Driver_insurence , ""));
        user.put(KEY_Driver_Other_DOC , pref.getString(KEY_Driver_Other_DOC , ""));
        user.put(KEY_LastUpdate , pref.getString(KEY_LastUpdate , ""));
        user.put(KEY_LastUpdateDate , pref.getString(KEY_LastUpdateDate , ""));
        user.put(KEY_Completed_Rides , pref.getString(KEY_Completed_Rides , ""));
        user.put(KEY_Rejected_Rides  , pref.getString(KEY_Rejected_Rides  , ""));
        user.put(KEY_Cancelled_Rides , pref.getString(KEY_Cancelled_Rides , ""));
        user.put(KEY_Driver_login_logout , pref.getString(KEY_Driver_login_logout , ""));
        user.put(KEY_Driver_Busy_Status , pref.getString(KEY_Driver_Busy_Status , ""));
        user.put(KEY_Driver_Online_Offline_Status , pref.getString(KEY_Driver_Online_Offline_Status , ""));
        user.put(KEY_Detail_Status , pref.getString(KEY_Detail_Status , ""));
        user.put(KEY_Admin_Status  , pref.getString(KEY_Admin_Status  , ""));
        user.put(KEY_CarTypeName , pref.getString(KEY_CarTypeName , ""));
        user.put(KEY_CarModelName , pref.getString(KEY_CarModelName , ""));
        user.put(KEY_UNIQUE_ID , pref.getString(KEY_UNIQUE_ID , ""));
        user.put(KEY_meter_range , pref.getString(KEY_meter_range , "100"));
        user.put(KEY_service_switcher , pref.getString(KEY_service_switcher , "1"));  // 1 means on normal duty and other means can change manually
        user.put(KEY_TAIL , pref.getString(KEY_TAIL , "100.0"));
        user.put(KEY_CITY_NAME , pref.getString(KEY_CITY_NAME , ""));
        user.put(KEY_accuracy , pref.getString(KEY_accuracy , "100.0"));
        user.put(KEY_DRIVER_BANK_NAME , pref.getString(KEY_DRIVER_BANK_NAME , ""));
        user.put(KEY_DRIVER_ACCOUNT_NUMBER , pref.getString(KEY_DRIVER_ACCOUNT_NUMBER , ""));
        user.put(KEY_DRIVER_ACCOUNT_NAME , pref.getString(KEY_DRIVER_ACCOUNT_NAME , ""));
        return user;
    }

    public void setAccuracy(String accuracy){
        editor.putString(KEY_accuracy, accuracy);
        editor.commit();
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }


    public void setTailValue(String value){
        editor.putString(KEY_TAIL, value);
        editor.commit();
    }


    public void setMeterRange(String meter_range){
        editor.putString(KEY_meter_range, meter_range);
        editor.commit();
    }



    public void setSwitcher(String switch_val){
        editor.putString(KEY_service_switcher, switch_val);
        editor.commit();
    }


    public void setonline_offline(boolean value ){
        if(value){
            editor.putString(KEY_Driver_Online_Offline_Status, "1");
            editor.commit();
        }else if (!value){
            editor.putString(KEY_Driver_Online_Offline_Status, "2");
            editor.commit();
        }
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }



    public String getCurrencyCode(){

        if(pref.getString(Currency_symbol, "").equals("0")){
            return ""+pref.getString(Currency_ISO_Code, "ISO") ;
        }if (pref.getString(Currency_ISO_Code, "").equals("0")){
            return ""+getSymbol() ;
        }else{
            return ""+getSymbol();
        }
    }


    public void setCurrencyCode(String iso_code , String symbol ){

        Log.i("" +TAG , "Setting symbol = "+symbol);
        Log.i("" +TAG , "Setting ISO = "+iso_code);
        editor.putString(Currency_ISO_Code, ""+iso_code);
        editor.putString(Currency_symbol, ""+symbol);
        editor.commit();
    }




    public String getSymbol(){
        String currency = "NA";
        switch (pref.getString(Currency_symbol, "")){
            case "0024":
                currency = "\u0024";
                break;
            case "00A2":
                currency = "\u00A2";
                break;
            case "00A3":
                currency = "\u00A3";
                break;
            case "00A5":
                currency = "\u00A5";
                break;
            case "058F":
                currency = "\u058F";
                break;
            case "060B":
                currency = "\u060B";
                break;
            case "09F2":
                currency = "\u09F2";
                break;
            case "20A0":
                currency = "\u20A0";
                break;
            case "20A1":
                currency = "\u20A1";
                break;
            case "20A2":
                currency = "\u20A2";
                break;
            case "20A3":
                currency = "\u20A3";
                break;
            case "20A4":
                currency = "\u20A4";
                break;
            case "20A5":
                currency = "\u20A5";
                break;
            case "20A6":
                currency = "\u20A6";
                break;
            case "20A7":
                currency = "\u20A7";
                break;
            case "20A8":
                currency = "\u20A8";
                break;
            case "20A9":
                currency = "\u20A9";
                break;
            case "20AA":
                currency = "\u20AA";
                break;
            case "20AB":
                currency = "\u20AB";
                break;
            case "20AC":
                currency = "\u20AC";
                break;
            case "20AD":
                currency = "\u20AD";
                break;
            case "20AE":
                currency = "\u20AE";
                break;
            case "20AF":
                currency = "\u20AF";
                break;
            case "20B0":
                currency = "\u20B0";
                break;
            case "20B1":
                currency = "\u20B1";
                break;
            case "20B2":
                currency = "\u20B2";
                break;
            case "20B3":
                currency = "\u20B3";
                break;
            case "20B4":
                currency = "\u20B4";
                break;
            case "20B5":
                currency = "\u20B5";
                break;
            case "20B6":
                currency = "\u20B6";
                break;
            case "20B7":
                currency = "\u20B7";
                break;
            case "20B8":
                currency = "\u20B8";
                break;
            case "20B9":
                currency = "\u20B9";
                break;
            case "20BA":
                currency = "\u20BA";
                break;
            case "20BB":
                currency = "\u20BB";
                break;
            case "20BC":
                currency = "\u20BC";
                break;
            case "20BD":
                currency = "\u20BD";
                break;
            case "20BE":
                currency = "\u20BE";
                break;
            case "20BF":
                currency = "\u20BF";
                break;
            case "A838":
                currency = "\uA838";
                break;
            case "FDFC":
                currency = "\uFDFC";
                break;
            case "FE69":
                currency = "\uFE69";
                break;
            case "FF04":
                currency = "\uFF04";
                break;
            case "FFE0":
                currency = "\uFFE0";
                break;
            case "FFE1":
                currency = "\uFFE1";
                break;
            case "FFE5":
                currency = "\uFFE5";
                break;
            case "FFE6":
                currency = "\uFFE6";
                break;
            case "00024":
                currency = "\u0024";
                break;
            default:
                currency = "##";
                break;
        }

        return currency ;
    }

}
