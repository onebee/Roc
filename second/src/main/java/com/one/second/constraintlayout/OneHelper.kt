package com.one.second.constraintlayout

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout
import com.one.second.R

/**
 * @author  diaokaibin@gmail.com on 11/15/20.
 */
class OneHelper(context: Context, attrs: AttributeSet) : ConstraintHelper(context, attrs) {


    override fun updatePostLayout(container: ConstraintLayout) {
        super.updatePostLayout(container)

        referencedIds.forEach {

            val view = container.getViewById(it)
            if (view.id == R.id.iv_one) {
                // 做个动画
                view.animate().rotation(360f).setDuration(2500).start()
            }

        }

    }
}