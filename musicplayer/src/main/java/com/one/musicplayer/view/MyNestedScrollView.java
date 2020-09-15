package com.one.musicplayer.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

/**
 * @author diaokaibin@gmail.com on 2020/9/15.
 */
public class MyNestedScrollView extends NestedScrollView {
    public void setScrollInterface(ScrollInterface scrollInterface) {
        mScrollInterface = scrollInterface;
    }

    ScrollInterface mScrollInterface;


    public MyNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public MyNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollInterface != null) {
            mScrollInterface.onScrollChange(l, t, oldl, oldt);
        }
    }

    public interface ScrollInterface {
        void onScrollChange(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }

}
