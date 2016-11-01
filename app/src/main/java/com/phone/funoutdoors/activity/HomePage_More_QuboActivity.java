package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
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
import com.phone.funoutdoors.adapter.HomeQuboMoreGridViewAdapter;
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

public class HomePage_More_QuboActivity extends AppCompatActivity {

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
    private String[] title = new String[]{"全部", "景点", "专访", "线路", "装备", "猎奇", "运动"};
    private RequestQueue requestQueue;
    private HomeQuboMoreRecyclerAdapter adapter;
    private List<MoreQubo.ResultListBean> totalList = new ArrayList<>();
    private int type;
    private int pageNo = 0;
    private MoreQubo qubo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_more_qubo);
        ButterKnife.bind(this);
        HomePageUtils.setToolbar(this, home_page_toolbar, home_page_banner_toolbar_title, "趣播", 0);
        setPopWindow();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        more_qubo_recycler.setLayoutManager(manager);
        adapter = new HomeQuboMoreRecyclerAdapter(this);
        adapter.setList(totalList);
        adapter.notifyDataSetChanged();
        more_qubo_recycler.setAdapter(adapter);
        type = getIntent().getIntExtra("type", 0);
        downParseJson(type, pageNo);

        more_qubo_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                totalList.clear();
                downParseJson(type, 1);
            }
        });

        adapter.setListener(new HomeRecyclerListener() {
            @Override
            public void onItemListener(int position) {
                Intent intent = new Intent(HomePage_More_QuboActivity.this, HomePage_QuboActivity.class);
                intent.putExtra("scene_id", qubo.getResultList().get(position).getScene_id());
                startActivity(intent);
            }
        });
    }

    private void downParseJson(final int type, final int pageNo) {
        Log.e("TAG", "downParseJson: type"+type);
        Log.e("TAG", "downParseJson: "+totalList.size() );
        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://www.quhuwai.cn/webservice/qhw1001/func_sub1106",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.e("TAG", "onResponse: "+s);
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

    /**
     * 设置弹出框
     */
    private void setPopWindow() {
        home_page_banner_toolbar_bnt.setVisibility(View.VISIBLE);
        home_page_banner_toolbar_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(HomePage_More_QuboActivity.this, R.layout.activity_home_page_more_qubo_pop, null);
                GridView gridView = (GridView) view.findViewById(R.id.more_qubo_pop);
                gridView.setAdapter(new HomeQuboMoreGridViewAdapter(title, HomePage_More_QuboActivity.this));
                final PopupWindow pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                pop.setOutsideTouchable(true);
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.showAsDropDown(home_page_toolbar, 0, 5);
                gridView.setSelection(type);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 2:
                                type = 3;
                                break;
                            case 3:
                                type=4;
                                break;
                            case 4:
                                type=2;
                                break;
                        }
                        home_page_banner_toolbar_bnt.setText(title[position]);
                        totalList.clear();
                        downParseJson(type,pageNo);
                        pop.dismiss();
                    }
                });
            }
        });
    }
}
