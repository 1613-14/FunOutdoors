package com.phone.funoutdoors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.GuestData;
import com.phone.funoutdoors.interfaces.OnUserIconClickListener;
import com.phone.funoutdoors.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/10/31.
 */
public class GuestDarenListAdapter extends BaseAdapter {

    private List<GuestData.DarenBean> list;
    private Context context;
    private LayoutInflater inflater;
    private OnUserIconClickListener onUserIconClickListener;
    private Animation animation;

    public GuestDarenListAdapter(List<GuestData.DarenBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        animation = AnimationUtils.loadAnimation(context, R.anim.circle_scale_anim);
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
            convertView = inflater.inflate(R.layout.guest_page_daren_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convertView.setAnimation(animation);
        GuestData.DarenBean darenBean = list.get(position);
        holder.darenNickname.setText(darenBean.getNickname());
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        Glide.with(context).load(Constant.PICPATH+darenBean.getAvatar()).override(widthPixels / 2, widthPixels / 2).centerCrop().into(holder.darenAvatar);
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.daren_avatar)
        CircleImageView darenAvatar;
        @BindView(R.id.daren_nickname)
        TextView darenNickname;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
