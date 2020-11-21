package com.one.second.viewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children

/**
 * @author  diaokaibin@gmail.com on 11/21/20.
 */
class TwoPager(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        var left=0
        var top=0
        var right=0
        var bottom=0

        for (index in children){

        }


    }

}