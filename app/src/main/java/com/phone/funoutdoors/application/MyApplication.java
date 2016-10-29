package com.phone.funoutdoors.application;

import android.app.Application;

/**
 * Created by Lenovo-SXX on 2016/10/24.
 */
public class MyApplication extends Application{

    private int width;
    private int height;
    private String picPath="http://image.quhuwai.cn/";

    public String getVideoPath() {
        return videoPath;
    }

    public String getPicPath() {
        return picPath;
    }

    private String videoPath="http://video.quhuwai.cn/";

    @Override
    public void onCreate() {
        super.onCreate();
        width = getResources().getDisplayMetrics().widthPixels;
        height = getResources().getDisplayMetrics().heightPixels;

    }
    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


}
