package com.one.screenadapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author diaokaibin@gmail.com on 2020/9/11.
 */
public class ScreenAdapterLayout extends RelativeLayout {

    private boolean flag;

    public ScreenAdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!flag) {
            float scaleX = UIUtils.getInstance(getContext()).getHorizontalScale();
            float scaleY = UIUtils.getInstance(getContext()).getVerticalScale();
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                params.width = (int) (params.width * scaleX);
                params.height = (int) (params.height * scaleY);

                params.topMargin = (int) (params.topMargin * scaleY);
                params.bottomMargin = (int) (params.bottomMargin * scaleY);
                params.leftMargin = (int) (params.leftMargin * scaleX);
                params.rightMargin = (int) (params.rightMargin * scaleX);

            }
            flag = true;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
