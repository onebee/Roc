package com.one.roc.interrupt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import static com.one.roc.interrupt.PrintUtils.printEvent;


/**
 * Created by Danny 姜.
 */
public class DownInterceptGroup extends FrameLayout {

    private static final String TAG = DownInterceptGroup.class.getSimpleName();

    public DownInterceptGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        printEvent(TAG, "dispatchTouchEvent", event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        printEvent(TAG, "onInterceptTouchEvent", ev);
        return super.onInterceptTouchEvent(ev);
    }

}
