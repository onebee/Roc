package com.one.second.drag

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

/**
 * @author  diaokaibin@gmail.com on 11/18/20.
 */

const val COLUMNS = 2
const val ROW = 3

class DragListenerGlideView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private var TAG = "DragListenerGlideView";

    var dragListener = OneDragListener()
    var dragView: View? = null
    var orderedChildren: MutableList<View> = ArrayList()

    override fun onFinishInflate() {
        super.onFinishInflate()
        for (child in children) {
            orderedChildren.add(child)
            child.setOnLongClickListener { v ->
                dragView = v
                v.startDrag(null, DragShadowBuilder(v), v, 0)
                false
            }

            child.setOnDragListener(dragListener)

        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val specWidth = MeasureSpec.getSize(widthMeasureSpec)
        val specHeight = MeasureSpec.getSize(heightMeasureSpec)
        val childWidth = specWidth / COLUMNS
        val childHeight = specHeight / ROW
        measureChildren(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY))
        setMeasuredDimension(specWidth, specHeight)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft: Int
        var childTop: Int
        val childWidth = width / COLUMNS
        val childHeight = height / ROW

        for ((index, child) in children.withIndex()) {
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.layout(0, 0, childWidth, childHeight)
            child.translationX = childLeft.toFloat()
            child.translationY = childTop.toFloat()

        }

    }

    fun sort(targetView: View) {

        var targetIndex = -1
        var dragIndex = -1

        for ((index, child) in orderedChildren.withIndex()) {

            if (targetView === child) {
                targetIndex = index
            } else if (dragView === child) {
                dragIndex = index
            }
        }

        orderedChildren.removeAt(dragIndex)
        orderedChildren.add(targetIndex, dragView!!)

        var childLeft: Int
        var childTop: Int
        val childWidth = width / COLUMNS
        val childHeight = height / ROW
        for ((index, child) in orderedChildren.withIndex()) {
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.animate()
                    .translationX(childLeft.toFloat())
                    .translationY(childTop.toFloat())
                    .setDuration(150)
        }

    }


    override fun onDragEvent(event: DragEvent): Boolean {
        Log.d(TAG, "onDragEvent: " + event.action)

        return super.onDragEvent(event)


    }

    inner class OneDragListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> if (event.localState === v) {
                    v.visibility = INVISIBLE
                }

                DragEvent.ACTION_DRAG_ENTERED -> {
                    if (event.localState !== v) {
                        sort(v)
                    }
                }

                DragEvent.ACTION_DRAG_EXITED -> {

                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    if (event.localState === v) {
                        v.visibility = VISIBLE
                    }
                }
            }

            return true
        }

    }
}