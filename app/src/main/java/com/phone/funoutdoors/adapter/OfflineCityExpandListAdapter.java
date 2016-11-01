package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.phone.funoutdoors.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/30.
 */
public class OfflineCityExpandListAdapter extends BaseExpandableListAdapter {
    private ArrayList<MKOLSearchRecord> groupList;
    private MKOfflineMap mkOfflineMap;
    private Context context;
    private LayoutInflater inflater;

    public OfflineCityExpandListAdapter(ArrayList<MKOLSearchRecord> groupList, MKOfflineMap mkOfflineMap, Context context) {
        this.groupList = groupList;
        this.mkOfflineMap = mkOfflineMap;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupList.get(groupPosition).childCities.size() > 0) {
            return groupList.get(groupPosition).childCities.size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (groupList.get(groupPosition).childCities.size() > 0) {
            return groupList.get(groupPosition).childCities.get(childPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.offline_map_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MKOLSearchRecord searchRecord = groupList.get(groupPosition);
        holder.city_name.setText(searchRecord.cityName);
        MKOLUpdateElement updateInfo = mkOfflineMap.getUpdateInfo(searchRecord.cityID);
        if (searchRecord.cityType == 1) {
            if (updateInfo == null){
                holder.state.setBackgroundResource(R.mipmap.after);
            }else{
                holder.state.setText("已下载");
                holder.state.setBackgroundResource(R.color.colorAccent);
            }
        } else {
            holder.tv_size.setText(searchRecord.size / 1024 / 1024 + "M");
            if (updateInfo == null) {
                holder.state.setBackgroundResource(R.mipmap.dow_01);
            } else {
                holder.state.setText("已下载");
                holder.state.setBackgroundResource(R.color.colorAccent);
            }
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.offline_map_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MKOLSearchRecord mkolSearchRecord = groupList.get(groupPosition).childCities.get(childPosition);
        holder.city_name.setText(mkolSearchRecord.cityName);
        holder.tv_size.setText(mkolSearchRecord.size / 1024 / 1024 + "M");
        MKOLUpdateElement updateInfo = mkOfflineMap.getUpdateInfo(mkolSearchRecord.cityID);
        if (updateInfo == null) {
            holder.state.setBackgroundResource(R.mipmap.dow_01);
        } else {
            holder.state.setText("已下载");
            holder.state.setBackgroundResource(R.color.colorAccent);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ViewHolder {
        TextView city_name, tv_size, state;

        ViewHolder(View view) {
            city_name = (TextView) view.findViewById(R.id.cityName);
            tv_size = (TextView) view.findViewById(R.id.size);
            state = (TextView) view.findViewById(R.id.state);
        }
    }
}
