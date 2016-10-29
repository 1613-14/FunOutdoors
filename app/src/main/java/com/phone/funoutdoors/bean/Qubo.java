package com.phone.funoutdoors.bean;

import java.util.List;

/**
 * Created by Lenovo-SXX on 2016/10/28.
 */
public class Qubo {

    /**
     * isSuccess : SUCCESS
     * message : 查询成功
     * resultCode : 0
     * resultList : [{"activity":[],"avatar":"1455875063.jpg","destination_id":0,"discuss_count":2,"guide":[],"list1":[],"media_height":720,"media_time":73,"media_type":1,"media_width":1280,"nickname":"趣户外官方","praiseFlg":0,"praise_count":0,"region_id":0,"relation":[{"discuss_count":0,"media_height":720,"media_time":0,"media_type":2,"media_width":1280,"praise_count":0,"scene_content":"石牛山溶洞群有大小洞穴10多个，洞深约1000米。洞内冬暖夏凉，汇集了不同地质年代发育生长的钟乳石，它们有的像皱褶的沙丘，有的像海底的珊瑚礁，有的犹如一根擎天的巨柱。晶莹剔透的石柱、石笋、石莲等，宛如夜空的银河倾泻而下，闪烁出迷人的光芒，让人不禁感叹大自然的鬼斧神工。","scene_id":342,"scene_img":"16102810472904.jpg","scene_title":"石牛山·难得的溶洞奇观","scene_type":1,"scene_video":"16102810470803.jpg","user_id":18,"view_count":167},{"discuss_count":0,"media_height":720,"media_time":0,"media_type":2,"media_width":1280,"praise_count":0,"scene_content":"在云南，有一条盘山公路，依山梁而修，在1500米的山上，顺着山梁绕来绕去，短短7公里路，共有68道拐，最短的距离不到100米就是一道拐，蜿蜒曲折，如一条一条巨龙在山间飞舞，这是目前世界上发现的最弯的一条公路，也是世界修路史上的一个奇迹。","scene_id":340,"scene_img":"16102713153010.jpg","scene_title":"68道拐·世界上最弯的公路","scene_type":1,"scene_video":"16102713150509.jpg","user_id":18,"view_count":197},{"discuss_count":0,"media_height":720,"media_time":126,"media_type":1,"media_width":1280,"praise_count":0,"scene_content":"荔波县，地处黔南边陲，位于东经107°37\u2032至108°18\u2032，北纬25°7\u2032至25°9\u2032之间。东北与黔东南苗族侗族自治州的从江县、榕江县接壤，东南与广西壮族自治区的环江县、南丹县毗邻。西与独山县相连，北与三都水族自治县交界。","scene_id":336,"scene_img":"16102510485197.jpg","scene_title":"荔波·地球腰带上的绿宝石","scene_type":1,"scene_video":"libo.mp4","user_id":18,"view_count":150},{"discuss_count":0,"media_height":720,"media_time":0,"media_type":2,"media_width":1280,"praise_count":2,"scene_content":"茶卡盐湖位于青海省乌兰县茶卡镇，镶嵌在雪山草地间，盐湖水域宽广，银波粼粼，天空白云悠悠，远处苍山峥嵘，蓝天白云、雪山牧草映入湖中，如诗如画，漫步湖上，犹如进入盐的世界，也因此被旅行者们称为中国\u201c天空之镜\u201d。","scene_id":308,"scene_img":"16101311225553.jpg","scene_title":"茶卡盐湖·天空之境","scene_type":1,"scene_video":"16101311230554.jpg","user_id":18,"view_count":260},{"discuss_count":0,"media_height":720,"media_time":0,"media_type":1,"media_width":1280,"praise_count":0,"scene_content":"念青唐古拉山脉，是雅鲁藏布江和怒江的分水岭。这里有逾百座6000米以上的山峰未曾攀登过，迷人的高傲雪峰和河谷环绕着一个个风景如画、历史悠久的村镇，加之点缀其间的无数冰川、湖泊令人们想起欧洲的阿尔卑斯山区，一些国外登山家称这里为\u201c西藏的阿尔卑斯\u201d。","scene_id":175,"scene_img":"16081510095778.jpg","scene_title":"念青唐古拉·西藏的阿尔卑斯","scene_type":1,"scene_video":"nianqingtanggula.mp4","user_id":18,"view_count":155}],"route":[],"scene_720url":"","scene_address":"","scene_alti":0,"scene_content":"洛带古镇是成都近郊保存最为完整的客家古镇，镇内90%以上的居民为客家人，至今仍讲客家话，沿袭客家习俗，被誉为\u201c中国西部客家第一镇\u201d。镇内千年老街、客家民居保存完好，老街呈\u201c一街七巷子\u201d格局，空间变化丰富；街道两边商铺林立，属典型的明清建筑风格。","scene_id":327,"scene_img":"16102017055001.jpg","scene_key":"洛带古镇","scene_lati":"","scene_longi":"","scene_time":"2016-10-20 17:07","scene_title":"洛带·中国西部客家第一镇","scene_type":1,"scene_video":"luodai.mp4","user_id":18,"view_count":161,"wapurl":"http://wap.quhuwai.cn/scene/scene327.html"}]
     */

