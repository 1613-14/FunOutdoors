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

public class MinePage_MessageInfoActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.message_default)
    LinearLayout messageDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 1);
        String title = intent.getStringExtra("title");
        setContentView(R.layout.activity_mine_page_message_info);
        ButterKnife.bind(this);
        initToolBar(title);
        switch (type) {
            case Constant.MESSAGE_SYSTEM:
                break;
            case Constant.MESSAGE_NOTIFICATION:
                break;
            case Constant.MESSAGE_PRIVATE:
                messageDefault.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 设置toolBar
     *
     * @param title
     */
    private void initToolBar(String title) {
        homePageBannerToolbarTitle.setText(title);
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
