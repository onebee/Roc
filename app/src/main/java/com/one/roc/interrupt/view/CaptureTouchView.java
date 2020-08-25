package com.one.roc.interrupt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static com.one.roc.interrupt.PrintUtils.printEvent;
import static com.one.roc.interrupt.PrintUtils.printParam;


public class CaptureTouchView extends View {
    private static final String TAG = CaptureTouchView.class.getSimpleName();

    public CaptureTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        printEvent(TAG, "dispatchTouchEvent", event);
        boolean result = super.dispatchTouchEvent(event);
        printParam(TAG, "dispatchTouchEvent result is " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        printEvent(TAG,"onTouchEvent", event);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(500, 300);
    }
}
