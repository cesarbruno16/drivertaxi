package com.ride.taxiDriver.models.newdriveraccount;

import java.util.List;

/**
 * Created by samirgoel3@gmail.com on 6/15/2017.
 */

public class GetAllModel {

    /**
     * status : 1
     * message : City And Car Type List
     * details : [{"city_id":"1","city_name":"Gurugram","city_latitude":"28.4595","city_longitude":"77.0266","city_admin_status":"1","Car_type_list":[{"car_type_id":"1","car_type_name":"Basic","car_type_image":"Basic","car_type_description":"Basic","car_type_admin_status":"1","rate_card_id":"4","city_id":"1","base_distance":"4","base_distance_price":"100","base_price_per_unit":"30","free_wating_time":"1","wating_price_minute":"10","free_ride_minutes":"1","price_per_ride_minute":"10","Car_Model_list":[{"car_model_id":"1","car_type_id":"1","car_model_name":"Creta","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"2","car_type_id":"1","car_model_name":"Nano","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"4","car_type_id":"1","car_model_name":"Alto","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"8","car_type_id":"1","car_model_name":"Eco Sport","car_model_image":"","car_model_description":"","car_model_status":"1"}]},{"car_type_id":"2","car_type_name":"Normal","car_type_image":"Normal","car_type_description":"Normal","car_type_admin_status":"1","rate_card_id":"5","city_id":"1","base_distance":"4","base_distance_price":"75","base_price_per_unit":"20","free_wating_time":"3","wating_price_minute":"12","free_ride_minutes":"2","price_per_ride_minute":"15","Car_Model_list":[{"car_model_id":"5","car_type_id":"2","car_model_name":"Korola","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"7","car_type_id":"2","car_model_name":"TATA","car_model_image":"","car_model_description":"","car_model_status":"1"}]},{"car_type_id":"3","car_type_name":"Luxury","car_type_image":"Luxury","car_type_description":"Luxury","car_type_admin_status":"1","rate_card_id":"6","city_id":"1","base_distance":"1","base_distance_price":"30","base_price_per_unit":"1","free_wating_time":"4","wating_price_minute":"1","free_ride_minutes":"1","price_per_ride_minute":"1","Car_Model_list":[{"car_model_id":"3","car_type_id":"3","car_model_name":"Audi Q7","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"6","car_type_id":"3","car_model_name":"BMW 350","car_model_image":"","car_model_description":"","car_model_status":"1"}]}]},{"city_id":"2","city_name":"Delhi","city_latitude":"28.7041","city_longitude":"77.1025","city_admin_status":"1","Car_type_list":[{"car_type_id":"1","car_type_name":"Basic","car_type_image":"Basic","car_type_description":"Basic","car_type_admin_status":"1","rate_card_id":"3","city_id":"2","base_distance":"3","base_distance_price":"50","base_price_per_unit":"25","free_wating_time":"3","wating_price_minute":"10","free_ride_minutes":"2","price_per_ride_minute":"15","Car_Model_list":[{"car_model_id":"1","car_type_id":"1","car_model_name":"Creta","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"2","car_type_id":"1","car_model_name":"Nano","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"4","car_type_id":"1","car_model_name":"Alto","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"8","car_type_id":"1","car_model_name":"Eco Sport","car_model_image":"","car_model_description":"","car_model_status":"1"}]},{"car_type_id":"3","car_type_name":"Luxury","car_type_image":"Luxury","car_type_description":"Luxury","car_type_admin_status":"1","rate_card_id":"13","city_id":"2","base_distance":"4","base_distance_price":"80","base_price_per_unit":"16","free_wating_time":"1","wating_price_minute":"10","free_ride_minutes":"1","price_per_ride_minute":"15","Car_Model_list":[{"car_model_id":"3","car_type_id":"3","car_model_name":"Audi Q7","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"6","car_type_id":"3","car_model_name":"BMW 350","car_model_image":"","car_model_description":"","car_model_status":"1"}]}]}]
     */

