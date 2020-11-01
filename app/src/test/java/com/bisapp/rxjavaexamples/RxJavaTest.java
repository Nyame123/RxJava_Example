package com.bisapp.rxjavaexamples;

import android.util.Log;

import org.junit.Assert;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class RxJavaTest {

    @Test
    public void createObservablesAndSubscribing() {
        final String expected =  "Emitting new Stuff !!";
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter emitter) {

                emitter.onNext(expected);
            }
        });

        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(String o) {

                try {
                    Assert.assertThat(expected, not(o));
                }catch (Exception e){
                    onError(e);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
