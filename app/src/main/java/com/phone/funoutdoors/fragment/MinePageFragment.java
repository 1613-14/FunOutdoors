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
import com.phone.funoutdoors.activity.MinePage_BannerActivity;
import com.phone.funoutdoors.activity.MinePage_CertificateActivity;
import com.phone.funoutdoors.activity.MinePage_EditPersonActivity;
import com.phone.funoutdoors.activity.MinePage_MessageActivity;
import com.phone.funoutdoors.activity.MinePage_SettingActivity;
import com.phone.funoutdoors.bean.User;
import com.phone.funoutdoors.db.UserDBManager;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.utils.PopupWindowScreen;
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
        boolean isLogin = config.getBoolean("login", false);
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
                PopupWindowScreen.getInstance().showDialog(context, getActivity());
                break;
            case R.id.edit_img:
                Intent intent1 = new Intent(context, MinePage_EditPersonActivity.class);
                startActivity(intent1);
                break;
            case R.id.banner_dicovered:
                Intent intent2 = new Intent(context, MinePage_BannerActivity.class);
                intent2.putExtra("type", Constant.MESSAGE_SYSTEM);
                intent2.putExtra("title", "我的发现");
                startActivity(intent2);
                break;
            case R.id.banner_route:
                Intent intent3 = new Intent(context, MinePage_BannerActivity.class);
                intent3.putExtra("type", Constant.MESSAGE_NOTIFICATION);
                intent3.putExtra("title", "我的路线");
                startActivity(intent3);
                break;
            case R.id.banner_travel:
                Intent intent4 = new Intent(context, MinePage_BannerActivity.class);
                intent4.putExtra("type", Constant.MESSAGE_PRIVATE);
                intent4.putExtra("title", "我的游记");
                startActivity(intent4);
                break;
            case R.id.logined_certification:
                Intent intent5 = new Intent(context, MinePage_CertificateActivity.class);
                startActivity(intent5);
                break;
            case R.id.logined_message:
                Intent intent6 = new Intent(context, MinePage_MessageActivity.class);
                startActivity(intent6);
                break;
            case R.id.logined_setting:
                Intent intent7 = new Intent(context, MinePage_SettingActivity.class);
                startActivityForResult(intent7, 101);
                break;
            case R.id.fans:
                Log.e("TAG", "粉丝");
                break;
            case R.id.attention:
                Log.e("TAG", "关注");
                break;
        }
        getActivity().overridePendingTransition(R.anim.activity_open, 0);
    }

    /**
     * 查询用户
     */
    private void queryUser(View view) {
        TextView fansCount = (TextView) view.findViewById(R.id.fansCount);
        TextView attentionCount = (TextView) view.findViewById(R.id.attentionCount);
        ImageView headPic = (ImageView) view.findViewById(R.id.headPic);
        TextView description = (TextView) view.findViewById(R.id.tv_description);
        //设置当前用户的基本信息
        long id = context.getSharedPreferences("config", Context.MODE_PRIVATE).getLong("userId", 0);
        List<User> user = UserDBManager.getInstance(context).findByUser(id);
        User u = user.get(0);
        fansCount.setText(String.valueOf(u.getFans()));
        attentionCount.setText(String.valueOf(u.getAttention()));
        tvUser.setText(u.getNickName());
        if (TextUtils.isEmpty(u.getDescription())) {
            description.setVisibility(View.GONE);
        } else {
            description.setText(u.getDescription());
        }
        if (!TextUtils.isEmpty(u.getHeadIcon())) {
            Glide.with(context).load(u.getHeadIcon()).into(headPic);
        } else {
            headPic.setImageResource(R.mipmap.avtor_default);
        }
        //设置当前用户的发现、游记、路线
        //TODO
    }

}
