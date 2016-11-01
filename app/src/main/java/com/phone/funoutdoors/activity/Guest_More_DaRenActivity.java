package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.phone.funoutdoors.adapter.GuestMoreDaRenAdapter;
import com.phone.funoutdoors.bean.MoreDaRen;
import com.phone.funoutdoors.interfaces.OnUserIconClickListener;
import com.phone.funoutdoors.utils.HomePageUtils;
import com.phone.funoutdoors.view.MySwipeRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Guest_More_DaRenActivity extends AppCompatActivity {

    @BindView(R.id.home_page_toolbar)
    Toolbar home_page_toolbar;
    @BindView(R.id.home_page_banner_toolbar_title)
    TextView home_page_banner_toolbar_title;
    @BindView(R.id.guest_recycle)
    ListView guest_recycle;
    @BindView(R.id.mySwipeRefreshView)
    MySwipeRefreshView mySwipeRefreshView;
    private RequestQueue requestQueue;
    private List<MoreDaRen.ResultListBean> totalList = new ArrayList<>();
    private int pageNo = 1;
    private GuestMoreDaRenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_more_daren);
        ButterKnife.bind(this);
        HomePageUtils.setToolbar(this, home_page_toolbar, home_page_banner_toolbar_title, "达人", 0);
        adapter = new GuestMoreDaRenAdapter(totalList, getApplicationContext());
        guest_recycle.setAdapter(adapter);
        downParseJson(pageNo);
        mySwipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                downParseJson(pageNo);
            }
        });
        mySwipeRefreshView.setOnUpRefreshListener(new MySwipeRefreshView.OnUpRefreshListener() {
            @Override
            public void onUpRefreshListener() {
                pageNo++;
                downParseJson(pageNo);
            }
        });
        mySwipeRefreshView.setColorSchemeColors(Color.BLACK, Color.GREEN, Color.BLUE);
        adapter.setOnUserIconClickListener(new OnUserIconClickListener() {
            @Override
            public void onUserIconClickListener(int userId) {
                Intent intent = new Intent(Guest_More_DaRenActivity.this, Guest_DaRen_InfoActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
    }

    /**
     * 下载并解析Json
     */
    private void downParseJson(final int pageNo) {
        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://www.quhuwai.cn/webservice/qhw2001/func_sub3105",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        MoreDaRen moreDaRen = gson.fromJson(s, MoreDaRen.class);
                        List<MoreDaRen.ResultListBean> resultList = moreDaRen.getResultList();
                        totalList.addAll(resultList);
                        adapter.notifyDataSetChanged();
                        mySwipeRefreshView.setRefreshing(false);
                        mySwipeRefreshView.setOnLoading(false);
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
                map.put("pageSize", "10");
                map.put("pageNo", String.valueOf(pageNo));
                map.put("userId", "0");
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
