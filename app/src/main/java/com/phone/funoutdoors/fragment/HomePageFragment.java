package com.phone.funoutdoors.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.activity.HomePage_BannerActivity;
import com.phone.funoutdoors.activity.HomePage_QuboActivity;
import com.phone.funoutdoors.activity.HomePage_SceneActivity;
import com.phone.funoutdoors.activity.HomePage_SearchActivity;
import com.phone.funoutdoors.adapter.HomeFragmentListViewAdpater;
import com.phone.funoutdoors.adapter.HomeViewPageAdapter;
import com.phone.funoutdoors.application.MyApplication;
import com.phone.funoutdoors.bean.Direction;
import com.phone.funoutdoors.bean.HomeInfo;
import com.phone.funoutdoors.interfaces.HomeRequestData;
import com.phone.funoutdoors.utils.HomePageUtils;
import com.phone.funoutdoors.utils.ZoomOutPageTransformer;
import com.phone.funoutdoors.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 首页的Fragment展示
 */
public class HomePageFragment extends Fragment {

    @BindView(R.id.home_page_direction)
    LinearLayout home_page_direction;
    @BindView(R.id.qubo_layout)
    MyListView qubo_layout;
    @BindView(R.id.home_page_viewPager)
    AutoScrollViewPager home_page_viewPager;
    @BindView(R.id.scene_hsv)
    LinearLayout scene_hsv;
    @BindView(R.id.home_page_viewPager_spot)
    LinearLayout home_page_viewPager_spot;
    @BindView(R.id.home_page_search)
    ImageView home_page_search;
    @BindView(R.id.fragment_container)
    FrameLayout fragment_container;
    private Context context;
    private MyApplication application;
    Handler handler = new Handler();

