package com.one.roc.plug;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.one.roc.R;

import androidx.appcompat.app.AppCompatActivity;

public class PlugcAtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug_activity);

        TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ISay say = new SayException();
                tv.setText(say.saySomething());
            }
        });
    }
}