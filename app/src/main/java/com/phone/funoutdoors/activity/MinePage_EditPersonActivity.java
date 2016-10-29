package com.phone.funoutdoors.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.User;
import com.phone.funoutdoors.db.UserDBManager;
import com.phone.funoutdoors.utils.PopupWindowScreen;
import com.phone.funoutdoors.view.PersonInfoItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MinePage_EditPersonActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.person_head)
    PersonInfoItem personHead;
    @BindView(R.id.person_nickname)
    PersonInfoItem personNickname;
    @BindView(R.id.person_sex)
    PersonInfoItem personSex;
    @BindView(R.id.person_birth)
    PersonInfoItem personBirth;
    @BindView(R.id.person_qr)
    PersonInfoItem personQr;
    @BindView(R.id.person_city)
    PersonInfoItem personCity;
    @BindView(R.id.person_description)
    PersonInfoItem personDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_page_edit_person_info);
        ButterKnife.bind(this);
        initToolBar();
        queryUser();
    }

    /**
     * 设置toolBar
     */
    private void initToolBar() {
        line.setBackgroundColor(Color.parseColor("#8e8f90"));
        homePageBannerToolbarTitle.setText("个人资料");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.person_head,R.id.person_nickname, R.id.person_sex, R.id.person_birth, R.id.person_qr, R.id.person_city, R.id.person_description})
    public void onClick(View view) {
        PopupWindowScreen instance = PopupWindowScreen.getInstance();
        switch (view.getId()) {
            case R.id.person_head:
                instance.showDialog(this, this);
                break;
            case R.id.person_nickname:
                break;
            case R.id.person_sex:
                Log.e("TAG", "性别");
                break;
            case R.id.person_birth:
                Log.e("TAG", "生日");
                break;
            case R.id.person_qr:
                Log.e("TAG", "我的二维码");
                break;
            case R.id.person_city:
                Log.e("TAG", "我的城市");
                break;
            case R.id.person_description:
                Log.e("TAG", "签名");
                break;
        }
        overridePendingTransition(R.anim.activity_open, 0);
    }

    /**
     * 查询用户
     */
    private void queryUser() {
        long id = getSharedPreferences("config", Context.MODE_PRIVATE).getLong("userId", 0);
        List<User> user = UserDBManager.getInstance(this).findByUser(id);
        User u = user.get(0);
        setPersonHead(u);
        setNickname(u);
        setPersonSex(u);
        setPersonBirthday(u);
        setPersonDescription(u);
        setPersonQr();
    }

    /**
     * 设置二维码
     */
    private void setPersonQr() {
        ImageView v = new ImageView(this);
        v.setPadding(2, 2, 2, 2);
        v.setImageResource(R.mipmap.dimensionalcode);
        personQr.addView(v);
    }

    /**
     * 设置签名
     *
     * @param u
     */
    private void setPersonDescription(User u) {
        String description = u.getDescription();
        if (!TextUtils.isEmpty(description)) {
            TextView v = new TextView(this);
            v.setPadding(2, 2, 2, 2);
            v.setGravity(Gravity.CENTER);
            v.setText(description);
            personDescription.addView(v);
        }
    }

    /**
     * 设置生日
     *
     * @param u
     */
    private void setPersonBirthday(User u) {
        String birth = u.getBirthday();
        if (!TextUtils.isEmpty(birth)) {
            TextView v = new TextView(this);
            v.setPadding(2, 2, 2, 2);
            v.setGravity(Gravity.CENTER);
            v.setText(birth);
            personBirth.addView(v);
        }
    }

    /**
     * 设置性别
     *
     * @param u
     */
    private void setPersonSex(User u) {
        int gender = u.getGender();
        String sex = "";
        if (gender == 0) {
            sex = "男";
        } else {
            sex = "女";
        }
        TextView v = new TextView(this);
        v.setPadding(2, 2, 2, 2);
        v.setGravity(Gravity.CENTER);
        v.setText(sex);
        personSex.addView(v);
    }

    /**
     * 设置昵称
     *
     * @param u
     */
    private void setNickname(User u) {
        String nickname = u.getNickName();
        if (!TextUtils.isEmpty(nickname)) {
            TextView v = new TextView(this);
            v.setPadding(2, 2, 2, 2);
            v.setGravity(Gravity.CENTER);
            v.setText(nickname);
            personNickname.addView(v);
        }
    }

    /**
     * 设置头像
     *
     * @param u
     */
    private void setPersonHead(User u) {
        ImageView v = new ImageView(this);
        v.setBackgroundResource(R.drawable.circle_pic_bind);
        v.setPadding(2, 2, 2, 2);
        if (TextUtils.isEmpty(u.getHeadIcon())) {
            v.setImageResource(R.mipmap.avtor_default);
        } else {
            Glide.with(this).load(u.getHeadIcon()).into(v);
        }
        personHead.addView(v);
    }
}
