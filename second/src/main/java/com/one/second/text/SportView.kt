package com.one.second.text

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.one.kcore.extension.dp
import com.one.second.R

/**
 * @author  diaokaibin@gmail.com on 2020/11/8.
 */
private val CIRCLE_COLOR = Color.parseColor("#90A4AE")
private val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")
private val RING_WIDTH = 20.dp
private val RADIUS = 150.dp

class SportView(context: Context, attrs: AttributeSet?) : View(context, attrs) {


    var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        textAlign = Paint.Align.CENTER
        textSize = 100.dp
        typeface = ResourcesCompat.getFont(context, R.font.font)
    }
    var fontMetrics = Paint.FontMetrics()
    var bounds = Rect()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        canvas.drawLine(0f, height / 2f, width.toFloat(), height / 2f, paint)

        paint.color = CIRCLE_COLOR
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = RING_WIDTH
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)

        paint.color = HIGHLIGHT_COLOR
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS, height / 2f + RADIUS, -90f, 225f, false, paint)

        //绘制文字1
//        paint.textSize = 100.dp
//        paint.getFontMetrics(fontMetrics)
//        paint.style = Paint.Style.FILL
//        val ascent = fontMetrics.ascent
//        val descent = fontMetrics.descent
//        Log.i("----", "ascent = $ascent , descent = $descent")
//        canvas.drawText("a b A", width / 2f, height / 2f - (descent + ascent) / 2f, paint)

        // 绘制文字2
        paint.textSize = 150.dp
        paint.textAlign = Paint.Align.LEFT
        paint.getFontMetrics(fontMetrics)
        paint.getTextBounds("abab", 0, "abab".length, bounds)
        canvas.drawText("abab", -bounds.left.toFloat(), -bounds.top.toFloat(), paint)

        paint.textSize = 5.dp
        paint.getTextBounds("abab", 0, "abab".length, bounds)
        canvas.drawText("abab", - bounds.left.toFloat(), - bounds.top.toFloat(), paint)
    }
}