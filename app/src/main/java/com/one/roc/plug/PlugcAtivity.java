package com.one.roc.plug;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.one.roc.R;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import dalvik.system.DexClassLoader;

public class PlugcAtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug_activity);

        TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "say_something_hotfix.jar");

                if (!file.exists()) {
                    ISay say = new SayException();
                    tv.setText(say.saySomething());
                } else {
                    DexClassLoader dexClassLoader = new DexClassLoader(file.getAbsolutePath(),
                            getExternalCacheDir().getAbsolutePath(), null, getClassLoader()
                    );

                    try {
                        Class clazz = dexClassLoader.loadClass("com.one.roc.plug.SayRight");

                        ISay say = (ISay) clazz.newInstance();
                        tv.setText(say.saySomething());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }



            }
        });
    }
}