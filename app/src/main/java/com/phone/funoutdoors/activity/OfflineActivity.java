package com.phone.funoutdoors.activity;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.OfflineCityExpandListAdapter;
import com.phone.funoutdoors.adapter.OfflineCityListAdapter;
import com.phone.funoutdoors.view.MyExpandableListView;
import com.phone.funoutdoors.view.MyListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OfflineActivity extends AppCompatActivity implements MKOfflineMapListener {
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.search_city)
    EditText searchCity;
    @BindView(R.id.auto_list)
    ListView autoList;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private MKOfflineMap mkOfflineMap = null;
    private ArrayList<MKOLSearchRecord> list = new ArrayList<MKOLSearchRecord>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        SDKInitializer.initialize(getApplicationContext());
        ButterKnife.bind(this);
        initToolbar();
        mkOfflineMap = new MKOfflineMap();
        mkOfflineMap.init(this);
        initCurLocation();
        setHotCity();
        setAllCity();
        searchCity();
    }

    /**
     *
     */
    private void searchCity() {
        final OfflineCityListAdapter adapter = new OfflineCityListAdapter(list, mkOfflineMap, this);
        autoList.setAdapter(adapter);
        searchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                autoList.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<MKOLSearchRecord> mkolSearchRecords = mkOfflineMap.searchCity(searchCity.getText().toString());
                list = mkolSearchRecords;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<MKOLSearchRecord> records = mkOfflineMap.searchCity(searchCity.toString());
                list = records;
                adapter.notifyDataSetChanged();
            }
        });
        autoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                downMap(view, list.get(position).cityID);
            }
        });
    }

    /**
     * 修改开始下载地图时各个控件的状态
     *
     * @param v
     */
    private void downMap(View v, int cityId) {
        TextView state = (TextView) v.findViewById(R.id.state);
        mkOfflineMap.start(cityId);
        state.setText("正在下载...");
        state.setBackgroundResource(R.color.colorAccent);
    }

    /**
     *
     */
    private void initToolbar() {
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    LocationManager lm = null; // location管理器
    LocationClient mLocClient;

    private void initCurLocation() {
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (lm != null) {
            // 定位初始化
            mLocClient = new LocationClient(this);
            mLocClient.registerLocationListener(new MyLocationListenner());
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true);// 打开GPS
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setPriority(LocationClientOption.NetWorkFirst);//设置网络优先(不设置，默认是gps优先)
            option.setAddrType("all");// 返回的定位结果包含地址信息
            option.setScanSpan(10000);// 设置发起定位请求的间隔时间为10s(小于1秒则一次定位)
            mLocClient.setLocOption(option);
            mLocClient.start();

        } else {
            Toast.makeText(this, "请打开GPS定位设置", Toast.LENGTH_SHORT);
        }
    }

    public void setCurrentLocation(String currentLocation) {
        ArrayList<MKOLSearchRecord> offlineCityList = mkOfflineMap.getOfflineCityList();
        MKOLSearchRecord searchRecord = null;
        for (int i = 0; i < offlineCityList.size(); i++) {
            if (offlineCityList.get(i).cityName.equals(currentLocation)) {
                searchRecord = offlineCityList.get(i);
            }
        }
        TextView cityName = (TextView) findViewById(R.id.cityName);
        TextView size = (TextView) findViewById(R.id.size);
        final TextView state = (TextView) findViewById(R.id.state);
        RelativeLayout pre_city = (RelativeLayout) findViewById(R.id.pre_city);
        cityName.setText(currentLocation);
        size.setText(searchRecord.size / 1024 / 1024 + "M");
        final MKOLUpdateElement updateInfo = mkOfflineMap.getUpdateInfo(searchRecord.cityID);
        if (updateInfo == null) {
            state.setBackgroundResource(R.mipmap.dow_01);
        } else {
            state.setText("已下载");
            state.setBackgroundResource(R.color.colorAccent);
        }
        final MKOLSearchRecord finalSearchRecord = searchRecord;
        pre_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateInfo == null) {
                    mkOfflineMap.start(finalSearchRecord.cityID);
                    state.setText("正在下载...");
                    state.setBackgroundResource(R.color.colorAccent);
                } else {
                    Intent intent = new Intent(OfflineActivity.this, OfflineMapManagerActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
                }
            }
        });
    }


    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            String city = location.getCity();
            setCurrentLocation(city);
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    /**
     *
     */
    private void setAllCity() {
        final MyExpandableListView expandableListView = (MyExpandableListView) findViewById(R.id.allcitylist);
        expandableListView.setChildIndicator(null);
        expandableListView.setGroupIndicator(null);
        final ArrayList<MKOLSearchRecord> offlineCityList = mkOfflineMap.getOfflineCityList();
        OfflineCityExpandListAdapter offlineCityExpandListAdapter = new OfflineCityExpandListAdapter(offlineCityList, mkOfflineMap, this);
        expandableListView.setAdapter(offlineCityExpandListAdapter);
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                downMap(view, offlineCityList.get(position).cityID);
            }
        });
    }

    /**
     * 设置热门城市
     */
    private void setHotCity() {
        MyListView myListView = (MyListView) findViewById(R.id.hotcitylist);
        final ArrayList<MKOLSearchRecord> hotCityList = mkOfflineMap.getHotCityList();
        OfflineCityListAdapter adapter = new OfflineCityListAdapter(hotCityList, mkOfflineMap, this);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                downMap(view, hotCityList.get(position).cityID);
            }
        });
    }

    @OnClick({R.id.localButton})
    public void onClick(View view) {
        Intent intent = new Intent(this, OfflineMapManagerActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
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
