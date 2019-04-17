package com.ride.taxiDriver.parsing;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;

import org.json.JSONObject;

import java.io.File;
import java.util.Locale;

import com.ride.taxiDriver.logger.Logger;

/**
 * Created by Bhuvneshwar on 11/2/2016.
 */
public class AccountModule implements Apis {

    ApiManager.APIFETCHER apiFetcher;

    public AccountModule(ApiManager.APIFETCHER apiFetcher , Context context) {
        this.apiFetcher = apiFetcher;
    }


    public void registerDocsApi(String driver_id, String insurance, String license, String rc, String driver_token, String language_id , String rc_expire , String insurance_expire , String license_expire) {

        Log.d("*********griver_token" , ""+driver_token);
        String url = registerDocs + "?driver_id=" + driver_id + "&insurance=" + insurance + "&license=" + license + "&rc=" + rc;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STARTED ,"Register Docs" );
        AndroidNetworking.upload(registerDocs)
                .addMultipartParameter("driver_id", driver_id)
                .addMultipartFile("insurance", new File(insurance))
                .addMultipartFile("license", new File(license))
                .addMultipartFile("rc", new File(rc))
                .addMultipartParameter("rc_expire", rc_expire)
                .addMultipartParameter("insurance_expire", insurance_expire)
                .addMultipartParameter("license_expire", license_expire)
                .addMultipartParameter("driver_token", driver_token)
                .addMultipartParameter("language_id", language_id)
                .setPriority(Priority.HIGH)
                .build()

                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.e("response          " + response);
                        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED ,"Register Docs");
                        apiFetcher.onFetchComplete("" + response, "Register Docs");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Register Docs");
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void loginApi(String email, String password, String language_id) {

        Log.e("CHECK INTENT_DTA==", "phone_number"+email + "password" + password);
        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STARTED , "Login");
        AndroidNetworking.post(login)
                .addBodyParameter("language_code" , ""+ Locale.getDefault().getLanguage())
                .addBodyParameter("driver_email_phone", email)
                .addBodyParameter("driver_password", password)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Login");
                        apiFetcher.onFetchComplete("" + response, "Login");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Login");
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void editProfile(String user_id, String user_name, String user_mobile, String user_image, String driver_token, String language_id, String driver_account_name, String driver_bank_name, String driver_account_number) {

        Log.d("**_url_edit_profile" , ""+editProfile);
        Log.d("**_url_edit_profile with Image" , ""+user_image);
        if (user_image.equals("")) {
            apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STARTED , "Edit Profile");
            AndroidNetworking.post(editProfile)
                    .addBodyParameter("language_code" , ""+ Locale.getDefault().getLanguage())
                    .addBodyParameter("driver_id", user_id)
                    .addBodyParameter("driver_name", user_name)
                    .addBodyParameter("driver_phone", user_mobile)
                    .addBodyParameter("driver_token", driver_token)
                    .addBodyParameter("language_id", language_id)
                    .addBodyParameter("driver_bank_name", driver_account_name)
                    .addBodyParameter("driver_account_name", driver_bank_name)
                    .addBodyParameter("driver_account_number", driver_account_number)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("**response__edit profile",  response);
                            apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Edit Profile");
                            apiFetcher.onFetchComplete("" + response, "Edit Profile");
                        }

                        @Override
                        public void onError(ANError anError) {
                            apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Edit Profile");
                            Logger.e("Error Body        " + anError.getErrorBody());
                            Logger.e("Error Code        " + anError.getErrorCode());
                            Logger.e("Error Detail      " + anError.getErrorDetail());
                            Logger.e("Error Message     " + anError.getMessage());
                            Logger.e("Error Localized   " + anError.getLocalizedMessage());
                        }
                    });
        } else {
            apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STARTED ,"Edit Profile");
            AndroidNetworking.upload(editProfile)
                    .addMultipartParameter("driver_id", user_id)
                    .addMultipartParameter("driver_name", user_name)
                    .addMultipartParameter("driver_phone", user_mobile)
                    .addMultipartFile("driver_image", new File(user_image))
                    .addMultipartParameter("driver_token", driver_token)
                    .addMultipartParameter("driver_bank_name", driver_account_name)
                    .addMultipartParameter("driver_account_name", driver_bank_name)
                    .addMultipartParameter("driver_account_number", driver_account_number)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {
                            Logger.e("response          " + response);
                            apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Edit Profile");
                            apiFetcher.onFetchComplete("" + response, "Edit Profile");
                        }

                        @Override
                        public void onError(ANError anError) {
                            apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Edit Profile");
                            Logger.e("Error Body        " + anError.getErrorBody());
                            Logger.e("Error Code        " + anError.getErrorCode());
                            Logger.e("Error Detail      " + anError.getErrorDetail());
                            Logger.e("Error Message     " + anError.getMessage());
                            Logger.e("Error Localized   " + anError.getLocalizedMessage());
                        }
                    });
        }
    }

    public void cpApi(String driver_id, String o_p, String n_p, String driver_token, String language_id) {

        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STARTED ,"Change Password" );
        AndroidNetworking.post(changePassword)
                .addBodyParameter("language_code" , ""+ Locale.getDefault().getLanguage())
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("old_password", o_p)
                .addBodyParameter("new_password", n_p)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Change Password");
                        apiFetcher.onFetchComplete("" + response, "Change Password");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Change Password");
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void fpApi(String email, String language_id) {

        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STARTED , "Forgot Password");
        AndroidNetworking.post(forgotPassword)
                .addBodyParameter("language_code" , ""+ Locale.getDefault().getLanguage())
                .addBodyParameter("driver_email", email)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Forgot Password");
                        apiFetcher.onFetchComplete("" + response, "Forgot Password");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Forgot Password");
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }


    public void logoutApi(String driver_id, String driver_token, String language_id) {

        String url = logout + "?driver_id=" + driver_id + "&driver_token=" + driver_token + "&language_id=" + language_id;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STARTED , "Logout");
        AndroidNetworking.post(logout)
                .addBodyParameter("language_code" , ""+ Locale.getDefault().getLanguage())
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Logout");
                        apiFetcher.onFetchComplete("" + response, "Logout");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiManager.APIFETCHER.KEY_API_IS_STOPPED , "Logout");

                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

}
