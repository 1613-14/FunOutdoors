package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.GuideDetail;
import com.phone.funoutdoors.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/1.
 */
public class GuideDetailListAdapter extends BaseAdapter {
    private List<GuideDetail.ResultListBean.ShareBean> list;
    private Context context;
    private LayoutInflater inflater;
    private Animation animation;

    public GuideDetailListAdapter(Context context, List<GuideDetail.ResultListBean.ShareBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        animation = AnimationUtils.loadAnimation(context, R.anim.listview_anim);
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
            convertView = inflater.inflate(R.layout.guide_detail_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        convertView.startAnimation(animation);
        GuideDetail.ResultListBean.ShareBean shareBean = list.get(position);
        Glide.with(context).load(Constant.PICPATH + shareBean.getFp_img())
                .placeholder(R.mipmap.default_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .animate(new AlphaAnimation(0f,1f))
                .into(viewHolder.fpImg);
        viewHolder.fpContent.setText(shareBean.getFp_content());
        viewHolder.tvDiscuss.setText(String.valueOf(shareBean.getDiscuss_count()));
        viewHolder.tvPraise.setText(String.valueOf(shareBean.getPraise_count()));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.fp_img)
        ImageView fpImg;
        @BindView(R.id.fp_content)
        TextView fpContent;
        @BindView(R.id.tv_discuss)
        TextView tvDiscuss;
        @BindView(R.id.tv_praise)
        TextView tvPraise;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
