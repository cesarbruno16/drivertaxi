package com.ride.taxiDriver.models;

/**
 * Created by lenovo-pc on 12/21/2017.
 */

public class ModelCheckTime {

    /**
     * result : 0
     * msg : you can not start this ride right now , you notified half an hour prior before starting this ride thank you!
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
