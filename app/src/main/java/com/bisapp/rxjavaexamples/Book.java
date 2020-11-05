package com.bisapp.rxjavaexamples;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class Book {

    String page = "First page";

    public void setPage(String page) {
        this.page = page;
    }

    public Observable<String> justObservable() {
        return Observable.just(page);
    }

    public Observable<String> deferObservable() {
        return Observable.defer(new Callable<ObservableSource<String>>() {

            @Override
            public ObservableSource<String> call() throws Exception {
                return Observable.just(page);
            }
        });
    }

}
