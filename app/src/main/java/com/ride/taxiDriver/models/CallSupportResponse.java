package com.ride.taxiDriver.models;

/**
 * Created by samirgoel3@gmail.com on 9/2/2017.
 */

public class CallSupportResponse {

    /**
     * result : 1
     * details : {"page_id":"2","name":"Help Center","title":"Keshav Goyal","description":"+919560506619","title_arabic":"","description_arabic":"","title_french":"","description_french":""}
     */

    private int result;
    private DetailsBean details;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public DetailsBean getDetails() {
        return details;
    }

    public void setDetails(DetailsBean details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * page_id : 2
         * name : Help Center
         * title : Keshav Goyal
         * description : +919560506619
         * title_arabic :
         * description_arabic :
         * title_french :
         * description_french :
         */

        private String page_id;
        private String name;
        private String title;
        private String description;
        private String title_arabic;
        private String description_arabic;
        private String title_french;
        private String description_french;

        public String getPage_id() {
            return page_id;
        }

        public void setPage_id(String page_id) {
            this.page_id = page_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle_arabic() {
            return title_arabic;
        }

        public void setTitle_arabic(String title_arabic) {
            this.title_arabic = title_arabic;
        }

        public String getDescription_arabic() {
            return description_arabic;
        }

        public void setDescription_arabic(String description_arabic) {
            this.description_arabic = description_arabic;
        }

        public String getTitle_french() {
            return title_french;
        }

        public void setTitle_french(String title_french) {
            this.title_french = title_french;
        }

        public String getDescription_french() {
            return description_french;
        }

        public void setDescription_french(String description_french) {
            this.description_french = description_french;
        }
    }
}
