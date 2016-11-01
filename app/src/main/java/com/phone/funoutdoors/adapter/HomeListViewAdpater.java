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

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.Scene;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.utils.HomePageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo-SXX on 2016/10/28.
 */
public class HomeListViewAdpater extends BaseAdapter {
    List<Scene.ResultListBean.TopRealScenesBean> list;
    Context context;
    LayoutInflater inflater;
    private Animation animation;

    public HomeListViewAdpater(List<Scene.ResultListBean.TopRealScenesBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        animation = AnimationUtils.loadAnimation(context, R.anim.listview_anim);
    }

    @Override
    public int getCount() {
        return list ==null?0:list.size();
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

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_home_page_item_qubo, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convertView.startAnimation(animation);
        Glide.with(context).load(Constant.PICPATH + list.get(position).getScene_img()).into(holder.qubo_image);
        int type = list.get(position).getMedia_type();
        HomePageUtils.getMediaType(type, holder.media_typeName, holder.media_type);
        holder.media_name.setText(list.get(position).getScene_title());
        HomePageUtils.getSceneType(list.get(position).getScene_type(), holder.scene_type);
        holder.view_count.setText(String.valueOf(list.get(position).getView_count()));
        return convertView;
    }

    class ViewHolder {
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
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
