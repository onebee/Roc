package com.one.second.animate

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.one.kcore.extension.dp

/**
 * @author  diaokaibin@gmail.com on 2020/11/11.
 */
class PointFView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var point = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }


    init {
        setLayerType(LAYER_TYPE_HARDWARE,null)
        setLayerType(LAYER_TYPE_SOFTWARE,null)
        setLayerType(LAYER_TYPE_NONE,null)

        paint.strokeWidth = 25.dp
        paint.strokeCap = Paint.Cap.ROUND;
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPoint(point.x, point.y, paint)
    }
}