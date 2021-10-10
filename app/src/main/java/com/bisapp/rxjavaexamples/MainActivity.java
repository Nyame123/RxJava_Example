 package com.bisapp.rxjavaexamples;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

import static android.speech.tts.TextToSpeech.SUCCESS;


public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //groupJoinOperator();

        Observable
                .zip(Observable.fromIterable(Arrays.asList(1, 2, 3, 4, 5, 6)),Observable.interval(5, TimeUnit.SECONDS),new BiFunction<Integer, Long, Object>() {

                    @Override
                    public Object apply( Integer integer,  Long aLong) throws Exception {
                        return integer;
                    }
                })
                .subscribe(
                        v -> {
                            Log.d(LOG_TAG,"The next result => " + v);
                        }
                );


    }

    private void joinOperator(){

        Observable<Long> firstObservable = Observable.interval(2,TimeUnit.SECONDS).take(10)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(LOG_TAG,"First Observable "+aLong);
                    }
                });
        Observable<Long> seconObservable = Observable.interval(1,TimeUnit.SECONDS).take(7)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(LOG_TAG,"Second Observable "+aLong);
                    }
                });

        Observable<Long> firstWindow = Observable.timer(2,TimeUnit.SECONDS);
        Observable<Long> secondWindow = Observable.timer(1,TimeUnit.SECONDS);

       Disposable disposable = firstObservable.join(seconObservable, new Function<Long, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(@NonNull Long aLong) throws Exception {
                return firstWindow;
            }
        }, new Function<Long, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(@NonNull Long aLong) throws Exception {
                return secondWindow;
            }
        }, new BiFunction<Long, Long, String>() {
            @NonNull
            @Override
            public String apply(@NonNull Long aLong, @NonNull Long tRight) throws Exception {
                return String.format("First Item %d Second Item %d",aLong,tRight);
            }
        }).subscribe(v->{
            Log.d(LOG_TAG,"Results = "+v);
        });

    }


    private void groupJoinOperator(){
        Observable<Long> firstObs = Observable.interval(2,TimeUnit.SECONDS).take(7)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(LOG_TAG, "On Next of First Observable "+aLong);
                    }
                });
        Observable<Long> secondObs = Observable.interval(1,TimeUnit.SECONDS).take(10)
                .doOnNext(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(LOG_TAG, "On Next of Second Observable "+aLong);
            }
        });;
        Observable<Long> firstWindow = Observable.timer(2,TimeUnit.SECONDS);
        Observable<Long> secondWindow = Observable.timer(1,TimeUnit.SECONDS);

        Disposable disposable = firstObs.groupJoin(secondObs, new Function<Long, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(@NonNull Long aLong) throws Exception {
                return firstWindow;
            }
        }, new Function<Long, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(@NonNull Long aLong) throws Exception {
                return secondWindow;
            }
        }, new BiFunction<Long, Observable<Long>, Observable<String>>() {
            @NonNull
            @Override
            public Observable<String> apply(@NonNull Long aLong, @NonNull Observable<Long> tRight) throws Exception {
                return  tRight.map(new Function<Long, String>() {
                    @Override
                    public String apply(@NonNull Long bLong) throws Exception {
                        return String.format("Source1 %d and Source2 %d",aLong,bLong);
                    }
                });

            }
        }).concatMap(new Function<Observable<String>, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Observable<String> stringObservable) throws Exception {
                return stringObservable;
            }
        }).subscribe(v->{
            Log.d(LOG_TAG, v);
        });
    }
























    private static void switchOperator1(){
        Observable<Integer> parentObservableSource =  Observable.range(1,9)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer aLong) throws Exception {
                        Log.d(LOG_TAG,"Emitted by OuterSource "+ aLong);
                    }
                });

        ObservableSource<Integer> childObservable = Observable.range(1,12)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(LOG_TAG,"Subscription started by Child Source ");
                    }
                });

        Disposable disposable =  Observable.switchOnNext(parentObservableSource.map(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Integer aLong) throws Exception {
                return childObservable;
            }
        })).subscribe(v->{
            Log.d(LOG_TAG,"Relayed item "+ v);
        });
    }

    private static void switchOperator(){
        Observable<Long> parentObservableSource =  Observable.interval(3, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(LOG_TAG,"Emitted by OuterSource "+ aLong);
                    }
                });

       ObservableSource<Long> childObservable = Observable.interval(1,TimeUnit.SECONDS)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(LOG_TAG,"Subscription started by Child Source ");
                    }
                });

      Disposable disposable =  Observable.switchOnNext(parentObservableSource.map(new Function<Long, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Long aLong) throws Exception {
                return childObservable;
            }
        })).subscribe(v->{
            Log.d(LOG_TAG,"Relayed item "+ v);
        });
    }

















    private void launchApplicationFrom(){
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.surveycto.collect.android");
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.surveycto.collect.android"));
            startActivity(intent);
        }
    }

    private static void mergeOperator2(){

         Observable<String> observableFirst = Observable.interval(1,TimeUnit.SECONDS)
                 .take(8)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(@NonNull Long aLong) throws Exception {
                        return String.format("Observable 1 ---%d",aLong);
                    }
                });
        Observable<String> observableSecond = Observable.interval(3,TimeUnit.SECONDS)
                .take(8)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(@NonNull Long aLong) throws Exception {
                        return String.format("Observable 2 ---%d",aLong);
                    }
                });
        Disposable disposable = Observable.mergeArray(observableFirst,observableSecond,observableSecond,observableSecond,observableSecond)
                .subscribe(result->{
                    Log.d("Merge Operator",String.valueOf(result));
                });
    }

    private static void mergeOperator(){
        final String[] listFirst = {"A1", "A2", "A3", "A4","A5"};
        final String[] listSecond = {"B1", "B2", "B3"};

        final Observable<String> observableFirst = Observable.fromArray(listFirst);
        final Observable<String> observableSecond = Observable.fromArray(listSecond);
       Disposable disposable = Observable.merge(observableFirst,observableSecond)
               .subscribe(result->{
                   Log.d("Merge Operator",String.valueOf(result));
               });
    }


















    private static void combineLatestOperator(){

        //Observable<Long> numbers = Observable.interval(1000,TimeUnit.MILLISECONDS).take(3);
        Observable<Long> firstObservable = Observable.interval(1000,TimeUnit.MILLISECONDS).take(3);
        Observable<Integer> secondObservable = Observable.fromArray(32,43,4,65,67,76,43).zipWith(Observable.interval(1, TimeUnit.SECONDS),
                new BiFunction<Integer, Long, Integer>() {
                    @NonNull
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Long aLong) throws Exception {
                        return integer;
                    }
                });
       Disposable disposable = Observable.combineLatest(firstObservable, secondObservable, new BiFunction<Long, Integer, String>() {
            @NonNull
            @Override
            public String apply(@NonNull Long integer, @NonNull Integer integer2) throws Exception {
                return integer +" - "+ integer2;
            }
        }).subscribe(result->{
            Log.d("CombineLatest Operator",String.valueOf(result));
        });
    }


























    private void textToSpeech() {
        TextToSpeech textToSpeech = null;
        TextToSpeech finalTextToSpeech = textToSpeech;
        TextToSpeech.OnInitListener listener = new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == SUCCESS){
                    int result = finalTextToSpeech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.d("Unsupported Text","No Language available");
                    }else{
                        finalTextToSpeech.speak("Unsupported Text",TextToSpeech.QUEUE_FLUSH,null);
                    }
                }else{
                    Log.d("Unsupported Text","Initialization Failed");
                }
            }
        };

        textToSpeech = new TextToSpeech(this,listener);


    }

    private static void zipOperator(){
        Observable<String> students = Observable.fromArray("Nat","Kelvin","Rockson","Cindy")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        Thread.sleep(2000);
                        //Log.d("Zip Operator",s+" == "+Thread.currentThread().getName());
                        return s;
                    }
                }).subscribeOn(Schedulers.newThread());
        Observable<Integer> scores = Observable.just(20,30,50,60)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        //Log.d("Zip Operator",integer+" == "+Thread.currentThread().getName());

                        return integer;
                    }
                });

        Disposable disposable = Observable.zip(students, scores, new BiFunction<String, Integer, String>() {
            @NonNull
            @Override
            public String apply(@NonNull String s, @NonNull Integer integer) throws Exception {
                //Log.d("Zip Operator","Final Result == "+Thread.currentThread().getName());
                return String.format("%s --- %d",s,integer);
            }
        }).subscribe(result->{
            Log.d("Zip Operator",String.valueOf(result));
        });

        disposable.dispose();
    }

    private static void zipOperator1(){

        Observable<Integer> scores = Observable.just(20,30,50,60)
                .subscribeOn(Schedulers.io());
        List<Observable<Integer>> observableList = Arrays.asList(scores,scores,scores,scores,
                scores,scores,scores,scores,scores,scores,scores,scores,scores,scores,scores,scores,scores,scores,scores);
       Disposable disposable = Observable.zipIterable(observableList, (Function<Object[], Object>) objects -> {

            StringBuilder stringBuilder = new StringBuilder();
            for (Object s: objects){
                stringBuilder.append(s.toString()).append(" ");
            }
            return stringBuilder.toString();
        }, true, 1).subscribe(result->{
            Log.d("Zip Operator",String.valueOf(result));
        });
    }



    private static void throttleFirstOperator(){
        Disposable disposable = Observable.range(0,13)
                .zipWith(Observable.interval(1,TimeUnit.SECONDS),(item,val)-> item)
                .throttleFirst(2,TimeUnit.SECONDS)
                .subscribe(result->{
                    Log.d("ThrottleFirst Operator",String.valueOf(result));
                });
    }


    private static void takeUntilOperator() {
        Disposable disposable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
                .zipWith(Observable.interval(1, TimeUnit.SECONDS), new BiFunction<Integer, Long, Integer>() {
                    @NonNull
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Long aLong) throws Exception {
                        return integer;
                    }
                })
                .takeUntil(Observable.timer(6,TimeUnit.SECONDS))
                .subscribe(result->{
                    Log.d("Sample Operator",String.valueOf(result));
                });
    }











    private static void skipUntilOperator() {
        Disposable disposable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
                .zipWith(Observable.interval(1, TimeUnit.SECONDS), new BiFunction<Integer, Long, Integer>() {
                    @NonNull
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Long aLong) throws Exception {
                        return integer;
                    }
                })
                .takeUntil(Observable.timer(6,TimeUnit.SECONDS))
                .subscribe(result->{
                    Log.d("Sample Operator",String.valueOf(result));
                });
    }


    private void sampleOperator(){
        int[] array = {12,13,4,6,7,8,2,5};
      Disposable disposable = Observable.range(0,array.length)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        return array[integer];
                    }
                }).zipWith(Observable.interval(2, TimeUnit.SECONDS), new BiFunction<Integer, Long, Integer>() {
                  @NonNull
                  @Override
                  public Integer apply(@NonNull Integer integer, @NonNull Long aLong) throws Exception {
                      return integer;
                  }
              })
              .sample(4,TimeUnit.SECONDS)
                .subscribe(result->{
                    Log.d("Sample Operator",String.valueOf(result));
                },err->{
                    Log.d("Sample Operator",err.getLocalizedMessage());
                },()->{
                    Log.d("Sample Operator","Complete");
                });

    }
























    private static void bufferOperator(){
        Observable.range(1,20)
                .buffer(7)
                .subscribe(value->{
                    System.out.println(value);
                },err->{
                    System.out.println(err);
                });
    }

    private static void flatMapOperator(){
        Flowable.range(1, 10)
                .concatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                )
                .blockingSubscribe(System.out::println);
    }

    private static void mapOperator(){
        Observable.range(1,10)
                .map(new Function<Integer, Double>() {
                    @Override
                    public Double apply(@NonNull Integer integer) throws Exception {
                        return Double.parseDouble(""+integer) / 2;
                    }
                }).blockingSubscribe(System.out::println);
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