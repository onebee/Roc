package com.one.screenadapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * @author diaokaibin@gmail.com on 2020/9/11.
 */
public class PercentLayout extends RelativeLayout {
    public PercentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams params = child.getLayoutParams();
            // 如果是百分比布局
            if (checkLayoutParams(params)) {
                LayoutParams lp = (LayoutParams) params;

                float widthPercent = lp.widthPercent;
                float heightPercent = lp.heightPercent;
                float marginTopPercent = lp.marginTopPercent;
                float marginLeftPercent = lp.marginLeftPercent;
                float marginRightPercent = lp.marginRightPercent;
                float marginBottomPercent = lp.marginBottomPercent;
                if (widthPercent > 0) {
                    params.width = (int) (widthSize * widthPercent);
                }


                if (heightPercent > 0) {
                    params.height = (int) (heightSize * heightPercent);
                }


                if (marginTopPercent > 0) {
                    ((LayoutParams) params).topMargin = (int) (heightSize * marginTopPercent);
                }
                if (marginBottomPercent > 0) {
                    ((LayoutParams) params).bottomMargin = (int) (heightSize * marginBottomPercent);
                }


                if (marginLeftPercent > 0) {
                    ((LayoutParams) params).leftMargin = (int) (widthSize * marginLeftPercent);
                }
                if (marginRightPercent > 0) {
                    ((LayoutParams) params).rightMargin = (int) (widthPercent * marginRightPercent);
                }

            }

        }

    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {

        private float widthPercent;
        private float heightPercent;
        private float marginTopPercent;
        private float marginLeftPercent;
        private float marginRightPercent;
        private float marginBottomPercent;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.PercentLayout);
            widthPercent = a.getFloat(R.styleable.PercentLayout_widthPercent, 0);
            heightPercent = a.getFloat(R.styleable.PercentLayout_heightPercent, 0);
            marginTopPercent = a.getFloat(R.styleable.PercentLayout_marginTopPercent, 0);
            marginLeftPercent = a.getFloat(R.styleable.PercentLayout_marginLeftPercent, 0);
            marginRightPercent = a.getFloat(R.styleable.PercentLayout_marginRightPercent, 0);
            marginBottomPercent = a.getFloat(R.styleable.PercentLayout_marginBottomPercent, 0);
            a.recycle();


        }
    }

}
