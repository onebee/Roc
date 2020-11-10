package com.one.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.one.second.clipandcamera.CameraView
import com.one.second.draw.PieView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       var cameraView=  findViewById<CameraView>(R.id.view)
        cameraView.setOnClickListener {

            cameraView.setStart()
        }
    }
}