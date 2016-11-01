package com.phone.funoutdoors.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.GuestTravelRecyclerAdapter;
import com.phone.funoutdoors.bean.DaRen;
import com.phone.funoutdoors.utils.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class Guest_DaRen_InfoActivity extends AppCompatActivity {

    @BindView(R.id.act_cover)
    ImageView act_cover;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.intro)
    TextView intro;
    @BindView(R.id.fansCount)
    TextView fansCount;
    @BindView(R.id.attentionCount)
    TextView attentionCount;
    @BindView(R.id.travelView)
    RecyclerView travelView;
    private int width;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_da_ren_info);
        ButterKnife.bind(this);
        width = getResources().getDisplayMetrics().widthPixels;
        downParseJson();


    }

    /**
     * 下载并解析json
     */
    private void downParseJson() {
        final int userId = getIntent().getIntExtra("userId", 0);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://www.quhuwai.cn/webservice/qhw2001/func_sub3102",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        DaRen daRen = gson.fromJson(s, DaRen.class);
                        DaRen.ResultListBean list = daRen.getResultList().get(0);
                        setPersonInfo(list);
                        GridLayoutManager manager = new GridLayoutManager(Guest_DaRen_InfoActivity.this, list.getGuides().size());
                        travelView.setLayoutManager(manager);
                        List<DaRen.ResultListBean.GuidesBean> guides = list.getGuides();
                        travelView.setAdapter(new GuestTravelRecyclerAdapter(guides, Guest_DaRen_InfoActivity.this, width));


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("myId", "21196");
                map.put("userId", String.valueOf(userId));
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                return map;
            }

        };
        queue.add(request);
    }

    /**
     * 设置个人信息
     *
     * @param list
     */
    private void setPersonInfo(DaRen.ResultListBean list) {
        Glide.with(getApplicationContext()).load(Constant.PICPATH + list.getAvatar()).into(avatar);
        nickname.setText(list.getNickname());
        intro.setText(list.getIntro());
        fansCount.setText(String.valueOf(list.getFansCnt()));
        attentionCount.setText(String.valueOf(list.getGender()));
    }

    @OnClick(R.id.back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
