package com.tomduan.bigfishdemo.Usecase;

import rx.Observable;

/**
 * Created by tomduan on 16-3-7.
 */
public interface Usecase<T> {
    Observable<T> execute();
}
