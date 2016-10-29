package com.phone.funoutdoors.bean;

import java.util.List;

/**
 * Created by Lenovo-SXX on 2016/10/24.
 */
public class HomeInfo {

    /**
     * destination_address : 冕宁灵山寺景区
     * destination_alti : 0
     * destination_id : 86
     * destination_img1 : 1#2016052611264891.jpg#
     * destination_lati : 28.552546
     * destination_longi : 102.293868
     * destination_name : 俄尔则俄
     * destination_summary : 俄尔则俄的迷人不是因为他的高度，而是因为他星罗棋布的海，成片的杜鹃林，绵延起伏的山。在它的脚下，东面有茶马古道著名的登相营、九盘云古驿站，西面有庄严的灵山古刹。围绕着主峰的高山海子大大小小20多个，其中以连三海、九海、黑海、红海、歪海最为出名，还有世上罕见的间隙泉！
     * destination_time : 2016-05-26 11:28
     * region_id : 1
     * top_index : 4
     */

    private List<DestinationBean> destination;
    private List<?> infomation;
    /**
     * content_type : 6
     * pic_url : 16102411231242.png
     * promotion_id : 41
     * promotion_time : 2016-09-30 17:30
     * promotion_title : 若尔盖•云端天堂
     * promotion_url : http://wap.quhuwai.cn/vtour/ruoergai2.html
     * share_content :
     */

    private List<PromotionBean> promotion;
    /**
     * discuss_count : 0
     * media_height : 720
     * media_time : 51
     * media_type : 3
     * media_width : 1280
     * praise_count : 0
     * scene_content : 拉开十几公斤的滑翔伞包，将滑翔伞平铺在草地上，整理好伞线，穿戴好头盔和座袋，拉紧绳索，一阵快速的奔跑后，滑翔伞迎风张开，腾空而起，飞行在空中，随着风向调整好伞翼，伞借风势，直冲云霄，它没有多快速多刺激，这是一场温暖空气中的漂浮游戏。
     * scene_id : 334
     * scene_img : 16102414454553.jpg
     * scene_title : high玩滑翔伞 实现你的飞天梦
     * scene_type : 6
     * scene_video : qj27.mp4
     * user_id : 18
     * view_count : 171
     */

    private List<RealSceneBean> realScene;

    public List<DestinationBean> getDestination() {
        return destination;
    }

    public void setDestination(List<DestinationBean> destination) {
        this.destination = destination;
    }

    public List<?> getInfomation() {
        return infomation;
    }

    public void setInfomation(List<?> infomation) {
        this.infomation = infomation;
    }

    public List<PromotionBean> getPromotion() {
        return promotion;
    }

    public void setPromotion(List<PromotionBean> promotion) {
        this.promotion = promotion;
    }

    public List<RealSceneBean> getRealScene() {
        return realScene;
    }

    public void setRealScene(List<RealSceneBean> realScene) {
        this.realScene = realScene;
    }

    public static class DestinationBean {
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

    public static class RealSceneBean {
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
