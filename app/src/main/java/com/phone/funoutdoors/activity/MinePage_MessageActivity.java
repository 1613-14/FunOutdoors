package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.view.MessageItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MinePage_MessageActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.system_message)
    MessageItem systemMessage;
    @BindView(R.id.notification_message)
    MessageItem notificationMessage;
    @BindView(R.id.private_message)
    MessageItem privateMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page_message);
        ButterKnife.bind(this);
        initToolBar();
    }

    /**
     * 设置toolbar
     */
    private void initToolBar() {
        homePageBannerToolbarTitle.setText("消息");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.system_message, R.id.notification_message, R.id.private_message})
    public void onClick(View view) {
        Intent intent = new Intent(this, MinePage_MessageInfoActivity.class);
        switch (view.getId()) {
            case R.id.system_message:
                intent.putExtra("type", Constant.MESSAGE_SYSTEM);
                intent.putExtra("title", systemMessage.getText().toString());
                break;
            case R.id.notification_message:
                intent.putExtra("type", Constant.MESSAGE_NOTIFICATION);
                intent.putExtra("title", notificationMessage.getText().toString());
                break;
            case R.id.private_message:
                intent.putExtra("type", Constant.MESSAGE_PRIVATE);
                intent.putExtra("title", privateMessage.getText().toString());
                break;
        }
        startActivity(intent);
        overridePendingTransition(R.anim.activity_open,0);
    }
}
