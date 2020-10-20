package com.one.customviewsample.viewrootimpl

import android.app.Activity
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.one.customviewsample.R
import kotlinx.android.synthetic.main.activity_view_root.*
import kotlin.concurrent.thread

/**
 * @author  diaokaibin@gmail.com on 2020/10/11.
 */
class ViewRootImplActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_root)



        tv.setOnClickListener {
//            it.requestLayout()

//            tv.text = "ddddddas"
            thread {
                tv.text = "子线程更新UI"


//                Looper.prepare()
//                val button = Button(this)
//
//                windowManager.addView(button, WindowManager.LayoutParams())
//
//                button.setOnClickListener {
//
//                    button.text = """
//                        ${Thread.currentThread().name}
//                        : ${SystemClock.uptimeMillis()}
//
//                    """.trimIndent()
//                }
//
//                Looper.loop()


//                Looper.prepare()
//                Toast.makeText(this,"dasfadsfas",Toast.LENGTH_SHORT).show()
//
//                Looper.loop()
            }
        }


    }
}