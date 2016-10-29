package com.phone.funoutdoors.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;

/**
 * Created by Administrator on 2016/10/27.
 */
public class IDCartView extends FrameLayout {
    private ImageView idCartImage;
    private int bgImage;


    public IDCartView(Context context) {
        super(context);
        initView(context);
    }


    public IDCartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bgImage = attrs.getAttributeResourceValue(Constant.NAMESPACE, "bgImage", R.mipmap.default_img);
        initView(context);
    }

    public IDCartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.id_cart_layout, this);
        idCartImage = (ImageView) view.findViewById(R.id.id_card_image);
        idCartImage.setImageResource(bgImage);
    }

    public void setBgImage(String path, Context context) {
        Glide.with(context).load(path).into(idCartImage);
    }
}
