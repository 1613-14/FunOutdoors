package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.phone.funoutdoors.adapter.HomeQuboMoreRecyclerAdapter;
import com.phone.funoutdoors.bean.MoreQubo;
import com.phone.funoutdoors.interfaces.HomeRecyclerListener;
import com.phone.funoutdoors.utils.HomePageUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Guest_More_InivateActivity extends AppCompatActivity {

    @BindView(R.id.home_page_toolbar)
    Toolbar home_page_toolbar;
    @BindView(R.id.home_page_banner_toolbar_title)
    TextView home_page_banner_toolbar_title;
    @BindView(R.id.home_page_banner_toolbar_bnt)
    TextView home_page_banner_toolbar_bnt;
    @BindView(R.id.more_qubo_refresh)
    SwipeRefreshLayout more_qubo_refresh;
    @BindView(R.id.more_qubo_recycler)
    RecyclerView more_qubo_recycler;
    private RequestQueue requestQueue;
    private MoreQubo qubo;
    private List totalList = new ArrayList<>();
    ;
    private HomeQuboMoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_inivate);
        ButterKnife.bind(this);
        HomePageUtils.setToolbar(this, home_page_toolbar, home_page_banner_toolbar_title, "专访", 0);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        more_qubo_recycler.setLayoutManager(manager);
        adapter = new HomeQuboMoreRecyclerAdapter(this);
        adapter.setList(totalList);
        adapter.notifyDataSetChanged();
        more_qubo_recycler.setAdapter(adapter);
        downParseJson(3, 1);
        more_qubo_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                totalList.clear();
                downParseJson(3, 1);
            }
        });

        adapter.setListener(new HomeRecyclerListener() {
            @Override
            public void onItemListener(int position) {
                Intent intent = new Intent(Guest_More_InivateActivity.this, HomePage_QuboActivity.class);
                intent.putExtra("scene_id", qubo.getResultList().get(position).getScene_id());
                startActivity(intent);
            }
        });

    }

    private void downParseJson(final int type, final int pageNo) {
        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://www.quhuwai.cn/webservice/qhw1001/func_sub1106",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        qubo = gson.fromJson(s, MoreQubo.class);
                        List<MoreQubo.ResultListBean> list = qubo.getResultList();
                        totalList.addAll(list);
                        adapter.setList(totalList);
                        adapter.notifyDataSetChanged();
                        more_qubo_refresh.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("pageSize", "10");
                map.put("region_id", "0");
                map.put("type", String.valueOf(type));
                map.put("pageNo", String.valueOf(pageNo));
                map.put("destination_id", "0");

                return map;
            }
        };
        requestQueue.add(request);
    }
}
