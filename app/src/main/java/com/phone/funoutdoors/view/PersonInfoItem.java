package com.phone.funoutdoors.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;

/**
 * Created by Administrator on 2016/10/29.
 */
public class PersonInfoItem extends RelativeLayout {
    private String title;
    private FrameLayout frView;

    public PersonInfoItem(Context context) {
        super(context);
        initView(context);
    }


    public PersonInfoItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        title = attrs.getAttributeValue(Constant.NAMESPACE, "leftTitle");
        initView(context);
    }

    public PersonInfoItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.person_info_item, this);
        TextView leftTitle = (TextView) view.findViewById(R.id.left_tilte);
        leftTitle.setText(title);
        frView = (FrameLayout) view.findViewById(R.id.fr_view);

    }

    public void addView(View v) {
        if (v instanceof ImageView){
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(80,80);
            layoutParams.gravity = Gravity.CENTER;
            v.setLayoutParams(layoutParams);
        }
        frView.addView(v);
    }
}
