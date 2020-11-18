package com.one.second.layoutsize

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import java.lang.Double.min
import kotlin.math.min

/**
 * @author  diaokaibin@gmail.com on 11/13/20.
 */
class SquareImageView(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        measuredHeight
        measuredWidth
        width
        height

        var size = min(measuredHeight,measuredWidth)
        setMeasuredDimension(size,size)


    }

    override fun onDragEvent(event: DragEvent): Boolean {
        var TAG = "-----"
        Log.d(TAG, "SquareImageView onDragEvent: " + event.getAction())

        return super.onDragEvent(event)

    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {

//        var width = r - l
//        var height = b - t
//
//        var size = min(width, height)
        super.layout(l, t, r, b)

    }
}