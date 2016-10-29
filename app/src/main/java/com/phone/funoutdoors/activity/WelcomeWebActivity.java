package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.phone.funoutdoors.R;


public class WelcomeWebActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;
    private ImageView img_back;
    private ImageView img_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_web);

        initView();

        Intent intent = getIntent();
        if (intent != null) {
            String promotion_url = intent.getStringExtra("promotion_url");
            webView.loadUrl(promotion_url);
        }
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.welcome_webView);
        img_back = (ImageView) findViewById(R.id.share);
        img_share = (ImageView) findViewById(R.id.back);
        img_back.setOnClickListener(this);
        img_share.setOnClickListener(this);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.share:



                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        return true;
    }
}
