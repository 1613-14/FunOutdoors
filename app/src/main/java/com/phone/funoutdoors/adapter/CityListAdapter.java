package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phone.funoutdoors.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CityListAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;
    private List<Integer> letterCharList;
    private String[] title;
    private LayoutInflater inflater;

    private Map<String, Integer> map = new HashMap<>();

    public CityListAdapter(Context context, List<String> data, List<Integer> letterCharList, String[] title) {
        this.context = context;
        this.data = data;
        this.letterCharList = letterCharList;
        this.title = title;
        inflater = LayoutInflater.from(context);
        int index = 0;
        for (int i = 0; i < letterCharList.size(); i++) {
            if (letterCharList.get(i) == -1) {
                map.put(data.get(i), -1);
            } else {
                map.put(data.get(i), index++);
            }
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.city_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int pre = map.get(data.get(position));
        if (pre != -1) {
            holder.mainlistItemTv01.setVisibility(View.VISIBLE);
            holder.mainlistItemTv01.setText(title[pre]);
            holder.mainlistItemTv02.setText(data.get(position));
        } else {
            holder.mainlistItemTv01.setVisibility(View.GONE);
            holder.mainlistItemTv02.setText(data.get(position));
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.mainlist_item_tv01)
        TextView mainlistItemTv01;
        @BindView(R.id.mainlist_item_tv02)
        TextView mainlistItemTv02;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
