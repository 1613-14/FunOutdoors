package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.HomeListViewAdpater;
import com.phone.funoutdoors.bean.Scene;
import com.phone.funoutdoors.utils.HomePageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePage_Scene_MoreActivity extends AppCompatActivity {

    @BindView(R.id.scene_more)
    ListView scene_more;
    @BindView(R.id.home_page_toolbar)
    Toolbar home_page_toolbar;
    @BindView(R.id.home_page_banner_toolbar_title)
    TextView home_page_banner_toolbar_title;
    @BindView(R.id.scene_more_swipe)
    SwipeRefreshLayout scene_more_swipe;
    private HomeListViewAdpater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_scene_more);
        ButterKnife.bind(this);
        HomePageUtils.setToolbar(this,home_page_toolbar,home_page_banner_toolbar_title,"趣播",R.menu.home_page_toolbar_photo_menu);
        final List<Scene.ResultListBean.TopRealScenesBean> list =  (List<Scene.ResultListBean.TopRealScenesBean>) getIntent().getSerializableExtra("list");
         adapter = new HomeListViewAdpater(list, this);
        scene_more.setAdapter(adapter);
       scene_more.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(HomePage_Scene_MoreActivity.this,HomePage_QuboActivity.class);
               int scene_id = list.get(position).getScene_id();
               intent.putExtra("scene_id",scene_id);
               intent.putExtra("tag",0);
               startActivity(intent);
           }
       });
        scene_more_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                scene_more_swipe.setRefreshing(false);
            }
        });
    }
}
