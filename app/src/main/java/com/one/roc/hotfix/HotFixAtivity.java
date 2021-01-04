package com.one.roc.hotfix;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.one.roc.R;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class HotFixAtivity extends AppCompatActivity {

    TextView tv;
    Button btn;
    Button reload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fix_ativity);

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        reload = findViewById(R.id.reload);


        Title title = new Title();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv.setText(title.getTitle());
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File apk = new File(getCacheDir() + File.separator + "hotfix.apk");
                if (!apk.exists()) {
                    try (Source source = Okio.source(getAssets().open("apk/app-debug.apk"))) {
                        BufferedSink sink = Okio.buffer(Okio.sink(apk));
                        sink.writeAll(source);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

    }
}