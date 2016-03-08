package com.tomduan.bigfishdemo.Model.rest;

import com.tomduan.bigfishdemo.Model.Result;
import com.tomduan.bigfishdemo.Model.Send;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by tomduan on 16-3-7.
 */
public interface DaYuAPI {
    String BASE_API = "http://gw.api.taobao.com";

    @POST("/router/rest")
    Observable<Response<Result>> send(
            @QueryMap Map<String, String> send
    );

    @POST("/router/rest")
    String xsend(
            @QueryMap Map<String, String> send
    );

}
