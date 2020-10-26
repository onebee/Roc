package com.one.kcore.extension

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.TypedValue

/**
 * @author  diaokaibin@gmail.com on 2020/8/29.
 */


fun dp2px(value: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().displayMetrics)
}

val Float.dp
    get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
    )

val Int.dp
    get() = this.toFloat().dp


fun getAvatar(res: Resources, width: Int, id: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(res, id, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(res, id, options)

}

fun resizeImage(bitmap: Bitmap, width: Int, height: Int): Bitmap {
    val bmpWidth = bitmap.width
    val bmpHeight = bitmap.height
    val scaleWidth = width.toFloat() / bmpWidth
    val scaleHeight = height.toFloat() / bmpHeight
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    return Bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight, matrix, true)
}
