package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.GuideDetailListAdapter;
import com.phone.funoutdoors.bean.GuideDetail;
import com.phone.funoutdoors.interfaces.GetGuestGuideDetailData;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.view.GuideDetailItem;
import com.phone.funoutdoors.view.MyListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuestGuideDetailActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.warning)
    ImageView warning;
    @BindView(R.id.act_covert)
    ImageView actCovert;
    @BindView(R.id.act_avatar)
    CircleImageView actAvatar;
    @BindView(R.id.act_nickname)
    TextView actNickname;
    @BindView(R.id.act_title)
    TextView actTitle;
    @BindView(R.id.start_data)
    GuideDetailItem startData;
    @BindView(R.id.count_data)
    GuideDetailItem countData;
    @BindView(R.id.start_place)
    GuideDetailItem startPlace;
    @BindView(R.id.list_detail)
    MyListView listDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_guide_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int actId = intent.getIntExtra("actId", 0);
        int userId = intent.getIntExtra("userId", 0);
        initToolbar();
        downloadContent(actId, userId);
    }

    /**
     * 设置toolBar
     */
    private void initToolbar() {
        line.setVisibility(View.GONE);
        homePageBannerToolbarTitle.setText("游记攻略");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.warning)
    public void onClick() {

    }

    private void downloadContent(int actId, int userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.quhuwai.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetGuestGuideDetailData getGuestGuideDetailData = retrofit.create(GetGuestGuideDetailData.class);
        Call<GuideDetail> moreGuideDate = getGuestGuideDetailData.getMoreGuideDate(userId, actId);
        moreGuideDate.enqueue(new Callback<GuideDetail>() {
            @Override
            public void onResponse(Call<GuideDetail> call, Response<GuideDetail> response) {
                if (response != null) {
                    GuideDetail d = response.body();
                    setContent(d);
                    setListAdapter(d.getResultList().get(0).getShare());
                }
            }

            @Override
            public void onFailure(Call<GuideDetail> call, Throwable t) {

            }
        });

    }

    /**
     * 填充listview
     *
     * @param share
     */
    private void setListAdapter(List<GuideDetail.ResultListBean.ShareBean> share) {
        GuideDetailListAdapter guideDetailListAdapter = new GuideDetailListAdapter(this, share);
        listDetail.setAdapter(guideDetailListAdapter);
    }

    /**
     * 设置各个控件的内容
     *
     * @param d
     */
    private void setContent(GuideDetail d) {
        GuideDetail.ResultListBean resultListBean = d.getResultList().get(0);
        Glide.with(this).load(Constant.PICPATH + resultListBean.getAct_cover())
                .placeholder(R.mipmap.default_img)
                .animate(new AlphaAnimation(0.5f,1f))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(actCovert);
        Glide.with(this).load(Constant.PICPATH + resultListBean.getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(actAvatar);
        actNickname.setText(resultListBean.getNickname());
        actTitle.setText(resultListBean.getAct_title());
        startData.setBannerInfo(resultListBean.getAct_stdate());
        countData.setBannerInfo(String.valueOf(resultListBean.getAct_days()));
        startPlace.setBannerInfo("");
    }
}
