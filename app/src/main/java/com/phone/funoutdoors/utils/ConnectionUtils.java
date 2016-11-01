package com.phone.funoutdoors.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.phone.funoutdoors.R;

/**
 * Created by Lenovo-SXX on 2016/11/1.
 */
public class ConnectionUtils {

    /**
     * 网络连接失败的弹出框
     *
     * @param v
     */
    public static void showConnectionFailure(Context context, Handler handler, View v) {
        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundResource(R.drawable.dialog_home_bg);
        TextView tv = new TextView(context);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv.setText("连接失败!!!");
        tv.setGravity(Gravity.CENTER);
        layout.addView(tv);
        final PopupWindow popupWindow = new PopupWindow(layout, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.MyPopWindowAnimation);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        }, 5000);
    }

}
