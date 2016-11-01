package com.phone.funoutdoors.bean;

import java.util.List;

/**
 * Created by Lenovo-SXX on 2016/10/31.
 */
public class DaRenList {


    /**
     * isSuccess : SUCCESS
     * message : 查询成功
     * resultCode : 0
     * resultList : [{"avatar":"","cover_horizontal":"","cover_vertical":"","description":"","intro":"","is_auth":"0","member_type":"0","nickname":"小猪。","rate_id":1,"user_id":18944},{"avatar":"1461655775919.jpg","cover_horizontal":"","cover_vertical":"","description":"","intro":"","is_auth":"0","member_type":"0","nickname":"猪老三","rate_id":1,"user_id":5379},{"avatar":"","cover_horizontal":"","cover_vertical":"","description":"","intro":"","is_auth":"0","member_type":"0","nickname":"猪猪侠儿~~","rate_id":1,"user_id":20746},{"avatar":"1463535524.jpg","cover_horizontal":"","cover_vertical":"","description":"","intro":"没时间解释了，快上车","is_auth":"0","member_type":"0","nickname":"猪儿","rate_id":1,"user_id":1297},{"avatar":"","cover_horizontal":"","cover_vertical":"","description":"","intro":"","is_auth":"0","member_type":"0","nickname":"猪仔","rate_id":1,"user_id":2321},{"avatar":"","cover_horizontal":"","cover_vertical":"","description":"","intro":"","is_auth":"0","member_type":"0","nickname":"欢猪","rate_id":1,"user_id":276},{"avatar":"","cover_horizontal":"","cover_vertical":"","description":"","intro":"","is_auth":"0","member_type":"0","nickname":"叶子猪","rate_id":1,"user_id":10521},{"avatar":"","cover_horizontal":"","cover_vertical":"","description":"","intro":"","is_auth":"0","member_type":"0","nickname":"我是猪","rate_id":1,"user_id":541},{"avatar":"","cover_horizontal":"","cover_vertical":"","description":"","intro":"","is_auth":"0","member_type":"0","nickname":"猪宝宝","rate_id":1,"user_id":12831},{"avatar":"1461656046883.jpg","cover_horizontal":"","cover_vertical":"","description":"","intro":"","is_auth":"0","member_type":"0","nickname":"文猪","rate_id":1,"user_id":5422}]
     */

    private String isSuccess;
    private String message;
    private String resultCode;
    /**
     * avatar :
     * cover_horizontal :
     * cover_vertical :
     * description :
     * intro :
     * is_auth : 0
     * member_type : 0
     * nickname : 小猪。
     * rate_id : 1
     * user_id : 18944
     */

    private List<DaListBean> resultList;

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

    public List<DaListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<DaListBean> resultList) {
        this.resultList = resultList;
    }

    public static class DaListBean {
        private String avatar;
        private String cover_horizontal;
        private String cover_vertical;
        private String description;
        private String intro;
        private String is_auth;
        private String member_type;
        private String nickname;
        private int rate_id;
        private int user_id;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCover_horizontal() {
            return cover_horizontal;
        }

        public void setCover_horizontal(String cover_horizontal) {
            this.cover_horizontal = cover_horizontal;
        }

        public String getCover_vertical() {
            return cover_vertical;
        }

        public void setCover_vertical(String cover_vertical) {
            this.cover_vertical = cover_vertical;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(String is_auth) {
            this.is_auth = is_auth;
        }

        public String getMember_type() {
            return member_type;
        }

        public void setMember_type(String member_type) {
            this.member_type = member_type;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getRate_id() {
            return rate_id;
        }

        public void setRate_id(int rate_id) {
            this.rate_id = rate_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }
}