    public HomePageFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        application = new MyApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        ButterKnife.bind(this, view);
        home_page_viewPager_spot.removeAllViews();
        if (HomePageUtils.isNetworkConnected(context)) {
            downContentInfo();
            downDirectionInfo();
        } else {
//            fragment_container.setVisibility(View.INVISIBLE);
            showPopUp(fragment_container);

        }
        return view;
    }


    private void showPopUp(View v) {
        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundResource(R.drawable.dialog_net_bg);
        TextView tv = new TextView(context);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv.setText("连接失败!!!");
        layout.addView(tv);
        final PopupWindow popupWindow = new PopupWindow(layout, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.MyPopWindowAnimation);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        },5000);


    }

    /**
     * 下载全部信息
     */
    private void downContentInfo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://quhuwaiwap.oss-cn-beijing.aliyuncs.com/").addConverterFactory(GsonConverterFactory.create()).build();
        HomeRequestData homeRequestData = retrofit.create(HomeRequestData.class);
        Call<HomeInfo> call = homeRequestData.getContentData();
        call.enqueue(new Callback<HomeInfo>() {
            @Override
            public void onResponse(Call<HomeInfo> call, Response<HomeInfo> response) {
                HomeInfo info = response.body();
                setPicturePlay(info);
                setScene(info);
                setQubo(info);
            }

            @Override
            public void onFailure(Call<HomeInfo> call, Throwable t) {

            }
        });
    }

    /**
     * 设置趣播
     *
     * @param info
     */
    private void setQubo(HomeInfo info) {
        final List<HomeInfo.RealSceneBean> list = info.getRealScene();
        qubo_layout.setAdapter(new HomeFragmentListViewAdpater(list, context));
        qubo_layout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, HomePage_QuboActivity.class);
                intent.putExtra("scene_id", list.get(position).getScene_id());
                intent.putExtra("tag", 1);
                startActivity(intent);
            }
        });
    }

    /**
     * 设置景色
     *
     * @param info
     */
    private void setScene(final HomeInfo info) {
        for (int i = 0; i < info.getDestination().size(); i++) {
            String[] split = info.getDestination().get(i).getDestination_img1().split("#");
            View view = View.inflate(context, R.layout.fragment_home_page_item_scene, null);
            RelativeLayout.LayoutParams ll = new RelativeLayout.LayoutParams(700, 400);
            ll.leftMargin = 10;
            ImageView destination_img1 = (ImageView) view.findViewById(R.id.destination_img1);
            destination_img1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            destination_img1.setLayoutParams(ll);
            TextView destination_name = (TextView) view.findViewById(R.id.destination_name);
            destination_name.setText(info.getDestination().get(i).getDestination_name());
            Glide.with(context).load(application.getPicPath() + split[1]).placeholder(R.mipmap.default_img).into(destination_img1);
            scene_hsv.addView(view);

            final int finalI = i;
            destination_img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HomePage_SceneActivity.class);
                    intent.putExtra("title", info.getDestination().get(finalI).getDestination_name());
                    intent.putExtra("destination_id", info.getDestination().get(finalI).getDestination_id());
                    startActivity(intent);
                }
            });
        }
    }




    /**
     * 图片自动播放
     *
     * @param info
     */
    private void setPicturePlay(final HomeInfo info) {
        final List<ImageView> images = new ArrayList<>();
        images.clear();
        for (int i = 0; i < info.getPromotion().size(); i++) {
            setPicturePlayItem(info, i, images);
        }
        int size = images.size();
        if (size <= 3) {
            for (int i = 0; i < info.getPromotion().size(); i++) {
                setPicturePlayItem(info, i, images);
            }
        }
        ImageView spot = (ImageView) home_page_viewPager_spot.getChildAt(0);
        spot.setImageResource(R.drawable.banner_spot_selected);
        home_page_viewPager.setBorderAnimation(true);
        home_page_viewPager.setInterval(3000);
        home_page_viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        home_page_viewPager.setAdapter(new HomeViewPageAdapter(images));
        home_page_viewPager.startAutoScroll();
        home_page_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int count = home_page_viewPager_spot.getChildCount();
                for (int i = 0; i < count; i++) {
                    ImageView img = (ImageView) home_page_viewPager_spot.getChildAt(i);
                    if (i != position % (images.size() / 2)) {
                        img.setImageResource(R.drawable.banner_spot_default);
                    } else {
                        img.setImageResource(R.drawable.banner_spot_selected);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 设置图片轮播的详细操作
     *
     * @param info
     * @param i
     */
    private void setPicturePlayItem(final HomeInfo info, int i, List<ImageView> images) {
//        设置ViewPager的图片
        String pic_url = info.getPromotion().get(i).getPic_url();
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        ImageView image = new ImageView(context);
        image.setLayoutParams(ll);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        String path = application.getPicPath() + pic_url;
        Glide.with(context).load(path).placeholder(R.mipmap.default_img).into(image);
        final int finalI = i;
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = info.getPromotion().get(finalI).getPromotion_title();
                String url = info.getPromotion().get(finalI).getPromotion_url();
                Intent intent = new Intent(context, HomePage_BannerActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        images.add(image);
//        设置原点
        if (home_page_viewPager_spot.getChildCount() == i) {
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llp.rightMargin = 10;
            ImageView spot = new ImageView(context);
            spot.setLayoutParams(llp);
            spot.setImageResource(R.drawable.banner_spot_default);
            home_page_viewPager_spot.addView(spot);
        }

    }


    /**
     * 下载访问的信息
     */
    private void downDirectionInfo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://quhuwaiwap.oss-cn-beijing.aliyuncs.com/").addConverterFactory(GsonConverterFactory.create()).build();
        HomeRequestData homeRequestData = retrofit.create(HomeRequestData.class);
        Call<Direction> call = homeRequestData.getDirectionData();
        call.enqueue(new Callback<Direction>() {
            @Override
            public void onResponse(Call<Direction> call, Response<Direction> response) {
                Direction info = response.body();
                for (int i = 0; i < info.getIcon().size(); i++) {
                    LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    View view = View.inflate(context, R.layout.fragment_home_page_item_direction, null);
                    ll.weight = 1;
                    view.setLayoutParams(ll);
                    ImageView driection_icon = (ImageView) view.findViewById(R.id.direction_icon);
                    Glide.with(context).load(info.getIcon().get(i).getIcon_url()).into(driection_icon);
                    home_page_direction.addView(view);
                }
            }

            @Override
            public void onFailure(Call<Direction> call, Throwable t) {

            }
        });
    }


    /**
     * 跳转到搜索的Activity
     *
     * @param view
     */
    @OnClick(R.id.home_page_search)
    public void onClick(View view) {
        Intent intent = new Intent(context, HomePage_SearchActivity.class);
        startActivity(intent);
    }

}
