package com.touchtest;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;

public class ButtonSpy
        extends Button {

    private GestureDetector gestureDetector;

    private boolean clicked = false;

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

//        if (gestureDetector.onTouchEvent(e)) {
//            result = true;
//            Log.d("ButtonSpy", String.format("onTouchEvent'(%s, %s, %s)", getTag(), result, e));
//        }

//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downY = e.getY();
//                scrolling = false;
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                float distanceY = Math.abs(e.getY() - downY);
//                Log.d("ButtonSpy", String.format("onTouchEvent-distanceY=%s (%s)", distanceY, scaleTouchSlop));
//                scrolling = distanceY > scaleTouchSlop;
//                result = !scrolling;
//                break;
//
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                scrolling = false;
//                downY = -1;
//                break;
//        }
//
//        Log.d("ButtonSpy", String.format("onTouchEvent'(%s, %s, %s)", getTag(), result, e));
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        boolean result = false; //super.dispatchTouchEvent(ev);

        if (clicked) {
//            result = super.dispatchTouchEvent(ev);
        }

        Log.d("ButtonSpy", String.format("dispatchTouchEvent(%s, %s, %s)", getTag(), result, ev));

        if (gestureDetector.onTouchEvent(ev)) {
            result = true;
            Log.d("ButtonSpy", String.format("dispatchTouchEvent'(%s, %s, %s)", getTag(), result, ev));
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                clicked = false;
                break;
        }

        Log.d("ButtonSpy", String.format("dispatchTouchEvent'(%s, %s, %s)", getTag(), result, ev));
        return result;
    }

    private void init() {

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                Log.d("ButtonSpy-gesture", String.format("onDown(%s, %s)", getTag(), e));
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.d("ButtonSpy-gesture", String.format("onScroll(%s, %s, %s, %s, %s)",
                                                         getTag(), e1, e2, distanceX, distanceY));
                getList().scrollBy(0, (int)distanceY);
                return false;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d("ButtonSpy-gesture", String.format("onFling(%s, %s, %s, %s, %s)",
                                                         getTag(), e1, e2, velocityX, velocityY));
                getList().fling(0, (int)-velocityY);
                return false;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.d("ButtonSpy-gesture", String.format("onSingleTapConfirmed(%s, %s)", getTag(), e));
                clicked = true;
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setPressed(true);
                        performClick();

                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setPressed(false);
                                clicked = false;
                            }
                        }, 150);
                    }
                }, 50);
                return true;
            }
        });
    }

    private RecyclerView getList() {
        return (RecyclerView)((Activity)getContext()).findViewById(android.R.id.list);
    }
}
