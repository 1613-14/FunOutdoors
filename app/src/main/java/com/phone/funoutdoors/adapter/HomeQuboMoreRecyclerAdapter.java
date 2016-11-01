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
import com.phone.funoutdoors.bean.MoreQubo;
import com.phone.funoutdoors.interfaces.HomeRecyclerListener;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.utils.HomePageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo-SXX on 2016/10/30.
 */
public class HomeQuboMoreRecyclerAdapter extends RecyclerView.Adapter<HomeQuboMoreRecyclerAdapter.MyViewHolder> {

    List<MoreQubo.ResultListBean> list;
    Context context;
    LayoutInflater inflater;
    private View view;
    HomeRecyclerListener listener;

    public HomeQuboMoreRecyclerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<MoreQubo.ResultListBean> list) {
        this.list = list;
    }

    public void setListener(HomeRecyclerListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.fragment_home_page_item_qubo, parent, false);
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
        Glide.with(context).load(Constant.PICPATH + list.get(position).getScene_img()).into(holder.qubo_image);
        int type = list.get(position).getMedia_type();
        HomePageUtils.getMediaType(type, holder.media_typeName, holder.media_type);
        holder.media_name.setText(list.get(position).getScene_title());
        HomePageUtils.getSceneType(list.get(position).getScene_type(), holder.scene_type);
        holder.view_count.setText(String.valueOf(list.get(position).getView_count()));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.media_name)
        TextView media_name;
        @BindView(R.id.qubo_image)
        ImageView qubo_image;
        @BindView(R.id.media_type)
        ImageView media_type;
        @BindView(R.id.media_typeName)
        TextView media_typeName;
        @BindView(R.id.scene_type)
        TextView scene_type;
        @BindView(R.id.view_count)
        TextView view_count;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
