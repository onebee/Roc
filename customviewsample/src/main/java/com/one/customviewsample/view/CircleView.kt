package com.one.customviewsample.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.one.ktools.ex.dp

/**
 * @author  diaokaibin@gmail.com on 2020/9/21.
 */

private val PADDING = 100.dp
private val RADIUS = 100.dp

class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.color = Color.YELLOW
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val size = ((PADDING + RADIUS) * 2).toInt()
        val width = resolveSize(size, widthMeasureSpec)
        val height = resolveSize(size, heightMeasureSpec)
        setMeasuredDimension(width, height)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, paint)

    }

}