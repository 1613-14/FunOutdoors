package com.phone.funoutdoors.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.phone.funoutdoors.R;

/**
 * Created by Lenovo-SXX on 2016/10/31.
 */
public class MySwipeRefreshView extends SwipeRefreshLayout implements AbsListView.OnScrollListener {

    private View footerview;
    private boolean isStop = false;
    private ListView listView;
    private OnUpRefreshListener onUpRefreshListener;
    private boolean isLoading, isLast;
    private int count;

    public interface OnUpRefreshListener {
        void onUpRefreshListener();
    }

    public void setOnUpRefreshListener(OnUpRefreshListener onUpRefreshListener) {
        this.onUpRefreshListener = onUpRefreshListener;
    }

    public MySwipeRefreshView(Context context) {
        super(context);
        footerview = View.inflate(context, R.layout.activity_loding_layout, null);
    }

    public MySwipeRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        footerview = View.inflate(context, R.layout.activity_loding_layout, null);
        ImageView pullImage = (ImageView) footerview.findViewById(R.id.pullImage);

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view instanceof ListView) {
                listView = (ListView) view;
                listView.setOnScrollListener(this);
               /* listView.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                isStop = false;
                                break;
                            case MotionEvent.ACTION_UP:
                                isStop = true;
                                break;
                            case MotionEvent.ACTION_MOVE:
                                isStop = false;
                                break;
                        }
                        return false;
                    }
                });*/
            }
        }
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (count <= 11) {
            isStop = SCROLL_STATE_IDLE == scrollState;
            if (isLast && isStop) {
                setOnLoading(false);
                onUpRefreshListener.onUpRefreshListener();
            }
            if (isLast && !isLoading && !isStop) {
                setOnLoading(true);
            }
        } else {
            setOnLoading(false);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        isLast = firstVisibleItem + visibleItemCount == totalItemCount;
        count = totalItemCount;


    }


    public void setOnLoading(boolean loading) {
        isLoading = loading;
        if (!loading) {
            listView.addFooterView(footerview);
            isLoading = true;
        } else {
            listView.removeFooterView(footerview);
            isLoading = false;
        }
    }
}
