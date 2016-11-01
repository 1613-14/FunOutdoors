package com.phone.funoutdoors.bean;

import java.util.List;

/**
 * Created by Lenovo-SXX on 2016/10/31.
 */
public class TravelList {

    /**
     * isSuccess : SUCCESS
     * message : 查询成功
     * resultCode : 0
     * resultList : [{"act_cover":"16101817220300.jpg","act_create_time":"2016-10-18 17:23","act_days":11,"act_id":867,"act_stdate":"2016-01-27","act_title":"越南游轮之旅","act_type":3,"avatar":"16101911363621.jpg","city_id":252,"discuss_count":0,"find_count":0,"nickname":"肖希泽","praise_count":0,"province_id":20,"user_id":21169,"view_count":147},{"act_cover":"16101114091902.jpg","act_create_time":"2016-10-11 14:12","act_days":8,"act_id":856,"act_stdate":"2016-10-03","act_title":"畅游西班牙巴塞罗那和塞维利亚","act_type":3,"avatar":"16101110264360.jpg","city_id":35,"discuss_count":0,"find_count":0,"nickname":"苏一潞","praise_count":1,"province_id":1,"user_id":21128,"view_count":213},{"act_cover":"16092916351965.jpg","act_create_time":"2016-09-29 15:47","act_days":11,"act_id":847,"act_stdate":"2016-07-01","act_title":"台湾攀岩环岛游","act_type":3,"avatar":"16092815022043.jpg","city_id":71,"discuss_count":0,"find_count":0,"nickname":"Forrest刘泽贤","praise_count":0,"province_id":6,"user_id":21070,"view_count":160},{"act_cover":"16092311275150.jpg","act_create_time":"2016-09-23 11:12","act_days":1,"act_id":840,"act_stdate":"2016-07-07","act_title":"游拍沙坡头","act_type":4,"avatar":"16092310500409.jpg","city_id":268,"discuss_count":0,"find_count":0,"nickname":"养龙","praise_count":0,"province_id":22,"user_id":21040,"view_count":164},{"act_cover":"16092014491872.jpg","act_create_time":"2016-09-20 14:50","act_days":11,"act_id":834,"act_stdate":"2016-08-30","act_title":"环游英国","act_type":2,"avatar":"16092014473170.jpg","city_id":35,"discuss_count":0,"find_count":0,"nickname":"行者洛艺嘉","praise_count":0,"province_id":1,"user_id":21042,"view_count":122},{"act_cover":"16091815401466.jpg","act_create_time":"2016-09-18 15:42","act_days":11,"act_id":831,"act_stdate":"2016-03-30","act_title":"曼谷清迈一个月环游","act_type":3,"avatar":"16091815303964.jpg","city_id":299,"discuss_count":0,"find_count":0,"nickname":"木头叔叔在路上","praise_count":0,"province_id":25,"user_id":21038,"view_count":137},{"act_cover":"16091216501019.jpg","act_create_time":"2016-09-12 16:50","act_days":11,"act_id":825,"act_stdate":"2015-12-02","act_title":"自游自在·尼泊尔EBC","act_type":3,"avatar":"16091216210902.jpg","city_id":304,"discuss_count":0,"find_count":0,"nickname":"丽江贱公子","praise_count":1,"province_id":25,"user_id":21026,"view_count":228},{"act_cover":"16082915274805.jpg","act_create_time":"2016-08-26 14:28","act_days":11,"act_id":803,"act_stdate":"2016-07-25","act_title":"永不落幕的蜜月·自驾中国游","act_type":2,"avatar":"16082513374478.jpg","city_id":107,"discuss_count":0,"find_count":0,"nickname":"折腾的小肉团","praise_count":0,"province_id":9,"user_id":20957,"view_count":382},{"act_cover":"16081613325339.jpg","act_create_time":"2016-08-16 13:33","act_days":11,"act_id":780,"act_stdate":"2016-02-08","act_title":"孤独星球，冰与火之歌！冰岛自驾环游","act_type":4,"avatar":"16081611420735.jpg","city_id":107,"discuss_count":0,"find_count":0,"nickname":"行者小彭","praise_count":1,"province_id":9,"user_id":20934,"view_count":96},{"act_cover":"16081211065227.jpg","act_create_time":"2016-08-12 11:08","act_days":11,"act_id":775,"act_stdate":"2015-11-14","act_title":"第二次摩托车环游中国","act_type":2,"avatar":"16081210235825.jpg","city_id":227,"discuss_count":0,"find_count":0,"nickname":"旅行家陈超波","praise_count":0,"province_id":18,"user_id":20916,"view_count":42}]
     */

    private String isSuccess;
    private String message;
    private String resultCode;
    /**
     * act_cover : 16101817220300.jpg
     * act_create_time : 2016-10-18 17:23
     * act_days : 11
     * act_id : 867
     * act_stdate : 2016-01-27
     * act_title : 越南游轮之旅
     * act_type : 3
     * avatar : 16101911363621.jpg
     * city_id : 252
     * discuss_count : 0
     * find_count : 0
     * nickname : 肖希泽
     * praise_count : 0
     * province_id : 20
     * user_id : 21169
     * view_count : 147
     */

    private List<TravelListBean> resultList;

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

    public List<TravelListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<TravelListBean> resultList) {
        this.resultList = resultList;
    }

    public static class TravelListBean {
        private String act_cover;
        private String act_create_time;
        private int act_days;
        private int act_id;
        private String act_stdate;
        private String act_title;
        private int act_type;
        private String avatar;
        private int city_id;
        private int discuss_count;
        private int find_count;
        private String nickname;
        private int praise_count;
        private int province_id;
        private int user_id;
        private int view_count;

        public String getAct_cover() {
            return act_cover;
        }

        public void setAct_cover(String act_cover) {
            this.act_cover = act_cover;
        }

        public String getAct_create_time() {
            return act_create_time;
        }

        public void setAct_create_time(String act_create_time) {
            this.act_create_time = act_create_time;
        }

        public int getAct_days() {
            return act_days;
        }

        public void setAct_days(int act_days) {
            this.act_days = act_days;
        }

        public int getAct_id() {
            return act_id;
        }

        public void setAct_id(int act_id) {
            this.act_id = act_id;
        }

        public String getAct_stdate() {
            return act_stdate;
        }

        public void setAct_stdate(String act_stdate) {
            this.act_stdate = act_stdate;
        }

        public String getAct_title() {
            return act_title;
        }

        public void setAct_title(String act_title) {
            this.act_title = act_title;
        }

        public int getAct_type() {
            return act_type;
        }

        public void setAct_type(int act_type) {
            this.act_type = act_type;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getDiscuss_count() {
            return discuss_count;
        }

        public void setDiscuss_count(int discuss_count) {
            this.discuss_count = discuss_count;
        }

        public int getFind_count() {
            return find_count;
        }

        public void setFind_count(int find_count) {
            this.find_count = find_count;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(int praise_count) {
            this.praise_count = praise_count;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
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
