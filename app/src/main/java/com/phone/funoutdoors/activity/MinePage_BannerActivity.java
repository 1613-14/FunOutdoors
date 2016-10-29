package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinePage_BannerActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.mine_banner_default)
    LinearLayout mineBannerDefault;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page__banner);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        type = intent.getIntExtra("type",1);
        String title = intent.getStringExtra("title");
        switch (type){
            case Constant.MESSAGE_SYSTEM:
                mineBannerDefault.setVisibility(View.GONE);
                break;
            case Constant.MESSAGE_NOTIFICATION:
                break;
            case Constant.MESSAGE_PRIVATE:
                break;
        }
        initToolBar(title);
    }

    /**
     * 设置toolBar
     */
    private void initToolBar(String title) {
        line.setVisibility(View.GONE);
        homePageBannerToolbarTitle.setText(title);
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MinePage_CertificateActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_open,0);
    }
}
