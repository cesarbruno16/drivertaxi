package com.ride.taxiDriver.models;

/**
 * Created by samirgoel3@gmail.com on 4/6/2017.
 */

public class CustomerSupportModel {


    /**
     * result : 1
     * msg : Your Query Successfully Submitted!!
     */

    private int result;
    private String msg;

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
}
