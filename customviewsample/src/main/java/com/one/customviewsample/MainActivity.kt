package com.one.customviewsample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.BitmapCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)

        val bitmap = Bitmap.createBitmap(25, 25, Bitmap.Config.ARGB_8888)
        val toDrawable = bitmap.toDrawable(resources)
        val drawable = ColorDrawable()
        val toBitmap = drawable.toBitmap(25, 25, Bitmap.Config.ARGB_8888)

    }
}
