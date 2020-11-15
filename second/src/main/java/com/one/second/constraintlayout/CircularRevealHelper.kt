package com.one.second.constraintlayout

import android.content.Context
import android.util.AttributeSet
import android.view.ViewAnimationUtils
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.hypot

/**
 * @author  diaokaibin@gmail.com on 11/15/20.
 */
class CircularRevealHelper(context: Context?, attrs: AttributeSet?) : ConstraintHelper(context, attrs) {


    override fun updatePostLayout(container: ConstraintLayout) {
        super.updatePostLayout(container)

        referencedIds.forEach {
            val view = container.getViewById(it)
            val radius = hypot(view.width.toDouble(), view.height.toDouble())
            ViewAnimationUtils.createCircularReveal(view, 0, 0, 0f, radius.toFloat())
                    .setDuration(5000)
                    .start()
        }

    }
}