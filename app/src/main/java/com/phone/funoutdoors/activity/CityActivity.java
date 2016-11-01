package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.CityListAdapter;
import com.phone.funoutdoors.interfaces.OnTouchingLetterChangeListener;
import com.phone.funoutdoors.utils.CityData;
import com.phone.funoutdoors.view.MySideBar;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity implements OnTouchingLetterChangeListener {

    private MapView mMapView;
    private LocationClient mLocationClient;
    private BaiduMap mBaiduMap;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private BitmapDescriptor mCurrentMarker;
    private ListView mainList;
    private MySideBar myView;
    private TextView tvMain;
    private List<String> data = new ArrayList<String>();
    private List<Integer> letterCharList = new ArrayList<>();
    private List<Integer> letterPositionList = new ArrayList<>();
    private String[] title = {"当前城市", "热门城市", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
    private int lastFirstVisibleItem;
    private String city;
    boolean isFirstLoc = true; // 是否首次定位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_city);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mLocationClient = new LocationClient(this);
        mBaiduMap = mMapView.getMap();
        //开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd0911");
        option.setScanSpan(1000);
        //设置定位完成后需要返回地址
        option.setIsNeedAddress(true);
        //设置定位完成后需要的定位描述
        option.setIsNeedLocationDescribe(true);
        mLocationClient.start();

        mainList = (ListView) findViewById(R.id.mainlist);
        myView = (MySideBar) findViewById(R.id.myview);
        tvMain = (TextView) findViewById(R.id.main_tv01);
        myView.setOnTouchingLetterChangedListener(this);
        tvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("city", city);
                setResult(100);
                finish();
            }
        });
        int index = 0, position = 0;
        letterCharList.add(index);
        for (int i = 0; i < CityData.array.length; i++) {
            for (int j = 0; j < CityData.array[i].length; j++) {
                if (i == 0 && j == 0) {
                    index++;
                    letterPositionList.add(position);
                } else if (j == 0) {
                    letterCharList.add(index);
                    letterPositionList.add(position);
                    index++;
                } else {
                    letterCharList.add(-1);
                }
                position++;
                data.add(CityData.array[i][j]);
            }
            CityListAdapter adapter = new CityListAdapter(this, data, letterCharList, title);
            mainList.setAdapter(adapter);
            mainList.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (letterCharList.get(firstVisibleItem) >= 0) {
                        tvMain.setText(title[letterCharList.get(firstVisibleItem)]);
                        if (TextUtils.isEmpty(city)) {
                            tvMain.setText("当前城市");
                        } else {
                            tvMain.setText("当前城市" + city);
                        }

                        lastFirstVisibleItem = firstVisibleItem;
                    } else {
                        if (lastFirstVisibleItem > firstVisibleItem) {
                            tvMain.setText(city);
                        }
                    }
                }
            });
            //点击item项，转换成要定位的城市
            mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

//          Toast.makeText(getApplicationContext(), mainList.getItemAtPosition(position)+"我被点击了。。。", 0).show();
                    Toast.makeText(getApplicationContext(), "当前城市" + mainList.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                    tvMain.setText((CharSequence) mainList.getItemAtPosition(position));//将城市定位的位置换成点击的城市
                }
            });
        }


    }


    @Override
    public void onTouchingLetterChanged(int s) {
        mainList.setSelection(letterPositionList.get(s));
    }

    class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
//              tv_map.setText("[我的位置]\n" + location.getStreet());
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            //定位当前城市
            city = location.getCity();
            tvMain.setText(city);//将当前定位的城市设置给textview
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mLocationClient.stop();
        //定位层关闭
        mBaiduMap.setMyLocationEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

}
