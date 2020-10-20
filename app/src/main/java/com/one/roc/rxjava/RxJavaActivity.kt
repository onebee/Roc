package com.one.roc.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.one.roc.R
import com.one.roc.rxjava.model.Repo
import com.one.roc.rxjava.network.Api
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxjava.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author  diaokaibin@gmail.com on 2020/10/20.
 */
class RxJavaActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()


        val api = retrofit.create(Api::class.java)

        api.getRepos("onebee").observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(object : SingleObserver<MutableList<Repo>> {
            override fun onSubscribe(@NonNull d: @NonNull Disposable?) {
                tv.text = "正在请求"
            }

            override fun onSuccess(t: MutableList<Repo>) {
                tv.text = "${t[0].name}"
            }

            override fun onError(e: Throwable) {
                tv.text = e.message ?: e.javaClass.name
            }


        })


    }

    override fun onDestroy() {
        super.onDestroy()

    }
}