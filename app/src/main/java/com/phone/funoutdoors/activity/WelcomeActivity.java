package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.phone.funoutdoors.R;

public class WelcomeActivity extends AppCompatActivity implements Handler.Callback {

    private Handler handler = new Handler(this);
    private ImageView img_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        img_welcome = (ImageView) findViewById(R.id.image_welcome);
        //跳过欢迎界面
        skipWelcome();
    }

    private void skipWelcome() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                handler.sendEmptyMessageDelayed(100, 3000);
            }
        }.start();
    }


    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 100:
                Intent intent = new Intent(this, Welcome2Activity.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }
}