    private String isSuccess;
    private String message;
    private String resultCode;
    /**
     * activity : []
     * avatar : 1455875063.jpg
     * destination_id : 0
     * discuss_count : 2
     * guide : []
     * list1 : []
     * media_height : 720
     * media_time : 73
     * media_type : 1
     * media_width : 1280
     * nickname : 趣户外官方
     * praiseFlg : 0
     * praise_count : 0
     * region_id : 0
     * relation : [{"discuss_count":0,"media_height":720,"media_time":0,"media_type":2,"media_width":1280,"praise_count":0,"scene_content":"石牛山溶洞群有大小洞穴10多个，洞深约1000米。洞内冬暖夏凉，汇集了不同地质年代发育生长的钟乳石，它们有的像皱褶的沙丘，有的像海底的珊瑚礁，有的犹如一根擎天的巨柱。晶莹剔透的石柱、石笋、石莲等，宛如夜空的银河倾泻而下，闪烁出迷人的光芒，让人不禁感叹大自然的鬼斧神工。","scene_id":342,"scene_img":"16102810472904.jpg","scene_title":"石牛山·难得的溶洞奇观","scene_type":1,"scene_video":"16102810470803.jpg","user_id":18,"view_count":167},{"discuss_count":0,"media_height":720,"media_time":0,"media_type":2,"media_width":1280,"praise_count":0,"scene_content":"在云南，有一条盘山公路，依山梁而修，在1500米的山上，顺着山梁绕来绕去，短短7公里路，共有68道拐，最短的距离不到100米就是一道拐，蜿蜒曲折，如一条一条巨龙在山间飞舞，这是目前世界上发现的最弯的一条公路，也是世界修路史上的一个奇迹。","scene_id":340,"scene_img":"16102713153010.jpg","scene_title":"68道拐·世界上最弯的公路","scene_type":1,"scene_video":"16102713150509.jpg","user_id":18,"view_count":197},{"discuss_count":0,"media_height":720,"media_time":126,"media_type":1,"media_width":1280,"praise_count":0,"scene_content":"荔波县，地处黔南边陲，位于东经107°37\u2032至108°18\u2032，北纬25°7\u2032至25°9\u2032之间。东北与黔东南苗族侗族自治州的从江县、榕江县接壤，东南与广西壮族自治区的环江县、南丹县毗邻。西与独山县相连，北与三都水族自治县交界。","scene_id":336,"scene_img":"16102510485197.jpg","scene_title":"荔波·地球腰带上的绿宝石","scene_type":1,"scene_video":"libo.mp4","user_id":18,"view_count":150},{"discuss_count":0,"media_height":720,"media_time":0,"media_type":2,"media_width":1280,"praise_count":2,"scene_content":"茶卡盐湖位于青海省乌兰县茶卡镇，镶嵌在雪山草地间，盐湖水域宽广，银波粼粼，天空白云悠悠，远处苍山峥嵘，蓝天白云、雪山牧草映入湖中，如诗如画，漫步湖上，犹如进入盐的世界，也因此被旅行者们称为中国\u201c天空之镜\u201d。","scene_id":308,"scene_img":"16101311225553.jpg","scene_title":"茶卡盐湖·天空之境","scene_type":1,"scene_video":"16101311230554.jpg","user_id":18,"view_count":260},{"discuss_count":0,"media_height":720,"media_time":0,"media_type":1,"media_width":1280,"praise_count":0,"scene_content":"念青唐古拉山脉，是雅鲁藏布江和怒江的分水岭。这里有逾百座6000米以上的山峰未曾攀登过，迷人的高傲雪峰和河谷环绕着一个个风景如画、历史悠久的村镇，加之点缀其间的无数冰川、湖泊令人们想起欧洲的阿尔卑斯山区，一些国外登山家称这里为\u201c西藏的阿尔卑斯\u201d。","scene_id":175,"scene_img":"16081510095778.jpg","scene_title":"念青唐古拉·西藏的阿尔卑斯","scene_type":1,"scene_video":"nianqingtanggula.mp4","user_id":18,"view_count":155}]
     * route : []
     * scene_720url :
     * scene_address :
     * scene_alti : 0
     * scene_content : 洛带古镇是成都近郊保存最为完整的客家古镇，镇内90%以上的居民为客家人，至今仍讲客家话，沿袭客家习俗，被誉为“中国西部客家第一镇”。镇内千年老街、客家民居保存完好，老街呈“一街七巷子”格局，空间变化丰富；街道两边商铺林立，属典型的明清建筑风格。
     * scene_id : 327
     * scene_img : 16102017055001.jpg
     * scene_key : 洛带古镇
     * scene_lati :
     * scene_longi :
     * scene_time : 2016-10-20 17:07
     * scene_title : 洛带·中国西部客家第一镇
     * scene_type : 1
     * scene_video : luodai.mp4
     * user_id : 18
     * view_count : 161
     * wapurl : http://wap.quhuwai.cn/scene/scene327.html
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
        private String avatar;
        private int destination_id;
        private int discuss_count;
        private int media_height;
        private int media_time;
        private int media_type;
        private int media_width;
        private String nickname;
        private int praiseFlg;
        private int praise_count;
        private int region_id;
        private String scene_720url;
        private String scene_address;
        private int scene_alti;
        private String scene_content;
        private int scene_id;
        private String scene_img;
        private String scene_key;
        private String scene_lati;
        private String scene_longi;
        private String scene_time;
        private String scene_title;
        private int scene_type;
        private String scene_video;
        private int user_id;
        private int view_count;
        private String wapurl;
        private List<?> activity;
        private List<?> guide;
        private List<?> list1;
        /**
         * discuss_count : 0
         * media_height : 720
         * media_time : 0
         * media_type : 2
         * media_width : 1280
         * praise_count : 0
         * scene_content : 石牛山溶洞群有大小洞穴10多个，洞深约1000米。洞内冬暖夏凉，汇集了不同地质年代发育生长的钟乳石，它们有的像皱褶的沙丘，有的像海底的珊瑚礁，有的犹如一根擎天的巨柱。晶莹剔透的石柱、石笋、石莲等，宛如夜空的银河倾泻而下，闪烁出迷人的光芒，让人不禁感叹大自然的鬼斧神工。
         * scene_id : 342
         * scene_img : 16102810472904.jpg
         * scene_title : 石牛山·难得的溶洞奇观
         * scene_type : 1
         * scene_video : 16102810470803.jpg
         * user_id : 18
         * view_count : 167
         */

