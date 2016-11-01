package com.phone.funoutdoors.bean;

import java.util.List;

/**
 * Created by Lenovo-SXX on 2016/10/31.
 */
public class QuboList {

    /**
     * isSuccess : SUCCESS
     * message : 查询成功
     * resultCode : 0
     * resultList : [{"discuss_count":0,"media_height":720,"media_time":139,"media_type":1,"media_width":1280,"praise_count":0,"scene_content":"嵩山，古称\u201c外方\u201d，夏商时称\u201c崇高\u201d、\u201c崇山\u201d，西周时成称为 \u201c岳山\u201d，以嵩山为中央左岱（泰山）右华（华山），定嵩山为中岳，始称\u201c中岳嵩山\u201d。嵩山是三教的策源地，对三教的形成和传播都起到了极大的作用，嵩山是三教合一体现最为完美的地方。","scene_id":328,"scene_img":"16102113134960.jpg","scene_title":"嵩山·三教合一","scene_type":1,"scene_video":"songshan.mp4","user_id":18,"view_count":163}]
     */

    private String isSuccess;
    private String message;
    private String resultCode;
    /**
     * discuss_count : 0
     * media_height : 720
     * media_time : 139
     * media_type : 1
     * media_width : 1280
     * praise_count : 0
     * scene_content : 嵩山，古称“外方”，夏商时称“崇高”、“崇山”，西周时成称为 “岳山”，以嵩山为中央左岱（泰山）右华（华山），定嵩山为中岳，始称“中岳嵩山”。嵩山是三教的策源地，对三教的形成和传播都起到了极大的作用，嵩山是三教合一体现最为完美的地方。
     * scene_id : 328
     * scene_img : 16102113134960.jpg
     * scene_title : 嵩山·三教合一
     * scene_type : 1
     * scene_video : songshan.mp4
     * user_id : 18
     * view_count : 163
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
        private int discuss_count;
        private int media_height;
        private int media_time;
        private int media_type;
        private int media_width;
        private int praise_count;
        private String scene_content;
        private int scene_id;
        private String scene_img;
        private String scene_title;
        private int scene_type;
        private String scene_video;
        private int user_id;
        private int view_count;

        public int getDiscuss_count() {
            return discuss_count;
        }

        public void setDiscuss_count(int discuss_count) {
            this.discuss_count = discuss_count;
        }

        public int getMedia_height() {
            return media_height;
        }

        public void setMedia_height(int media_height) {
            this.media_height = media_height;
        }

        public int getMedia_time() {
            return media_time;
        }

        public void setMedia_time(int media_time) {
            this.media_time = media_time;
        }

        public int getMedia_type() {
            return media_type;
        }

        public void setMedia_type(int media_type) {
            this.media_type = media_type;
        }

        public int getMedia_width() {
            return media_width;
        }

        public void setMedia_width(int media_width) {
            this.media_width = media_width;
        }

        public int getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(int praise_count) {
            this.praise_count = praise_count;
        }

        public String getScene_content() {
            return scene_content;
        }

        public void setScene_content(String scene_content) {
            this.scene_content = scene_content;
        }

        public int getScene_id() {
            return scene_id;
        }

        public void setScene_id(int scene_id) {
            this.scene_id = scene_id;
        }

        public String getScene_img() {
            return scene_img;
        }

        public void setScene_img(String scene_img) {
            this.scene_img = scene_img;
        }

        public String getScene_title() {
            return scene_title;
        }

        public void setScene_title(String scene_title) {
            this.scene_title = scene_title;
        }

        public int getScene_type() {
            return scene_type;
        }

        public void setScene_type(int scene_type) {
            this.scene_type = scene_type;
        }

        public String getScene_video() {
            return scene_video;
        }

        public void setScene_video(String scene_video) {
            this.scene_video = scene_video;
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
