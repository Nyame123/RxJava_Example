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
        return Observable.defer((Callable<ObservableSource<String>>) () -> Observable.just(page));
    }

}
