package com.one.screenadapter;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

/**
 * @author diaokaibin@gmail.com on 2020/9/11.
 */
public class Density {


    private static final float WIDTH = 360; //参考设备的宽,单位是dp


    private static float appDensity;  // 屏幕密度
    private static float appScaleDensity; // 字体缩放比例


    public static void setDensity(final Application app, Activity activity) {

        // 获取当前app 的屏幕显示信息
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        if (appDensity == 0) {
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;

            // 添加字体变化监听回调
            app.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration newConfig) {
                    // 字体发生更改, 重新对 scaleDensity 进行赋值
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaleDensity = app.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        // 计算目标值  density , scaleDensity , densityDpi
        float targetDensity = displayMetrics.widthPixels / WIDTH; // 1080/360=3.0

        // 目标字体的缩放比例
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);

        // 屏幕像素密度
        int targetDensityDpi = (int) (targetDensity * 160);


        // 替换Activity 的 density ,scaleDensity , densityDpi
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;

    }

}
