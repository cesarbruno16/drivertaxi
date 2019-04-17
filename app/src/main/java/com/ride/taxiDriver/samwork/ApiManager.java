package com.ride.taxiDriver.samwork;

import android.annotation.SuppressLint;
import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.apporio.apporiologs.ApporioLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

import static com.ride.taxiDriver.samwork.ApiManager.APIFETCHER.KEY_API_IS_ERRORED;
import static com.ride.taxiDriver.samwork.ApiManager.APIFETCHER.KEY_API_IS_STARTED;
import static com.ride.taxiDriver.samwork.ApiManager.APIFETCHER.KEY_API_IS_STOPPED;


/**
 * Created by samir on 30/01/17.
 */

public class ApiManager {

    public Context context;
    String url;
    HashMap map;
    GsonBuilder gsonBuilder;
    Gson gson;
    APIFETCHER apifetcher;

    private static final String TAG = "APIExecution";




    public ApiManager(APIFETCHER apifetcher) {
        this.context = context;
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        map = new HashMap();
        this.apifetcher = apifetcher;
    }


    @SuppressLint("LongLogTag")
    public void execution_method_post(final String tag, String url, HashMap<String, String> bodyparameter) {
        bodyparameter.put("language_code" , ""+ Locale.getDefault().getLanguage());
        ApporioLog.logD(tag +" **Body API Posting parameter ==> ",  ""+ bodyparameter);
        ApporioLog.logD(tag + " **Url API Url executed ==> ", "" + url);
        apifetcher.onAPIRunningState(KEY_API_IS_STARTED , tag);
        AndroidNetworking.post("" + url)
                .addBodyParameter(bodyparameter)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        ApporioLog.logI(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        ApporioLog.logI(TAG, " bytesSent : " + bytesSent);
                        ApporioLog.logI(TAG, " bytesReceived : " + bytesReceived);
                        ApporioLog.logI(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        ApporioLog.logD(tag+" **Response Response==> ", "" + jsonObject);
                        apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                        apifetcher.onFetchComplete(jsonObject, tag);
                    }

                    @Override
                    public void onError(ANError anError) {
                        apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                        ApporioLog.logE("errror", "" + anError.getErrorBody());
                        ApporioLog.logE("errror", "" + anError.getErrorDetail());
                        ApporioLog.logE("errror", "" + anError.getMessage());
                        ApporioLog.logE("error", "" + anError.getStackTrace());
                        ApporioLog.logE("error", "" + anError.getCause());
                    }
                });
    }

    public void execution_method_get(final String tag, String url) {
        ApporioLog.logD(tag + " **Url API Url executed ==> ", "" + url+"&language_code="+Locale.getDefault().getLanguage());

        apifetcher.onAPIRunningState(KEY_API_IS_STARTED , tag);
        AndroidNetworking.get(url+"&language_code="+Locale.getDefault().getLanguage())
                .setTag(this).setPriority(Priority.MEDIUM)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        ApporioLog.logD(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        ApporioLog.logD(TAG, " bytesSent : " + bytesSent);
                        ApporioLog.logD(TAG, " bytesReceived : " + bytesReceived);
                        ApporioLog.logD(TAG, " isFromCache : " + isFromCache);
                    }
                }).getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(final JSONObject jsonObject) {
                ApporioLog.logD(tag+" **Response Response==> ", "" + jsonObject);
                apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                apifetcher.onFetchComplete(jsonObject, tag);
            }

            @Override
            public void onError(ANError anError) {
                apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                ApporioLog.logE(""+TAG, "" + anError.getErrorBody());
                ApporioLog.logE(""+TAG, "" + anError.getErrorDetail());
                apifetcher.onAPIRunningState(KEY_API_IS_ERRORED , tag);
            }
        });
    }

    public void execution_method_image_post(final String tag ,   String url ,HashMap<String ,File> images  , HashMap<String  , String > data ){
        data.put("language_code" , ""+ Locale.getDefault().getLanguage());


        ApporioLog.logD(tag +" **Body API Posting parameter ==> ",  ""+ data);
        ApporioLog.logD(tag +" **Body(Images) API Posting parameter ==> ",  ""+ images);
        ApporioLog.logD(tag + " **Url API Url executed ==> ", "" + url);

        apifetcher.onAPIRunningState(KEY_API_IS_STARTED , tag);
        AndroidNetworking.upload("" + url)
                .addMultipartFile(images)
                .addMultipartParameter(data)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        ApporioLog.logD(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        ApporioLog.logD(TAG, " bytesSent : " + bytesSent);
                        ApporioLog.logD(TAG, " bytesReceived : " + bytesReceived);
                        ApporioLog.logD(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        ApporioLog.logD(tag+" **Response Response==> ", "" + jsonObject);
                        apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                        apifetcher.onFetchComplete(jsonObject, tag);
                    }

                    @Override
                    public void onError(ANError anError) {
                        apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                        ApporioLog.logE("errror", "" + anError.getErrorBody());
                        ApporioLog.logE("errror", "" + anError.getErrorDetail());
                        ApporioLog.logE("errror", "" + anError.getMessage());
                        ApporioLog.logE("error", "" + anError.getStackTrace());
                        ApporioLog.logE("error", "" + anError.getCause());
                    }
                });
    }




    public void execution_method_multipart(final String tag , String url , HashMap<String , String> hashmapdetails ,String image_key,  String image){
        hashmapdetails.put("language_code" , ""+ Locale.getDefault().getLanguage());

        ApporioLog.logD(tag +" **Body API Posting parameter ==> ",  ""+ hashmapdetails);
        ApporioLog.logD(tag +" **Body(Images) API Posting parameter ==> ",  image_key+ "  "+ image);
        ApporioLog.logD(tag + " **Url API Url executed ==> ", "" + url);
        apifetcher.onAPIRunningState(KEY_API_IS_STARTED , tag);
        AndroidNetworking.upload(url)
                .addMultipartParameter(hashmapdetails)
                .addMultipartFile(""+image_key, new File(image))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ApporioLog.logD(tag+" **Response Response==> ", "" + jsonObject);
                        apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                        apifetcher.onFetchComplete(jsonObject, tag);
                    }
                    @Override
                    public void onError(ANError anError) {
                        apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                        ApporioLog.logE("errror", "" + anError.getErrorBody());
                        ApporioLog.logE("errror", "" + anError.getErrorDetail());
                        ApporioLog.logE("errror", "" + anError.getMessage());
                        ApporioLog.logE("error", "" + anError.getStackTrace());
                        ApporioLog.logE("error", "" + anError.getCause());
                    }
                });
    }



    public void execution_method_multipart_double_image(final String tag , String url , HashMap<String , String> hashmapdetails , String key_image_one , String image1 ,String key_image_two, String image2){


        ApporioLog.logD(tag +" **Body API Posting parameter ==> ",  ""+ hashmapdetails);
        ApporioLog.logD(tag +" **Body(Images) API Posting parameter ==> ",  key_image_one+ "  "+ image1);
        ApporioLog.logD(tag +" **Body(Images) API Posting parameter ==> ",  key_image_two+ "  "+ image2);
        ApporioLog.logD(tag + " **Url API Url executed ==> ", "" + url);


        AndroidNetworking.upload(url)
                .addMultipartParameter(hashmapdetails)
                .addMultipartFile(""+key_image_one, new File(image1))
                .addMultipartFile(""+key_image_two, new File(image2))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ApporioLog.logD(tag+" **Response Response==> ", "" + jsonObject);
                        apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                        apifetcher.onFetchComplete(jsonObject, tag);
                    }
                    @Override
                    public void onError(ANError anError) {
                        apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                        ApporioLog.logE("errror", "" + anError.getErrorBody());
                        ApporioLog.logE("errror", "" + anError.getErrorDetail());
                        ApporioLog.logE("errror", "" + anError.getMessage());
                        ApporioLog.logE("error", "" + anError.getStackTrace());
                        ApporioLog.logE("error", "" + anError.getCause());
                    }
                });
    }






    public interface APIFETCHER {

        public static int KEY_API_IS_STARTED = 0;
        public static int KEY_API_IS_RUNNING = 1;
        public static int KEY_API_IS_STOPPED = 2;
        public static int KEY_API_IS_ERRORED = 3;

        public static int KEY_ERRORED = 4 ;

        void onAPIRunningState(int a, String APINAME);  // state - API Starts(0) , API Running(1) , API Stops(2)  API Error(3)


        void onFetchComplete(Object script, String APINAME); // This will give the full script

        void onFetchResultZero(String script);

    }
}
