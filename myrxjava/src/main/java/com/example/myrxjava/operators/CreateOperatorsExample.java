package com.example.myrxjava.operators;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class CreateOperatorsExample {

    public static int firstValue = 4;
    public static int secondValue = 3;

    public static void main(String[] args) {

        //1. Creators of Observables
        //a. Create
        //b. defer
        //c. range
        //d. just
        //e. interval
        //f. timer
        //e empty or error or never
        //g. repeat
        //h. from
        //2. Transformation of Observables
        //3. Filtering of Observables
        //4. Combination of Observables

        intervalOperator();
    }

    //using create operator in rxjava
    private static void createOperator() {
        Observable<Integer> createObservable = Observable.create(emitter -> emitter.onNext(summation(firstValue, secondValue)));
        createObservable.subscribe((value) -> {
            System.out.println("The last result => " + value);
        });
        firstValue = 7;
        secondValue = 5;

        createObservable.subscribe(v -> {
            System.out.println("The next result => " + v);
        });

    }

    private static void deferOperator() {
        Observable<Integer> deferObservable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(firstValue);
            }
        });

        deferObservable.subscribe(v -> {
            System.out.println("The first result => " + v);
        });

        firstValue = 10;
        deferObservable.subscribe(v -> {
            System.out.println("The next result => " + v);
        });
    }

    private static int summation(int a, int b) {
        return a + b;
    }

    private static void justOperator() {
        Observable<Integer> justObservable = Observable.just(firstValue);

        justObservable.subscribe(v -> {
            System.out.println("The just first result => " + v);
        }, error -> {

        });

        firstValue = 9;
        justObservable.subscribe(v -> {
            System.out.println("The just last result => " + v);
        }, error -> {

        });
    }

    private static void range() {
        Observable<Integer> rangeObservable = Observable.range(0, firstValue);

        rangeObservable.subscribe(v -> {
            System.out.println("The  first result => " + v);
        }, error -> {

        });

        firstValue = 10;
        rangeObservable.subscribe(v -> {
            System.out.println("The last result => " + v);
        }, error -> {

        });

    }

    private static void fromOperator() {
        Observable<Integer> rangeObservable = Observable.fromArray(3, 45, 56, 67, 45, 43, 45);

        rangeObservable.subscribe(v -> {
            System.out.println("The  first result => " + v);
        }, error -> {

        });


    }

    private static void intervalOperator() {
        Observable.interval(1, TimeUnit.SECONDS)
                .buffer(3, TimeUnit.SECONDS)
                .subscribe(v -> {
                    System.out.println("Rx Java " + String.valueOf(v));
                });
    }
}
