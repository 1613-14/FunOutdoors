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
import com.phone.funoutdoors.bean.MoreScene;
import com.phone.funoutdoors.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo-SXX on 2016/11/2.
 */
public class HomeSceneMoreListViewAdapter extends BaseAdapter {

    List<MoreScene.ResultListBean> list;
    Context context;
    LayoutInflater inflater;

    public HomeSceneMoreListViewAdapter(List<MoreScene.ResultListBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_home_page_more_scene_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String img = list.get(position).getDestination_img1().split("#")[1];
        Glide.with(context).load(Constant.PICPATH + img).into(holder.destination_img1);
        holder.destination_name.setText(list.get(position).getDestination_name());
        holder.destination_summary.setText(list.get(position).getDestination_summary());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.destination_img1)
        ImageView destination_img1;
        @BindView(R.id.destination_name)
        TextView destination_name;
        @BindView(R.id.destination_summary)
        TextView destination_summary;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
