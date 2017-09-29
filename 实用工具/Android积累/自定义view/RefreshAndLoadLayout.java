package com.qf.understandmusic.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.qf.understandmusic.R;

/**
 * Created by Administrator on 16-1-15.
 */
public class RefreshAndLoadLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {
    private View loadingview;//加载更多
    private ListView lv;
    private int tauchSlop;//滚动的标准最小值

    private int downY, lastY;
    private boolean isLoading, isUpPull, isToBootom;

    public RefreshAndLoadLayout(Context context) {
        this(context, null);

    }

    public RefreshAndLoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        tauchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        //获取滚动值
        loadingview = LayoutInflater.from(context).inflate(R.layout.footer_load, null);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                lastY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                isUpPull = (downY - lastY >= tauchSlop);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (lv == null) {
            lv = (ListView) getChildAt(1);
            lv.setOnScrollListener(this);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isToBootom && scrollState == SCROLL_STATE_IDLE && !isLoading && isUpPull) {
            //加载下页数据
            if (loadListener != null) {
                setLoading(true);
                loadListener.onLoad();
            }
        }
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
        if (isLoading) {
            lv.addFooterView(loadingview);//开始加载
        } else {
            lv.removeFooterView(loadingview);//加载完成
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            isToBootom = true;
        } else {
            isToBootom = false;
        }
    }

    private OnLoadListener loadListener;

    public void setOnLoadListener(OnLoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public interface OnLoadListener {
        void onLoad();
    }
}
