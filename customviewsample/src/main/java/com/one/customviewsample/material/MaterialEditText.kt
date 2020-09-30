package com.one.customviewsample.material

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.one.customviewsample.R
import com.one.ktools.ex.dp


/**
 * @author  diaokaibin@gmail.com on 2020/9/29.
 */
private val TEXT_SIZE = 12.dp
private val TEXT_MARGIN = 8.dp
private val HORIZONTAL_OFFSET = 5.dp
private val VERTICAL_OFFSET = 23.dp
private val EXTRA_VERTICAL_OFFSET = 16.dp

class MaterialEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var floatLabelShow = false
    var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    val animator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
    }
    private var useFloatingLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(paddingLeft, (paddingTop + TEXT_MARGIN + TEXT_SIZE).toInt(), paddingRight, paddingBottom)
                } else {
                    setPadding(paddingLeft, (paddingTop - TEXT_SIZE - TEXT_MARGIN).toInt(), paddingRight, paddingBottom)

                }
            }

        }

    init {

        val textSize = TEXT_SIZE
        paint.textSize = textSize


        val typeArray = context.obtainStyledAttributes(attrs, intArrayOf(R.attr.useFloatingLabel))
        useFloatingLabel = typeArray.getBoolean(0, true)

    }


    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

        if (floatLabelShow && text.isNullOrEmpty() && useFloatingLabel) {
            floatLabelShow = false
            animator.reverse()

        } else if (!floatLabelShow && !text.isNullOrEmpty() && useFloatingLabel) {
            floatLabelShow = true
            animator.start()

        }


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.alpha = (floatingLabelFraction * 0xff).toInt()
        val currentVerticalValue = VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - floatingLabelFraction)
        canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, currentVerticalValue, paint)

    }


}