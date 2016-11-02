package com.phone.funoutdoors.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.video.venvy.param.JjVideoView;
import cn.com.video.venvy.param.OnJjOpenSuccessListener;

public class HomePage_Qubo_VideoPalyActivity extends AppCompatActivity {

    @BindView(R.id.qubo_video_back)
    ImageView qubo_video_back;
    @BindView(R.id.sdk_ijk_progress_bar)
    RelativeLayout progressBar;


    private JjVideoView mVideoView;//

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_qubo_video_paly);
        ButterKnife.bind(this);
        mVideoView = (JjVideoView) findViewById(R.id.video);
        mVideoView.setVideoJjAppKey("Bk6DK3Ugg");//请替换为您申请的AppKey
        mVideoView.setVideoJjPageName("com.phone.funoutdoors");//请替换成您的包名，此处为本demo的包名
        mVideoView.setVideoJjType(2);
        Intent intent = getIntent();
        String video = Constant.SPILT_VIDEO_URL + intent.getStringExtra("video");
        mVideoView.setResourceVideo(video);
        mVideoView.start();
        mVideoView.setOnJjOpenSuccess(new OnJjOpenSuccessListener() {
            @Override
            public void onJjOpenSuccess() {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    @OnClick(R.id.back)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null)
            mVideoView.onDestroy();
    }
}
