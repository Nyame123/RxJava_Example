package com.bisapp.rxjavaexamples;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deferExample();
    }

    private void deferExample(){
        Book newBook = new Book();
        Observable<String> justObservable = newBook.justObservable();
        Observable<String> deferObservable = newBook.deferObservable();
        newBook.setPage("Second Page");

        justObservable.subscribe(s -> {
            //This will print the default value which is First Page
            //Since the just create the observable even before the subscription
            Log.d("RxJava Just",s);
        });
        deferObservable.subscribe(s -> {
            //This will print the default value which is Second Page
            //Since the defer wrapper wait till subscription before creating the observable
            Log.d("RxJava Defer",s);
        });;
    }

    private void createObservablesAndSubscribing() {

        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter emitter) {

                emitter.onNext("Emitting new Stuff !!");
                emitter.onComplete();
            }
        });

        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(String o) {

                Log.d("RxJava Create", o);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("RxJava Create","Observable consumed");

            }
        });
    }
}