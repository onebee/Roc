package com.one.second.Xfermode

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.one.ktools.ex.dp
import com.one.second.R

/**
 * @author  diaokaibin@gmail.com on 2020/11/8.
 */

private val IMAGE_SIZE = 200.dp
private val IMAGE_PADDING = 25.dp
private val XFERMODE = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

class AvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paint = Paint()
    var bounds = RectF(IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING + IMAGE_SIZE, IMAGE_PADDING + IMAGE_SIZE)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val saveLayer = canvas.saveLayer(bounds, null)
        canvas.drawOval(bounds, paint)
        paint.xfermode = XFERMODE
        canvas.drawBitmap(getAvatar(IMAGE_SIZE.toInt()), IMAGE_PADDING, IMAGE_PADDING, paint)
        paint.xfermode = null
        canvas.restoreToCount(saveLayer)

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