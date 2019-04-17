package com.ride.taxiDriver.others;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/**
 * Created by samirgoel3@gmail.com on 5/26/2017.
 */

public class FireBaseFetcherutil {

   public static  FirebaseRemoteConfig mFirebaseRemoteConfig ;

   public static String KEY_LOGIN_DOMAIN = "customer_login_domain";
   public static String KEY_CUSTOMER_BASE_DOMAIN = "customer_base_domain";
   public static String KEY_CUSTOMER_BASE_IMAGE_DOMAIN = "customer_image_domain";

   ////////////////////////////////////// Receive Passenger Activity
   public static String KEY_RECEIVERPASSENGER_UNFINISHED_PROGRESS_COLOR = "driver_accept_reject_unfinished_progress_color";
   public static String KEY_RECEIVERPassenger_FINISHED_PROGRESS_COLOR = "driver_accept_reject_finished_progress_color";





   public static String fetchConfigWithKey(String KEY){
      return ""+FireBaseFetcherutil.mFirebaseRemoteConfig.getString(KEY) ;
   }


}
