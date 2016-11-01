package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.HomeViewPageAdapter;
import com.phone.funoutdoors.bean.Scene;
import com.phone.funoutdoors.interfaces.HomeRequestData;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.utils.HomePageUtils;
import com.phone.funoutdoors.utils.ZoomOutPageTransformer;

import java.io.Serializable;
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

public class HomePage_SceneActivity extends AppCompatActivity {

    @BindView(R.id.home_page_toolbar)
    Toolbar home_page_toolbar;
    @BindView(R.id.home_page_banner_toolbar_title)
    TextView home_page_banner_toolbar_title;
    @BindView(R.id.home_page_scene_layout)
    RelativeLayout home_page_scene_layout;
    @BindView(R.id.home_page_scene_no_layout)
    RelativeLayout home_page_scene_no_layout;
    @BindView(R.id.destination_summary)
    TextView destination_summary;
    @BindView(R.id.tip_info)
    TextView tip_info;
    @BindView(R.id.expand_tip)
    ImageView expand_tip;
    @BindView(R.id.scene_item_video)
    LinearLayout scene_item_video;
    @BindView(R.id.home_page_viewPager_spot)
    LinearLayout home_page_viewPager_spot;
    @BindView(R.id.scene_item_route)
    LinearLayout scene_item_route;
    private Scene scene;
    @BindView(R.id.home_page_viewPager)
    AutoScrollViewPager home_page_viewPager;
    @BindView(R.id.home_page_scrollView)
    ScrollView home_page_scrollView;
    private boolean isExtand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_scene);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int destination_id = intent.getIntExtra("destination_id", 0);
        HomePageUtils.setToolbar(this, home_page_toolbar, home_page_banner_toolbar_title, title, R.menu.home_page_toolbar_map_menu);
        downSceneJson(destination_id);

        home_page_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(HomePage_SceneActivity.this, HomePage_Scene_MapActivity.class);
                intent.putExtra("destination_alti", scene.getResultList().get(0).getDestination_lati());
                intent.putExtra("destination_longi", scene.getResultList().get(0).getDestination_longi());
                startActivity(intent);
                return true;
            }
        });
    }

    /**
     * 下载详细页的网络数据
     *
     * @param destination_id
     */
    private void downSceneJson(int destination_id) {
        if (HomePageUtils.isNetworkConnected(this)) {
            home_page_scene_layout.setVisibility(View.VISIBLE);
            home_page_scene_no_layout.setVisibility(View.INVISIBLE);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.quhuwai.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            HomeRequestData request = retrofit.create(HomeRequestData.class);
            Call<Scene> call = request.getSceneData(destination_id);
            call.enqueue(new Callback<Scene>() {
                @Override
                public void onResponse(Call<Scene> call, Response<Scene> response) {
                    scene = response.body();
                    setPicturePlayItem(scene);
                    destination_summary.setText(scene.getResultList().get(0).getDestination_summary());
                    List<Scene.ResultListBean.TopRealScenesBean> list = scene.getResultList().get(0).getTop_real_scenes();
                    HomePageUtils.setQubo(getApplicationContext(), list, scene_item_video, null);
                    setRouter();
                }

                @Override
                public void onFailure(Call<Scene> call, Throwable t) {

                }
            });
        } else {
            home_page_scene_no_layout.setVisibility(View.VISIBLE);
            home_page_scene_layout.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置图片轮播的详细操作
     */
    private void setPicturePlayItem(final Scene scene) {
//        设置ViewPager的图片
        List<ImageView> images = new ArrayList<>();
        List<Scene.ResultListBean.ImgsBean> list = scene.getResultList().get(0).getImgs();
        for (int i = 0; i < list.size(); i++) {
            String pic_url = list.get(i).getImg_url().split("#")[1];
            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
            ImageView image = new ImageView(this);
            image.setLayoutParams(ll);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String path = Constant.PICPATH + pic_url;
            Glide.with(getApplication()).load(path).placeholder(R.mipmap.default_img).into(image);
            final int finalI = i;
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                todo 大屏显示图片
                }
            });
            images.add(image);
//        设置原点
            int childCount = home_page_viewPager_spot.getChildCount();
            if (childCount == i) {
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                llp.rightMargin = 10;
                ImageView spot = new ImageView(this);
                spot.setLayoutParams(llp);
                spot.setImageResource(R.drawable.banner_spot_default);
                home_page_viewPager_spot.addView(spot);
            }
        }
        ImageView spot = (ImageView) home_page_viewPager_spot.getChildAt(0);
        spot.setImageResource(R.drawable.banner_spot_selected);
        home_page_viewPager.setAdapter(new HomeViewPageAdapter(images));
        home_page_viewPager.startAutoScroll();
        home_page_viewPager.setBorderAnimation(false);
        home_page_viewPager.setInterval(3000);
        home_page_viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        home_page_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int count = home_page_viewPager_spot.getChildCount();
                for (int i = 0; i < count; i++) {
                    ImageView img = (ImageView) home_page_viewPager_spot.getChildAt(i);
                    if (i != position) {
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
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.tip_scene, R.id.more_qubo, R.id.profile_url})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tip_scene:
//                小贴士的提示
                tip_info.setText(scene.getResultList().get(0).getDestination_tips());
                if (isExtand) {
                    tip_info.setVisibility(View.GONE);
                    expand_tip.setImageResource(R.mipmap.tips_shape1);
                    isExtand = false;
                } else {
                    tip_info.setVisibility(View.VISIBLE);
                    expand_tip.setImageResource(R.mipmap.tips_shape);
                    tip_info.setPadding(0, 20, 0, 0);
                    isExtand = true;
                }
                break;
            case R.id.more_qubo:
