package com.one.roc.interrupt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import static com.one.roc.interrupt.PrintUtils.printEvent;
import static com.one.roc.interrupt.PrintUtils.printParam;


/**
 * Created by Danny 姜.
 */
public class MyScrollView extends ScrollView {

    private static final String TAG = MyScrollView.class.getSimpleName();

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        printEvent(TAG,"onTouchEvent", event);
        boolean intercepted = super.onInterceptTouchEvent(event);
        printParam(TAG, "MyScrollView intercepted is " + intercepted);
        return intercepted;
    }
}
