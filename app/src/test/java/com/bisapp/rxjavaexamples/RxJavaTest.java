package com.bisapp.rxjavaexamples;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.core.IsNot.not;

public class RxJavaTest {

    @Test
    public void testObservableTimer() {
        Observable
                .range(0, 10)
                .window(2,TimeUnit.SECONDS)
                .subscribe(v->{
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
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int sum = 3 + 5;
                return String.valueOf(sum);
            }
        });

        TestObserver<String> testSubscriber = new TestObserver();
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
