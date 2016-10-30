package com.phone.funoutdoors.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.overlayutil.WalkingRouteOverlay;
import com.phone.funoutdoors.utils.HomePageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePage_Scene_MapActivity extends AppCompatActivity {

    @BindView(R.id.home_page_toolbar)
    Toolbar home_page_toolbar;
    @BindView(R.id.home_page_banner_toolbar_title)
    TextView home_page_banner_toolbar_title;
    @BindView(R.id.mMapView)
    MapView mMapView;
    @BindView(R.id.location)
    ImageView location;
    @BindView(R.id.layout)
    LinearLayout layout;
    public BDLocationListener myListener = new MyBDListner();
    private BaiduMap map;
    private LocationClient mLocationClient;
    private double destination_alti, destination_longi, latitude, longitude;
    private RoutePlanSearch search;
    private PopupWindow pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_home_page_scene_map);
        ButterKnife.bind(this);
        HomePageUtils.setToolbar(this, home_page_toolbar, home_page_banner_toolbar_title, "地图", 0);

        destination_alti = Double.parseDouble(getIntent().getStringExtra("destination_alti"));
        destination_longi = Double.parseDouble(getIntent().getStringExtra("destination_longi"));

        mMapView.removeViewAt(2);//去掉缩小放大的图标
        map = mMapView.getMap();
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        setMarker(destination_alti, destination_longi);
    }


    /**
     * 导航的弹出窗体
     *
     * @param v
     */
    public void onShowUp(View v) {
        View view = View.inflate(HomePage_Scene_MapActivity.this, R.layout.navigation_item, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMyLocation();//获取我的位置
                getRoute();//设置路线
                if (latitude != 0 && longitude != 0) {
                    LatLng start = new LatLng(latitude, longitude);
                    LatLng end = new LatLng(destination_alti, destination_longi);
                    PlanNode startNode = PlanNode.withLocation(start);
                    PlanNode endNode = PlanNode.withLocation(end);
                    search.walkingSearch(new WalkingRoutePlanOption().from(startNode).to(endNode));
                    pop.dismiss();
                }
            }
        });
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(new ColorDrawable());
        pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }


    /**
     * 设置地图类型的弹出窗体
     */
    private void onShowMapTypeWindow() {
        View view1 = View.inflate(HomePage_Scene_MapActivity.this, R.layout.map_type_show, null);
        ImageView map_c = (ImageView) view1.findViewById(R.id.map_c);
        ImageView map_s = (ImageView) view1.findViewById(R.id.map_s);
        map_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            }
        });
        map_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
            }
        });
        pop = new PopupWindow(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(new ColorDrawable());
        pop.showAtLocation(layout, Gravity.NO_GRAVITY, layout.getLeft(), layout.getTop() - 20);
    }

    /**
     * 获取路线
     */
    private void getRoute() {
        search = RoutePlanSearch.newInstance();
        search.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                if (walkingRouteResult != null && walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    map.clear();
                    WalkingRouteOverlay overlay = new WalkingRouteOverlay(map);
                    overlay.setData(walkingRouteResult.getRouteLines().get(0));
                    overlay.addToMap();
                    overlay.zoomToSpan();
                }
            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        });
    }

    /**
     * 设置标记点
     */
    private void setMarker(double lati, double longi) {
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.map_location_red);
        LatLng point = new LatLng(lati, longi);
        OverlayOptions options = new MarkerOptions()
                .position(point)  //设置marker的位置
                .icon(bitmap)  //设置marker图标
                .zIndex(9)  //设置marker所在层级
                .draggable(true);  //设置手势拖拽
//将marker添加到地图上
        Marker marker = (Marker) (map.addOverlay(options));
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(15)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        map.setMapStatus(mMapStatusUpdate);
    }

    /**
     * 设置定位
     */
    private void setMyLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    /**
     * 定位的接口
     */
    class MyBDListner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //   根据返回结果值实例化   位置参数信息
            MyLocationData data = new MyLocationData.Builder().latitude(bdLocation.getLatitude()).longitude(bdLocation.getLongitude()).direction(bdLocation.getDirection()).build();
            // 在地图上设置 位置
            map.setMyLocationData(data);
            latitude = bdLocation.getLatitude();
            longitude = bdLocation.getLongitude();

        }
    }

    /**
     * 点击事件(地图类型，我的位置)
     *
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mapType:
                onShowMapTypeWindow();
                break;
            case R.id.location:
                map.setMyLocationEnabled(true);
                setMyLocation();
                onShowUp(location);
                break;

        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

}
