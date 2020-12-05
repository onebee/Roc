package com.one.second.plug;

import android.os.Bundle;
import android.widget.TextView;

import com.one.second.R;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import dalvik.system.DexClassLoader;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * @author diaokaibin@gmail.com on 12/5/20.
 */
public class PlugActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug);
        TextView tv = findViewById(R.id.tv);

        File file = new File(getCacheDir() + File.separator + "plug.apk");
        try (Source source = Okio.source(getAssets().open("plugsample-debug.apk"));
             BufferedSink sink = Okio.buffer(Okio.sink(file))) {
            sink.writeAll(source);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DexClassLoader dexClassLoader = new DexClassLoader(file.getPath(), getCacheDir().getPath(), null, null);


        try {

//            Class clazz = Class.forName("com.one.second.plug.tools.Util");
            Class clazz = dexClassLoader.loadClass("com.one.plugsample.Util");
            Constructor constructor = clazz.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            Object util = constructor.newInstance();

            Method method = clazz.getDeclaredMethod("shut");
            method.setAccessible(true);
            method.invoke(util);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
