package com.one.screenadapter;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

import static android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

public class MainActivity extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 判断手机是不是刘海屏
        boolean hasDisplayCutout = hasDisplayCutout(window);
        if (hasDisplayCutout) {
            WindowManager.LayoutParams params = window.getAttributes();
//        #LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT       全屏模式下,内容区下移
//        #LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES  允许内容区延伸进刘海屏
//        #LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER         不允许内容延伸进刘海
            params.layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(params);
            // 设置成沉浸式
            int flags = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            int visibility = window.getDecorView().getSystemUiVisibility();
            visibility |= flags; // 追加沉浸式设置
            window.getDecorView().setSystemUiVisibility(visibility);
        }


        setContentView(R.layout.activity_main);


        System.out.println("MainActivity 加载 " + this.getClassLoader());
//        System.out.println(this.getClassLoader());
        System.out.println("Activity  加载 " + Activity.class.getClassLoader());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean hasDisplayCutout(Window window) {
        DisplayCutout displayCutout;
        View rootView = window.getDecorView();
        WindowInsets insets = rootView.getRootWindowInsets();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && insets != null) {
            displayCutout = insets.getDisplayCutout();
            if (displayCutout != null) {
                displayCutout.getBoundingRects();
                if (displayCutout.getBoundingRects().size() > 0 && displayCutout.getSafeInsetTop() > 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
