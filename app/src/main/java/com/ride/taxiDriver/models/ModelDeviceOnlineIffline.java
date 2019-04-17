package com.ride.taxiDriver.models;

/**
 * Created by lenovo-pc on 12/30/2017.
 */

public class ModelDeviceOnlineIffline {

    /**
     * result : 1
     * msg : ONLINE
     * offline : 1
     */

    private int result;
    private String msg;
    private int offline;

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

    public int getOffline() {
        return offline;
    }

    public void setOffline(int offline) {
        this.offline = offline;
    }
}
