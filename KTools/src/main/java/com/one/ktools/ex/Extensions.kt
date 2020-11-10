package com.one.ktools.ex

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue
import com.one.ktools.R

/**
 * @author  diaokaibin@gmail.com on 2020/9/21.
 */
val Float.dp
    get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
    )
val Int.dp
    get() = this.toFloat().dp


private fun getAvatar(width: Int, resources: Resources, id: Int): Bitmap {

    var option = BitmapFactory.Options()
    option.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, id, option)
    option.inJustDecodeBounds = false

    option.inDensity = option.outWidth
    option.inTargetDensity = width
    return BitmapFactory.decodeResource(resources, id, option)

}