    private int status;
    private String message;
    private List<DetailsBean> details;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * city_id : 1
         * city_name : Gurugram
         * city_latitude : 28.4595
         * city_longitude : 77.0266
         * city_admin_status : 1
         * Car_type_list : [{"car_type_id":"1","car_type_name":"Basic","car_type_image":"Basic","car_type_description":"Basic","car_type_admin_status":"1","rate_card_id":"4","city_id":"1","base_distance":"4","base_distance_price":"100","base_price_per_unit":"30","free_wating_time":"1","wating_price_minute":"10","free_ride_minutes":"1","price_per_ride_minute":"10","Car_Model_list":[{"car_model_id":"1","car_type_id":"1","car_model_name":"Creta","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"2","car_type_id":"1","car_model_name":"Nano","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"4","car_type_id":"1","car_model_name":"Alto","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"8","car_type_id":"1","car_model_name":"Eco Sport","car_model_image":"","car_model_description":"","car_model_status":"1"}]},{"car_type_id":"2","car_type_name":"Normal","car_type_image":"Normal","car_type_description":"Normal","car_type_admin_status":"1","rate_card_id":"5","city_id":"1","base_distance":"4","base_distance_price":"75","base_price_per_unit":"20","free_wating_time":"3","wating_price_minute":"12","free_ride_minutes":"2","price_per_ride_minute":"15","Car_Model_list":[{"car_model_id":"5","car_type_id":"2","car_model_name":"Korola","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"7","car_type_id":"2","car_model_name":"TATA","car_model_image":"","car_model_description":"","car_model_status":"1"}]},{"car_type_id":"3","car_type_name":"Luxury","car_type_image":"Luxury","car_type_description":"Luxury","car_type_admin_status":"1","rate_card_id":"6","city_id":"1","base_distance":"1","base_distance_price":"30","base_price_per_unit":"1","free_wating_time":"4","wating_price_minute":"1","free_ride_minutes":"1","price_per_ride_minute":"1","Car_Model_list":[{"car_model_id":"3","car_type_id":"3","car_model_name":"Audi Q7","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"6","car_type_id":"3","car_model_name":"BMW 350","car_model_image":"","car_model_description":"","car_model_status":"1"}]}]
         */

        private String city_id;
        private String city_name;
        private String city_latitude;
        private String city_longitude;
        private String city_admin_status;
        private List<CarTypeListBean> Car_type_list;

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getCity_latitude() {
            return city_latitude;
        }

        public void setCity_latitude(String city_latitude) {
            this.city_latitude = city_latitude;
        }

        public String getCity_longitude() {
            return city_longitude;
        }

        public void setCity_longitude(String city_longitude) {
            this.city_longitude = city_longitude;
        }

        public String getCity_admin_status() {
            return city_admin_status;
        }

        public void setCity_admin_status(String city_admin_status) {
            this.city_admin_status = city_admin_status;
        }

        public List<CarTypeListBean> getCar_type_list() {
            return Car_type_list;
        }

        public void setCar_type_list(List<CarTypeListBean> Car_type_list) {
            this.Car_type_list = Car_type_list;
        }

        public static class CarTypeListBean {
            /**
             * car_type_id : 1
             * car_type_name : Basic
             * car_type_image : Basic
             * car_type_description : Basic
             * car_type_admin_status : 1
             * rate_card_id : 4
             * city_id : 1
             * base_distance : 4
             * base_distance_price : 100
             * base_price_per_unit : 30
             * free_wating_time : 1
             * wating_price_minute : 10
             * free_ride_minutes : 1
             * price_per_ride_minute : 10
             * Car_Model_list : [{"car_model_id":"1","car_type_id":"1","car_model_name":"Creta","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"2","car_type_id":"1","car_model_name":"Nano","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"4","car_type_id":"1","car_model_name":"Alto","car_model_image":"","car_model_description":"","car_model_status":"1"},{"car_model_id":"8","car_type_id":"1","car_model_name":"Eco Sport","car_model_image":"","car_model_description":"","car_model_status":"1"}]
             */

            private String car_type_id;
            private String car_type_name;
            private String car_type_image;
            private String car_type_description;
            private String car_type_admin_status;
            private String rate_card_id;
            private String city_id;
            private String base_distance;
            private String base_distance_price;
            private String base_price_per_unit;
            private String free_wating_time;
            private String wating_price_minute;
            private String free_ride_minutes;
            private String price_per_ride_minute;
            private List<CarModelListBean> Car_Model_list;

            public String getCar_type_id() {
                return car_type_id;
            }

            public void setCar_type_id(String car_type_id) {
                this.car_type_id = car_type_id;
            }

            public String getCar_type_name() {
                return car_type_name;
            }

            public void setCar_type_name(String car_type_name) {
                this.car_type_name = car_type_name;
            }

            public String getCar_type_image() {
                return car_type_image;
            }

            public void setCar_type_image(String car_type_image) {
                this.car_type_image = car_type_image;
            }

            public String getCar_type_description() {
                return car_type_description;
            }

            public void setCar_type_description(String car_type_description) {
                this.car_type_description = car_type_description;
            }

            public String getCar_type_admin_status() {
                return car_type_admin_status;
            }

            public void setCar_type_admin_status(String car_type_admin_status) {
                this.car_type_admin_status = car_type_admin_status;
            }

            public String getRate_card_id() {
                return rate_card_id;
            }

            public void setRate_card_id(String rate_card_id) {
                this.rate_card_id = rate_card_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getBase_distance() {
                return base_distance;
            }

            public void setBase_distance(String base_distance) {
                this.base_distance = base_distance;
            }

            public String getBase_distance_price() {
                return base_distance_price;
            }

            public void setBase_distance_price(String base_distance_price) {
                this.base_distance_price = base_distance_price;
            }

            public String getBase_price_per_unit() {
                return base_price_per_unit;
            }

            public void setBase_price_per_unit(String base_price_per_unit) {
                this.base_price_per_unit = base_price_per_unit;
            }

            public String getFree_wating_time() {
                return free_wating_time;
            }

            public void setFree_wating_time(String free_wating_time) {
                this.free_wating_time = free_wating_time;
            }

            public String getWating_price_minute() {
                return wating_price_minute;
            }

            public void setWating_price_minute(String wating_price_minute) {
                this.wating_price_minute = wating_price_minute;
            }

            public String getFree_ride_minutes() {
                return free_ride_minutes;
            }

            public void setFree_ride_minutes(String free_ride_minutes) {
                this.free_ride_minutes = free_ride_minutes;
            }

            public String getPrice_per_ride_minute() {
                return price_per_ride_minute;
            }

            public void setPrice_per_ride_minute(String price_per_ride_minute) {
                this.price_per_ride_minute = price_per_ride_minute;
            }

            public List<CarModelListBean> getCar_Model_list() {
                return Car_Model_list;
            }

            public void setCar_Model_list(List<CarModelListBean> Car_Model_list) {
                this.Car_Model_list = Car_Model_list;
            }

            public static class CarModelListBean {
                /**
                 * car_model_id : 1
                 * car_type_id : 1
                 * car_model_name : Creta
                 * car_model_image :
                 * car_model_description :
                 * car_model_status : 1
                 */

                private String car_model_id;
                private String car_type_id;
                private String car_model_name;
                private String car_model_image;
                private String car_model_description;
                private String car_model_status;

                public String getCar_model_id() {
                    return car_model_id;
                }

                public void setCar_model_id(String car_model_id) {
                    this.car_model_id = car_model_id;
                }

                public String getCar_type_id() {
                    return car_type_id;
                }

                public void setCar_type_id(String car_type_id) {
                    this.car_type_id = car_type_id;
                }

                public String getCar_model_name() {
                    return car_model_name;
                }

                public void setCar_model_name(String car_model_name) {
                    this.car_model_name = car_model_name;
                }

                public String getCar_model_image() {
                    return car_model_image;
                }

                public void setCar_model_image(String car_model_image) {
                    this.car_model_image = car_model_image;
                }

                public String getCar_model_description() {
                    return car_model_description;
                }

                public void setCar_model_description(String car_model_description) {
                    this.car_model_description = car_model_description;
                }

                public String getCar_model_status() {
                    return car_model_status;
                }

                public void setCar_model_status(String car_model_status) {
                    this.car_model_status = car_model_status;
                }
            }
        }
    }
}
