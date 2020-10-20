package com.one.roc.rxjava.network;

import com.one.roc.rxjava.model.Repo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author diaokaibin@gmail.com on 2020/10/20.
 */
public interface Api {

    @GET("users/{username}/repos")
    Single<List<Repo>> getRepos(@Path("username") String username);
}
