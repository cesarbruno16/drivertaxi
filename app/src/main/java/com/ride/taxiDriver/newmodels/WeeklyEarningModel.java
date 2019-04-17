package com.ride.taxiDriver.newmodels;

import java.util.List;

/**
 * Created by Bhuvneshwar on 7/10/2017.
 */

public class WeeklyEarningModel {


    /**
     * result : 1
     * msg : Weekly Earning
     * fare_recevied : 263.8
     * company_cut : 0
     * total_payment_eraned : 517
     * weekly_amount : 263.8
     * company_payment : 0
     * driver_payment :
     * total_rides : 2
     * details : [{"date":"2017-07-10","day":"Monday","detail":{"driver_earning_id":"0","driver_id":"185","rides":"0","amount":"0","date":"2017-07-10"}},{"date":"2017-07-11","day":"Tuesday","detail":{"driver_earning_id":"0","driver_id":"185","rides":"0","amount":"0","date":"2017-07-11"}},{"date":"2017-07-12","day":"Wednesday","detail":{"driver_earning_id":"0","driver_id":"185","rides":"0","amount":"0","date":"2017-07-12"}},{"date":"2017-07-13","day":"Thursday","detail":{"driver_earning_id":"0","driver_id":"185","rides":"0","amount":"0","date":"2017-07-13"}},{"date":"2017-07-14","day":"Friday","detail":{"driver_earning_id":"0","driver_id":"185","rides":"0","amount":"0","date":"2017-07-14"}},{"date":"2017-07-15","day":"Saturday","detail":{"driver_earning_id":"4","driver_id":"185","rides":"2","amount":"263.8","date":"2017-07-15"}},{"date":"2017-07-16","day":"Sunday","detail":{"driver_earning_id":"0","driver_id":"185","rides":"0","amount":"0","date":"2017-07-16"}}]
     */

    private int result;
    private String msg;
    private String fare_recevied;
    private String company_cut;
    private String total_payment_eraned;
    private double weekly_amount;
    private String company_payment;
    private String driver_payment;
    private int total_rides;
    private List<DetailsBean> details;

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

    public String getFare_recevied() {
        return fare_recevied;
    }

    public void setFare_recevied(String fare_recevied) {
        this.fare_recevied = fare_recevied;
    }

    public String getCompany_cut() {
        return company_cut;
    }

    public void setCompany_cut(String company_cut) {
        this.company_cut = company_cut;
    }

    public String getTotal_payment_eraned() {
        return total_payment_eraned;
    }

    public void setTotal_payment_eraned(String total_payment_eraned) {
        this.total_payment_eraned = total_payment_eraned;
    }

    public double getWeekly_amount() {
        return weekly_amount;
    }

    public void setWeekly_amount(double weekly_amount) {
        this.weekly_amount = weekly_amount;
    }

    public String getCompany_payment() {
        return company_payment;
    }

    public void setCompany_payment(String company_payment) {
        this.company_payment = company_payment;
    }

    public String getDriver_payment() {
        return driver_payment;
    }

    public void setDriver_payment(String driver_payment) {
        this.driver_payment = driver_payment;
    }

    public int getTotal_rides() {
        return total_rides;
    }

    public void setTotal_rides(int total_rides) {
        this.total_rides = total_rides;
    }

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * date : 2017-07-10
         * day : Monday
         * detail : {"driver_earning_id":"0","driver_id":"185","rides":"0","amount":"0","date":"2017-07-10"}
         */

        private String date;
        private String day;
        private DetailBean detail;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * driver_earning_id : 0
             * driver_id : 185
             * rides : 0
             * amount : 0
             * date : 2017-07-10
             */

            private String driver_earning_id;
            private String driver_id;
            private String rides;
            private String amount;
            private String date;

            public String getDriver_earning_id() {
                return driver_earning_id;
            }

            public void setDriver_earning_id(String driver_earning_id) {
                this.driver_earning_id = driver_earning_id;
            }

            public String getDriver_id() {
                return driver_id;
            }

            public void setDriver_id(String driver_id) {
                this.driver_id = driver_id;
            }

            public String getRides() {
                return rides;
            }

            public void setRides(String rides) {
                this.rides = rides;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}