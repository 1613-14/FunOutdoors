package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone.funoutdoors.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class GuestPagePromotionActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.promotion_webview)
    WebView promotionWebview;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page_promotion);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        initToolBar();
        downLoadWebViewContent();
    }

    /**
     * 设置toolBar
     */
    private void initToolBar() {
        line.setVisibility(View.GONE);
        homePageBannerToolbarTitle.setText("如何成为趣客");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 加载webview的内容
     */
    private void downLoadWebViewContent() {
        promotionWebview.getSettings().setJavaScriptEnabled(true);
        promotionWebview.loadUrl(url);
    }

    @OnClick(R.id.share)
    public void onClick() {
        showShare();
    }

    /**
     * 分享菜单的处理事件
     */
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);
        //设置是否隐藏编辑页面
        oks.setSilent(false);
        oks.show(this);
    }
}
