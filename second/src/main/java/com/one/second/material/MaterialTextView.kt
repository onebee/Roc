package com.one.second.material

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import com.one.ktools.ex.dp

/**
 * @author  diaokaibin@gmail.com on 2020/11/12.
 */
val VERTICAL_OFFSET = 10.dp
val TEXT_SIZE = 10.dp
val HORIZONTAL_OFFSET = 10.dp

class MaterialTextView(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatEditText(context, attrs) {


    var paint = Paint().apply {
        textSize = TEXT_SIZE
    }

    var floatingLabelFraction = 0f
        set(value) {


        }


    val animator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
    }

    init {
        setPadding(paddingLeft, (paddingTop + VERTICAL_OFFSET).toInt(), paddingRight, paddingBottom)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, 25f, paint)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (text.isNullOrEmpty()) {

        } else {

        }
    }
}