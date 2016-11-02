package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.GuestData;
import com.phone.funoutdoors.interfaces.GuestSceneItemClick;
import com.phone.funoutdoors.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/31.
 */
public class GuestSceneListAdapter extends RecyclerView.Adapter<GuestSceneListAdapter.MyViewHolder> {
    private List<GuestData.SceneBean> list;
    private Context context;
    private LayoutInflater inflater;
    private GuestSceneItemClick sceneItemClick;

    public GuestSceneListAdapter(List<GuestData.SceneBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setSceneItemClick(GuestSceneItemClick sceneItemClick) {
        this.sceneItemClick = sceneItemClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.guest_page_scene_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final GuestData.SceneBean sceneBean = list.get(position);
        Glide.with(context).load(Constant.PICPATH + sceneBean.getScene_img()).into(holder.sceneImag);
        holder.sceneTitle.setText(sceneBean.getScene_title());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sceneItemClick != null) {
                    sceneItemClick.onItemClick(sceneBean.getScene_id());
                }
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.scene_imag)
        ImageView sceneImag;
        @BindView(R.id.scene_play)
        ImageButton scenePlay;
        @BindView(R.id.scene_title)
        TextView sceneTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
