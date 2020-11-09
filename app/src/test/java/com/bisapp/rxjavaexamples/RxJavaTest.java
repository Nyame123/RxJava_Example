package com.bisapp.rxjavaexamples;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

public class RxJavaTest {

    @Test
    public void testObservableJoin() {

        Observable<Long> firstObservable = Observable.interval(0, 2, TimeUnit.SECONDS).take(3);
        Observable<Long> secondObservable = Observable.interval(6, 1, TimeUnit.SECONDS).take(3);
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
        }).subscribe(System.out::println);

    }

    @Test
    public void testObservableMerge() {

        TestObserver<String> testSubscriber = new TestObserver<>();

        Observable.merge(
                Observable.fromArray(new String[]{"I", "am"}),
                Observable.fromArray(new String[]{"trying to learn", "RxJava"})
        ).subscribe(testSubscriber);

        testSubscriber.assertValues("I", "am", "trying to learn", "RxJava");

    }

    @Test
    public void testObservableStartWith() {

        TestObserver<String> testSubscriber = new TestObserver<>();
        Observable<String> first = Observable.fromArray(new String[]{"I", "am"});
       Observable<String> second = Observable.fromArray(new String[]{"trying to learn", "RxJava"});

        first.startWith(second).subscribe(testSubscriber);

        testSubscriber.assertValues( "trying to learn", "RxJava","I", "am");

    }

    @Test
    public void testObservableMergeWithDelayError() {

        TestObserver<String> testSubscriber = new TestObserver<>();

        Observable.mergeDelayError(
                Observable.fromArray(new String[]{"hello", "world"}),
                Observable.error(new RuntimeException("Some exception")),
                Observable.fromArray(new String[]{"rxjava"})
        ).subscribe(testSubscriber);

        testSubscriber.assertValues("hello", "world", "rxjava");
        testSubscriber.assertError(RuntimeException.class);

    }

    @Test
    public void testObservableZip() {

        TestObserver<String> testSubscriber = new TestObserver<>();
        List<String> zippedStrings = new ArrayList<>();

        Observable.zip(
                Observable.fromArray(new String[]{"Simple", "Moderate", "Complex"}),
                Observable.fromArray(new String[]{"Solutions", "Success", "Hierarchy"}),
                (str1, str2) -> str1 + " " + str2).subscribe(zippedStrings::add);

        Assert.assertThat(zippedStrings.size(), is(3));
        //Assert.assertThat(zippedStrings,containsString("Simple Solutions", "Moderate Success", "Complex Hierarchy"));

    }

    @Test
    public void testObservableCombineLatest() {

        Observable<Integer> integerObservable = Observable.just(1, 3, 5, 6);
        Observable<String> stringObservable = Observable.just("a", "r", "ysd");
        Observable.combineLatest(stringObservable, integerObservable, new BiFunction<String, Integer, Object>() {
            @Override
            public Object apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).withLatestFrom(stringObservable, new BiFunction<Object, String, Object>() {
            @Override
            public Object apply(Object o, String s) throws Exception {
                return o + s;
            }
        }).subscribe(System.out::println);

    }

    @Test
    public void testObservableTakeLast() {

        Observable.range(1, 12)
                .takeLast(3)
                .subscribe(System.out::println);

    }

    @Test
    public void testObservableTake() {

        Observable.range(1, 12)
                .take(3)
                .subscribe(System.out::println);

    }

    @Test
    public void testObservableSkipLast() {

        Observable.range(1, 12)
                .skipLast(7)
                .subscribe(System.out::println);

    }

    @Test
    public void testObservableSkip() {

        Observable.range(1, 12)
                .skip(7)
                .subscribe(System.out::println);

    }

    @Test
    public void testObservableSample() {

        Observable.range(1, 900)
                .sample(1, TimeUnit.MICROSECONDS)
                .subscribe(System.out::println);

    }

    @Test
    public void testObservableLast() {

        Observable.range(1, 10)
                .last(3)
                .subscribe(System.out::println);

    }

    @Test
    public void testObservableIgnoreElement() {

        Observable.range(1, 10)
                .ignoreElements()
                .subscribe();

    }

    @Test
    public void testObservableFirst() {

        Observable.range(1, 10)
                .first(2)
                .subscribe(v -> {
                    System.out.println(v);
                });

    }

    @Test
    public void testObservableFilter() {

        Observable.range(1, 10)
                .filter(integer -> integer % 3 == 0)
                .subscribe(v -> {
                    System.out.println(v);
                });

    }

    @Test
    public void testObservableElementAt() {

        Observable.fromArray(1, 1, 2, 4, 5, 4, 5, 4, 3, 5)
                .elementAt(4)
                .subscribe(v -> {
                    System.out.println(v);
                });

    }

    @Test
    public void testObservableDistinct() {

        Observable.fromArray(1, 1, 2, 4, 5, 4, 5, 4, 3, 5)
                .distinct()
                .subscribe(v -> {
                    System.out.println(v);
                });

    }

    @Test
    public void testObservableDebounce() {

        Observable.range(1, 10)
                .debounce(3, TimeUnit.MICROSECONDS)
                .subscribe(v -> {
                    System.out.println(v);
                });

    }

    @Test
    public void testObservableWindow() {
        Observable
                .range(0, 10)
                .window(2, TimeUnit.SECONDS)
                .subscribe(v -> {
                    v.subscribe(System.out::print);
                });

    }

    @Test
    public void testObservableRange() {
        // This will terminate, and emit values from 2
        // with a 9 number of items
        Observable.range(2, 9)
                .test()
                .assertValueCount(9);

    }

    @Test
    public void testObservableJust() {
        // This will terminate, it will emit just 2.
        Observable.just(2)
                .test()
                .assertValue(v -> {
                    return v.equals(2);
                });

    }

    @Test
    public void testObservableInterval() {
        // This will not terminate, it will emit values from 0 to infinity
        Observable.interval(2, TimeUnit.SECONDS)
                .test()
                .assertNotTerminated();

    }

    @Test
    public void testObservableFromArray() {
        String[] items = {"First", "Second", "Third"};
        Observable.fromArray(items).test()
                .assertComplete()
                .assertValueCount(items.length)
                .assertTerminated();

        // This will be printing First, Second, Third in this order
    }

    @Test
    public void testObservableFromCallable() {
        //this creates observable from callable object
        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int sum = 3 + 5;
                return String.valueOf(sum);
            }
        }).test()
                .assertTerminated()
                .assertComplete()
                .assertValue(value -> {
                    return value.equalsIgnoreCase("8");
                });
    }

    @Test
    public void testObservableFromFuture() {
        //this creates observable from Future Task object
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int sum = 3 + 5;
                return String.valueOf(sum);
            }
        });

        TestObserver<String> testSubscriber = new TestObserver<>();
        TestScheduler testScheduler = new TestScheduler();
        Observable.fromFuture(futureTask, 5, TimeUnit.SECONDS, testScheduler).doOnSubscribe(disposable -> {
            futureTask.run();
            //don't forget to call the run() on
            // futureTask lest the test will hang since the task has not started
        }).subscribe(testSubscriber);

        testScheduler.triggerActions();

        testSubscriber
                .assertValue(value -> {
                    return value.equalsIgnoreCase("8");
                })
                .assertComplete();
    }


    @Test
    public void testNoEmission() {
        Observable.empty().subscribe(value -> {
            Assert.assertNull(value);
        });

    }

    @Test
    public void testErrorOccurred() {
        //Creating Observable with Error
        Throwable throwable = new Throwable("Error happened!!!");
        Observable.error(throwable).test().assertError(throwable);
    }

    @Test
    public void testSubscriptionNotTerminating() {
        Observable.never().test().assertNotTerminated();
    }

    @Test
    public void createObservablesAndSubscribing() {
        final String expected = "Emitting new Stuff !!";
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
