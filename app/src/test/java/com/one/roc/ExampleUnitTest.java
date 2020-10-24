package com.one.roc;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private static void doAction() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();


        Request request = new Request.Builder().url("https://www.baidu.com").build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Timber.e(e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Timber.e(response.body().string());

            }
        });
    }

    public static void main(String[] args) {
//        doAction();
    }
}