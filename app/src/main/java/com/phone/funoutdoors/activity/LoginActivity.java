package com.phone.funoutdoors.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.User;
import com.phone.funoutdoors.db.UserDBManager;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.utils.PopupWindowDialog;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.qq_login)
    ImageView qqLogin;
    @BindView(R.id.weibo_login)
    ImageView weiboLogin;
    @BindView(R.id.weixin_login)
    ImageView weixinLogin;
    @BindView(R.id.bnt_login)
    Button bntLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.tv_username)
    TextInputEditText tvUsername;
    @BindView(R.id.tv_password)
    TextInputEditText tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //绑定控件
        ButterKnife.bind(this);
        //初始化ShareSDK
        ShareSDK.initSDK(this);
        //播放动画
        playAnimation();
    }

    /**
     * 动画播放
     */
    private void playAnimation() {
        ImageView loginBg = (ImageView) findViewById(R.id.login_bg);
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        FrameLayout.LayoutParams rl = new FrameLayout.LayoutParams(widthPixels + 400, ViewGroup.LayoutParams.MATCH_PARENT);
        loginBg.setLayoutParams(rl);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator_translateX1 = ObjectAnimator.ofFloat(loginBg, "translationX", 0, -200, 0);
        animator_translateX1.setRepeatCount(-1);
        animator_translateX1.setDuration(10000);
        animator_translateX1.start();
    }

    /**
     * 监听物理返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        overridePendingTransition(0, R.anim.activity_close);
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.qq_login, R.id.weibo_login, R.id.weixin_login, R.id.bnt_login, R.id.tv_register, R.id.tv_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qq_login:
                //使用QQ账号登录
                login(QQ.NAME);
                break;
            case R.id.weibo_login:
                //使用微博账号登录
                login(SinaWeibo.NAME);
                break;
            case R.id.weixin_login:
                //使用微信账号登录
                login(Wechat.NAME);
                break;
            case R.id.bnt_login:
                //使用趣户外账号登录
                loginFunOutDoor();
                break;
            case R.id.tv_register:
                //立即注册
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_close:
                //点击关闭文本
                finish();
                overridePendingTransition(0, R.anim.activity_close);
                break;
        }
    }

    /**
     * 使用趣户外账号登录
     */
    private void loginFunOutDoor() {
        PopupWindowDialog windowDialog = PopupWindowDialog.getInstance();
        String username = tvUsername.getText().toString();
        if (TextUtils.isEmpty(username)) {
            windowDialog.showPopupWindowDialog("用户名不能为空", Constant.TEXTVIEW,this);
        } else {
            String password = tvPassword.getText().toString();
            if (TextUtils.isEmpty(password)) {
                windowDialog.showPopupWindowDialog("密码不能为空", Constant.TEXTVIEW, this);
            } else {
                List<User> user = UserDBManager.getInstance(this).findByUser(username);
                if (user.size() > 0) {
                    for (int i = 0; i < user.size(); i++) {
                        String pass = user.get(i).getPassword();
                        if (password.equals(pass)) {
                            SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
                            config.edit().putBoolean("login", true).commit();
                            config.edit().putLong("userId", user.get(i).getUser_id()).commit();
                            finish();
                        } else {
                            windowDialog.showPopupWindowDialog("账号和密码不匹配", Constant.TEXTVIEW, this);
                        }
                    }
                } else {
                    windowDialog.showPopupWindowDialog("用户不存在", Constant.TEXTVIEW, this);
                }
            }
        }
    }

    /**
     * @param name 第三方登录
     */
    private void login(String name) {
        //获取平台对象
        Platform pf = ShareSDK.getPlatform(this, name);
        //移除登录记录
        pf.removeAccount();
        //请求数据
        pf.showUser(null);
        //设置监听器
        pf.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//                登录成功返回
                String userIcon = platform.getDb().getUserIcon();
                String userName = platform.getDb().getUserName();
                String userGender = platform.getDb().getUserGender();

                int sex = 0;
                if ("男".equals(userGender)) {
                    sex = 0;
                } else {
                    sex = 1;
                }
                long id = UserDBManager.getInstance(LoginActivity.this).getId();
                User user = new User(id, "", userName, sex, "", userIcon, 0, 0);
                boolean b = UserDBManager.getInstance(LoginActivity.this).insertUser(user);
                if (b) {
                    SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
                    config.edit().putBoolean("login", true).commit();
                    config.edit().putLong("userId", user.getUser_id()).commit();
                    finish();
                } else {
                    PopupWindowDialog.getInstance().showPopupWindowDialog("服务器响应失败", Constant.TEXTVIEW, LoginActivity.this);
                }

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                //登录失败返回
                PopupWindowDialog.getInstance().showPopupWindowDialog("服务器响应失败", Constant.TEXTVIEW, LoginActivity.this);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                //取消登录返回
                PopupWindowDialog.getInstance().showPopupWindowDialog("取消登录了", Constant.TEXTVIEW, LoginActivity.this);
            }
        });
    }
}
