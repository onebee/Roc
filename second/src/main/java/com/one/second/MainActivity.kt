package com.one.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.one.second.view.PieView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pie = findViewById<PieView>(R.id.pie)

        pie.setOnClickListener {

            var random = Random.nextInt(5)
            pie.setIndex(random)
        }

    }
}