//                趣播更多
                Intent intent = new Intent(this, HomePage_Scene_MoreActivity.class);
                List<Scene.ResultListBean.TopRealScenesBean> list = scene.getResultList().get(0).getTop_real_scenes();
                intent.putExtra("list", (Serializable) list);
                startActivity(intent);
                break;
            case R.id.profile_url:
//                目的地跳转
                Intent intent1 = new Intent(HomePage_SceneActivity.this, HomePage_BannerActivity.class);
                intent1.putExtra("title", scene.getResultList().get(0).getDestination_name());
                intent1.putExtra("url", scene.getResultList().get(0).getProfile_url());
                startActivity(intent1);
                break;
        }

    }


    /**
     * 设置路线的布局
     */
    private void setRouter() {
        int size = scene.getResultList().get(0).getRelation_routes().size();
        final List<Scene.ResultListBean.RelationRoutesBean> routes = scene.getResultList().get(0).getRelation_routes();
        if (size > 0) {
            for (int i = 0; i < routes.size(); i++) {
                View view = View.inflate(HomePage_SceneActivity.this, R.layout.activity_home_page_scene_router_item, null);
                ImageView scene_item_route_image = (ImageView) view.findViewById(R.id.scene_item_route_image);
                TextView scene_item_route_title = (TextView) view.findViewById(R.id.scene_item_route_title);
                TextView scene_item_region = (TextView) view.findViewById(R.id.scene_item_region);
                TextView scene_item_route_state = (TextView) view.findViewById(R.id.scene_item_route_state);
                Glide.with(HomePage_SceneActivity.this).load(Constant.PICPATH + routes.get(i).getRoute_cover()).into(scene_item_route_image);
                scene_item_route_title.setText(routes.get(i).getRoute_title());
                switch (routes.get(i).getCity_id()) {
                    case 269:
                        SpannableStringBuilder spannable = new SpannableStringBuilder("成都市");
                        spannable.setSpan(new ForegroundColorSpan(Color.rgb(255, 166, 0)), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        scene_item_region.append(spannable);
                        break;
                }
                SpannableStringBuilder spannable = new SpannableStringBuilder(routes.get(i).getRoute_stdate());
                spannable.setSpan(new ForegroundColorSpan(Color.rgb(255, 166, 0)), 0, routes.get(i).getRoute_stdate().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                scene_item_route_state.append(spannable);

                final int finalI = i;
                scene_item_route_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomePage_SceneActivity.this, HomePage_BannerActivity.class);
                        intent.putExtra("title", routes.get(finalI).getRoute_title());
                        intent.putExtra("url", routes.get(finalI).getWapurl());
                        startActivity(intent);
                    }
                });
                if (scene_item_route.getChildCount() == i) {
                    scene_item_route.addView(view);
                }
            }


        }
    }


}
