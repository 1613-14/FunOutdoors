package com.phone.funoutdoors.bean;

import java.util.List;

/**
 * Created by Lenovo-SXX on 2016/11/1.
 */
public class DaRen {


    /**
     * isSuccess : SUCCESS
     * message : 查询成功
     * resultCode : 0
     * resultList : [{"accept_strange":true,"acts":[],"avatar":"gydnlx_h.png","caredCnt":0,"caredFlg":0,"city_id":0,"description":"","fansCnt":8,"friend_notename":"","gender":0,"guides":[{"act_cover":"16072215001163.jpg","act_create_time":"2016-07-22 15:01","act_days":1,"act_id":751,"act_stdate":"2016-07-04","act_title":"世外桃源 年宝玉则","act_type":1,"avatar":"gydnlx_h.png","city_id":269,"discuss_count":0,"find_count":11,"nickname":"桂圆带你去旅行","praise_count":0,"province_id":23,"user_id":12495,"view_count":33}],"home_cover":"","intro":"佳图视觉签约摄影师、户外领队、摄影领队","is_auth":"3","member_type":"1","nickname":"桂圆带你去旅行","province_id":0,"rate_id":1,"user_id":12495}]
     */

    private String isSuccess;
    private String message;
    private String resultCode;
    /**
     * accept_strange : true
     * acts : []
     * avatar : gydnlx_h.png
     * caredCnt : 0
     * caredFlg : 0
     * city_id : 0
     * description :
     * fansCnt : 8
     * friend_notename :
     * gender : 0
     * guides : [{"act_cover":"16072215001163.jpg","act_create_time":"2016-07-22 15:01","act_days":1,"act_id":751,"act_stdate":"2016-07-04","act_title":"世外桃源 年宝玉则","act_type":1,"avatar":"gydnlx_h.png","city_id":269,"discuss_count":0,"find_count":11,"nickname":"桂圆带你去旅行","praise_count":0,"province_id":23,"user_id":12495,"view_count":33}]
     * home_cover :
     * intro : 佳图视觉签约摄影师、户外领队、摄影领队
     * is_auth : 3
     * member_type : 1
     * nickname : 桂圆带你去旅行
     * province_id : 0
     * rate_id : 1
     * user_id : 12495
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
        private boolean accept_strange;
        private String avatar;
        private int caredCnt;
        private int caredFlg;
        private int city_id;
        private String description;
        private int fansCnt;
        private String friend_notename;
        private int gender;
        private String home_cover;
        private String intro;
        private String is_auth;
        private String member_type;
        private String nickname;
        private int province_id;
        private int rate_id;
        private int user_id;
        private List<?> acts;
        /**
         * act_cover : 16072215001163.jpg
         * act_create_time : 2016-07-22 15:01
         * act_days : 1
         * act_id : 751
         * act_stdate : 2016-07-04
         * act_title : 世外桃源 年宝玉则
         * act_type : 1
         * avatar : gydnlx_h.png
         * city_id : 269
         * discuss_count : 0
         * find_count : 11
         * nickname : 桂圆带你去旅行
         * praise_count : 0
         * province_id : 23
         * user_id : 12495
         * view_count : 33
         */

        private List<GuidesBean> guides;

        public boolean isAccept_strange() {
            return accept_strange;
        }

        public void setAccept_strange(boolean accept_strange) {
            this.accept_strange = accept_strange;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getCaredCnt() {
            return caredCnt;
        }

        public void setCaredCnt(int caredCnt) {
            this.caredCnt = caredCnt;
        }

        public int getCaredFlg() {
            return caredFlg;
        }

        public void setCaredFlg(int caredFlg) {
            this.caredFlg = caredFlg;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getFansCnt() {
            return fansCnt;
        }

        public void setFansCnt(int fansCnt) {
            this.fansCnt = fansCnt;
        }

        public String getFriend_notename() {
            return friend_notename;
        }

        public void setFriend_notename(String friend_notename) {
            this.friend_notename = friend_notename;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getHome_cover() {
            return home_cover;
        }

        public void setHome_cover(String home_cover) {
            this.home_cover = home_cover;
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

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
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

        public List<?> getActs() {
            return acts;
        }

        public void setActs(List<?> acts) {
            this.acts = acts;
        }

        public List<GuidesBean> getGuides() {
            return guides;
        }

        public void setGuides(List<GuidesBean> guides) {
            this.guides = guides;
        }

        public static class GuidesBean {
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
}
