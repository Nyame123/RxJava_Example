package com.bisapp.rxjavaexamples;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testObservableJoin();
        //noEmissionExample();
    }

    public void testObservableJoin() {

        Observable<Long> firstObservable = Observable.interval(0, 2, TimeUnit.SECONDS).take(3);
        Observable<Long> secondObservable = Observable.interval(2, 1, TimeUnit.SECONDS).take(3);
        firstObservable.join(secondObservable, new Function<Long, Observable<Long>>() {
            @Override
            public Observable<Long> apply(Long aLong) throws Exception {
                return Observable.timer(2, TimeUnit.SECONDS);
            }
        }, new Function<Long, Observable<Long>>() {
            @Override
            public Observable<Long> apply(Long bLong) throws Exception {
                return Observable.timer(1, TimeUnit.SECONDS);
            }
        }, new BiFunction<Long, Long, String>() {
            @Override
            public String apply(Long aLong, Long tRight) throws Exception {
                return aLong + " null " + tRight;
            }
        }).subscribe(v->{
            Log.d("Rx Java", String.valueOf(v));
        });

    }


    private void observableFromFilter() {

        Observable.fromArray(1, 1,2,4,5,4,5,4,3,5)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(v -> {
                    Log.d("Rx Java", String.valueOf(v));

                });

    }
private void observableFromDistinct() {

        Observable.fromArray(1, 1,2,4,5,4,5,4,3,5)
                .distinct()
                .subscribe(v -> {
                    Log.d("Rx Java", String.valueOf(v));

                });

    }

    private void observableFromDebounce() {

        Observable.range(1, 1200)
                .delay(8,TimeUnit.SECONDS)
                .throttleLast(1, TimeUnit.MILLISECONDS)
                .subscribe(v -> {
                    Log.d("Rx Java", String.valueOf(v));

                });

    }

    private void observableFromWindow() {

        Observable.range(1, 10)
                .window(2, TimeUnit.SECONDS)
                .subscribe(v -> {
                    v.subscribe(v1 -> {
                        Log.d("Rx Java", String.valueOf(v1));
                    });

                });

    }

    private void observableFromScan() {

        Observable.range(1, 10)
                .scan("", new BiFunction<String, Integer, String>() {
                    @Override
                    public String apply(String s, Integer integer)
                            throws Exception {
                        return s + "" + integer;
                    }
                })
                .subscribe(v -> {
                    Log.d("Rx Java", String.valueOf(v));
                });

        //Rx Java: 1
        //Rx Java: 12
        //Rx Java: 123
        //Rx Java: 1234
        //Rx Java: 12345
        //Rx Java: 123456
        //Rx Java: 1234567
        //Rx Java: 12345678
        //Rx Java: 123456789
        //Rx Java: 12345678910

        //Checking from the results, we are getting the
        // previous result in the
        // subsequent result but we are using
        // String concatenation in the mapping function.
    }

    private void observableFromMap() {
        //This will emit value =>
        // Rx Java: 1
        // Rx Java: 2
        // Rx Java: 2
        // Rx Java: 1
        // Rx Java: 3
        Observable<String> mySource = Observable.just("a", "bb", "cc", "d", "eee");
        mySource.map(new Function<String, Object>() {
            @Override
            public Object apply(String s) throws Exception {
                return s.length();
            }
        }).subscribe(v -> {
            Log.d("Rx Java", String.valueOf(v));
        });
    }

    private void observableFromGroupBy() {
        //This will emit value =>
        // Rx Java: [a, d]
        // Rx Java: [bb, cc]
        // Rx Java: [eee]
        Observable<String> mySource = Observable.just("a", "bb", "cc", "d", "eee");
        mySource.groupBy(s -> s.length())
                .flatMapSingle(new Function<GroupedObservable<Integer, String>, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(GroupedObservable<Integer,
                            String> integerStringGroupedObservable) throws Exception {
                        return integerStringGroupedObservable.toList();
                    }
                })
                .subscribe(v -> {
                    Log.d("Rx Java", String.valueOf(v));
                });
    }

    private void observableFromFlatMap() {
        //This will emit value => Item from + emitted value from interval
        Observable.interval(1, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Long aLong) throws Exception {
                        String item = "Item from " + aLong;
                        return Observable.just(item);
                    }
                })
                .subscribe(v -> {
                    Log.d("Rx Java", String.valueOf(v));
                });
    }

    private void observableFromBuffer() {
        //This will emit array of 8 items at a time
        Observable.interval(1, TimeUnit.SECONDS)
                .buffer(3, TimeUnit.SECONDS)
                .subscribe(v -> {
                    Log.d("Rx Java", String.valueOf(v));
                });
    }

    private void observableFromTimer() {
        //This will emit 0 after a delay of 2 seconds
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(v -> {
                    Log.d("Rx Java", String.valueOf(v));
                });
    }

    private void observableFromRepeat() {
        long start = System.currentTimeMillis();
        Observable.just(2, 9)
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        return objectObservable.delay(8, TimeUnit.SECONDS);
                    }
                })
                .subscribe(v -> {
                    Log.d("Rx Java", String.valueOf(v));
                });
    }

    private void observableFromRange() {
        Observable.range(2, 9)
                .subscribe(v -> {
                    Log.d("Rx Java", String.valueOf(v) + Thread.currentThread());
                });
    }

    private void observableFromInterval() {
        Observable.interval(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
                .subscribe(v -> {
                    Log.d("Rx Java", String.valueOf(v) + Thread.currentThread());
                });
    }

    private void observableFrom() {
        //Creating Observable from Array of items
        String[] items = {"First", "Second", "Third"};
        Observable.fromArray(items).
                subscribe(value -> {
                    Log.d("Rx Java Array", value);
                    //we get First, Second, Third
                });

        Observable.fromCallable(() -> {
            int sum = 3 + 5;
            return String.valueOf(sum);
        }).subscribe(value -> {
            Log.d("Rx Java Callable", value);
            //we get First, Second, Third
        });

        FutureTask futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int sum = 3 + 5;
                return String.valueOf(sum);
            }
        });

        Observable.fromFuture(futureTask, Schedulers.io()).doOnSubscribe(disposable -> {
            futureTask.run();
        }).subscribe(value -> {
            Log.d("Rx Java Future", (String) value);
        }, err -> {
            Log.d("Rx Java Future", err.toString());
        });
    }

    private void noEmissionExample() {
        //Creating Observable with empty
        Observable.empty()
                .subscribe(value -> {
                    Log.d("Rx Java Future", (String) value);
                });

        //Creating Observable with Error
        Observable.error(new Throwable("Error happened!!!") {

        }).subscribe(value -> {

        }, error -> {
            Log.d("Rx Java Error", error.getLocalizedMessage());
        });

        //creating observable with never
        Observable.never().subscribe(value -> {
            Log.d("Rx Java Future", (String) value);
        });
    }

    private void deferExample() {
        Book newBook = new Book();
        Observable<String> justObservable = newBook.justObservable();
        Observable<String> deferObservable = newBook.deferObservable();
        newBook.setPage("Second Page");

        justObservable.subscribe(s -> {
            //This will print the default value which is First Page
            //Since the just create the observable even before the subscription
            Log.d("RxJava Just", s);
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