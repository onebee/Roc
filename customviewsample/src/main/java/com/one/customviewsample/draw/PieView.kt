package com.one.customviewsample.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.one.ktools.ex.dp
import kotlin.math.cos
import kotlin.math.sin


/**
 * @author  diaokaibin@gmail.com on 2020/9/21.
 */

private val RADIUS = 150.dp
private val COLORS = listOf(Color.parseColor("#C2185B"), Color.parseColor("#00ACC1"), Color.parseColor("#558B2F"), Color.parseColor("#5D4037"))
private val ANGLES = floatArrayOf(60f, 90f, 150f, 60f)
private val OFFSET_LENGTH = 40.dp

class PieView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var startAngle = 0f
        for ((index, angle) in ANGLES.withIndex()) {
            paint.color = COLORS[index]
            if (index == 1) {
                canvas.save()
                canvas.translate(OFFSET_LENGTH * cos(Math.toRadians(startAngle + angle / 2f.toDouble())).toFloat(),
                        OFFSET_LENGTH * sin(Math.toRadians(startAngle + angle / 2f.toDouble())).toFloat())
            }

            canvas.drawArc(width / 2f - RADIUS,
                    height / 2f - RADIUS,
                    width / 2f + RADIUS,
                    height / 2f + RADIUS,
                    startAngle,
                    angle,
                    true,
                    paint)
            startAngle += angle

            if (index == 1) {
                canvas.restore()
            }


        }

    }
}