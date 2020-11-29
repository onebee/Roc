package com.one.second.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @author  diaokaibin@gmail.com on 11/29/20.
 */
interface ApiService {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>
}