package com.ride.taxiDriver.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by samirgoel3@gmail.com on 9/6/2017.
 */

@IgnoreExtraProperties
public class ActiveRidesPool {
    public String ride_id;
    public String ride_status;



    public ActiveRidesPool() {
    }

    public ActiveRidesPool(String ride_id ,
                          String ride_status ) {
        this.ride_id = ride_id;
        this.ride_status = ride_status ;

    }






}
