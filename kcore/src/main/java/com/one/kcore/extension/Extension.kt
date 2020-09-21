package com.one.kcore.extension

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author  diaokaibin@gmail.com on 2020/8/29.
 */


fun dp2px(value: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().displayMetrics)
}
