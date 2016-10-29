package com.phone.funoutdoors.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServerAgreementActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.server_agreement_webview)
    WebView serverAgreementWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_agreement);
        ButterKnife.bind(this);

        initToolbar();
        downLoadWebViewContent();
    }

    /**
     * 加载webview的内容
     */
    private void downLoadWebViewContent() {
        serverAgreementWebview.getSettings().setJavaScriptEnabled(true);
        serverAgreementWebview.loadUrl(Constant.SERVERAGREEMENT);
    }

    /**
     * 给toolBar初始化
     */
    private void initToolbar() {
        homePageToolbar.setTitle("");
        homePageBannerToolbarTitle.setText("用户协议");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
