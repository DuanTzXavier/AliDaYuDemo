package com.tomduan.bigfishdemo.Usecase;

import com.tomduan.bigfishdemo.Model.Repo;
import com.tomduan.bigfishdemo.Model.Result;
import com.tomduan.bigfishdemo.Model.Send;

import java.util.Map;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tomduan on 16-3-7.
 */
public class SendUsecase implements Usecase<Response<Result>> {

    private final Repo mRepo;
    private final Map<String, String> send;

    public SendUsecase(Repo mRepo, Map<String, String> send) {
        this.mRepo = mRepo;
        this.send = send;
    }

    @Override
    public Observable<Response<Result>> execute() {
        return mRepo.send(send)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
