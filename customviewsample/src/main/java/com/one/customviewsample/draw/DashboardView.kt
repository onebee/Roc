package com.one.customviewsample.draw

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.INFINITE
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import com.one.ktools.ex.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author  diaokaibin@gmail.com on 2020/9/25.
 */


private const val OPEN_ANGLE = 120F
private const val MARK = 5
private val RADIUS = 150.dp
private val LENGTH = 125.dp
private val DASH_WIDTH = 2.dp
private val DASH_LENGTH = 10.dp

class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val dash = Path()
    private lateinit var pathEffect: PathEffect

    private var fracation = 0
        set(value) {
            field = value
            invalidate()
        }


    private val animator = ObjectAnimator.ofInt(this, "fracation", 0, 11).setDuration(11 * 1000)


    init {
        paint.strokeWidth = 3.dp
        paint.style = Paint.Style.STROKE
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)
        animator.interpolator = FastOutLinearInInterpolator()
        animator.repeatCount= INFINITE
        animator.start()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.addArc(width / 2 - RADIUS,
                height / 2 - RADIUS,
                width / 2 + RADIUS,
                height / 2 + RADIUS,
                90 + OPEN_ANGLE / 2f,
                360 - OPEN_ANGLE
        )

        val pathMeasure = PathMeasure(path, false)
        pathEffect = PathDashPathEffect(dash, (pathMeasure.length - DASH_WIDTH) / 10f, 0f, PathDashPathEffect.Style.ROTATE)


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path, paint)

        // 画刻度
        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        canvas.drawLine(width / 2f, height / 2f,
                (width / 2f + LENGTH * cos(markToRadians(fracation))).toFloat(),
                (height / 2f + LENGTH * sin(markToRadians(fracation))).toFloat(), paint)


    }

    private fun markToRadians(mark: Int) = Math.toRadians((90 + OPEN_ANGLE / 2f + (360 - OPEN_ANGLE) / 10f * mark).toDouble())
}
