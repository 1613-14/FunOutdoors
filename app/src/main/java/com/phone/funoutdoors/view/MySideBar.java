package com.phone.funoutdoors.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.phone.funoutdoors.interfaces.OnTouchingLetterChangeListener;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MySideBar extends View {
    OnTouchingLetterChangeListener onTouchingLetterChangeListener;
    // 按住改变背景色
    private boolean showBkg;
    public static String[] b = { "#","定位","热门", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };
    /**被选中位置*/
    int choose = -1;
    private Paint paint = new Paint();
    public MySideBar(Context context) {
        super(context);
    }

    public MySideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(showBkg){
            canvas.drawColor(Color.parseColor("#ccddFF"));
        }
        float height = getHeight();
        float width = getWidth();
        //     计算单个字母高度
        float singleHeight = height / (float)(b.length);
        for(int i = 0; i < b.length; i++ ){
            paint.setColor(Color.GRAY);
            paint.setTextSize(25);
            if (i == choose) {
                //             选中的颜色
                paint.setColor(Color.parseColor("#3399ff"));
                //             加粗
                paint.setFakeBoldText(true);
            }
            //         设置文本坐标
            float xPos = width / 2 - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();
        }
    }
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        final int oldChoose = choose;
        final int c = (int) (y / getHeight() * b.length);
        final OnTouchingLetterChangeListener listener = onTouchingLetterChangeListener;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                showBkg = true;
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < b.length) {
                        listener.onTouchingLetterChanged(c);
                        choose = c;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < b.length) {
                        listener.onTouchingLetterChanged(c);
                        choose = c;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showBkg = false;
                choose = -1;
                invalidate();
                break;
        }
        return true;
    }
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangeListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangeListener = onTouchingLetterChangedListener;
    }
}
