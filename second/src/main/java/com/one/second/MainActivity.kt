package com.one.second

import android.animation.*
import android.graphics.PointF
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.postDelayed
import com.one.kcore.extension.dp
import com.one.second.animate.CameraView2
import com.one.second.animate.ProvinceTypeEvaluator
import com.one.second.clipandcamera.CameraView
import com.one.second.draw.PieView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view.postDelayed(3000) {
            view.useFloatingLabel = false

        }


    }


}