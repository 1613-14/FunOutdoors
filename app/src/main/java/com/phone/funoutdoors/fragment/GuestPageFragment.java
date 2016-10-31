package com.phone.funoutdoors.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.GuestGuideListAdapter;
import com.phone.funoutdoors.bean.GuestData;
import com.phone.funoutdoors.interfaces.GetGuestData;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.view.MyListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.gridlist_scene)
    GridView gridlistScene;
    @BindView(R.id.recycler_daren)
    RecyclerView recyclerDaren;
    private GuestData.PromotionBean promotion;
    private List<GuestData.SceneBean> sceneList;
    private List<GuestData.GuideBean> guideList;
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
                }
            }

            @Override
            public void onFailure(Call<GuestData> call, Throwable t) {

            }
        });

    }

    /**
     * 设置游记列表
     */
    private void setGuide() {
        GuestGuideListAdapter guestGuideListAdapter = new GuestGuideListAdapter(guideList, context);
        listGuide.setAdapter(guestGuideListAdapter);
    }

}
