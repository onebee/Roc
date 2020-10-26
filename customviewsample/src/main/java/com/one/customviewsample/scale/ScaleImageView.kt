package com.one.customviewsample.scale

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
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

class ScaleImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {

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

    private val gestureDetector = GestureDetectorCompat(context, this).apply {

    }

    private var scaleFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val scaleAnimate by lazy {
        ObjectAnimator.ofFloat(this, "scaleFraction", 0f, 1f).apply {
            duration = 300
        }
    }


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
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        canvas.translate(offsetX, offsetY)

        var scale = smallScale + (bigScale - smallScale) * scaleFraction
        canvas.scale(scale, scale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originOffsetX, originOffsetY, paint)

        canvas.drawRect(originOffsetX, originOffsetY,
                originOffsetX + bitmap.width.toFloat(),
                originOffsetY + bitmap.height.toFloat(),
                paint)


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)

    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent?) {


    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {


        if (big) {
            offsetX -= distanceX

            // 滑动的偏移修正
            offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
            offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)


            offsetY -= distanceY
            offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
            offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)

            invalidate()
        }


        return false

    }

    override fun onLongPress(e: MotionEvent?) {


    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        if (big) {
            scroller.fling(
                    offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                    -((bitmap.width * bigScale - width) / 2).toInt(), ((bitmap.width * bigScale - width) / 2).toInt(),
                    -((bitmap.height * bigScale - height) / 2).toInt(), ((bitmap.height * bigScale - height) / 2).toInt(),
                    55.dp.toInt(),55.dp.toInt()  )

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

            ViewCompat.postOnAnimation(this,this)
        }
        return false
    }


    private fun refresh() {
        // 相当于掐个表
        scroller.computeScrollOffset()
        offsetX = scroller.currX.toFloat()
        offsetY = scroller.currY.toFloat()
        invalidate()
    }
    override fun run() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.currX.toFloat()
            offsetY = scroller.currY.toFloat()
            invalidate()
//            postOnAnimation(this)
            ViewCompat.postOnAnimation(this,this)
        }

    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {

        return false
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        big = !big
        if (big) {
            scaleAnimate.start()
        } else {
            scaleAnimate.reverse()
        }

        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return true
    }


}
