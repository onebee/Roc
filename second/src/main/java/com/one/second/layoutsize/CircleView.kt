package com.one.second.layoutsize

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.toColorInt
import com.one.ktools.ex.dp

/**
 * @author  diaokaibin@gmail.com on 11/13/20.
 */

val RADIUS = 100.dp
val PADDING = 100.dp

class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    val paint = Paint().apply {
        color = "#987654".toColorInt()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, paint)
    }
}