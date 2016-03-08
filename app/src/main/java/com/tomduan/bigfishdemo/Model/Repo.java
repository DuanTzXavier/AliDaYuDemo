package com.tomduan.bigfishdemo.Model;

import java.util.Map;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by tomduan on 16-3-7.
 */
public interface Repo {

    Observable<Response<Result>> send(Map<String, String> send);
}
