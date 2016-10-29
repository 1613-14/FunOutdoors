package com.phone.funoutdoors.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.activity.ConsultHeaderDetailsActivity;
import com.phone.funoutdoors.activity.SpecialActivity;
import com.phone.funoutdoors.activity.WebActivity;
import com.phone.funoutdoors.adapter.ConsultLVAdapter;
import com.phone.funoutdoors.bean.ConsultLVData;
import com.phone.funoutdoors.bean.ConsultLVHeaderData;
import com.phone.funoutdoors.interfaces.GetConsultLVData;
import com.phone.funoutdoors.interfaces.GetConsultLVHeaderData;
import com.phone.funoutdoors.utils.Constant;

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
public class ConsultPageFragment extends Fragment {

    private View view;
    private com.handmark.pulltorefresh.library.PullToRefreshListView mListView;
    private Context context;
    private String feature_content;
    private int page = 0;
    TextView headerTitle, headerMore;
    ImageView headerImage;
    ConsultLVAdapter adapter;
    ListView listView;
    private ConsultLVHeaderData consultLVHeaderData;
    List<ConsultLVData.ResultListBean> allList = new ArrayList<>();
    private View headerView;
    String feature_title, feature_img, feature_cover;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //为context进行初始化
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view==null) {
            view = inflater.inflate(R.layout.fragment_consult_page, container, false);
            initHeaderView();
            initView();
            getDataFromNet(page);
        }
        return view;
    }

    private void initHeaderView() {
        headerView = View.inflate(context, R.layout.consult_listview_header, null);
        headerTitle = (TextView) headerView.findViewById(R.id.consult_listView_header_title);
        headerImage = (ImageView) headerView.findViewById(R.id.consult_listView_header_image);
        headerMore = (TextView) headerView.findViewById(R.id.consult_listView_header_more);
        headerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConsultHeaderDetailsActivity.class);
                intent.putExtra("headerTitle", feature_title);
                intent.putExtra("feature_content", feature_content);
                intent.putExtra("feature_cover", feature_cover);
                startActivity(intent);
            }
        });
        headerMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SpecialActivity.class);
                intent.putExtra("consultLVHeaderData", consultLVHeaderData);
                startActivity(intent);
            }
        });
        getHeaderDataFromNet();
    }

    private void getHeaderDataFromNet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.quhuwai.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //拼接请求
        GetConsultLVHeaderData getConsultLVHeaderData = retrofit.create(GetConsultLVHeaderData.class);
        Call<ConsultLVHeaderData> call = getConsultLVHeaderData.getConsultRVData(5, 1);
        //进行请求
        call.enqueue(new Callback<ConsultLVHeaderData>() {
            @Override
            public void onResponse(Call<ConsultLVHeaderData> call, Response<ConsultLVHeaderData> response) {
                if (response != null) {
                    consultLVHeaderData = response.body();
                    feature_title = consultLVHeaderData.getResultList().get(0).getFeature_title();
                    feature_content = consultLVHeaderData.getResultList().get(0).getFeature_content();
                    feature_img = consultLVHeaderData.getResultList().get(0).getFeature_img();

                    feature_cover = consultLVHeaderData.getResultList().get(0).getFeature_cover();
                    headerTitle.setText(feature_title);
                    Glide.with(context).load(Constant.SPILT_IMG_URL + feature_img).placeholder(R.mipmap.ic_launcher).into(headerImage);
                }
            }

            @Override
            public void onFailure(Call<ConsultLVHeaderData> call, Throwable t) {

            }
        });
    }

    /**
     * 添加头布局
     */
    private void addHeaderView() {
        listView = mListView.getRefreshableView();
        listView.addHeaderView(headerView);
    }

    private void initView() {

        mListView = ((PullToRefreshListView) view.findViewById(R.id.consult_listView));
        //设置刷新样式
        mListView.setMode(PullToRefreshBase.Mode.BOTH);

        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                allList.clear();
                page=0;
                getDataFromNet(page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                getDataFromNet(page);
            }
        });

        adapter = new ConsultLVAdapter(allList, context);
        //添加头布局
        addHeaderView();

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("wapUrl", allList.get(position - 2).getWapurl());
                intent.putExtra("title", allList.get(position - 2).getInfo_title());
                startActivity(intent);
            }
        });
    }


    private void getDataFromNet(int page) {
        /**
         资讯列表：http://www.quhuwai.cn/webservice/qhw1001/func_sub1201
         请求方式：POST
         请求头：Content-Type: application/x-www-form-urlencoded
         请求体：pageSize=20&pageNo=3
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.quhuwai.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //拼接请求
        GetConsultLVData getConsultLVData = retrofit.create(GetConsultLVData.class);
        Call<ConsultLVData> call = getConsultLVData.getConsultRVData(20, page);
        //进行请求
        call.enqueue(new Callback<ConsultLVData>() {
            @Override
            public void onResponse(Call<ConsultLVData> call, Response<ConsultLVData> response) {
                if (response != null) {
                    ConsultLVData consultLVData = response.body();
                    List<ConsultLVData.ResultListBean> resultList = consultLVData.getResultList();

                    allList.addAll(resultList);
                    adapter.notifyDataSetChanged();

                    //设置刷新完成
                    mListView.onRefreshComplete();
                }
            }

            @Override
            public void onFailure(Call<ConsultLVData> call, Throwable t) {

            }
        });
    }

}
