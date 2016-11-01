package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.MoreScene;
import com.phone.funoutdoors.interfaces.HomeRecyclerListener;
import com.phone.funoutdoors.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo-SXX on 2016/10/30.
 */
public class HomeSceneMoreRecyclerAdapter extends RecyclerView.Adapter<HomeSceneMoreRecyclerAdapter.MyViewHolder> {

    List<MoreScene.ResultListBean> list;
    Context context;
    LayoutInflater inflater;
    private View view;
    HomeRecyclerListener listener;

    public HomeSceneMoreRecyclerAdapter( Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<MoreScene.ResultListBean> list) {
        this.list = list;
    }

    public void setListener(HomeRecyclerListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.activity_home_page_more_scene_item, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        ButterKnife.bind(holder, view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemListener(holder.getLayoutPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String img = list.get(position).getDestination_img1().split("#")[1];
        Glide.with(context).load(Constant.PICPATH + img).into(holder.destination_img1);
        holder.destination_name.setText(list.get(position).getDestination_name());
        holder.destination_summary.setText(list.get(position).getDestination_summary());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.destination_img1)
        ImageView destination_img1;
        @BindView(R.id.destination_name)
        TextView destination_name;
        @BindView(R.id.destination_summary)
        TextView destination_summary;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
