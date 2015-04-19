package com.touchtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class FrameLayoutSpy
        extends FrameLayout {

    public FrameLayoutSpy(Context context) {
        super(context);
    }

    public FrameLayoutSpy(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FrameLayoutSpy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        boolean result = super.onInterceptTouchEvent(e);
        Log.d("FrameLayoutSpy", String.format("onInterceptTouchEvent(%s, %s, %s)", getTag(), result, e));
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean result = super.onTouchEvent(e);
        Log.d("FrameLayoutSpy", String.format("onTouchEvent(%s, %s, %s)", getTag(), result, e));
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Log.d("FrameLayoutSpy", String.format("dispatchTouchEvent(%s, %s, %s)", getTag(), result, ev));
        return result;
    }
}
