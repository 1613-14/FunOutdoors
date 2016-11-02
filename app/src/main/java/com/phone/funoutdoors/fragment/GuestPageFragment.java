package com.phone.funoutdoors.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.activity.GuestGuideDetailActivity;
import com.phone.funoutdoors.activity.GuestPageGuideMoreActivity;
import com.phone.funoutdoors.activity.GuestPagePromotionActivity;
import com.phone.funoutdoors.activity.Guest_DaRen_InfoActivity;
import com.phone.funoutdoors.activity.Guest_More_DaRenActivity;
import com.phone.funoutdoors.activity.HomePage_QuboActivity;
import com.phone.funoutdoors.adapter.GuestDarenListAdapter;
import com.phone.funoutdoors.adapter.GuestGuideListAdapter;
import com.phone.funoutdoors.adapter.GuestSceneListAdapter;
import com.phone.funoutdoors.bean.GuestData;
import com.phone.funoutdoors.bean.GuideBean;
import com.phone.funoutdoors.interfaces.GetGuestData;
import com.phone.funoutdoors.interfaces.GuestSceneItemClick;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.view.MyGridView;
import com.phone.funoutdoors.view.MyListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuestPageFragment extends Fragment {


    @BindView(R.id.tv_promotion)
    ImageView tvPromotion;
    @BindView(R.id.list_guide)
    MyListView listGuide;
    @BindView(R.id.list_scene)
    RecyclerView listScene;
    @BindView(R.id.recycler_daren)
    MyGridView recyclerDaren;
    @BindView(R.id.guide_more)
    TextView guideMore;
    @BindView(R.id.scene_more)
    TextView sceneMore;
    @BindView(R.id.daren_more)
    TextView darenMore;
    private GuestData.PromotionBean promotion;
    private List<GuestData.SceneBean> sceneList;
    private List<GuideBean> guideList;
    private List<GuestData.DarenBean> darenList;
    private Context context;

    public GuestPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_guest_page, container, false);
        }
        ButterKnife.bind(this, view);
        parseContent();
        return view;
    }

    /**
     * 设置头部图片
     */
    private void setHeader() {
        Glide.with(context).load(Constant.PICPATH + promotion.getPic_url())
                .into(tvPromotion);
        tvPromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GuestPagePromotionActivity.class);
                intent.putExtra("url", promotion.getPromotion_url());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_open, 0);
            }
        });
    }

    /**
     * 使用Retrofit解析接口
     */
    private void parseContent() {
        //创建对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://quhuwaiwap.oss-cn-beijing.aliyuncs.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建请求
        final GetGuestData getGuestData = retrofit.create(GetGuestData.class);
        //获得任务
        Call<GuestData> call = getGuestData.getDate();
        //执行任务
        call.enqueue(new Callback<GuestData>() {

            @Override
            public void onResponse(Call<GuestData> call, Response<GuestData> response) {
                if (response != null) {
                    GuestData guestData = response.body();
                    darenList = guestData.getDaren();
                    guideList = guestData.getGuide();
                    sceneList = guestData.getScene();
                    promotion = guestData.getPromotion();
                    setHeader();
                    setGuide();
                    setScene();
                    setDaren();
                }
            }

            @Override
            public void onFailure(Call<GuestData> call, Throwable t) {

            }
        });

    }

    /**
     * 设置达人
     */
    private void setDaren() {
        GuestDarenListAdapter guestDarenListAdapter = new GuestDarenListAdapter(darenList, context);
        recyclerDaren.setAdapter(guestDarenListAdapter);
        recyclerDaren.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, Guest_DaRen_InfoActivity.class);
                intent.putExtra("userId", darenList.get(position).getUser_id());
                startActivity(intent);
            }
        });
    }

    /**
     * 设置达人专访
     */
    private void setScene() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, sceneList.size());
        listScene.setLayoutManager(gridLayoutManager);
        GuestSceneListAdapter guestGuideListAdapter = new GuestSceneListAdapter(sceneList, context);
        listScene.setAdapter(guestGuideListAdapter);
        guestGuideListAdapter.setSceneItemClick(new GuestSceneItemClick() {
            @Override
            public void onItemClick(int secen_id) {
                Intent intent = new Intent(context, HomePage_QuboActivity.class);
                intent.putExtra("scene_id", secen_id);
                startActivity(intent);
            }
        });
    }

    /**
     * 设置游记列表
     */
    private void setGuide() {
        GuestGuideListAdapter guestGuideListAdapter = new GuestGuideListAdapter(guideList, context);
        listGuide.setAdapter(guestGuideListAdapter);
        listGuide.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, GuestGuideDetailActivity.class);
                intent.putExtra("actId", guideList.get(position).getAct_id());
                intent.putExtra("userId", guideList.get(position).getUser_id());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_open, 0);
            }
        });
    }

    @OnClick({R.id.guide_more, R.id.scene_more, R.id.daren_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guide_more:
                if (guideList.size() > 0) {
                    Intent intent = new Intent(context, GuestPageGuideMoreActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.scene_more:
                break;
            case R.id.daren_more:
                if (darenList.size() > 0) {
                    Intent intent = new Intent(context, Guest_More_DaRenActivity.class);
                    startActivity(intent);
                }
                break;
        }
        getActivity().overridePendingTransition(R.anim.activity_open, 0);
    }
}
