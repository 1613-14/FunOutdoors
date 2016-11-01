package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.GuestGuideListAdapter;
import com.phone.funoutdoors.bean.Guide;
import com.phone.funoutdoors.bean.GuideBean;
import com.phone.funoutdoors.interfaces.GetGuestMoreGuideData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuestPageGuideMoreActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.list_more_guide)
    ListView listMoreGuide;
    @BindView(R.id.line)
    TextView line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page_guide_more);
        ButterKnife.bind(this);
        initToolBar();
        downloadContent();
    }

    /**
     *
     */
    private void downloadContent() {
        //创建对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.quhuwai.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建请求
        GetGuestMoreGuideData getGuestMoreGuideData = retrofit.create(GetGuestMoreGuideData.class);
        //获得任务
        Call<Guide> moreGuideDate = getGuestMoreGuideData.getMoreGuideDate(10, 1, -1);
        //执行任务
        moreGuideDate.enqueue(new Callback<Guide>() {
            @Override
            public void onResponse(Call<Guide> call, Response<Guide> response) {
                if (response != null) {
                    Guide guestData = response.body();
                    setListAdapter(guestData);
                }
            }

            @Override
            public void onFailure(Call<Guide> call, Throwable t) {

            }
        });
    }

    private void setListAdapter(Guide guestData) {
        final List<GuideBean> resultList = guestData.getResultList();
        GuestGuideListAdapter guestGuideListAdapter = new GuestGuideListAdapter(resultList, this);
        listMoreGuide.setAdapter(guestGuideListAdapter);
        listMoreGuide.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GuestPageGuideMoreActivity.this, GuestGuideDetailActivity.class);
                intent.putExtra("actId",resultList.get(position).getAct_id());
                intent.putExtra("userId",resultList.get(position).getUser_id());
                startActivity(intent);
                overridePendingTransition(R.anim.activity_open, 0);
            }
        });
    }

    /**
     * 设置toolBar
     */
    private void initToolBar() {
        line.setVisibility(View.GONE);
        homePageBannerToolbarTitle.setText("游记列表");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
