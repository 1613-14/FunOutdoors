package com.phone.funoutdoors.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.phone.funoutdoors.R;

/**
 * Created by Administrator on 2016/10/26.
 */
public class PopupWindowDialog {
    private static PopupWindowDialog popubWindowDialog = null;
    private PopupWindow popupWindow;

    private PopupWindowDialog() {

    }

    public static PopupWindowDialog getInstance() {
        if (popubWindowDialog == null) {
            popubWindowDialog = new PopupWindowDialog();
        }
        return popubWindowDialog;
    }

    /**
     * 显示不同布局的窗体
     *
     * @param info
     * @param tag（判断条件）
     */
    public void showPopupWindowDialog(String info, int tag, Context context) {
        switch (tag) {
            case Constant.TEXTVIEW:
                //检查信息不合法弹出框
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_check_layout, null, false);
                TextView erroInfo = (TextView) view.findViewById(R.id.errorInfo);
                erroInfo.setText(info);
                showDialog(view, context);
                break;
            case Constant.IMAGE_TEXTVIEW_BOUTTON:
                //注册成功显示弹出框
                View registeSuccessView = LayoutInflater.from(context).inflate(R.layout.dialog_register_layout, null, false);
                showDialog(registeSuccessView, context);
                break;
            case Constant.TEXTVIEW_LOAD_ANIM:
                //注册登录加载时显示弹出框
                View loginSuccessView = LayoutInflater.from(context).inflate(R.layout.dialog_register_layout, null, false);
                ImageView loadImag = (ImageView) loginSuccessView.findViewById(R.id.loadImag);
                //加载动画
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.dialog_rotation);
                animation.setDuration(2000);
                animation.setRepeatCount(-1);
                //启动动画
                loadImag.startAnimation(animation);
                TextView loadState = (TextView) loginSuccessView.findViewById(R.id.loadState);
                loadState.setText(info);
                showDialog(loginSuccessView, context);
                break;
            case Constant.TEXTVIEW_AND_BUTTON:

                break;


        }

    }

    /**
     * 弹出选择添加图片方式
     */
    private void showSelectPic(Context context,AppCompatActivity activity) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_selector_picture, null);
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_register, null);

        popupWindow = new PopupWindow(context);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, -50);
    }


    /**
     * 弹出窗体显示，点击外边消失的
     *
     * @param view
     */
    public void showDialog(View view, Context context) {
        popupWindow = new PopupWindow(context);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(LayoutInflater.from(context).inflate(R.layout.activity_register, null), Gravity.CENTER, 0, 0);
    }

    public void hiddenDialog() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha, AppCompatActivity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(lp);
    }
}
