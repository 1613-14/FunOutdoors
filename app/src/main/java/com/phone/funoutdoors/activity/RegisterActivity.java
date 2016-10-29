package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.User;
import com.phone.funoutdoors.db.UserDBManager;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.utils.PopupWindowDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tvphone)
    EditText tvphone;
    @BindView(R.id.smscheck)
    EditText smscheck;
    @BindView(R.id.sendSMS)
    TextView sendSMS;
    @BindView(R.id.nickname)
    EditText nickname;
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.female)
    RadioButton female;
    @BindView(R.id.gender)
    RadioGroup gender;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.inviteCode)
    EditText inviteCode;
    @BindView(R.id.agreement)
    TextView agreement;
    @BindView(R.id.register)
    Button register;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                if (popupWindow != null) {
                    //两秒后弹出窗体自动消失
                    popupWindow.hiddenDialog();
                }
            }else {
                popupWindow.hiddenDialog();
                finish();
            }
        }
    };
    private PopupWindowDialog popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //185078d4032d8   4cd802d192e5c466c1d6fcb3b5759c3c
    }

    @OnClick({R.id.back, R.id.sendSMS, R.id.agreement, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.sendSMS:

                break;
            case R.id.agreement:
                agreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                Intent intent = new Intent(this, ServerAgreementActivity.class);
                startActivity(intent);
                break;
            case R.id.register:
                registerHandler();
                break;
        }
    }

    /**
     * 注册事件
     */
    private void registerHandler() {
        UserDBManager manager = UserDBManager.getInstance(this);
        popupWindow = PopupWindowDialog.getInstance();
        String phone = tvphone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            popupWindow.showPopupWindowDialog("请输入手机号码", Constant.TEXTVIEW, this);
            handler.sendEmptyMessageDelayed(100, 2000);
        } else {
            String code = smscheck.getText().toString();
            if (TextUtils.isEmpty(code)) {
                popupWindow.showPopupWindowDialog("验证码不能为空", Constant.TEXTVIEW, this);
                handler.sendEmptyMessageDelayed(100, 2000);
            } else {
                String nick = nickname.getText().toString();
                if (TextUtils.isEmpty(nick)) {
                    popupWindow.showPopupWindowDialog("昵称不能为空", Constant.TEXTVIEW, this);
                    handler.sendEmptyMessageDelayed(100, 2000);
                } else {
                    String pass = password.getText().toString();
                    if (TextUtils.isEmpty(pass)) {
                        popupWindow.showPopupWindowDialog("密码不能为空", Constant.TEXTVIEW, this);
                        handler.sendEmptyMessageDelayed(100, 2000);
                    } else {
                        if (pass.length() < 6) {
                            popupWindow.showPopupWindowDialog("密码不能小于6位", Constant.TEXTVIEW, this);
                            handler.sendEmptyMessageDelayed(100, 2000);
                        } else {
                            String invite = inviteCode.getText().toString();
                            if (invite == null) {
                                invite = "";
                            } else {
                                //检验邀请码
                            }
                            int sex = 0;
                            int buttonId = gender.getCheckedRadioButtonId();
                            switch (buttonId) {
                                case R.id.female:
                                    sex = 1;
                                    break;
                                case R.id.male:
                                    sex = 0;
                                    break;
                            }
                            long id = manager.getId();
                            User user = new User(id + 1, phone, nick, sex, pass, "", 0, 0,null,null);
                            boolean b = manager.insertUser(user);
                            if (b) {
                                popupWindow.showPopupWindowDialog(null, Constant.IMAGE_TEXTVIEW_BOUTTON, this);
                                handler.sendEmptyMessageDelayed(101, 2000);
                            } else {
                                popupWindow.showPopupWindowDialog("注册失败", Constant.TEXTVIEW, this);
                            }
                        }
                    }
                }

            }
        }
    }


}
