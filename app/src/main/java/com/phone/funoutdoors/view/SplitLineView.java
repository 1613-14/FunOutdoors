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
    private int lineColor;

    public SplitLineView(Context context) {
        super(context);
        initView(context);
    }

    public SplitLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        textContent = attrs.getAttributeValue(Constant.NAMESPACE, "textContent");
        lineColor = attrs.getAttributeIntValue(Constant.NAMESPACE, "lineColor",R.color.colorAccent);
        initView(context);
    }

    public SplitLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.split_line, this);
        content = (TextView) view.findViewById(R.id.content);
        TextView preLine = (TextView) view.findViewById(R.id.pre_line);
        TextView afterLine = (TextView) view.findViewById(R.id.after_line);
        preLine.setBackgroundColor(lineColor);
        afterLine.setBackgroundColor(lineColor);
        content.setTextColor(lineColor);
        content.setText(textContent);

    }
}
