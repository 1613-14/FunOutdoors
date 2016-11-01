package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.Hot;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by White丶 on 2016/10/27.
 */
public class HotRecyclerViewAdapter extends RecyclerView.Adapter<HotRecyclerViewAdapter.MyViewHolder> {

    private List<Hot.ResultListBean.OutdoorsBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private View view;
    private int width;
    private Animation animation;

    public HotRecyclerViewAdapter(List<Hot.ResultListBean.OutdoorsBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        animation = AnimationUtils.loadAnimation(context, R.anim.listview_anim);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.hot_recyclerview_item, parent, false);
        view.startAnimation(animation);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        holder.hotUserName.setText(list.get(position).getNickname());
        Glide.with(context).load(Constant.SPILT_IMG_URL + list.get(position).getOut_img1()).placeholder(R.mipmap.ic_launcher).into(holder.hotUserHead);
        holder.hotContent.setText(list.get(position).getOut_content());
        holder.hotLikeCounts.setText(String.valueOf(list.get(position).getPraise_count()));
        holder.hotCommentCounts.setText(String.valueOf(list.get(position).getDiscuss_count()));
        holder.hotTime.setText(String.valueOf(list.get(position).getCreate_time()));
        if (list.get(position).getImgs().size() == 1) {
            width = screenWidth;
            holder.hotImageContainer.setNumColumns(1);
        } else if (list.get(position).getImgs().size() > 1 && list.get(position).getImgs().size() <= 4) {
            width = screenWidth / 2;
            holder.hotImageContainer.setNumColumns(2);
        } else if (list.get(position).getImgs().size() > 4) {
            width = screenWidth / 3;
            holder.hotImageContainer.setNumColumns(3);
        }
        NineGridViewAdapter nineGridViewAdapter = new NineGridViewAdapter(context, list.get(position).getImgs(), width);
        holder.hotImageContainer.setAdapter(nineGridViewAdapter);
        holder.hotUserHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hot_userHead)
        CircleImageView hotUserHead;
        @BindView(R.id.hot_userName)
        TextView hotUserName;
        @BindView(R.id.hot_time)
        TextView hotTime;
        @BindView(R.id.hot_content)
        TextView hotContent;
        @BindView(R.id.hot_imageContainer)
        MyGridView hotImageContainer;
        @BindView(R.id.hot_like)
        CheckBox hotLike;
        @BindView(R.id.hot_likeCounts)
        TextView hotLikeCounts;
        @BindView(R.id.hot_comment)
        Button hotComment;
        @BindView(R.id.hot_commentCounts)
        TextView hotCommentCounts;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
