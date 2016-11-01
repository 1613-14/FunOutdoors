package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.phone.funoutdoors.adapter.HomeSearchListViewAdapter;
import com.phone.funoutdoors.bean.DaRenList;
import com.phone.funoutdoors.bean.QuboList;
import com.phone.funoutdoors.bean.SceneList;
import com.phone.funoutdoors.bean.TravelList;
import com.phone.funoutdoors.utils.HomePageUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePage_Search_ItemActivity extends AppCompatActivity {

    @BindView(R.id.home_page_toolbar)
    Toolbar home_page_toolbar;
    @BindView(R.id.home_page_banner_toolbar_title)
    TextView home_page_banner_toolbar_title;
    @BindView(R.id.search_listView)
    ListView search_listView;
    private RequestQueue requestQueue;
    private int pageNo;
    private HomeSearchListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_search_item);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        final int flag = intent.getIntExtra("flag", 1);
        final String keyword = intent.getStringExtra("keyword");
        String search_type = intent.getStringExtra("search_type");
        home_page_banner_toolbar_title.setTextColor(getResources().getColor(R.color.blue));
        HomePageUtils.setToolbar(this, home_page_toolbar, home_page_banner_toolbar_title, search_type, 0);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = View.inflate(HomePage_Search_ItemActivity.this, R.layout.activity_home_page_search_error_item, null);
        ll.gravity = Gravity.CENTER;
        view.setLayoutParams(ll);
        ((ViewGroup) search_listView.getParent()).addView(view);
        search_listView.setEmptyView(view);


        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://www.quhuwai.cn/webservice/qhw1001/func_sub0001",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        switch (flag) {
                            case 1:
                                SceneList search = gson.fromJson(s, SceneList.class);
                                List<SceneList.ResultListBean> list = search.getResultList();
                                search_listView.setAdapter(new HomeSearchListViewAdapter(getApplicationContext(), list, 1));
                                break;
                            case 2:

                                break;
                            case 3:
                                TravelList travel = gson.fromJson(s, TravelList.class);
                                List<TravelList.TravelListBean> travelList = travel.getResultList();
                                search_listView.setAdapter(new HomeSearchListViewAdapter(getApplicationContext(), travelList, 3, 0));
                                break;
                            case 4:
                                DaRenList daren = gson.fromJson(s, DaRenList.class);
                                List<DaRenList.DaListBean> daList = daren.getResultList();
                                search_listView.setAdapter(new HomeSearchListViewAdapter(getApplicationContext(), 4, daList));
                                break;
                            case 5:
                                QuboList qubo = gson.fromJson(s, QuboList.class);
                                List<QuboList.ResultListBean> quboList = qubo.getResultList();
                                search_listView.setAdapter(new HomeSearchListViewAdapter(quboList, getApplicationContext(), 5));
                                break;

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("pageSize", "10");
                map.put("searchFlg", String.valueOf(flag));
                map.put("keyWord", keyword);
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
}
