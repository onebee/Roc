package com.one.second.drag

import android.content.ClipData
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.one.ktools.ex.dp
import kotlinx.android.synthetic.main.drag_to_collect.view.*

/**
 * @author  diaokaibin@gmail.com on 11/18/20.
 */
class DragToCollectLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {


    var dragStart = OnLongClickListener {
        val imageData = ClipData.newPlainText("name", it.contentDescription)
        ViewCompat.startDragAndDrop(it, imageData, DragShadowBuilder(it), null, 0)

    }

    var dragListener = CollectListener()
    override fun onFinishInflate() {
        super.onFinishInflate()
        avatarView.setOnLongClickListener(dragStart)
        logoView.setOnLongClickListener(dragStart)
        tv.setOnLongClickListener(dragStart)
        collectorLayout.setOnDragListener(dragListener)
    }

    inner class CollectListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DROP -> {
                    if (v is LinearLayout) {
                        var text = TextView(context)
                        text.textSize = 15f
                        text.text = event.clipData.getItemAt(0).text
                        v.addView(text)

                    }

                }


            }

            return true
        }

    }

}