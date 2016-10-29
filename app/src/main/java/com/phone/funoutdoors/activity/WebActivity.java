package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone.funoutdoors.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.text_web_title)
    TextView mTitle;
    @BindView(R.id.webview_web)
    WebView mWebView;
    @BindView(R.id.image_web_back)
    ImageView image_web_back;
    @BindView(R.id.image_web_share)
    ImageView image_web_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            String info_title = intent.getStringExtra("title");
            String wapUrl = intent.getStringExtra("wapUrl");
            mTitle.setText(info_title);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(wapUrl);
        }

        image_web_back.setOnClickListener(this);
        image_web_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_web_back:
                finish();
                break;
            case R.id.image_web_share:

                break;
        }
    }
}
