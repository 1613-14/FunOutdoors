package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import com.phone.funoutdoors.adapter.HomePageRelationAdapter;
import com.phone.funoutdoors.bean.Qubo;
import com.phone.funoutdoors.interfaces.HomeRecyclerListener;
import com.phone.funoutdoors.utils.ConnectionUtils;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.utils.HomePageUtils;
import com.phone.funoutdoors.vr.SimpleVrPanoramaActivity;
import com.phone.funoutdoors.vr.SimpleVrVideoActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePage_QuboActivity extends AppCompatActivity {

    @BindView(R.id.qubo_scene_img)
    ImageView qubo_scene_img;
    @BindView(R.id.qubo_scene_mediaimg)
    ImageView qubo_scene_mediaimg;
    @BindView(R.id.qubo_scene_mediatitle)
    TextView qubo_scene_mediatitle;
    @BindView(R.id.qubo_scene_type)
    TextView qubo_scene_type;
    @BindView(R.id.qubo_scene_title)
    TextView qubo_scene_title;
    @BindView(R.id.qubo_scene_content)
    TextView qubo_scene_content;
    @BindView(R.id.qubo_scene_praise_count)
    TextView qubo_scene_praise_count;
    @BindView(R.id.qubo_scene_discuss_count)
    TextView qubo_scene_discuss_count;
    @BindView(R.id.qubo_scene_view_count)
    TextView qubo_scene_view_count;
    @BindView(R.id.qubo_scene_info)
    TextView qubo_scene_info;
    @BindView(R.id.relation)
    RecyclerView relation;
    @BindView(R.id.fragment_container)
    FrameLayout fragment_container;
    @BindView(R.id.cardView)
    CardView cardView;
    private RequestQueue requestQueue;
    private Handler handler = new Handler();


    private int scene_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_qubo);
        ButterKnife.bind(this);
        scene_id = getIntent().getIntExtra("scene_id", 0);
        int tag = getIntent().getIntExtra("tag", 1);
        switch (tag) {
            case 0:
                cardView.setVisibility(View.GONE);
                break;
            case 1:
                qubo_scene_info.setVisibility(View.GONE);
                break;
        }
        downParseJson();

    }

    /**
     * 下载解析Json
     */
    private void downParseJson() {
        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://www.quhuwai.cn/webservice/qhw1001/func_sub1104",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        Qubo qubo = gson.fromJson(s, Qubo.class);
                        Qubo.ResultListBean bean = qubo.getResultList().get(0);
                        setDataForLayout(bean);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("myId", "21196");
                map.put("scene_id", String.valueOf(scene_id));
                return map;
            }
        };
        requestQueue.add(request);
    }


    /**
     * 为布局设值
     *
     * @param bean
     */
    private void setDataForLayout(final Qubo.ResultListBean bean) {
        Glide.with(getApplicationContext()).load(Constant.PICPATH + bean.getScene_img()).into(qubo_scene_img);
        HomePageUtils.getMediaType(bean.getMedia_type(), qubo_scene_mediatitle, qubo_scene_mediaimg);
        HomePageUtils.getSceneType(bean.getScene_type(), qubo_scene_type);
        qubo_scene_title.setText(bean.getScene_title());
        qubo_scene_content.setText(bean.getScene_content());
        qubo_scene_praise_count.setText(String.valueOf(bean.getPraise_count()));
        int praiseFlg = bean.getPraiseFlg();
        if (praiseFlg != 0) {
            qubo_scene_praise_count.setSelected(true);
        }
        qubo_scene_discuss_count.setText(String.valueOf(bean.getDiscuss_count()));
        qubo_scene_view_count.setText(String.valueOf(bean.getView_count()));
        GridLayoutManager manager = new GridLayoutManager(this, bean.getRelation().size());
        relation.setLayoutManager(manager);
        final List<Qubo.ResultListBean.RelationBean> list = bean.getRelation();
        HomePageRelationAdapter adapter = new HomePageRelationAdapter(list, this);
        relation.setAdapter(adapter);

        qubo_scene_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                判断是否有网
                if (HomePageUtils.isNetworkConnected(HomePage_QuboActivity.this)) {
//                    判断是否是WiFi，若不是
                    if (HomePageUtils.getNetworkType(HomePage_QuboActivity.this) != 1) {
                        View view = View.inflate(HomePage_QuboActivity.this, R.layout.activity_home_page_net_dialog, null);
                        Button commit_bnt = (Button) view.findViewById(R.id.commit_bnt);
                        Button cancel_bnt = (Button) view.findViewById(R.id.cancel_bnt);
                        final PopupWindow pop = new PopupWindow(view, ViewPager.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        commit_bnt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                toNextActivity(bean);
                                pop.dismiss();
                            }
                        });
                        cancel_bnt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pop.dismiss();
                            }
                        });
                        pop.setOutsideTouchable(true);
                        pop.setFocusable(true);
                        pop.setBackgroundDrawable(new BitmapDrawable());
                        pop.showAtLocation(qubo_scene_img, Gravity.CENTER, 0, 0);
                    } else {
//                        判断是否是WiFi，若是
                        toNextActivity(bean);
                    }
                } else {
//                    判断是否有网，无网
                    ConnectionUtils.showConnectionFailure(HomePage_QuboActivity.this, handler, fragment_container);
                }
            }
        });
        adapter.setListener(new HomeRecyclerListener() {
            @Override
            public void onItemListener(int position) {
                Intent intent = new Intent(HomePage_QuboActivity.this, HomePage_QuboActivity.class);
                intent.putExtra("scene_id", list.get(position).getScene_id());
                startActivity(intent);
            }
        });
        qubo_scene_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage_QuboActivity.this, HomePage_SceneActivity.class);
                intent.putExtra("title", bean.getScene_title());
                intent.putExtra("destination_id", bean.getDestination_id());
                startActivity(intent);

            }
        });

    }

    private void toNextActivity(Qubo.ResultListBean bean) {
        Intent intent = new Intent();
        switch (bean.getMedia_type()) {
            case 1:
                intent.setClass(HomePage_QuboActivity.this, HomePage_Qubo_VideoPalyActivity.class);
                intent.putExtra("time", bean.getMedia_time());
                break;
            case 2:
                intent.setClass(HomePage_QuboActivity.this, SimpleVrPanoramaActivity.class);
                intent.putExtra("time", bean.getMedia_time());
                break;
            case 3:
                intent.setClass(HomePage_QuboActivity.this, SimpleVrVideoActivity.class);
                break;
        }
        intent.putExtra("video", bean.getScene_video());
        startActivity(intent);
    }


    @OnClick(R.id.qubo_scene_back)
    public void onBackClick(View view) {
        finish();
    }
}
