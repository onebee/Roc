package com.one.kcore.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup

/**
 * @author  diaokaibin@gmail.com
 */
class TabLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }

    /**
     * 如果不是滑动控件,需要重写,如果是滑动控件 不需要重写
     */
    override fun shouldDelayChildPressedState(): Boolean {
        return super.shouldDelayChildPressedState()

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }
}