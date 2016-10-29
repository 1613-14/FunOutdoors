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
import com.phone.funoutdoors.bean.ConsultLVHeaderData;
import com.phone.funoutdoors.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by White丶 on 2016/10/26.
 */
public class SpecialAdapter extends BaseAdapter {
    private List<ConsultLVHeaderData.ResultListBean> list;
    private Context context;
    private LayoutInflater inflater;

    public SpecialAdapter(List<ConsultLVHeaderData.ResultListBean> list, Context context) {
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
        MyViewHolder holder = null;
        if (convertView == null) {
            holder = new MyViewHolder();
            convertView = inflater.inflate(R.layout.specilal_listview_item, null);
            ButterKnife.bind(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = ((MyViewHolder) convertView.getTag());
        }
        holder.title.setText(list.get(position).getFeature_title());
        Glide.with(context).load(Constant.SPILT_IMG_URL + list.get(position).getFeature_cover()).into(holder.image);
        return convertView;
    }

    static class MyViewHolder {
        @BindView(R.id.consult_listView_header_title)
        TextView title;
        @BindView(R.id.consult_listView_header_image)
        ImageView image;
    }

}
