package com.phone.funoutdoors.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class GuestData {

    /**
     * content_type : 5
     * pic_url : 16071813060657.png
     * promotion_id : 37
     * promotion_time : 2016-07-13 16:24
     * promotion_title : 如何成为趣客
     * promotion_url : http://wap.quhuwai.cn/wapinfo/info221.html
     * share_content :
     */

    private PromotionBean promotion;
    /**
     * avatar : 16101815252521.jpg
     * cover_horizontal : 16101815252521.jpg
     * cover_vertical : 16101815253622.jpg
     * description :
     * intro : 途牛旅游大玩家、豆瓣阅读作者
     * is_auth : 3
     * member_type : 0
     * nickname : 善良酱
     * rate_id : 1
     * user_id : 21170
     */

    private List<DarenBean> daren;
    /**
     * act_cover : 16082915274805.jpg
     * act_create_time : 2016-08-26 14:28
     * act_days : 11
     * act_id : 803
     * act_stdate : 2016-07-25
     * act_title : 永不落幕的蜜月·自驾中国游
     * act_type : 2
     * avatar : 16082513374478.jpg
     * city_id : 107
     * discuss_count : 0
     * find_count : 0
     * nickname : 折腾的小肉团
     * praise_count : 0
     * province_id : 9
     * user_id : 20957
     * view_count : 382
     */

    private List<GuideBean> guide;
    /**
     * discuss_count : 0
     * media_height : 720
     * media_time : 391
     * media_type : 1
     * media_width : 1280
     * praise_count : 0
     * scene_content : 程石，延时成都拍摄者，大范围延时摄影师
     他说不会Maya的调色师不是一个好的延时摄影玩家
     * scene_id : 344
     * scene_img : 16102813383136.jpg
     * scene_title : 我看到的 都给你
     * scene_type : 3
     * scene_video : chengshi.mp4
     * user_id : 18
     * view_count : 138
     */

    private List<SceneBean> scene;

    public PromotionBean getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionBean promotion) {
        this.promotion = promotion;
    }

    public List<DarenBean> getDaren() {
        return daren;
    }

    public void setDaren(List<DarenBean> daren) {
        this.daren = daren;
    }

    public List<GuideBean> getGuide() {
        return guide;
    }

    public void setGuide(List<GuideBean> guide) {
        this.guide = guide;
    }

    public List<SceneBean> getScene() {
        return scene;
    }

    public void setScene(List<SceneBean> scene) {
        this.scene = scene;
    }

    public static class PromotionBean {
        private int content_type;
        private String pic_url;
        private int promotion_id;
        private String promotion_time;
        private String promotion_title;
        private String promotion_url;
        private String share_content;

        public int getContent_type() {
            return content_type;
        }

        public void setContent_type(int content_type) {
            this.content_type = content_type;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getPromotion_id() {
            return promotion_id;
        }

        public void setPromotion_id(int promotion_id) {
            this.promotion_id = promotion_id;
        }

        public String getPromotion_time() {
            return promotion_time;
        }

        public void setPromotion_time(String promotion_time) {
            this.promotion_time = promotion_time;
        }

        public String getPromotion_title() {
            return promotion_title;
        }

        public void setPromotion_title(String promotion_title) {
            this.promotion_title = promotion_title;
        }

        public String getPromotion_url() {
            return promotion_url;
        }

        public void setPromotion_url(String promotion_url) {
            this.promotion_url = promotion_url;
        }

        public String getShare_content() {
            return share_content;
        }

        public void setShare_content(String share_content) {
            this.share_content = share_content;
        }
    }

    public static class DarenBean {
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

    public static class SceneBean {
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
