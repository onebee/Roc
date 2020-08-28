package com.one.roc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.one.roc.ioc1.RViewAct;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView imageView = findViewById(R.id.iv);
//        String pkg = "com.kohler.photo";//your package name
//        Drawable icon = null;
//        try {
//            icon = this.getPackageManager().getApplicationIcon(pkg);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        imageView.setImageDrawable(icon);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RViewAct.class);
                startActivity(intent);
            }
        });


    }
}