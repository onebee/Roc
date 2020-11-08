package com.one.second.text

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.one.kcore.extension.dp
import com.one.second.R

/**
 * @author  diaokaibin@gmail.com on 2020/11/8.
 */
private val text = "严选在2016年618大促和双十一大促期间推出“三件生活美学”概念，不同于其他电商为了鼓励用户多买而推出的“凑单满减”的促销政策，网易严选力推“3件生活美学”概念，鼓励用户在全场只购买3件以内的商品，直接享受整单8折优惠。 [19] " +
        "通过此次活动，严选倡导一种合理有度的消费观，并鼓励用户在认真挑选商品的过程中，找到真正所需和自己对生活美学的理解。活动收到了众多用户的肯定，618期间，将流水翻了20倍；在双十一期间，活动开启一小时销量爆发，流水超平时100倍"


private val IMAGE_SIZE = 250.dp
private val IMAGE_PADDING = 155.dp


class MultiTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val bitmap = getAvatar(IMAGE_SIZE.toInt())
    private val paint = Paint().apply {
        style = Paint.Style.FILL
        textSize = 25.dp
        textAlign

    }

    val paintText = Paint().apply {
        style = Paint.Style.FILL
        textSize = 25.dp
        textAlign

    }

    private var fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(bitmap, width - IMAGE_SIZE, IMAGE_PADDING, paint)
        paintText.getFontMetrics(fontMetrics)


        var measuredWidth = floatArrayOf(0f)

        var verticalOffset = -fontMetrics.top

        var maxWidth: Float
        var start = 0
        var count: Int
        while (start < text.length) {
            maxWidth = if (verticalOffset + fontMetrics.bottom < IMAGE_PADDING || verticalOffset + fontMetrics.top > IMAGE_PADDING + IMAGE_SIZE) {
                width.toFloat()
            } else {
                width - IMAGE_SIZE
            }

            count = paintText.breakText(text, start, text.length, true, maxWidth, measuredWidth)
            canvas.drawText(text,start,start+count,0f,verticalOffset,paintText)
            start +=count
            verticalOffset += paintText.fontSpacing

        }


    }


    private fun getAvatar(width: Int): Bitmap {

        var option = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar, option)
        option.inJustDecodeBounds = false

        option.inDensity = option.outWidth
        option.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar, option)

    }
}