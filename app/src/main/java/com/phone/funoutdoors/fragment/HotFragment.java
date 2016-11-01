package com.phone.funoutdoors.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.HotRecyclerViewAdapter;
import com.phone.funoutdoors.bean.Hot;
import com.phone.funoutdoors.interfaces.GetHotRVData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {

    private View view;
    private RecyclerView mRecyclerView;
    private List<Hot.ResultListBean.OutdoorsBean> list = new ArrayList<>();
    private Context context;
    private HotRecyclerViewAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int page = 1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot, container, false);
        initView();
        getDataFromNet(page);
        return view;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.hot_recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayot);
        //设计小圆圈的颜色变化
        mSwipeRefreshLayout.setColorSchemeColors(Color.YELLOW, Color.WHITE, Color.BLACK);
        //滑动监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=0;
                list.clear();
                getDataFromNet(page);
            }
        });
        //设置布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(manager);
        adapter = new HotRecyclerViewAdapter(list, context);
        mRecyclerView.setAdapter(adapter);
    }

    private void getDataFromNet(int page) {

//        户外圈：http://www.quhuwai.cn/webservice/qhw3001/func_sub1001
//        请求方式：POST
//        请求头：Content-Type: application/x-www-form-urlencoded
//        请求体：pageSize=20&orderby=1&cityId=0&myId=21196&pageNo=1&userId=0
//        请求值：orderby 1-热门  2-关注  3-约伴
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.quhuwai.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //拼接
        GetHotRVData getHotRVData = retrofit.create(GetHotRVData.class);
        //获取任务
        Call<Hot> call = getHotRVData.getHotRVData(20, 1, 0, 21196, page, 0);

        call.enqueue(new Callback<Hot>() {
            @Override
            public void onResponse(Call<Hot> call, Response<Hot> response) {
                Hot hot = response.body();
                List<Hot.ResultListBean> resultList = hot.getResultList();
                mSwipeRefreshLayout.setRefreshing(false);
                list.addAll(resultList.get(0).getOutdoors());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Hot> call, Throwable t) {

            }
        });

    }

}
