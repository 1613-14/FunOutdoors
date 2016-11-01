package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phone.funoutdoors.R;

import java.util.List;

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
    private int pre = 0;

    public CityListAdapter(Context context, List<String> data, List<Integer> letterCharList, String[] title) {
        this.context = context;
        this.data = data;
        this.letterCharList = letterCharList;
        this.title = title;
        inflater = LayoutInflater.from(context);
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
        if (letterCharList.get(0) == 0 && position == 0) {
            holder.mainlistItemTv01.setText(title[0]);
            pre = 1;
            holder.mainlistItemTv02.setText("城市定位中...");
        } else {
            if (letterCharList.get(position) == -1) {
                holder.mainlistItemTv01.setVisibility(View.GONE);
                holder.mainlistItemTv02.setText(data.get(position));
            } else {
                holder.mainlistItemTv02.setTag(pre);
                Integer itemTv02 = (Integer) holder.mainlistItemTv02.getTag();
                if (itemTv02 == pre) {
                    holder.mainlistItemTv01.setText(title[itemTv02]);
                } else {
                    holder.mainlistItemTv01.setText(title[pre]);
                }
                holder.mainlistItemTv01.setVisibility(View.VISIBLE);
                holder.mainlistItemTv01.setText(title[pre]);
                holder.mainlistItemTv02.setText(data.get(position));
                if (pre > title.length) {
                    pre = 0;
                }
                pre++;

            }
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
