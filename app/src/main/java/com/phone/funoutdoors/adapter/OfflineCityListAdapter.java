package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.phone.funoutdoors.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/30.
 */
public class OfflineCityListAdapter extends BaseAdapter {
    private ArrayList<MKOLSearchRecord> list;
    private MKOfflineMap mkOfflineMap;
    private Context context;
    private LayoutInflater inflater;

    public OfflineCityListAdapter(ArrayList<MKOLSearchRecord> list, MKOfflineMap mkOfflineMap, Context context) {
        this.list = list;
        this.mkOfflineMap = mkOfflineMap;
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
            convertView = inflater.inflate(R.layout.offline_map_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MKOLSearchRecord searchRecord = list.get(position);
        holder.cityName.setText(searchRecord.cityName);
        holder.size.setText(searchRecord.size / 1024 / 1024 + "M");
        MKOLUpdateElement updateInfo = mkOfflineMap.getUpdateInfo(searchRecord.cityID);
        if (updateInfo == null) {
            holder.sate.setBackgroundResource(R.mipmap.dow_01);
        } else {
            holder.sate.setText("已下载");
            holder.sate.setBackgroundResource(R.color.colorAccent);
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.cityName)
        TextView cityName;
        @BindView(R.id.size)
        TextView size;
        @BindView(R.id.state)
        TextView sate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
