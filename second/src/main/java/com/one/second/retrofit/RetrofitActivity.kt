package com.one.second.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.one.second.R
import kotlinx.android.synthetic.main.retrofit_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author  diaokaibin@gmail.com on 11/29/20.
 */
class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.retrofit_activity)
    }

    override fun onResume() {
        super.onResume()


        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(ApiService::class.java)
        val call: Call<List<Repo>> = service.listRepos("onebee")
        call.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                var str = ""
                val list = response.body()
                if (list != null) {
                    for (repo in list) {
                        str += repo.archive_url +"\n" + repo.description + "\n" + repo.created_at +  "\n" +repo.name+ "\n\n"
                    }
                }
                tv.text = str
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

                tv.text = t.message
            }

        })
    }
}

