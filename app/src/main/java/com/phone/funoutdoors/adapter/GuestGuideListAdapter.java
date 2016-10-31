package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.GuestData;
import com.phone.funoutdoors.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/10/31.
 */
public class GuestGuideListAdapter extends BaseAdapter {
    private List<GuestData.GuideBean> list;
    private Context context;
    private LayoutInflater inflater;

    public GuestGuideListAdapter(List<GuestData.GuideBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.guest_page_guide_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GuestData.GuideBean guideBean = list.get(position);
        viewHolder.tvDiscuss.setText(String.valueOf(guideBean.getDiscuss_count()));
        viewHolder.tvNickname.setText(guideBean.getNickname());
        viewHolder.tvPraise.setText(String.valueOf(guideBean.getPraise_count()));
        viewHolder.tvTitle.setText(guideBean.getAct_title());
        viewHolder.tvView.setText(String.valueOf(guideBean.getView_count()));
        Glide.with(context).load(Constant.PICPATH + guideBean.getAct_cover()).into(viewHolder.ivCover);
        Glide.with(context).load(Constant.PICPATH + guideBean.getAvatar()).into(viewHolder.civAvatar);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.civ_avatar)
        CircleImageView civAvatar;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.tv_view)
        TextView tvView;
        @BindView(R.id.tv_discuss)
        TextView tvDiscuss;
        @BindView(R.id.tv_praise)
        TextView tvPraise;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
