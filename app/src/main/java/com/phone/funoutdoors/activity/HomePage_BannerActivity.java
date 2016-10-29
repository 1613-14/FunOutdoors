package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.DownloadListener;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.HomePageUtils;
import com.phone.funoutdoors.view.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePage_BannerActivity extends AppCompatActivity {

    @BindView(R.id.home_page_toolbar)
    Toolbar home_page_toolbar;
    @BindView(R.id.home_page_ad_webView)
    ProgressWebView home_page_ad_webView;
    @BindView(R.id.home_page_banner_toolbar_title)
    TextView home_page_banner_toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_home_page);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        HomePageUtils.setToolbar(this,home_page_toolbar,home_page_banner_toolbar_title,title,R.menu.home_page_toolbar_share_menu);
        home_page_ad_webView.getSettings().setJavaScriptEnabled(true);
        home_page_ad_webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                if (url != null && url.startsWith("http://"))
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
        home_page_ad_webView.loadUrl(url);
    }



}
