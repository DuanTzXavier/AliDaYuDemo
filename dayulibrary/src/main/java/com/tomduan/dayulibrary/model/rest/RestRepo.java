package com.tomduan.dayulibrary.model.rest;
import com.tomduan.dayulibrary.model.Repo;
import com.tomduan.dayulibrary.model.Result;

import java.util.Map;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by tomduan on 16-3-7.
 */
public class RestRepo implements Repo {

    private final DaYuAPI mDaYuAPI;

    public RestRepo() {
        mDaYuAPI = new Retrofit.Builder()
                    .baseUrl(DaYuAPI.BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build().create(DaYuAPI.class);
    }

    @Override
    public Observable<Response<Result>> send(Map<String, String> send) {
        return mDaYuAPI.send(send);
    }
}
