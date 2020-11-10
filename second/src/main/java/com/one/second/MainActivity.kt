package com.one.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.one.kcore.extension.dp
import com.one.second.animate.CameraView2
import com.one.second.clipandcamera.CameraView
import com.one.second.draw.PieView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//       var cameraView=  findViewById<CameraView2>(R.id.view)
//        cameraView.setOnClickListener {
//
//            cameraView.setStart()
//        }
//
//        cameraView.animate().

        view.animate().translationX(200.dp).setDuration(2500).start()
    }
}