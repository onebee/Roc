package com.one.customviewsample.drawable

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import com.one.ktools.ex.dp

/**
 * @author  diaokaibin@gmail.com on 2020/9/29.
 */
class DrawableView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    var drawable = MeshDrawable()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawable.setBounds(50.dp.toInt(),50.dp.toInt(),width,height)
        drawable.draw(canvas)
    }
}