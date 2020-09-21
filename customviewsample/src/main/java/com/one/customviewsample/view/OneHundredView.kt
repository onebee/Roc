package com.one.customviewsample.view

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @author  diaokaibin@gmail.com on 2020/9/21.
 */
class OneHundredView(context: Context, attrs: AttributeSet) : View(context, attrs) {

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
////        setMeasuredDimension(100,100)
//    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r+100, b+100)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }
}