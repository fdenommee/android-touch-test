package com.touchtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class RecyclerViewSpy
        extends RecyclerView {

    public RecyclerViewSpy(Context context) {
        super(context);
    }

    public RecyclerViewSpy(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewSpy(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        boolean result = super.onInterceptTouchEvent(e);
        Log.d("RecyclerViewSpy", String.format("onInterceptTouchEvent(%s, %s)", result, e));
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean result = super.onTouchEvent(e);
        Log.d("RecyclerViewSpy", String.format("onTouchEvent(%s, %s)", result, e));
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Log.d("RecyclerViewSpy", String.format("dispatchTouchEvent(%s, %s)", result, ev));
        return result;
    }
}