        private List<RelationBean> relation;
        private List<?> route;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getDestination_id() {
            return destination_id;
        }

        public void setDestination_id(int destination_id) {
            this.destination_id = destination_id;
        }

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getPraiseFlg() {
            return praiseFlg;
        }

        public void setPraiseFlg(int praiseFlg) {
            this.praiseFlg = praiseFlg;
        }

        public int getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(int praise_count) {
            this.praise_count = praise_count;
        }

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }

        public String getScene_720url() {
            return scene_720url;
        }

        public void setScene_720url(String scene_720url) {
            this.scene_720url = scene_720url;
        }

        public String getScene_address() {
            return scene_address;
        }

        public void setScene_address(String scene_address) {
            this.scene_address = scene_address;
        }

        public int getScene_alti() {
            return scene_alti;
        }

        public void setScene_alti(int scene_alti) {
            this.scene_alti = scene_alti;
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

        public String getScene_key() {
            return scene_key;
        }

        public void setScene_key(String scene_key) {
            this.scene_key = scene_key;
        }

        public String getScene_lati() {
            return scene_lati;
        }

        public void setScene_lati(String scene_lati) {
            this.scene_lati = scene_lati;
        }

        public String getScene_longi() {
            return scene_longi;
        }

        public void setScene_longi(String scene_longi) {
            this.scene_longi = scene_longi;
        }

        public String getScene_time() {
            return scene_time;
        }

        public void setScene_time(String scene_time) {
            this.scene_time = scene_time;
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

        public String getWapurl() {
            return wapurl;
        }

        public void setWapurl(String wapurl) {
            this.wapurl = wapurl;
        }

        public List<?> getActivity() {
            return activity;
        }

        public void setActivity(List<?> activity) {
            this.activity = activity;
        }

        public List<?> getGuide() {
            return guide;
        }

        public void setGuide(List<?> guide) {
            this.guide = guide;
        }

        public List<?> getList1() {
            return list1;
        }

        public void setList1(List<?> list1) {
            this.list1 = list1;
        }

        public List<RelationBean> getRelation() {
            return relation;
        }

        public void setRelation(List<RelationBean> relation) {
            this.relation = relation;
        }

        public List<?> getRoute() {
            return route;
        }

        public void setRoute(List<?> route) {
            this.route = route;
        }

        public static class RelationBean {
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
}
