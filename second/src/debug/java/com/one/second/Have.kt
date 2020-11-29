package com.one.second

import android.app.Activity
import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Color.RED
import android.provider.CalendarContract
import android.view.ViewGroup
import android.widget.TextView

/**
 * @author  diaokaibin@gmail.com on 11/23/20.
 */
fun Activity.drawTag(){
    var view = TextView(this)
    view.setBackgroundColor(RED)
    view.textSize = 25f
    view.setTextColor(BLACK)
    view.text = "debug"

    var v = window.decorView as ViewGroup
    v.addView(view,500,500)

}