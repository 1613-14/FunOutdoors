package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.phone.funoutdoors.GetWelcomePic;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.WelcomePic;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Welcome2Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image_welcome2;
    private String url = "http://quhuwaiwap.oss-cn-beijing.aliyuncs.com/appjson/ad.json";
    private String promotion_url;
    private Button btn_skipWelcome;
    private boolean isClick = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(Welcome2Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);
        image_welcome2 = (ImageView) findViewById(R.id.image_welcome2);
        btn_skipWelcome = (Button) findViewById(R.id.btn_skipWelcome);

        getPicFromNet();

        image_welcome2.setOnClickListener(this);
        btn_skipWelcome.setOnClickListener(this);


        skipWelcome();

    }

    private void skipWelcome() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                handler.sendEmptyMessageDelayed(100, 2000);
            }
        }.start();
    }

    private void getPicFromNet() {
        String baseUrl = "http://quhuwaiwap.oss-cn-beijing.aliyuncs.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
//拼接
        GetWelcomePic getWelcomePic = retrofit.create(GetWelcomePic.class);
        Call<WelcomePic> call = getWelcomePic.getWelcomePic();

        call.enqueue(new Callback<WelcomePic>() {

            @Override
            public void onResponse(Call<WelcomePic> call, Response<WelcomePic> response) {
                String pic_url = response.body().getPic_url();
                promotion_url = response.body().getPromotion_url();
                Picasso.with(Welcome2Activity.this).load("http://image.quhuwai.cn/" + pic_url).placeholder(R.mipmap.ic_launcher).fit().into(image_welcome2);
            }

            @Override
            public void onFailure(Call<WelcomePic> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        isClick = true;
        switch (v.getId()) {
            case R.id.image_welcome2:
                Intent intent1 = new Intent(this, WelcomeWebActivity.class);
                intent1.putExtra("promotion_url", promotion_url);
                startActivity(intent1);
                handler.removeMessages(100);
                finish();
                break;
            case R.id.btn_skipWelcome:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                handler.removeMessages(100);
                finish();
                break;
        }
    }
}
