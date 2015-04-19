package com.touchtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

public class ButtonSpy
        extends Button {

    private GestureDetector gestureDetector;

    public ButtonSpy(Context context) {
        super(context);
        init();
    }

    public ButtonSpy(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonSpy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setClickable(boolean clickable) {
        Log.d("ButtonSpy", String.format("setClickable(%s)", clickable));
        super.setClickable(clickable);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean result = super.onTouchEvent(e);
        Log.d("ButtonSpy", String.format("onTouchEvent(%s, %s, %s)", getTag(), result, e));
        if (gestureDetector.onTouchEvent(e)) {
            result = true;
            Log.d("ButtonSpy", String.format("onTouchEvent'(%s, %s, %s)", getTag(), result, e));
        }
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Log.d("ButtonSpy", String.format("dispatchTouchEvent(%s, %s, %s)", getTag(), result, ev));
        return true; //result;
    }

    private void init() {
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                Log.d("ButtonSpy-gesture", String.format("onDown(%s, %s)", getTag(), e));
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.d("ButtonSpy-gesture", String.format("onScroll(%s, %s, %s, %s, %s)",
                                                         getTag(), e1, e2, distanceX, distanceY));
                return true; //super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d("ButtonSpy-gesture", String.format("onFling(%s, %s, %s, %s, %s)",
                                                         getTag(), e1, e2, velocityX, velocityY));
                return true; //super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.d("ButtonSpy-gesture", String.format("onSingleTapConfirmed(%s, %s)", getTag(), e));
                Toast.makeText(getContext(), "Clicked!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
