package com.one.second

import android.animation.AnimatorSet
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
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


        val length = 200.dp
        val keyframe1 = Keyframe.ofFloat(0f, 0f)
        val keyframe2 = Keyframe.ofFloat(0.2f, 1.5f * length)
        val keyframe3 = Keyframe.ofFloat(0.8f, 0.6f * length)
        val keyframe4 = Keyframe.ofFloat(1f, 1f * length)

        var keyframeHolder = PropertyValuesHolder.ofKeyframe("translationX",keyframe1,keyframe2,keyframe3,keyframe4)

        val animator= ObjectAnimator.ofPropertyValuesHolder(view,keyframeHolder)
        animator.startDelay = 1000
        animator.duration = 5000
        animator.start()

    }
}