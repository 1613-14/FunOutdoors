package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePage_Qubo_VideoPalyActivity extends AppCompatActivity {

    @BindView(R.id.qubo_video_play)
    VideoView qubo_video_play;
    @BindView(R.id.qubo_video_back)
    ImageView qubo_video_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_qubo_video_paly);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int time = intent.getIntExtra("time", 0);
        String video = Constant.SPILT_VIDEO_URL + intent.getStringExtra("video");
        Log.e("TAG", "onCreate: " + video);
        Uri uri = Uri.parse(video);
        qubo_video_play.setVideoURI(uri);
        MediaController controller = new MediaController(this);
        //给VideoView设置媒体控制器
        qubo_video_play.setMediaController(controller);
        qubo_video_play.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mp.getVideoWidth() > 0 && mp.getVideoHeight() > 0) {
                    qubo_video_play.start();
                } else {
                    Toast.makeText(HomePage_Qubo_VideoPalyActivity.this, "视频出错，请重试!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        qubo_video_play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });
        qubo_video_play.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });
    }

    @OnClick(R.id.qubo_video_back)
    public void onClick(View view) {
        finish();
    }
}
