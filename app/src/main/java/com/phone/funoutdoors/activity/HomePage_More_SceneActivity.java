package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.HomeSceneMoreListViewAdapter;
import com.phone.funoutdoors.bean.MoreScene;
import com.phone.funoutdoors.utils.HomePageUtils;
import com.phone.funoutdoors.view.MySwipeRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePage_More_SceneActivity extends AppCompatActivity {

    @BindView(R.id.home_page_toolbar)
    Toolbar home_page_toolbar;
    @BindView(R.id.home_page_banner_toolbar_image)
    TextView home_page_banner_toolbar_image;
    @BindView(R.id.more_scene_listView)
    ListView more_scene_listView;
    @BindView(R.id.home_page_banner_toolbar_title)
    TextView home_page_banner_toolbar_title;
    @BindView(R.id.more_scene_refresh)
    MySwipeRefreshView more_scene_refresh;
    private int region_id, width, pageNo = 1;
    private String[] titles = new String[]{"西南", "西北", "东北", "华东", "华南", "华北"};
    private RequestQueue requestQueue;
    List<MoreScene.ResultListBean> totalList = new ArrayList<>();
    private HomeSceneMoreListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_more_scene);
        ButterKnife.bind(this);
        width = getResources().getDisplayMetrics().widthPixels;
        region_id = getIntent().getIntExtra("region_id", 1);
        setPopWindow();
        downParseJson(region_id, pageNo);
        initListener();
        adapter = new HomeSceneMoreListViewAdapter(totalList, HomePage_More_SceneActivity.this);
        more_scene_listView.setAdapter(adapter);
    }

    /**
     * 设置SwipeRefreshView的监听事件
     */
    private void initListener() {
        more_scene_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                totalList.clear();
                downParseJson(region_id, 1);

            }
        });
        more_scene_refresh.setOnUpRefreshListener(new MySwipeRefreshView.OnUpRefreshListener() {
            @Override
            public void onUpRefreshListener() {
                pageNo++;
                downParseJson(region_id, pageNo);
            }
        });
    }

    /**
     * 下载并解析JSON
     */
    private void downParseJson(final int region_id, final int pageNo) {
        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://www.quhuwai.cn/webservice/qhw1001/func_sub1005",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        MoreScene scene = gson.fromJson(s, MoreScene.class);
                        setLayoutData(scene);
                        totalList.addAll(scene.getResultList());
                        adapter.notifyDataSetChanged();
                        more_scene_refresh.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("region_id", String.valueOf(region_id));
                map.put("pageSize", "10");
                map.put("pageNo", String.valueOf(pageNo));
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                return map;
            }
        };
        requestQueue.add(request);
    }


    /**
     * 设置布局数据
     *
     * @param scene
     */
    private void setLayoutData(MoreScene scene) {
        final List<MoreScene.ResultListBean> list = scene.getResultList();
        totalList.addAll(list);
        adapter.notifyDataSetChanged();
        more_scene_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomePage_More_SceneActivity.this, HomePage_SceneActivity.class);
                intent.putExtra("title", list.get(position).getDestination_name());
                intent.putExtra("destination_id", list.get(position).getDestination_id());
                startActivity(intent);
            }
        });
    }


    /**
     * 设置弹出窗体
     */
    private void setPopWindow() {

        home_page_banner_toolbar_title.setVisibility(View.GONE);
        home_page_banner_toolbar_image.setVisibility(View.VISIBLE);
        HomePageUtils.setToolbar(this, home_page_toolbar, home_page_banner_toolbar_image, titles[region_id - 1], 0);
        home_page_banner_toolbar_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(HomePage_More_SceneActivity.this, R.layout.activity_home_page_more_scene_pop, null);
                ListView more_scene_pop = (ListView) view.findViewById(R.id.more_scene_pop);
                ArrayAdapter adapter = new ArrayAdapter(HomePage_More_SceneActivity.this, R.layout.activity_home_page_more_scene_pop_item, R.id.dialog_listview, titles);
                more_scene_pop.setAdapter(adapter);
                view.setBackgroundResource(R.mipmap.model_pop_bg_c);
                final PopupWindow pop = new PopupWindow(view, width / 2, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                pop.setOutsideTouchable(true);
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.showAsDropDown(home_page_banner_toolbar_image, -width / 4 + 25, 25);
                more_scene_pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        region_id = position + 1;
                        HomePageUtils.setToolbar(HomePage_More_SceneActivity.this, home_page_toolbar, home_page_banner_toolbar_image, titles[position], 0);
                        totalList.clear();
                        downParseJson(position + 1, 1);
                        pop.dismiss();
                    }
                });
            }
        });
    }

}
