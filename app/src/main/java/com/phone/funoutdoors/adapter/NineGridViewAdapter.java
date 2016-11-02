package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.Hot;
import com.phone.funoutdoors.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by White丶 on 2016/10/28.
 */
public class NineGridViewAdapter extends BaseAdapter {
    private List<Hot.ResultListBean.OutdoorsBean.ImgsBean> list;
    private Context context;
    private LayoutInflater inflater;
    private int width;

    public NineGridViewAdapter(Context context, List<Hot.ResultListBean.OutdoorsBean.ImgsBean> list, int width) {
        this.context = context;
        this.list = list;
        this.width = width;
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.nine_gridview_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.nine_gridview_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(Constant.SPILT_IMG_URL + list.get(position).getImg_url()).resize(width, width).placeholder(R.mipmap.default_img).into(holder.imageView);
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
    }
}
