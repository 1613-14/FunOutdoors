package com.phone.funoutdoors.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by White丶 on 2016/10/26.
 */
public class ConsultLVHeaderData implements Serializable {

    /**
     * isSuccess : SUCCESS
     * message : 查询成功
     * resultCode : 0
     * resultList : [{"create_time":"2016-09-02 18:35","feature_content":"中国重庆武隆国际山地户外运动公开赛是由国家体育总局、国家广电总局、重庆市人民政府联合主办的国内唯一的国际山地户外体育运动A级赛事，是世界最具影响力、规模最大、水平最高的三大山地户外运动赛事之一。","feature_cover":"16090218334665.jpg","feature_id":4,"feature_img":"16090218334665.jpg","feature_keyword":"国际山地 户外运动 公开赛","feature_title":"\u201c仙女山杯\u201d国际山地户外公开赛","feature_type":0,"img_height":540,"img_width":1020,"is_top":false,"user_id":2,"view_count":453}]
     */

    private String isSuccess;
    private String message;
    private String resultCode;
    /**
     * create_time : 2016-09-02 18:35
     * feature_content : 中国重庆武隆国际山地户外运动公开赛是由国家体育总局、国家广电总局、重庆市人民政府联合主办的国内唯一的国际山地户外体育运动A级赛事，是世界最具影响力、规模最大、水平最高的三大山地户外运动赛事之一。
     * feature_cover : 16090218334665.jpg
     * feature_id : 4
     * feature_img : 16090218334665.jpg
     * feature_keyword : 国际山地 户外运动 公开赛
     * feature_title : “仙女山杯”国际山地户外公开赛
     * feature_type : 0
     * img_height : 540
     * img_width : 1020
     * is_top : false
     * user_id : 2
     * view_count : 453
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

    public static class ResultListBean implements Serializable{
        private String create_time;
        private String feature_content;
        private String feature_cover;
        private int feature_id;
        private String feature_img;
        private String feature_keyword;
        private String feature_title;
        private int feature_type;
        private int img_height;
        private int img_width;
        private boolean is_top;
        private int user_id;
        private int view_count;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getFeature_content() {
            return feature_content;
        }

        public void setFeature_content(String feature_content) {
            this.feature_content = feature_content;
        }

        public String getFeature_cover() {
            return feature_cover;
        }

        public void setFeature_cover(String feature_cover) {
            this.feature_cover = feature_cover;
        }

        public int getFeature_id() {
            return feature_id;
        }

        public void setFeature_id(int feature_id) {
            this.feature_id = feature_id;
        }

        public String getFeature_img() {
            return feature_img;
        }

        public void setFeature_img(String feature_img) {
            this.feature_img = feature_img;
        }

        public String getFeature_keyword() {
            return feature_keyword;
        }

        public void setFeature_keyword(String feature_keyword) {
            this.feature_keyword = feature_keyword;
        }

        public String getFeature_title() {
            return feature_title;
        }

        public void setFeature_title(String feature_title) {
            this.feature_title = feature_title;
        }

        public int getFeature_type() {
            return feature_type;
        }

        public void setFeature_type(int feature_type) {
            this.feature_type = feature_type;
        }

        public int getImg_height() {
            return img_height;
        }

        public void setImg_height(int img_height) {
            this.img_height = img_height;
        }

        public int getImg_width() {
            return img_width;
        }

        public void setImg_width(int img_width) {
            this.img_width = img_width;
        }

        public boolean isIs_top() {
            return is_top;
        }

        public void setIs_top(boolean is_top) {
            this.is_top = is_top;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }
    }
}
