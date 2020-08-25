package com.one.kcore.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @author  diaokaibin@gmail.com on 2020/8/24.
 */
class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)

    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(event)

    }
}