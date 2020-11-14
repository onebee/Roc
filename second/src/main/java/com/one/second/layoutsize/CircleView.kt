package com.one.second.layoutsize

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.one.ktools.ex.dp

/**
 * @author  diaokaibin@gmail.com on 11/13/20.
 */

val RADIUS = 100.dp
val PADDING = 100.dp

class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val paint = Paint().apply {
        color = Color.YELLOW
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var size = ((RADIUS + PADDING) * 2).toInt()

        var width = resolveSize(size, widthMeasureSpec)
        var height = resolveSize(size, heightMeasureSpec)

        setMeasuredDimension(width, height)


    }
}