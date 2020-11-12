package com.one.second.bitmap_drawable

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.BLUE
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.one.kcore.extension.dp

/**
 * @author  diaokaibin@gmail.com on 2020/11/12.
 */
class DrawableView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var drawable = MeshDrawable()
    var paint = Paint().apply {
        color = BLUE
        textSize = 32.dp
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawable.setBounds(0, 0, width/2, height/2)
        drawable.draw(canvas)

        canvas.drawText("onebit", width / 2f, height / 2f, paint)

    }
}