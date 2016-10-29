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
import com.phone.funoutdoors.bean.Qubo;
import com.phone.funoutdoors.interfaces.HomeRecyclerListener;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.utils.HomePageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo-SXX on 2016/10/28.
 */
public class HomePageRelationAdapter extends RecyclerView.Adapter<HomePageRelationAdapter.MyViewHolder> {

    List<Qubo.ResultListBean.RelationBean> list;
    Context context;
    LayoutInflater inflater;
    HomeRecyclerListener listener;

    public void setListener(HomeRecyclerListener listener) {
        this.listener = listener;
    }


    public HomePageRelationAdapter(List<Qubo.ResultListBean.RelationBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_home_page_relation_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        ButterKnife.bind(viewHolder, view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemListener(viewHolder.getLayoutPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(Constant.PICPATH + list.get(position).getScene_img()).into(holder.relation_img);
        HomePageUtils.getMediaType(list.get(position).getMedia_type(), holder.relation_media_type, holder.relation_type);
        HomePageUtils.getSceneType(list.get(position).getScene_type(), holder.relation_title);
        holder.relation_title.append(list.get(position).getScene_title());
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.relation_img)
        ImageView relation_img;
        @BindView(R.id.relation_type)
        ImageView relation_type;
        @BindView(R.id.relation_media_type)
        TextView relation_media_type;
        @BindView(R.id.relation_title)
        TextView relation_title;

        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }
}
