package com.phone.funoutdoors.bean;

import java.util.List;

/**
 * Created by Lenovo-SXX on 2016/10/24.
 */
public class Direction {

    /**
     * icon : [{"icon_url":"http://wap.quhuwai.cn/icon/southwest02.png","icon_prority":1,"icon_name":"西南"},{"icon_url":"http://wap.quhuwai.cn/icon/northwest02.png","icon_prority":2,"icon_name":"西北"},{"icon_url":"http://wap.quhuwai.cn/icon/northeast02.png","icon_prority":3,"icon_name":"东北"},{"icon_url":"http://wap.quhuwai.cn/icon/eastchina02.png","icon_prority":4,"icon_name":"华东"},{"icon_url":"http://wap.quhuwai.cn/icon/southchina02.png","icon_prority":5,"icon_name":"华南"},{"icon_url":"http://wap.quhuwai.cn/icon/northchina02.png","icon_prority":6,"icon_name":"华北"}]
     * default_bg : http://image.quhuwai.cn/216092111075709.jpg
     */

    private String default_bg;
    /**
     * icon_url : http://wap.quhuwai.cn/icon/southwest02.png
     * icon_prority : 1
     * icon_name : 西南
     */

    private List<IconBean> icon;

    public String getDefault_bg() {
        return default_bg;
    }

    public void setDefault_bg(String default_bg) {
        this.default_bg = default_bg;
    }

    public List<IconBean> getIcon() {
        return icon;
    }

    public void setIcon(List<IconBean> icon) {
        this.icon = icon;
    }

    public static class IconBean {
        private String icon_url;
        private int icon_prority;
        private String icon_name;

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public int getIcon_prority() {
            return icon_prority;
        }

        public void setIcon_prority(int icon_prority) {
            this.icon_prority = icon_prority;
        }

        public String getIcon_name() {
            return icon_name;
        }

        public void setIcon_name(String icon_name) {
            this.icon_name = icon_name;
        }
    }
}
