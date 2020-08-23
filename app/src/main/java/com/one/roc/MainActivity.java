package com.one.roc;

import android.os.Bundle;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(new Cache(getCacheDir(), 25 * 1024 * 1024));
        OkHttpClient client = builder.build();
        Request request = new Request.Builder().url("http://github.com").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });


    }
}