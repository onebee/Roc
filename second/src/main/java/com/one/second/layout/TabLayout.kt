package com.one.second.layout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

/**
 * @author  diaokaibin@gmail.com on 11/14/20.
 */

var childBounds = mutableListOf<Rect>()

class TabLayout(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {


        var widthUsed = 0
        var heightUsed = 0

        var lineWidthUsed = 0
        var lineMaxHeight = 0

        val specWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val specWidthMode = MeasureSpec.getMode(widthMeasureSpec)


        for ((index, child) in children.withIndex()) {

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)

            // 换行
            if (specWidthMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.measuredWidth > specWidthSize) {

                lineWidthUsed = 0;
                heightUsed += lineMaxHeight
                lineMaxHeight = 0

                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            }

            if (index >= childBounds.size) {
                childBounds.add(Rect())
            }

            val childBound = childBounds[index]

            childBound.set(lineWidthUsed, heightUsed, lineWidthUsed + child.measuredWidth, heightUsed + child.measuredHeight)
            lineWidthUsed += child.measuredWidth
            widthUsed = max(widthUsed, lineWidthUsed)
            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)

        }

        val selfWidth = widthUsed
        val selfHeight = heightUsed + lineMaxHeight
        setMeasuredDimension(selfWidth, selfHeight)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (childCount > 0 && childBounds.size == childCount) {
            for ((index, child) in children.withIndex()) {
                val rect = childBounds[index]
                child.layout(rect.left, rect.top, rect.right, rect.bottom)
            }
        }

    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}