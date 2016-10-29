package com.phone.funoutdoors.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;

/**
 * Created by Administrator on 2016/10/26.
 */
public class LoginedBannerItem extends RelativeLayout {
    private TextView bannerInfo;
    private String bannerTitle;

    public LoginedBannerItem(Context context) {
        super(context);
        initView(context);
    }

    public LoginedBannerItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        bannerTitle = attrs.getAttributeValue(Constant.NAMESPACE, "bannerTitle");
        initView(context);
    }

    public LoginedBannerItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.logined_banner_item, this);
        bannerInfo = (TextView) view.findViewById(R.id.logined_info);
        TextView title = (TextView) view.findViewById(R.id.logined_title);
        title.setText(bannerTitle);
    }

    public void setBannerInfo(String info) {
        bannerInfo.setText(info);
    }


}
