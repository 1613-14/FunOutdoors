package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.phone.funoutdoors.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MinePage_SettingActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.offLineMap)
    TextView offLineMap;
    @BindView(R.id.safe)
    TextView safe;
    @BindView(R.id.about)
    TextView about;
    @BindView(R.id.clearCache)
    TextView clearCache;
    @BindView(R.id.exitLogin)
    TextView exitLogin;
    @BindView(R.id.line)
    TextView line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page_setting);
        ButterKnife.bind(this);
        initToolBar();
    }

    /**
     * 设置toolBar
     */
    private void initToolBar() {
        homePageBannerToolbarTitle.setText("设置");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        line.setBackgroundColor(Color.GRAY);
    }

    @OnClick({R.id.offLineMap, R.id.safe, R.id.about, R.id.clearCache, R.id.exitLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.offLineMap:
                break;
            case R.id.safe:
                break;
            case R.id.about:
                break;
            case R.id.clearCache:
                break;
            case R.id.exitLogin:
                showDialog();
                break;
        }
    }


    /**
     * 弹出窗体显示，点击外边消失的
     */
    public void showDialog() {
        final PopupWindow popupWindow = new PopupWindow(this);
        View exitDialog = LayoutInflater.from(this).inflate(R.layout.dialog_ok_exit, null);
        exitDialog.findViewById(R.id.ok_bnt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setFocusable(true);
                getSharedPreferences("config", MODE_PRIVATE).edit().putBoolean("login", false);
                Intent intent = new Intent(MinePage_SettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                popupWindow.dismiss();
            }
        });
        Button getupBnt = (Button) exitDialog.findViewById(R.id.getup_bnt);
        getupBnt.setFocusable(true);
        exitDialog.findViewById(R.id.getup_bnt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(exitDialog);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0X00000000));
        backgroundAlpha(0.4f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        popupWindow.showAtLocation(exitDialog, Gravity.CENTER, 0, 0);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
