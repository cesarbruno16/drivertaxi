package com.ride.taxiDriver.models.restmodels;

/**
 * Created by samirgoel3@gmail.com on 8/4/2017.
 */

public class NewUpdateLatLongModel {


    /**
     * result : 1
     * msg : Updated Successfully
     * currency_iso_code : 0
     * currency_unicode : 20B9
     * application_accuracy : 134
     */

    private int result;
    private String msg;
    private String currency_iso_code;
    private String currency_unicode;
    private String application_accuracy;

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

    public String getCurrency_iso_code() {
        return currency_iso_code;
    }

    public void setCurrency_iso_code(String currency_iso_code) {
        this.currency_iso_code = currency_iso_code;
    }

    public String getCurrency_unicode() {
        return currency_unicode;
    }

    public void setCurrency_unicode(String currency_unicode) {
        this.currency_unicode = currency_unicode;
    }

    public String getApplication_accuracy() {
        return application_accuracy;
    }

    public void setApplication_accuracy(String application_accuracy) {
        this.application_accuracy = application_accuracy;
    }
}
