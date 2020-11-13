package com.one.second.material

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.RED
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import com.one.ktools.ex.dp
import com.one.second.R

/**
 * @author  diaokaibin@gmail.com on 2020/11/12.
 */
val VERTICAL_OFFSET = 10.dp
val TEXT_SIZE = 15.dp
val HORIZONTAL_OFFSET = 10.dp

class MaterialTextView(context: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatEditText(context, attrs) {
    var paint = Paint().apply {
        textSize = TEXT_SIZE
        color = RED
    }
    var floatingLabelFraction = 0f
        set(value) {
            field = value
            Log.i("---", value.toString())
            invalidate()
        }

    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
    }

    var useFloatingLabel = false
        set(value) {
            if (field != value) {

                field = value
                if (field) {
                    setPadding(paddingLeft, (paddingTop + VERTICAL_OFFSET).toInt(), paddingRight, paddingBottom)
                } else {
                    setPadding(paddingLeft, (paddingTop - VERTICAL_OFFSET).toInt(), paddingRight, paddingBottom)
                }
            }

        }

    init {
        for (index in 0 until attrs.attributeCount) {

            attrs.getAttributeName(index)
            attrs.getAttributeValue(index)


            Log.i("-----", " attrs name = ${attrs.getAttributeName(index)}"
                    + " , attrs value = ${attrs.getAttributeValue(index)} "
            )

        }


        val attributeSet = context.obtainStyledAttributes(attrs, R.styleable.MaterialTextView)
        useFloatingLabel = attributeSet.getBoolean(R.styleable.MaterialTextView_useFloatingLabel, true)

        attributeSet.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.alpha = (floatingLabelFraction * 0xff).toInt()
        var currentY = 15.dp + 10.dp * (1 - floatingLabelFraction)
        Log.i("---", " currentY $currentY")
        canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, currentY, paint)
    }

    var floatTagShow = false

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (!text.isNullOrEmpty() && !floatTagShow) {
            floatTagShow = true
            animator.start()
            Log.i("---", " animate start")
        } else if (text.isNullOrEmpty() && floatTagShow) {
            floatTagShow = false
            animator.reverse()
        }
    }
}