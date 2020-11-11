package com.bisapp.rxjavaexamples;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class RxTest {

    public static void main(String[] args){
        testAsyncSubject();
    }

    private static void testBehaviorSubject(){
        BehaviorSubject behaviorSubject = BehaviorSubject.create();
        StringBuilder stringBuilder1 = new StringBuilder();
        behaviorSubject.subscribe(v->{
            stringBuilder1.append(v);
        });

        behaviorSubject.onNext("1");
        behaviorSubject.onNext("2");
        behaviorSubject.onNext("3");
        behaviorSubject.onNext("4");

        StringBuilder stringBuilder2 = new StringBuilder();
        behaviorSubject.subscribe(v->{
            stringBuilder2.append(v);
        });

        behaviorSubject.onNext("5");
        behaviorSubject.onNext("6");


        System.out.println(stringBuilder1.toString()); //123456
        System.out.println(stringBuilder2.toString());  //456

        //This is because one only gets the most recently emitted item plus items after subscription
    }

    private static void testAsyncSubject() {

        AsyncSubject asyncSubject = AsyncSubject.create();
        StringBuilder stringBuilder1 = new StringBuilder();
        asyncSubject.subscribe(v -> {
            stringBuilder1.append(v);
        });

        asyncSubject.onNext("1");
        asyncSubject.onNext("2");
        asyncSubject.onNext("3");
        asyncSubject.onNext("4");

        StringBuilder stringBuilder2 = new StringBuilder();
        asyncSubject.subscribe(v -> {
            stringBuilder2.append(v);
        });

        asyncSubject.onNext("5");
        asyncSubject.onNext("6");

        asyncSubject.onComplete();


        System.out.println(stringBuilder1.toString()); //6
        System.out.println(stringBuilder2.toString());  //6

        //This is because one only gets the last item emitted following onComplete()
    }

    private static void testPublishSubject() {
        PublishSubject publishSubject = PublishSubject.create();
        StringBuilder stringBuilder1 = new StringBuilder();
        publishSubject.subscribe(v -> {
            stringBuilder1.append(v);
        });

        publishSubject.onNext("1");
        publishSubject.onNext("2");
        publishSubject.onNext("3");
        publishSubject.onNext("4");

        StringBuilder stringBuilder2 = new StringBuilder();
        publishSubject.subscribe(v -> {
            stringBuilder2.append(v);
        });

        publishSubject.onNext("5");
        publishSubject.onNext("6");


        System.out.println(stringBuilder1.toString()); //123456
        System.out.println(stringBuilder2.toString());  //56

        //This is because one only gets emitted item only after subscription
    }

    private static void testReplaySubject() {
        ReplaySubject replaySubject = ReplaySubject.create();
        StringBuilder stringBuilder1 = new StringBuilder();
        replaySubject.subscribe(v -> {
            stringBuilder1.append(v);
        });

        replaySubject.onNext("1");
        replaySubject.onNext("2");
        replaySubject.onNext("3");
        replaySubject.onNext("4");

        StringBuilder stringBuilder2 = new StringBuilder();
        replaySubject.subscribe(v -> {
            stringBuilder2.append(v);
        });

        replaySubject.onNext("5");
        replaySubject.onNext("6");


        System.out.println(stringBuilder1.toString()); //123456
        System.out.println(stringBuilder2.toString());  //123456

        //This gives back what was missed before subscription
    }

    private static void testConnectableObservables(){
        String message = "I am coming so wait";
        ConnectableObservable<String> con = Observable.just(message).publish();
        con.subscribe(System.out::println);
        //message = "So we are going on";
        con.connect();
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
