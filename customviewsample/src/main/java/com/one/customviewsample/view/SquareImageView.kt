package com.one.customviewsample.view

import android.content.Context
import android.util.AttributeSet
import kotlin.math.min


/**
 * @author  diaokaibin@gmail.com on 2020/9/21.
 */
class SquareImageView(context: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)

    }
}