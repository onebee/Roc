package com.one.second.animate

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.graphics.withSave
import com.one.kcore.extension.dp
import com.one.kcore.extension.getAvatar
import com.one.second.R

/**
 * @author  diaokaibin@gmail.com on 2020/11/8.
 */

private val BITMAP_SIZE = 200.dp
private val BITMAP_PADDING = 100.dp

class CameraView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(resources, 250.dp.toInt(), R.drawable.avatar)
    private val camera = Camera()

    private val cliped = Path().apply {

        addOval(BITMAP_PADDING, BITMAP_PADDING, BITMAP_PADDING + BITMAP_SIZE, BITMAP_PADDING + BITMAP_SIZE, Path.Direction.CCW)
    }

    var fraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var objectAnimator = ObjectAnimator.ofFloat(this, "fraction", 0f, 90f).apply {
        duration = 5000
//        interpolator = AccelerateDecelerateInterpolator()
    }


    private var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var bottomFlip = 30f
        set(value) {
            field = value
            invalidate()
        }

    private var flipRotation = 0f
        set(value) {
            field = value
            invalidate()
        }


    init {


        camera.setLocation(0f, 0f, -8f * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 上半部分
        canvas.withSave {
            canvas.translate((BITMAP_PADDING + BITMAP_SIZE / 2), (BITMAP_PADDING + BITMAP_SIZE / 2))
            canvas.rotate(-flipRotation)

            camera.save()
            camera.rotateX(topFlip)
            camera.applyToCanvas(canvas)
            camera.restore()

            canvas.clipRect(-BITMAP_SIZE, -BITMAP_SIZE, BITMAP_SIZE, 0f)
            canvas.rotate(flipRotation)
            canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
            canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        }


        // 下半部分
        canvas.withSave {
            canvas.translate((BITMAP_PADDING + BITMAP_SIZE / 2), (BITMAP_PADDING + BITMAP_SIZE / 2))
            canvas.rotate(-flipRotation)

            camera.save()
            camera.rotateX(bottomFlip)
            camera.applyToCanvas(canvas)
            camera.restore();


            canvas.clipRect(-BITMAP_SIZE, 0F, BITMAP_SIZE, BITMAP_SIZE)
            canvas.rotate(flipRotation)
            canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
            canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        }

    }

    public fun setStart() {
//        objectAnimator.start()
    }
}