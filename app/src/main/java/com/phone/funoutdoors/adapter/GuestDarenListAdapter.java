package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.GuestData;
import com.phone.funoutdoors.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/31.
 */
public class GuestDarenListAdapter extends RecyclerView.Adapter<GuestDarenListAdapter.MyViewHolder> {
    private List<GuestData.DarenBean> list;
    private Context context;
    private LayoutInflater inflater;

    public GuestDarenListAdapter(List<GuestData.DarenBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.guest_page_daren_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GuestData.DarenBean darenBean = list.get(position);
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        if (position<5){
            if (position%2== 0){
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(widthPixels / 5 * 3, 200);
                holder.darenAvatar.setLayoutParams(layoutParams);
            }else{
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(widthPixels / 5 * 2, 300);
                holder.darenAvatar.setLayoutParams(layoutParams);
            }
        }else if (position>=5&&position<10){
            if (position%2 == 1){
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(widthPixels / 5 * 3, 200);
                holder.darenAvatar.setLayoutParams(layoutParams);
            }else{
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(widthPixels / 5 * 2, 300);
                holder.darenAvatar.setLayoutParams(layoutParams);
            }
        }else{
            if (position%2== 0){
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(widthPixels / 5 * 3, 200);
                holder.darenAvatar.setLayoutParams(layoutParams);
            }else{
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(widthPixels / 5 * 2, 300);
                holder.darenAvatar.setLayoutParams(layoutParams);
            }
        }

        Glide.with(context).load(Constant.PICPATH + darenBean.getAvatar())
                .into(holder.darenAvatar);
        holder.darenNickname.setText(darenBean.getNickname());
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.daren_avatar)
        ImageView darenAvatar;
        @BindView(R.id.daren_nickname)
        TextView darenNickname;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
