package com.phone.funoutdoors.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.utils.PopupWindowDialog;
import com.phone.funoutdoors.view.IDCartView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MinePage_CertificateInfoActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.toolbar_layout)
    LinearLayout toolbarLayout;
    @BindView(R.id.commit_bnt)
    Button commitBnt;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.card_id)
    EditText cardId;
    @BindView(R.id.cart_font_image)
    IDCartView cartFontImage;
    @BindView(R.id.cart_back_image)
    IDCartView cartBackImage;
    @BindView(R.id.people_card)
    IDCartView peopleCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page__certificate_info);
        ButterKnife.bind(this);
        initToolbar();
    }

    @OnClick({R.id.commit_bnt, R.id.cart_font_image, R.id.cart_back_image, R.id.people_card})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commit_bnt:
                commitData();
                break;
            case R.id.cart_font_image:
                Log.e("TAG","弹出窗体");
                //PopupWindowDialog.getInstance().showPopupWindowDialog("",Constant.SELECT_PIC,this);
                break;
            case R.id.cart_back_image:
                Log.e("TAG","弹出窗体");
                //PopupWindowDialog.getInstance().showPopupWindowDialog("",Constant.SELECT_PIC,this);
                break;
            case R.id.people_card:
                Log.e("TAG","弹出窗体");
                //PopupWindowDialog.getInstance().showPopupWindowDialog("",Constant.SELECT_PIC,this);
                break;
        }
    }

    /**
     * 提交数据
     */
    private void commitData() {
        PopupWindowDialog windowDialog = PopupWindowDialog.getInstance();
        String username = name.getText().toString();
        if (TextUtils.isEmpty(username)) {
            windowDialog.showPopupWindowDialog("请输入姓名", Constant.TEXTVIEW_AND_BUTTON, this);
        } else {
            String id = cardId.getText().toString();
            if (TextUtils.isEmpty(id)) {
                windowDialog.showPopupWindowDialog("请输入正确的身份证号码！", Constant.TEXTVIEW_AND_BUTTON, this);
            } else {

            }
        }
    }

    /**
     * 设置toolBar
     */
    private void initToolbar() {
        line.setVisibility(View.GONE);
        homePageBannerToolbarTitle.setText("实名认证");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, R.anim.activity_close);
            }
        });
    }


}
