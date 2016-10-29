package com.phone.funoutdoors.bean;

/**
 * Created by White丶 on 2016/10/24.
 */
public class WelcomePic {

    /**
     * content_type : 6
     * pic_url : 16101215194635.png
     * promotion_id : 44
     * promotion_time : 2016-10-12 15:24
     * promotion_title : 趣户外带你游览世界
     * promotion_url : http://www.quhuwai.cn/weichat/index.html
     * share_content :
     */

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
