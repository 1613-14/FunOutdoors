package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.ConsultLVData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by White丶 on 2016/10/25.
 */
public class ConsultLVAdapter extends BaseAdapter {
    private List<ConsultLVData.ResultListBean> list = null;
    private Context context;
    private LayoutInflater inflater;
    private Animation animation;

    public ConsultLVAdapter(List<ConsultLVData.ResultListBean> list, Context context) {
        if (list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
        this.context = context;
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
        MyViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.consult_listview_item, null);
            holder = new MyViewHolder();
            ButterKnife.bind(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = ((MyViewHolder) convertView.getTag());
        }
        convertView.startAnimation(animation);
        holder.title.setText(list.get(position).getInfo_title());
        holder.content.setText(list.get(position).getInfo_summary());
        Picasso.with(context).load("http://image.quhuwai.cn/"+list.get(position).getInfo_cover()).fit().into(holder.image);
        return convertView;
    }

    static class MyViewHolder {
        @BindView(R.id.text_consult_title)
        TextView title;
        @BindView(R.id.text_consult_content)
        TextView content;
        @BindView(R.id.image_consult)
        ImageView image;

    }
}
