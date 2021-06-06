package com.example.myrxjava.operators;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CombiningOperators {

    public static void main(String[] args) {
        switchOperator();
    }

    private static void switchOperator(){
      Observable<Long> integerObservableSource =  Observable.interval(3, TimeUnit.SECONDS)
              .doOnNext(new Consumer<Long>() {
                  @Override
                  public void accept(Long aLong) throws Exception {
                      System.out.println("Emitted by OuterSource "+ aLong);
                  }
              });


      Disposable disposable =  Observable.switchOnNext(integerObservableSource.map(new Function<Long, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Long aLong) throws Exception {
                return Observable.interval(1,TimeUnit.SECONDS)
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                System.out.println("Inner Source subscribed ");
                            }
                        });
            }
        })).doOnNext(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("Relayed items "+o);
            }
        }).subscribe();
    }

    private static void mergeOperator(){
        final String[] listFirst = {"A1", "A2", "A3", "A4"};
        final String[] listSecond = {"B1", "B2", "B3"};

        final Observable<String> observableFirst = Observable.fromArray(listFirst);
        final Observable<String> observableSecond = Observable.fromArray(listSecond);
        Observable.merge(observableFirst,observableSecond)
                .subscribe(System.out::println);
    }

    private static void combineLatestOperator(){

        Observable<Integer> numbers = Observable.range(1,5).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(@NonNull Integer integer) throws Exception {
                Thread.sleep(3000);
                return integer;
            }
        }).subscribeOn(Schedulers.newThread());
        Observable<Integer> c = Observable.range(6,5);
        Observable.combineLatest(c, numbers, new BiFunction<Integer, Integer, String>() {
            @NonNull
            @Override
            public String apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                return integer +" - "+ integer2;
            }
        }).subscribe(System.out::println);
    }

    private static void zipOperator() {
        Observable<String> students = Observable.fromArray("Nat","Kelvin","Rockson","Cindy")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        Thread.sleep(2000);
                        System.out.println(s+" == "+Thread.currentThread().getName());
                        return s;
                    }
                }).subscribeOn(Schedulers.newThread());
        Observable<Integer> scores = Observable.just(20,30,50,60)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        System.out.println(integer+" == "+Thread.currentThread().getName());

                        return integer;
                    }
                });

      Disposable disposable = Observable.zip(students, scores, new BiFunction<String, Integer, String>() {
            @NonNull
            @Override
            public String apply(@NonNull String s, @NonNull Integer integer) throws Exception {
                System.out.println("Final Result == "+Thread.currentThread().getName());
                return String.format("%s --- %d",s,integer);
            }
        }).subscribe(System.out::println);

    }


}
