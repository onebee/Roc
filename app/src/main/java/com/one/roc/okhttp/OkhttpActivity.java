package com.one.roc.okhttp;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.roc.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * @author diaokaibin@gmail.com on 2020/10/24.
 */
public class OkhttpActivity extends AppCompatActivity {

    ProgressResponseBody.ProgressListener listener;
    ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        TextView tv = findViewById(R.id.tv);


        iv = findViewById(R.id.iv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoaderImage();


            }
        });
        listener = new ProgressResponseBody.ProgressListener() {
            @Override
            public void update(int percent) {
                tv.setText(" process = " + percent);
                Timber.e("process = " + percent);
            }
        };





    }

    private void downLoaderImage() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response.newBuilder().body(new ProgressResponseBody(response.body(), listener)).build();
            }
        });

        Request request = new Request.Builder()
                .url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603551045400&di=3f7eba62ef0be35c5e261f5b46e41a09&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D1484500186%2C1503043093%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D853")
//                .url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603551388213&di=aeca1ccea5ba7e804b649533379c2a7f&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D143233297%2C3561721230%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1500%26h%3D1000")
                .build();

        OkHttpClient client = builder.build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                byte[] data = response.body().bytes();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
                    }
                });

            }
        });


    }

    private void doAction() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();


        Request request = new Request.Builder().url("https://www.baidu.com").build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Timber.e(e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Timber.i(string);


            }
        });
    }


}
