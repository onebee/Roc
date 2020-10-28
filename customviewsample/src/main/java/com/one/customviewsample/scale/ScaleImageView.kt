package com.one.customviewsample.scale

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.one.customviewsample.R
import com.one.kcore.extension.dp
import com.one.kcore.extension.getAvatar
import kotlin.math.max
import kotlin.math.min

/**
 * @author  diaokaibin@gmail.com on 2020/10/25.
 */

private val IMAGE_SIZE = 100.dp.toInt()
private const val EXTRA_SCALE_FACTOR = 1.5F

class ScaleImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var bigScale = 0f
    private var smallScale = 0f
    private var originOffsetX = 0f
    private var originOffsetY = 0f

    private var offsetX = 0f
    private var offsetY = 0f


    private val bitmap = getAvatar(resources, IMAGE_SIZE, R.drawable.avater)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        style = Paint.Style.STROKE
        strokeWidth = 35f
    }


    /**
     * 相当于计算器 帮助我们计算坐标 帮我们计算滑动
     */
    private val scroller = OverScroller(context)

    private var big = false

    private val henOnGestureListener = HenOnGestureListener()

    private val gestureDetector = GestureDetectorCompat(context, henOnGestureListener).apply {

    }

    private val henRunnable = FillingRunnable()

    private var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val scaleAnimate = ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)

    private val scaleGestureDetectorListener = HenScaleGestureDetectorListener()
    private val scaleGestureDetector = ScaleGestureDetector(context, scaleGestureDetectorListener)


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originOffsetX = (width - IMAGE_SIZE) / 2f
        originOffsetY = (height - IMAGE_SIZE) / 2f

        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            bigScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
            smallScale = width / bitmap.width.toFloat()

        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * EXTRA_SCALE_FACTOR
        }

        currentScale = smallScale
        scaleAnimate.setFloatValues(smallScale, bigScale)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)

        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originOffsetX, originOffsetY, paint)

        canvas.drawRect(originOffsetX, originOffsetY,
                originOffsetX + bitmap.width.toFloat(),
                originOffsetY + bitmap.height.toFloat(),
                paint)


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }
        return true
    }


    private fun fixOffset() {
        offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
        offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)

        offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
        offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
    }


    private fun refresh() {
        // 相当于掐个表
        scroller.computeScrollOffset()
        offsetX = scroller.currX.toFloat()
        offsetY = scroller.currY.toFloat()
        invalidate()
    }


    inner class HenOnGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            if (big) {
                offsetX -= distanceX
                offsetY -= distanceY
                // 滑动的偏移修正,使其有边界 不能超出某个边界
                fixOffset()
                invalidate()
            }

            return false
        }


        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            if (big) {
                scroller.fling(
                        offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                        -((bitmap.width * bigScale - width) / 2).toInt(), ((bitmap.width * bigScale - width) / 2).toInt(),
                        -((bitmap.height * bigScale - height) / 2).toInt(), ((bitmap.height * bigScale - height) / 2).toInt(),
                        55.dp.toInt(), 55.dp.toInt())

//            for (i in 10..100 step 10) {
//                postDelayed({ refresh() }, i.toLong())
//            }

                /**
                 * 在下一帧调用 这里面传的是 runnable 每掉一次 都会创建 runnable 对象
                 */
//            postOnAnimation { refresh() }
//            postOnAnimation(this)
                // post 会马上推到主线程 .
//            post()

                ViewCompat.postOnAnimation(this@ScaleImageView, henRunnable)
            }
            return false
        }


        override fun onDoubleTap(e: MotionEvent): Boolean {
            big = !big
            if (big) {
                // 双击放大时候的修正 , 保证点击的位置 跟放大后的位置 一样
                offsetX = (e.x - width / 2) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2) * (1 - bigScale / smallScale)

                fixOffset()
                scaleAnimate.start()
            } else {

                // 不放到这里 放到 onDraw 里面
//            offsetX = 0f
//            offsetY = 0f
                scaleAnimate.reverse()
            }

            return true
        }

    }


    inner class HenScaleGestureDetectorListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            // 实时的放缩系数
//            currentScale *= detector.scaleFactor
//            currentScale = currentScale.coerceAtLeast(smallScale).coerceAtMost(bigScale)

//            return true

            val tempCurrentScale = currentScale * detector.scaleFactor
            if (tempCurrentScale < smallScale || tempCurrentScale > bigScale) {
                return false
            } else {
                currentScale *= detector.scaleFactor
                return true
            }
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {

            offsetX = (detector.focusX - width / 2) * (1 - bigScale / smallScale)
            offsetY = (detector.focusY - height / 2) * (1 - bigScale / smallScale)


            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {

        }

    }

    inner class FillingRunnable : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
//            postOnAnimation(this)
                ViewCompat.postOnAnimation(this@ScaleImageView, this)
            }
        }

    }
}
