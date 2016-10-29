package com.phone.funoutdoors.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.activity.LoginActivity;
import com.phone.funoutdoors.activity.MinePage_CertificateActivity;
import com.phone.funoutdoors.bean.User;
import com.phone.funoutdoors.db.UserDBManager;
import com.phone.funoutdoors.view.LoginedBannerItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinePageFragment extends Fragment {

    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.banner_dicovered)
    LoginedBannerItem bannerDicovered;
    @BindView(R.id.banner_route)
    LoginedBannerItem bannerRoute;
    @BindView(R.id.banner_travel)
    LoginedBannerItem bannerTravel;
    private Context context;

    public MinePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean isLogin =config.getBoolean("login",false);
        if (!isLogin) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent, 100);
            getActivity().overridePendingTransition(R.anim.activity_open, 0);
            return null;
        } else {
            View view = inflater.inflate(R.layout.fragment_mine_page, container, false);
            ButterKnife.bind(this, view);
            queryUser(view);
            return view;
        }
    }

    @OnClick({R.id.headPic, R.id.edit_img, R.id.banner_dicovered, R.id.banner_route, R.id.banner_travel, R.id.logined_certification, R.id.logined_message, R.id.logined_setting, R.id.fans, R.id.attention})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.headPic:
                Log.e("TAG", "头像");
                break;
            case R.id.edit_img:
                Log.e("TAG", "编辑");
                break;
            case R.id.banner_dicovered:
                Log.e("TAG", "发现");
                break;
            case R.id.banner_route:
                Log.e("TAG", "路线");
                break;
            case R.id.banner_travel:
                Log.e("TAG", "游记");
                break;
            case R.id.logined_certification:
                Intent intent = new Intent(context, MinePage_CertificateActivity.class);
                startActivity(intent);
                break;
            case R.id.logined_message:
                Log.e("TAG", "消息");
                break;
            case R.id.logined_setting:
                Log.e("TAG", "设置");
                break;
            case R.id.fans:
                Log.e("TAG", "粉丝");
                break;
            case R.id.attention:
                Log.e("TAG", "关注");
                break;
        }
    }

    /**
     * 查询用户
     */
    private void queryUser(View view) {
        TextView fansCount = (TextView) view.findViewById(R.id.fansCount);
        TextView attentionCount = (TextView) view.findViewById(R.id.attentionCount);
        ImageView headPic = (ImageView) view.findViewById(R.id.headPic);
        //设置当前用户的基本信息
        long id = context.getSharedPreferences("config", Context.MODE_PRIVATE).getLong("userId", 0);
        List<User> user = UserDBManager.getInstance(context).findByUser(id);
        fansCount.setText(String.valueOf(user.get(0).getFans()));
        attentionCount.setText(String.valueOf(user.get(0).getAttention()));
        tvUser.setText(user.get(0).getNickName());
        if (!TextUtils.isEmpty(user.get(0).getHeadIcon())) {
            Glide.with(context).load(user.get(0).getHeadIcon()).into(headPic);
        } else {
            headPic.setImageResource(R.mipmap.avtor_default);
        }
        //设置当前用户的发现、游记、路线
        //TODO
    }
}
