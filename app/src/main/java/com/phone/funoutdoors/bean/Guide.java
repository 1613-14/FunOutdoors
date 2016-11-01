package com.phone.funoutdoors.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class Guide {

    /**
     * isSuccess : SUCCESS
     * message : 查询成功
     * resultCode : 0
     * resultList : [{"act_cover":"16103116384512.jpg","act_create_time":"2016-10-31 15:37","act_days":1,"act_id":881,"act_stdate":"2016-10-21","act_title":"禾木村的美景，安静而美好！","act_type":6,"avatar":"16103115270284.jpg","city_id":35,"discuss_count":0,"find_count":18,"nickname":"青春河边巢","praise_count":0,"province_id":1,"user_id":21248,"view_count":126},{"act_cover":"16102810505008.jpg","act_create_time":"2016-10-28 11:16","act_days":1,"act_id":879,"act_stdate":"2016-10-11","act_title":"梦幻蓝色小镇-摩洛哥舍夫沙万","act_type":3,"avatar":"16102810500706.jpg","city_id":35,"discuss_count":0,"find_count":16,"nickname":"b型血的兔子","praise_count":0,"province_id":1,"user_id":21219,"view_count":139},{"act_cover":"16102715560049.jpg","act_create_time":"2016-10-27 14:15","act_days":2,"act_id":878,"act_stdate":"2016-09-15","act_title":"轿顶山自驾露营之旅","act_type":2,"avatar":"16102617330396.jpg","city_id":269,"discuss_count":0,"find_count":27,"nickname":"团子E菲","praise_count":0,"province_id":23,"user_id":21218,"view_count":173},{"act_cover":"16102615303170.jpg","act_create_time":"2016-10-26 15:31","act_days":5,"act_id":877,"act_stdate":"2016-06-03","act_title":"在甲米普吉岛邂逅蔚蓝安达曼海","act_type":3,"avatar":"16102614253268.jpg","city_id":150,"discuss_count":0,"find_count":22,"nickname":"唐伯虎2012","praise_count":0,"province_id":13,"user_id":21213,"view_count":158},{"act_cover":"16102716080053.jpg","act_create_time":"2016-10-25 15:56","act_days":11,"act_id":876,"act_stdate":"2016-09-24","act_title":"漫步俄罗斯之旅","act_type":4,"avatar":"16102511213909.jpg","city_id":107,"discuss_count":0,"find_count":14,"nickname":"在摄之戒","praise_count":0,"province_id":9,"user_id":21212,"view_count":108},{"act_cover":"16102513432213.jpg","act_create_time":"2016-10-25 13:44","act_days":3,"act_id":875,"act_stdate":"2016-09-20","act_title":"初秋，黄龙美如画！","act_type":4,"avatar":"16102511190907.jpg","city_id":269,"discuss_count":0,"find_count":19,"nickname":"冷山的微博","praise_count":0,"province_id":23,"user_id":21211,"view_count":192},{"act_cover":"16102415130255.jpg","act_create_time":"2016-10-24 15:13","act_days":3,"act_id":873,"act_stdate":"2015-11-25","act_title":"醉美黄山之行","act_type":4,"avatar":"16102414260844.jpg","city_id":107,"discuss_count":0,"find_count":15,"nickname":"时墨西","praise_count":0,"province_id":9,"user_id":21209,"view_count":115},{"act_cover":"16102116173193.jpg","act_create_time":"2016-10-21 16:19","act_days":1,"act_id":872,"act_stdate":"2016-08-18","act_title":"《琅琊榜》取景地大揭秘\u2014雁荡奇绝","act_type":2,"avatar":"16102116014189.jpg","city_id":124,"discuss_count":0,"find_count":14,"nickname":"Miss-沈小娴","praise_count":0,"province_id":11,"user_id":21191,"view_count":152},{"act_cover":"16102114195869.jpg","act_create_time":"2016-10-21 14:20","act_days":11,"act_id":871,"act_stdate":"2016-10-06","act_title":"朝圣-西藏秘境探寻之旅","act_type":3,"avatar":"16102114514585.jpg","city_id":304,"discuss_count":0,"find_count":15,"nickname":"奔跑的小东东","praise_count":0,"province_id":25,"user_id":21183,"view_count":163},{"act_cover":"16101915563164.jpg","act_create_time":"2016-10-19 14:58","act_days":6,"act_id":869,"act_stdate":"2016-09-19","act_title":"自驾青海甘肃川西","act_type":2,"avatar":"16101911390326.jpg","city_id":231,"discuss_count":0,"find_count":17,"nickname":"菜尾蝗-旅行摄影","praise_count":0,"province_id":19,"user_id":21182,"view_count":171}]
     */

    private String isSuccess;
    private String message;
    private String resultCode;
    /**
     * act_cover : 16103116384512.jpg
     * act_create_time : 2016-10-31 15:37
     * act_days : 1
     * act_id : 881
     * act_stdate : 2016-10-21
     * act_title : 禾木村的美景，安静而美好！
     * act_type : 6
     * avatar : 16103115270284.jpg
     * city_id : 35
     * discuss_count : 0
     * find_count : 18
     * nickname : 青春河边巢
     * praise_count : 0
     * province_id : 1
     * user_id : 21248
     * view_count : 126
     */

    private List<GuideBean> resultList;

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

    public List<GuideBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<GuideBean> resultList) {
        this.resultList = resultList;
    }

}
