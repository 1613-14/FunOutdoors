package com.phone.funoutdoors.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.Scene;

import java.util.List;

/**
 * Created by Lenovo-SXX on 2016/10/28.
 */
public class HomePageUtils {

    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    /**
     * 设置toolbar属性
     *
     * @param title
     */
    public static void setToolbar(final Activity context, Toolbar home_page_toolbar, TextView home_page_banner_toolbar_title, String title, int resId) {
        home_page_toolbar.setTitle("");
        if (resId != 0) {
            home_page_toolbar.inflateMenu(resId);
        }
        home_page_banner_toolbar_title.setText(title);
        home_page_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });

    }


    /**
     * 获取网络状态
     *
     * @return
     */
    private boolean getNetWork(Context context) {
        ConnectivityManager service = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = service.getAllNetworkInfo();
        for (int i = 0; i < networkInfo.length; i++) {
            if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测网络是否可用
     *
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }


    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
     */
    public static int getNetworkType(Context context) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!TextUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    /**
     * 设置趣播
     */
    public static void setQubo(Context context, List<Scene.ResultListBean.TopRealScenesBean> list, LinearLayout scene_item_video, SwipeRefreshLayout layout) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            View view = View.inflate(context, R.layout.fragment_home_page_item_qubo, null);
            ImageView qubo_image = (ImageView) view.findViewById(R.id.qubo_image);
            ImageView media_type = (ImageView) view.findViewById(R.id.media_type);
            TextView media_typeName = (TextView) view.findViewById(R.id.media_typeName);
            TextView media_name = (TextView) view.findViewById(R.id.media_name);
            TextView scene_type = (TextView) view.findViewById(R.id.scene_type);
            TextView view_count = (TextView) view.findViewById(R.id.view_count);
            Glide.with(context).load(Constant.PICPATH + list.get(i).getScene_img()).into(qubo_image);
            int type = list.get(i).getMedia_type();
            switch (type) {
                case 3:
                    media_typeName.setText("VR全景视频");
                    media_type.setImageResource(R.mipmap.bsjvr_play_40);
                    break;
                case 2:
                    media_typeName.setText("VR全景图片");
                    media_type.setImageResource(R.mipmap.fullview_40);
                    break;
                case 1:
                    media_type.setImageResource(R.mipmap.bsj_play_40);
            }
            media_name.setText(list.get(i).getScene_title());
            switch (list.get(i).getScene_type()) {
                case 1:
                    scene_type.setText("#景点#");
                    break;
                case 2:
                    scene_type.setText("#装备#");
                    break;
                case 3:
                    scene_type.setText("#专访#");
                    break;
                case 4:
                    scene_type.setText("#未知#");
                    break;
                case 5:
                    scene_type.setText("#猎奇#");
                    break;
                case 6:
                    scene_type.setText("#运动#");
                    break;
            }
            view_count.setText(String.valueOf(list.get(i).getView_count()));
            if (scene_item_video.getChildCount() == i) {
                scene_item_video.addView(view);
            }

        }
        if (layout != null) {
            layout.setRefreshing(false);
        }
    }

    /**
     * 设置视频类型
     *
     * @param type           类型值
     * @param media_typeName 标题名称
     * @param media_type     图片类型
     */
    public static void getMediaType(int type, TextView media_typeName, ImageView media_type) {
        switch (type) {
            case 3:
                media_typeName.setText("VR全景视频");
                media_type.setImageResource(R.mipmap.bsjvr_play_40);
                break;
            case 2:
                media_typeName.setText("VR全景图片");
                media_type.setImageResource(R.mipmap.fullview_40);
                break;
            case 1:
                media_type.setImageResource(R.mipmap.bsj_play_40);
        }
    }


    /**
     * 景点类型
     *
     * @param type       类型值
     * @param scene_type 类型名称
     */
    public static void getSceneType(int type, TextView scene_type) {
        switch (type) {
            case 1:
                scene_type.setText("#景点#");
                break;
            case 2:
                scene_type.setText("#装备#");
                break;
            case 3:
                scene_type.setText("#专访#");
                break;
            case 4:
                scene_type.setText("#未知#");
                break;
            case 5:
                scene_type.setText("#猎奇#");
                break;
            case 6:
                scene_type.setText("#运动#");
                break;
        }
    }
}
