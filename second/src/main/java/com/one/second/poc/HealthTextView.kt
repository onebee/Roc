package com.one.second.poc

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.one.second.R

/**
 * @author  diaokaibin@gmail.com on 11/16/20.
 */
class HealthTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    private val boundsCn = Rect()
    private val boundsEn = Rect()


    var paintCn = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 95f
        color= RED
        typeface = ResourcesCompat.getFont(context, R.font.font)
    }

    var paintEn = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 95f
        color = BLUE
        typeface = ResourcesCompat.getFont(context, R.font.helvetica_neue_lt_pro_lt)
    }


    init {


    }

    var startY = 150f
    override fun onDraw(canvas: Canvas) {


        var startX = 0f
        var lastChar = '1'
        text.forEach {

          paintEn.textSize =   textSize
            paintCn.textSize = textSize

            if (isLatin(it)) {
                var bound1 = Rect()
                paintCn.getTextBounds(it.toString(), 0, it.toString().length, bound1)
                var textWidth = (bound1.right - bound1.left)
                startX +=10
                canvas.drawText(it.toString(), startX, startY, paintCn)
                startX += textWidth

                lastChar = it

            } else {
                var bound2 = Rect()
                paintEn.getTextBounds(it.toString(), 0, it.toString().length, bound2)
                var textWidth = (bound2.right - bound2.left)

                if (isLatin(lastChar)) {
                    startX +=50
                }
                canvas.drawText(it.toString(), startX, startY, paintEn)
                startX += textWidth

                lastChar = it

            }

        }

    }

    fun isLatin(c: Char) = c.toInt() < 256


    fun isCnorEn(c: Char): Int {
        if (c.toInt() in 0x0391..0xFFE5) //中文字符
            1
        return if (c.toInt() in 0x0000..0x00FF) { //英文字符
            2
        } else 3
    }
}