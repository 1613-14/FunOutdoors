package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.phone.funoutdoors.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MinePage_CertificateActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.line)
    TextView line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page__certificate);
        ButterKnife.bind(this);

        initToolbar();
    }

    /**
     * 设置toolBar
     */
    private void initToolbar() {
        line.setVisibility(View.GONE);
        homePageBannerToolbarTitle.setText("认证");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,R.anim.activity_close);
            }
        });
    }

    @OnClick(R.id.mine_certifate)
    public void onClick() {
        Intent intent = new Intent(this,MinePage_CertificateInfoActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_open,0);
    }
}
