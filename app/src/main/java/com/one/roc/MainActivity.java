package com.one.roc;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.one.librview.Common;
import com.one.librview.annotation.ContentView;
import com.one.librview.annotation.OnClick;
import com.one.roc.ioc1.BaseActivity;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


//        ImageView imageView = findViewById(R.id.iv);
//        String pkg = "com.kohler.photo";//your package name
//        Drawable icon = null;
//        try {
//            icon = this.getPackageManager().getApplicationIcon(pkg);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        imageView.setImageDrawable(icon);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AOP1Activity.class);
//                startActivity(intent);
//            }
//        });


    }


    @OnClick({R.id.bt_nav_show, R.id.btn_nav_hide})
    public void nav(View view) {
        switch (view.getId()) {
            case R.id.bt_nav_show:
                Toast.makeText(this, "nav show", Toast.LENGTH_SHORT).show();
                Log.e(Common.TAG,"nav show");
                Settings.System.putInt(MainActivity.this.getContentResolver(), "ro.discovery.navibar_show", 1);
                break;

            case R.id.btn_nav_hide:
                Toast.makeText(this, "nav hide", Toast.LENGTH_SHORT).show();
                Settings.System.putInt(MainActivity.this.getContentResolver(), "ro.discovery.navibar_show", 0);
                Log.e(Common.TAG,"nav hide");

                break;
        }
    }
}