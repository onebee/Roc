package com.one.second

import android.app.Activity
import android.graphics.Color.BLUE
import android.view.ViewGroup
import android.widget.TextView

/**
 * @author  diaokaibin@gmail.com on 11/23/20.
 */
fun Activity.drawTag(){
    var view = TextView(this)
    view.setBackgroundColor(BLUE)
    view.text = "internal"
    view.textSize = 25f
    view.setTextColor(BLACK)
    var v = window.decorView as ViewGroup
    v.addView(view,500,500)

}