package com.one.second.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.one.ktools.ex.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author  diaokaibin@gmail.com on 2020/11/7.
 */

private val RADIUS = 150.dp
private val COLORS = listOf(Color.parseColor("#C2185B"), Color.parseColor("#00ACC1"), Color.parseColor("#558B2F"), Color.parseColor("#5D4037"))
private val ANGLES = floatArrayOf(60f, 90f, 150f, 60f)
private val OFFSET_LENGTH = 10.dp

class PieView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var INDEX = 1

    init {

        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var startAngle = 0f
        for ((index, angle) in ANGLES.withIndex()) {
            paint.color = COLORS[index]

            Log.i("-----","index = " + INDEX)
            if (index == INDEX) {
                canvas.save()
                canvas.translate((OFFSET_LENGTH * cos(Math.toRadians((startAngle + angle / 2f).toDouble()))).toFloat(),

                        (OFFSET_LENGTH * sin(Math.toRadians((startAngle + angle / 2f).toDouble()))).toFloat()
                )
            }

            canvas.drawArc(width / 2 - RADIUS, height / 2 - RADIUS, width / 2 + RADIUS, height / 2 + RADIUS, startAngle, angle, true, paint)
            startAngle += angle
            if (index == INDEX) {
                canvas.restore()
            }
        }
    }

    fun setIndex(index: Int) {
        INDEX = index
        invalidate()
    }
}