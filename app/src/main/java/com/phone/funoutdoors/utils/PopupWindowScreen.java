package com.phone.funoutdoors.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.phone.funoutdoors.R;

/**
 * Created by Administrator on 2016/10/29.
 */
public class PopupWindowScreen {
    private static PopupWindowScreen instance;
    private PopupWindow popupWindow;
    //private Context context;

    public PopupWindowScreen() {
        //this.context = context.getApplicationContext();
    }

    public static PopupWindowScreen getInstance() {
        if (instance == null) {
            instance = new PopupWindowScreen();
        }
        return instance;
    }

    /**
     * 弹出窗体显示，点击外边消失的
     */
    public View showDialog(Context context, final Activity activity) {
        popupWindow = new PopupWindow(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_selector_picture, null);

        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0X00000000));
        backgroundAlpha(0.4f, activity);
        popupWindow.setAnimationStyle(R.anim.activity_open);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f, activity);
            }
        });
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 30);
        return view;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha, Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(lp);
    }

    public void hiddenDialog() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
