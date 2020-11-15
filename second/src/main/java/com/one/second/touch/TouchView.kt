package com.one.second.touch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @author  diaokaibin@gmail.com on 11/15/20.
 */
class TouchView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action==MotionEvent.ACTION_UP) {
            performClick()
        }
        return true
    }
}