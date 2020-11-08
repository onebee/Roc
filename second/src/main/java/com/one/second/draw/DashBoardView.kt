package com.one.second.draw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.one.ktools.ex.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author  diaokaibin@gmail.com on 2020/11/7.
 */

private val RADIUS = 120.dp
private const val ANGLE = 120F
private const val MARK = 10
private val DASH_WIDTH = 5f.dp
private val DASH_HEIGHT = 15f.dp
private val LENGTH = 55.dp
private const val NUMBER = 20

class DashBoardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var path = Path()
    var dash = Path()
    lateinit var pathEffect: PathDashPathEffect

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3.dp
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CW)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)

        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        canvas.drawLine(width / 2f, height / 2f, width / 2f + (cos(markToRadius(MARK)) * LENGTH).toFloat(), height / 2f + (sin(markToRadius(MARK)) * LENGTH).toFloat(), paint)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.addArc(width / 2 - RADIUS, height / 2 - RADIUS, width / 2 + RADIUS, height / 2 + RADIUS, 90 + (ANGLE / 2), (360 - ANGLE))
        val pathMeasure = PathMeasure(path, false)
        pathEffect = PathDashPathEffect(dash, (pathMeasure.length - DASH_WIDTH) / NUMBER, 0f, PathDashPathEffect.Style.ROTATE)

    }

    private fun markToRadius(mark: Int) = Math.toRadians((90 + ANGLE / 2 + (360 - ANGLE) / NUMBER * mark).toDouble())
}