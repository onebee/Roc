package com.one.customviewsample.drawable;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.one.customviewsample.R;
import com.one.ktools.ex.ExtensionsKt;

import androidx.appcompat.app.AppCompatActivity;

public class SelfDrawableActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TaskClearDrawable mTaskClearDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_drawable);

        mImageView = findViewById(R.id.imageView);
        mTaskClearDrawable = new TaskClearDrawable(this, (int)ExtensionsKt.getDp(400), (int) ExtensionsKt.getDp(400));
        mImageView.setImageDrawable(mTaskClearDrawable);

        mImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.i("Zero", "mTaskClearDrawable = " + mTaskClearDrawable.isRunning() );
                if(false == mTaskClearDrawable.isRunning()){
//                    mTaskClearDrawable.start();
                }
            }
        });
        
    }
}
