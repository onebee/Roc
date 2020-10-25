package com.one.customviewsample.scale

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.one.customviewsample.R
import com.one.kcore.extension.getAvatar

/**
 * @author  diaokaibin@gmail.com on 2020/10/25.
 */
class ScaleImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    val bitmap = getAvatar(resources, 500, R.drawable.avater)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(bitmap,0f,0f,paint)

    }
}