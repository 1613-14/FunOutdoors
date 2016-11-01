package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.view.MyButton;

/**
 * Created by Lenovo-SXX on 2016/10/30.
 */
public class HomeQuboMoreGridViewAdapter extends BaseAdapter {

    String[] title = new String[]{"全部", "景点", "专访", "线路", "装备", "猎奇", "运动"};
    Context context;
    LayoutInflater inflater;

    public HomeQuboMoreGridViewAdapter(String[] title, Context context) {
        this.title = title;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return title[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_home_page_more_qubo_pop_item, parent, false);
            holder.qubo_pop_bnt = (MyButton) convertView.findViewById(R.id.qubo_pop_bnt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.qubo_pop_bnt.setText(title[position]);
        holder.qubo_pop_bnt.setFocusable(false);
        return convertView;
    }

    static class ViewHolder {
        MyButton qubo_pop_bnt;
    }
}
