package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.phone.funoutdoors.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/30.
 */
public class OfflineMapDowningListAdapter extends BaseAdapter {
    private ArrayList<MKOLUpdateElement> list;
    private Context context;
    private LayoutInflater inflater;

    public OfflineMapDowningListAdapter(ArrayList<MKOLUpdateElement> list, Context context) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.offline_manage_downing_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MKOLUpdateElement updateElement = list.get(position);
        holder.cityName.setText(updateElement.cityName);
        holder.size.setText(updateElement.serversize / 1024 / 1024 + "M");
        holder.state.setText("下载进度（" + updateElement.ratio + "%)");
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.cityName)
        TextView cityName;
        @BindView(R.id.size)
        TextView size;
        @BindView(R.id.state)
        TextView state;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
