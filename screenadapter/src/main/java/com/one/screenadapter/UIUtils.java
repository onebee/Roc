package com.one.screenadapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * @author diaokaibin@gmail.com on 2020/9/11.
 */
public class UIUtils {


    private String TAG = this.getClass().getSimpleName();

    private static UIUtils UIUtils;

    // 设计稿参考宽高
    private static final float STANDARD_WIDTH = 1080;
    private static final float STANDARD_HEIGHT = 1920;


    //屏幕显示宽高
    private int mDisplayWidth;
    private int mDisplayHeight;


    private UIUtils(Context context) {
        // 获取屏幕的宽和高
        if (mDisplayHeight == 0 || mDisplayWidth == 0) {
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (manager != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                manager.getDefaultDisplay().getMetrics(displayMetrics);
                if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                    // 横屏
                    mDisplayWidth = displayMetrics.heightPixels;
                    mDisplayHeight = displayMetrics.widthPixels;

                } else {
                    mDisplayWidth = displayMetrics.widthPixels;
                    mDisplayHeight = displayMetrics.heightPixels - getStatusBarHeight(context);
                }

                Log.i(TAG, " displayMetrics.widthPixels =  " + displayMetrics.widthPixels);
                Log.i(TAG, "  displayMetrics.heightPixels =  " + displayMetrics.heightPixels);

            }

        }
    }

    public static UIUtils getInstance(Context context) {
        if (UIUtils == null) {
            UIUtils = new UIUtils(context.getApplicationContext());
        }
        return UIUtils;
    }


    public int getStatusBarHeight(Context context) {
        int reID = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (reID > 0) {
            return context.getResources().getDimensionPixelSize(reID);
        }
        return 0;
    }


    /**
     * 获取水平方向的缩放比例
     *
     * @return
     */
    public float getHorizontalScale() {
        return mDisplayWidth / STANDARD_WIDTH;
    }

    public float getVerticalScale() {
        return mDisplayHeight / STANDARD_HEIGHT;
    }

}
