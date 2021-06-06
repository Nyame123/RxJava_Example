package com.example.myrxjava.operators;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class Filtering {
    public static void main(String[] args) {
        //sampleOperator();

        //take
        //takeLast
        //takeWhile
        //takeUntil

        throttleFirstOperator();

    }

    private static void throttleFirstOperator(){
        Disposable disposable = Observable.range(0,12)
                .throttleFirst(2,TimeUnit.SECONDS)
                .subscribe(System.out::println);
    }

    private static void takeWhileOperator(){
        Disposable disposable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
                .takeWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer != 4;
                    }
                })
                .subscribe(System.out::println);

    }

    private static void takeLastOperator(){
        Disposable disposable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
                .takeLast(5)
                .subscribe(System.out::println);

    }


    private static void takeOperator(){
       Disposable disposable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
               .take(5)
                .subscribe(System.out::println);

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
                .skipUntil(Observable.timer(3,TimeUnit.SECONDS))
                .subscribe(System.out::println);
    }


    private static void skipWhileOperator() {
        Disposable disposable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
                .skipWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer != 4;
                    }
                })
                .subscribe(System.out::println);
    }


    private static void skipLastOperator() {
        Disposable disposable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
                .skipLast(4)
                .subscribe(System.out::println);
    }

    private static void skipOperator(){
        Disposable disposable = Observable.fromArray(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19)
                .skip(4)
                .subscribe(System.out::println);
    }


    private static void filteringOperator(){
       Disposable disposable = Observable.fromArray(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer % 2 == 1;
                    }
                }).subscribe(System.out::println);
    }

    private static void filteringOperatorWithObjects(){
        Gender[] genders = {
                new Gender("Ernest",true),
                new Gender("Anin",true),
                new Gender("Roselina",false),
                new Gender("Alex",true),
                new Gender("Ernestina",false),
                new Gender("Justina",false),
                new Gender("Felix",true),
                new Gender("Kishore",true),
                new Gender("Swathi",false),
                new Gender("Gideon",true),
        };

       Disposable disposable = Observable.range(0,genders.length)
                .map(new Function<Integer, Gender>() {
                    @Override
                    public Gender apply(@NonNull Integer integer) throws Exception {
                        return genders[integer];
                    }
                })
                .filter(new Predicate<Gender>() {
                    @Override
                    public boolean test(@NonNull Gender gender) throws Exception {
                        return !gender.isMale;
                    }
                }).subscribe(System.out::println);
    }

    private static void sampleOperator(){
        Observable.fromArray(1,2,4,5,6,6,7,9,8,23,64,23,56,3432,123)
                .zipWith(Observable.interval(2, TimeUnit.SECONDS), new BiFunction<Integer, Long, Integer>() {
                    @NonNull
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Long aLong) throws Exception {
                        return integer;
                    }
                })
                .sample(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(System.out::println);
    }

    private static void distinctOperator() {

        Scores[] scores = {
                new Scores("Ernest",90),
                new Scores("Felix",99),
                new Scores("Bismark",50),
                new Scores("Anin",90),
                new Scores("Gideon",80),
                new Scores("Ernest",100),
                new Scores("Anin",100)
        };

       Disposable disposable = Observable.range(0,scores.length)
                .map(new Function<Integer, Scores>() {
                    @Override
                    public Scores apply(@NonNull Integer integer) throws Exception {
                        return scores[integer];
                    }
                })
                .distinct(new Function<Scores, String>() {
                    @Override
                    public String apply(@NonNull Scores scores) throws Exception {
                        return scores.getName();
                    }
                })
                .subscribe(v->{
                    System.out.println(v);
                });
    }

    private static void distinctUntilChangedOperator() {

        Scores[] scores = {
                new Scores("Ernest",90),
                new Scores("Ernest",100),
                new Scores("Felix",99),
                new Scores("Bismark",50),
                new Scores("Anin",90),
                new Scores("Anin",100),
                new Scores("Gideon",80),

        };

        Disposable disposable = Observable.range(0,scores.length)
                .map(new Function<Integer, Scores>() {
                    @Override
                    public Scores apply(@NonNull Integer integer) throws Exception {
                        return scores[integer];
                    }
                })
                .distinctUntilChanged(new Function<Scores, String>() {
                    @Override
                    public String apply(@NonNull Scores scores) throws Exception {
                        return scores.getName();
                    }
                })
                .subscribe(v->{
                    System.out.println(v);
                });
    }

    private static class Gender{
        private String name;
        private boolean isMale;

        public Gender(String name, boolean isMale) {
            this.name = name;
            this.isMale = isMale;
        }

        public String getName() {
            return name;
        }

        public boolean isMale() {
            return isMale;
        }

        @Override
        public String toString() {
            return "Gender{" +
                    "name='" + name + '\'' +
                    ", isMale=" + isMale +
                    '}';
        }
    }


    private static class Scores{
        private String name;
        private int score;

        public Scores(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Scores{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
