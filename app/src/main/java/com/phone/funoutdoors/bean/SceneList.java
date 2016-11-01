package com.phone.funoutdoors.bean;

import java.util.List;

/**
 * Created by Lenovo-SXX on 2016/10/31.
 */
public class SceneList {

    /**
     * isSuccess : SUCCESS
     * message : 查询成功
     * resultCode : 0
     * resultList : [{"destination_address":"嵩山","destination_alti":0,"destination_id":173,"destination_img1":"1#16101716094316.jpg#","destination_lati":"34.488805","destination_longi":"113.035941","destination_name":"嵩山","destination_summary":"嵩山，古称\u201c外方\u201d，夏商时称\u201c崇高\u201d、\u201c崇山\u201d，西周时成称为 \u201c岳山\u201d，以嵩山为中央左岱（泰山）右华（华山），定嵩山为中岳，始称\u201c中岳嵩山\u201d。 嵩山由太室山与少室山组成，共72峰，海拔最低为350米，最高处为1512米。主峰峻极峰位于太室山，高1491.7米，最高峰连天峰位于少室山，高1512米。","destination_time":"2016-10-17 16:09","region_id":5,"top_index":4}]
     */

    private String isSuccess;
    private String message;
    private String resultCode;
    /**
     * destination_address : 嵩山
     * destination_alti : 0
     * destination_id : 173
     * destination_img1 : 1#16101716094316.jpg#
     * destination_lati : 34.488805
     * destination_longi : 113.035941
     * destination_name : 嵩山
     * destination_summary : 嵩山，古称“外方”，夏商时称“崇高”、“崇山”，西周时成称为 “岳山”，以嵩山为中央左岱（泰山）右华（华山），定嵩山为中岳，始称“中岳嵩山”。 嵩山由太室山与少室山组成，共72峰，海拔最低为350米，最高处为1512米。主峰峻极峰位于太室山，高1491.7米，最高峰连天峰位于少室山，高1512米。
     * destination_time : 2016-10-17 16:09
     * region_id : 5
     * top_index : 4
     */

    private List<ResultListBean> resultList;

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<ResultListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultListBean> resultList) {
        this.resultList = resultList;
    }

    public static class ResultListBean {
        private String destination_address;
        private int destination_alti;
        private int destination_id;
        private String destination_img1;
        private String destination_lati;
        private String destination_longi;
        private String destination_name;
        private String destination_summary;
        private String destination_time;
        private int region_id;
        private int top_index;

        public String getDestination_address() {
            return destination_address;
        }

        public void setDestination_address(String destination_address) {
            this.destination_address = destination_address;
        }

        public int getDestination_alti() {
            return destination_alti;
        }

        public void setDestination_alti(int destination_alti) {
            this.destination_alti = destination_alti;
        }

        public int getDestination_id() {
            return destination_id;
        }

        public void setDestination_id(int destination_id) {
            this.destination_id = destination_id;
        }

        public String getDestination_img1() {
            return destination_img1;
        }

        public void setDestination_img1(String destination_img1) {
            this.destination_img1 = destination_img1;
        }

        public String getDestination_lati() {
            return destination_lati;
        }

        public void setDestination_lati(String destination_lati) {
            this.destination_lati = destination_lati;
        }

        public String getDestination_longi() {
            return destination_longi;
        }

        public void setDestination_longi(String destination_longi) {
            this.destination_longi = destination_longi;
        }

        public String getDestination_name() {
            return destination_name;
        }

        public void setDestination_name(String destination_name) {
            this.destination_name = destination_name;
        }

        public String getDestination_summary() {
            return destination_summary;
        }

        public void setDestination_summary(String destination_summary) {
            this.destination_summary = destination_summary;
        }

        public String getDestination_time() {
            return destination_time;
        }

        public void setDestination_time(String destination_time) {
            this.destination_time = destination_time;
        }

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }

        public int getTop_index() {
            return top_index;
        }

        public void setTop_index(int top_index) {
            this.top_index = top_index;
        }
    }
}
