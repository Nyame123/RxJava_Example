package com.bisapp.rxjavaexamples;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxTest {

    public static void main(String[] args){
        testOnErrorReturn();
    }


    private static void testOnErrorReturn(){
        AtomicReference<String> string = new AtomicReference<>();
        Observable.error(new Throwable("dsds"))
                .onErrorReturn(throwable -> {
                    string.set("An error occured");
                    return string;
                })
                //.onErrorReturnItem("This is what I want when there is an error")
        .subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
