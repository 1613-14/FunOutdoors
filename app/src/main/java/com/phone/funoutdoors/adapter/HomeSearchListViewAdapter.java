package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.DaRenList;
import com.phone.funoutdoors.bean.QuboList;
import com.phone.funoutdoors.bean.SceneList;
import com.phone.funoutdoors.bean.TravelList;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.utils.HomePageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Lenovo-SXX on 2016/10/31.
 */
public class HomeSearchListViewAdapter extends BaseAdapter {

    List<SceneList.ResultListBean> list;
    Context context;
    LayoutInflater inflater;
    List<DaRenList.DaListBean> daList;
    List<TravelList.TravelListBean> travelList;
    List<QuboList.ResultListBean> quboList;
    int type;


    /**
     * 游记
     *
     * @param context
     * @param travelList
     * @param type
     * @param flag
     */
    public HomeSearchListViewAdapter(Context context, List<TravelList.TravelListBean> travelList, int type, int flag) {
        this.context = context;
        this.travelList = travelList;
        this.type = type;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 景点
     *
     * @param context
     * @param list
     */
    public HomeSearchListViewAdapter(Context context, List<SceneList.ResultListBean> list, int type) {
        this.type = type;
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 达人
     *
     * @param context
     * @param type
     * @param daList
     */
    public HomeSearchListViewAdapter(Context context, int type, List<DaRenList.DaListBean> daList) {
        this.context = context;
        this.type = type;
        this.daList = daList;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 趣播
     *
     * @param quboList
     * @param context
     * @param type
     */
    public HomeSearchListViewAdapter(List<QuboList.ResultListBean> quboList, Context context, int type) {
        this.quboList = quboList;
        this.context = context;
        this.type = type;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        switch (type) {
            case 1:
                return list == null ? 0 : list.size();
            case 2:
                return 0;
            case 3:
                return travelList == null ? 0 : travelList.size();
            case 4:
                return daList == null ? 0 : daList.size();
            case 5:
                return quboList == null ? 0 : quboList.size();
        }
        return 0;

    }


    @Override
    public Object getItem(int position) {
        switch (type) {
            case 1:
                return list.get(position);
            case 2:
                return 0;
            case 3:
                return travelList.get(position);
            case 4:
                return daList.get(position);
            case 5:
                return quboList.get(position);
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (type) {
            case 1:
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.activity_home_page_more_scene_item, parent, false);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                String s = list.get(position).getDestination_img1().split("#")[1];
                Glide.with(context).load(Constant.PICPATH + s).placeholder(R.mipmap.default_img).error(R.mipmap.default_img_error).into(holder.destination_img1);
                holder.destination_name.setText(list.get(position).getDestination_name());
                holder.destination_summary.setText(list.get(position).getDestination_summary());
                break;
            case 3:
                ViewHolder2 holder2 = null;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.activity_home_page_search_travel_item, parent, false);
                    holder2 = new ViewHolder2(convertView);
                    convertView.setTag(holder2);
                } else {
                    holder2 = (ViewHolder2) convertView.getTag();
                }
                Glide.with(context).load(Constant.PICPATH + travelList.get(position).getAvatar()).placeholder(R.mipmap.default_img).error(R.mipmap.default_img_error).into(holder2.avatar);
                holder2.act_title.setText(travelList.get(position).getAct_title());
                holder2.act_stdate.setText(travelList.get(position).getAct_stdate());

                break;
            case 4:
                ViewHolder4 holder4 = null;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.activity_home_page_search_daren_item, parent, false);
                    holder4 = new ViewHolder4(convertView);
                    convertView.setTag(holder4);
                } else {
                    holder4 = (ViewHolder4) convertView.getTag();
                }
                Glide.with(context).load(Constant.PICPATH + daList.get(position).getAvatar()).placeholder(R.mipmap.avtor_default).error(R.mipmap.avtor_default).into(holder4.usericon);
                holder4.username.setText(daList.get(position).getNickname());
                String intro = daList.get(position).getIntro();
                if (!TextUtils.isEmpty(intro)) {
                    holder4.usersign.setText(intro);
                }

                break;
            case 5:
                ViewHolder5 holder5 = null;

                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.fragment_home_page_item_qubo, parent, false);
                    holder5 = new ViewHolder5(convertView);
                    convertView.setTag(holder5);
                } else {
                    holder5 = (ViewHolder5) convertView.getTag();
                }
                Glide.with(context).load(Constant.PICPATH + quboList.get(position).getScene_img()).into(holder5.qubo_image);
                int type = quboList.get(position).getMedia_type();
                HomePageUtils.getMediaType(type, holder5.media_typeName, holder5.media_type);
                holder5.media_name.setText(quboList.get(position).getScene_title());
                HomePageUtils.getSceneType(quboList.get(position).getScene_type(), holder5.scene_type);
                holder5.view_count.setText(String.valueOf(quboList.get(position).getView_count()));
                break;
        }
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

    static class ViewHolder2 {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.act_title)
        TextView act_title;
        @BindView(R.id.act_stdate)
        TextView act_stdate;

        public ViewHolder2(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder4 {
        @BindView(R.id.usericon)
        CircleImageView usericon;
        @BindView(R.id.username)
        TextView username;
        @BindView(R.id.usersign)
        TextView usersign;

        public ViewHolder4(View view) {


            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder5 {
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

        public ViewHolder5(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
