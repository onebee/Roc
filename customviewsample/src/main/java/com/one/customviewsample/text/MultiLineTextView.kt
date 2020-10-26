package com.one.customviewsample.text

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.one.customviewsample.R
import com.one.ktools.ex.dp

/**
 * @author  diaokaibin@gmail.com on 2020/9/21.
 */

private val IMAGE_SIZE = 250.dp
private val IMAGE_PADDING = 50.dp


class MultiLineTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

val text = "合肥片区重点发展高端制造、集成电路、人工智能、新型显示、量子信息、科技金融、跨境电商等产业，打造具有全球影响力的综合性国家科学中心和产业创新中心引领区。芜湖片区重点发展智能网联汽车、智慧家电、航空、机器人、航运服务、跨境电商等产业，打造战略性新兴产业先导区、江海联运国际物流枢纽区。蚌埠片区重点发展硅基新材料、生物基新材料、新能源等产业，打造世界级硅基和生物基制造业中心、皖北地区科技创新和开放发展引领区。第三点，我认为产业升级和科技创新是安徽自贸区可以打的两张非常好的牌。从这篇文章中，我们可以看到安徽在这方面的诸多优势。\n"
    val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    val bitmap = getAvatar(IMAGE_SIZE.toInt())

    val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {

        canvas.drawBitmap(bitmap, width - IMAGE_SIZE, IMAGE_PADDING, paint)
        paint.getFontMetrics(fontMetrics)
        val measuredWidth = floatArrayOf(0f)
        var start = 0
        var count: Int
        var verticalOffset = - fontMetrics.top
        var maxWidth: Float
        while (start < text.length) {
            maxWidth = if (verticalOffset + fontMetrics.bottom < IMAGE_PADDING
                    || verticalOffset + fontMetrics.top > IMAGE_PADDING + IMAGE_SIZE) {
                width.toFloat()
            } else {
                width.toFloat() - IMAGE_SIZE
            }
            count = paint.breakText(text, start, text.length, true, maxWidth, measuredWidth)
            canvas.drawText(text, start, start + count, 0f, verticalOffset, paint)
            start += count
            verticalOffset += paint.fontSpacing
        }

    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avater, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avater, options)
    }

}