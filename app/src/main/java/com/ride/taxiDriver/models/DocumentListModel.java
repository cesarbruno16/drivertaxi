package com.ride.taxiDriver.models;

import java.util.List;

/**
 * Created by samirgoel3@gmail.com on 6/20/2017.
 */

public class DocumentListModel {


    /**
     * result : 1
     * msg : [{"city_document_id":"3","city_id":"56","document_id":"3","city_document_status":"1","document_name":"Polution","documnet_varification_status":"0"},{"city_document_id":"4","city_id":"56","document_id":"2","city_document_status":"1","document_name":"Vehicle Registration Certificate","documnet_varification_status":"0"},{"city_document_id":"5","city_id":"56","document_id":"4","city_document_status":"1","document_name":"Insurance ","documnet_varification_status":"0"}]
     */

    private int result;
    private List<MsgBean> msg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * city_document_id : 3
         * city_id : 56
         * document_id : 3
         * city_document_status : 1
         * document_name : Polution
         * documnet_varification_status : 0
         */

        private String city_document_id;
        private String city_id;
        private String document_id;
        private String city_document_status;
        private String document_name;
        private String documnet_varification_status;

        public String getCity_document_id() {
            return city_document_id;
        }

        public void setCity_document_id(String city_document_id) {
            this.city_document_id = city_document_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getDocument_id() {
            return document_id;
        }

        public void setDocument_id(String document_id) {
            this.document_id = document_id;
        }

        public String getCity_document_status() {
            return city_document_status;
        }

        public void setCity_document_status(String city_document_status) {
            this.city_document_status = city_document_status;
        }

        public String getDocument_name() {
            return document_name;
        }

        public void setDocument_name(String document_name) {
            this.document_name = document_name;
        }

        public String getDocumnet_varification_status() {
            return documnet_varification_status;
        }

        public void setDocumnet_varification_status(String documnet_varification_status) {
            this.documnet_varification_status = documnet_varification_status;
        }
    }
}
