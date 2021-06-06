package com.example.myrxjava.operators;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

public class Transformers {

    public static void main(String[] args) {

        //buffer
        //flatMap
        //map
        //ConcatMap
        //groupBy
        //window
        //scan


        //I am going to school
        System.out.println("Window Operator");
        windowOperator();

        System.out.println("Buffer Operator");
        bufferOperator();
    }

    private static void bufferOperator() {
        Observable.range(1, 20)
                .buffer(3)
                .blockingSubscribe(System.out::println);
    }

    private static void windowOperator(){
        Observable.range(1,20)
                .window(3)
                .flatMap(new Function<Observable<Integer>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Observable<Integer> integerObservable) throws Exception {
                        return integerObservable.toList().toObservable();
                    }
                })
                .blockingSubscribe(System.out::println);
    }

    private static void flatMapOperator() {
        Observable.range(1, 10)
                .flatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Integer integer) throws Exception {
                        return Observable.just(integer)
                                .map(new Function<Integer, Integer>() {
                                    @Override
                                    public Integer apply(@NonNull Integer integer) throws Exception {
                                        return integer * 3;
                                    }
                                }).subscribeOn(Schedulers.io());
                    }
                }).blockingSubscribe(System.out::println);
    }

    private static void concatMapOperator() {
        Observable.range(1, 10)
                .concatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Integer integer) throws Exception {
                        return Observable.just(integer)
                                .map(new Function<Integer, Integer>() {
                                    @Override
                                    public Integer apply(@NonNull Integer integer) throws Exception {
                                        return integer * 3;
                                    }
                                }).subscribeOn(Schedulers.io());
                    }
                }).blockingSubscribe(System.out::println);
    }

    private static void mapOperator() {
        Observable.range(1, 10)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        return integer * 3;
                    }
                }).subscribeOn(Schedulers.io())
                .blockingSubscribe(System.out::println);

    }

    private static void groupByOperator() {
        Observable.range(1, 20)
                .groupBy(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                }).flatMap(new Function<GroupedObservable<Boolean, Integer>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull GroupedObservable<Boolean, Integer> booleanIntegerGroupedObservable) throws Exception {
                return booleanIntegerGroupedObservable.toList().toObservable();
            }
        }).blockingSubscribe(System.out::println);
    }

    private static void scanOperator(){
        //1,2,3,4,5,6,7,8,9,10
        Observable.range(1,10)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @NonNull
                    @Override
                    public Integer apply(@NonNull Integer prev, @NonNull Integer current) throws Exception {
                        return prev + current;
                    }
                }).blockingSubscribe(System.out::println);
    }




}
