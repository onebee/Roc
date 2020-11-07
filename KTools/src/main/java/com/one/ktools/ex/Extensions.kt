package com.one.ktools.ex

import android.content.res.Resources
import android.util.TypedValue

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
