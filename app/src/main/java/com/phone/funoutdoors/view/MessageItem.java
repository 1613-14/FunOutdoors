package com.phone.funoutdoors.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;

/**
 * Created by Administrator on 2016/10/28.
 */
public class MessageItem extends LinearLayout {

    private int icon;
    private String text;
    private TextView messageDatail;

    public MessageItem(Context context) {
        super(context);
        initView(context);
    }

    public String getText() {
        return text;
    }

    public MessageItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        icon = attrs.getAttributeResourceValue(Constant.NAMESPACE, "messageItemIcon", R.mipmap.system_message);
        text = attrs.getAttributeValue(Constant.NAMESPACE, "messageItemText");
        initView(context);
    }

    public MessageItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.line_text_item, this);
        ImageView leftIcon = (ImageView) view.findViewById(R.id.left_icon);
        TextView textContent = (TextView) view.findViewById(R.id.text_content);
        messageDatail = (TextView) view.findViewById(R.id.message_detail);
        leftIcon.setImageResource(icon);
        textContent.setText(text);
    }

    public void messageDetail(String message) {
        messageDatail.setVisibility(VISIBLE);
        messageDatail.setText(message);
    }
}
