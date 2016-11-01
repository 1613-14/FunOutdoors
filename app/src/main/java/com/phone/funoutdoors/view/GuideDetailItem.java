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
public class GuideDetailItem extends RelativeLayout {
    private TextView datainfo;
    private String dataTitle;

    public GuideDetailItem(Context context) {
        super(context);
        initView(context);
    }

    public GuideDetailItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        dataTitle = attrs.getAttributeValue(Constant.NAMESPACE, "dataTitle");
        initView(context);
    }

    public GuideDetailItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.guide_detail_item, this);
        datainfo = (TextView) view.findViewById(R.id.data_info);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(dataTitle);
    }

    public void setBannerInfo(String info) {
        datainfo.setText(info);
    }


}
