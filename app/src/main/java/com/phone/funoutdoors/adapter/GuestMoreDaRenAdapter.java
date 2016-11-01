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
import com.phone.funoutdoors.bean.MoreDaRen;
import com.phone.funoutdoors.interfaces.OnUserIconClickListener;
import com.phone.funoutdoors.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Lenovo-SXX on 2016/10/31.
 */
public class GuestMoreDaRenAdapter extends BaseAdapter {

    List<MoreDaRen.ResultListBean> list;
    Context context;
    LayoutInflater inflater;

    OnUserIconClickListener onUserIconClickListener;

    public void setOnUserIconClickListener(OnUserIconClickListener onUserIconClickListener) {
        this.onUserIconClickListener = onUserIconClickListener;
    }

    public GuestMoreDaRenAdapter(List<MoreDaRen.ResultListBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_guest_more_daren_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(Constant.PICPATH + list.get(position).getAvatar()).into(holder.avatar);
        holder.nickname.setText(list.get(position).getNickname());
        holder.intro.setText(list.get(position).getIntro());
        Glide.with(context).load(Constant.PICPATH + list.get(position).getGuide().getAct_cover()).into(holder.act_cover);
        holder.act_title.setText(list.get(position).getGuide().getAct_title());
        holder.act_create_time.setText(list.get(position).getGuide().getAct_create_time());
        holder.discuss_count.setText(String.valueOf(list.get(position).getGuide().getDiscuss_count()));
        holder.praise_count.setText(String.valueOf(list.get(position).getGuide().getPraise_count()));
        holder.view_count.setText(String.valueOf(list.get(position).getGuide().getView_count()));
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserIconClickListener.onUserIconClickListener(list.get(position).getUser_id());
            }
        });
        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.avatar)
        CircleImageView avatar;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.intro)
        TextView intro;
        @BindView(R.id.act_cover)
        ImageView act_cover;
        @BindView(R.id.act_title)
        TextView act_title;
        @BindView(R.id.act_create_time)
        TextView act_create_time;
        @BindView(R.id.discuss_count)
        TextView discuss_count;
        @BindView(R.id.praise_count)
        TextView praise_count;
        @BindView(R.id.view_count)
        TextView view_count;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
