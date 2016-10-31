package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.OfflineMapDownedListAdapter;
import com.phone.funoutdoors.adapter.OfflineMapDowningListAdapter;
import com.phone.funoutdoors.view.MyListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OfflineMapManagerActivity extends AppCompatActivity implements MKOfflineMapListener {

    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.downinglist)
    MyListView downinglist;
    @BindView(R.id.downedlist)
    MyListView downedlist;
    MKOfflineMap mkOfflineMap = null;
    private ArrayList<MKOLUpdateElement> allUpdateInfo = new ArrayList<>();
    private OfflineMapDownedListAdapter downedAdapter;
    private ArrayList<MKOLUpdateElement> downingInfo = new ArrayList<>();
    private OfflineMapDowningListAdapter downingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_map_manager);
        ButterKnife.bind(this);
        mkOfflineMap = new MKOfflineMap();
        mkOfflineMap.init(this);
        initToolBar();
        setDowninglist();
        setDownedList();
    }

    /**
     * 设置下载列表
     */
    private void setDownedList() {
        allUpdateInfo = mkOfflineMap.getAllUpdateInfo();
        downedAdapter = new OfflineMapDownedListAdapter(allUpdateInfo, this);
        downedlist.setAdapter(downedAdapter);
        downedlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.findViewById(R.id.handle_down_map).setVisibility(View.VISIBLE);
                handlerDownedCity(view, allUpdateInfo.get(position).cityID);
            }
        });
    }

    /**
     * 设置已下载城市的操作
     *
     * @param view
     * @param cityId
     */
    private void handlerDownedCity(View view, final int cityId) {
        view.findViewById(R.id.query_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看地图
            }
        });
        view.findViewById(R.id.down_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //下载更新
                MKOLUpdateElement updateInfo = mkOfflineMap.getUpdateInfo(cityId);
                if (updateInfo.update) {
                    mkOfflineMap.update(cityId);
                }
            }
        });
        view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                mkOfflineMap.remove(cityId);
                allUpdateInfo = mkOfflineMap.getAllUpdateInfo();
                downedAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 设置正在下载列表
     */
    private void setDowninglist() {
        ArrayList<MKOLUpdateElement> allUpdateInfo = mkOfflineMap.getAllUpdateInfo();
        if(allUpdateInfo != null){
            for (int i = 0; i < allUpdateInfo.size(); i++) {
                MKOLUpdateElement updateElement = allUpdateInfo.get(i);
                if (updateElement.ratio < 100) {
                    downingInfo.add(updateElement);
                }
            }
        }
        downingListAdapter = new OfflineMapDowningListAdapter(downingInfo, this);
        downinglist.setAdapter(downedAdapter);
        downinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handlerDowing(view, downingInfo.get(position).cityID);
            }
        });
    }

    /**
     * 设置下载任务
     *
     * @param view
     * @param cityID
     */
    private void handlerDowing(View view, final int cityID) {
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        MKOLUpdateElement updateInfo = mkOfflineMap.getUpdateInfo(cityID);
        progressBar.setProgress(updateInfo.ratio);
        view.findViewById(R.id.select_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看地图

            }
        });
        view.findViewById(R.id.stop_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂停下载
                mkOfflineMap.pause(cityID);
            }
        });
        view.findViewById(R.id.delete_downing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除下载任务
                mkOfflineMap.remove(cityID);
                downingInfo = mkOfflineMap.getAllUpdateInfo();
                downingListAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 设置toolbar
     */
    private void initToolBar() {
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfflineMapManagerActivity.this, OfflineActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.tran_previous_in, R.anim.tran_previous_out);
            }
        });
    }

    @OnClick(R.id.clButton)
    public void onClick() {
        Intent intent = new Intent(this, OfflineActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.tran_previous_in, R.anim.tran_previous_out);
    }

    @Override
    public void onGetOfflineMapState(int i, int i1) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mkOfflineMap.destroy();
    }
}
