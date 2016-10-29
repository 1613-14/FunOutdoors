package com.phone.funoutdoors.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;

/**
 * 自定义组合控件
 * 文本加线条
 */
public class SplitLineView extends LinearLayout {


    private String textContent;
    private TextView content;

    public SplitLineView(Context context) {
        super(context);
        initView(context);
    }

    public SplitLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        textContent = attrs.getAttributeValue(Constant.NAMESPACE, "textContent");
        initView(context);
    }

    public SplitLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.split_line, this);
        content = (TextView) view.findViewById(R.id.content);
        content.setText(textContent);
    }
}
