package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.DaRen;
import com.phone.funoutdoors.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo-SXX on 2016/10/30.
 */
public class GuestTravelRecyclerAdapter extends RecyclerView.Adapter<GuestTravelRecyclerAdapter.MyViewHolder> {

    List<DaRen.ResultListBean.GuidesBean> list;
    Context context;
    LayoutInflater inflater;
    private View view;
    private int width;


    public GuestTravelRecyclerAdapter(List<DaRen.ResultListBean.GuidesBean> list, Context context, int width) {
        this.list = list;
        this.context = context;
        this.width = width;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.guest_travel_recycle_item, parent, false);
        RelativeLayout.LayoutParams ll = new RelativeLayout.LayoutParams(width - 25, 600);
        view.setLayoutParams(ll);
        final MyViewHolder holder = new MyViewHolder(view);
        ButterKnife.bind(holder, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(Constant.PICPATH + list.get(position).getAct_cover()).into(holder.act_cover);
        holder.act_title.setText(list.get(position).getAct_title());
        holder.act_create_time.setText("发布于" + list.get(position).getAct_create_time());
        holder.discuss_count.setText(String.valueOf(list.get(position).getDiscuss_count()));
        holder.praise_count.setText(String.valueOf(list.get(position).getPraise_count()));
        holder.view_count.setText(String.valueOf(list.get(position).getView_count()));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.act_cover)
        ImageView act_cover;
        @BindView(R.id.act_title)
        TextView act_title;
        @BindView(R.id.act_create_time)
        TextView act_create_time;
        @BindView(R.id.view_count)
        TextView view_count;
        @BindView(R.id.discuss_count)
        TextView discuss_count;
        @BindView(R.id.praise_count)
        TextView praise_count;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
