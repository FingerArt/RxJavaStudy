package io.chengguo.rxjava;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by fingerart on 17/3/30.
 */
public class RxScheduler {

    @Test
    public void scheduler() throws Exception {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("OnSubscribe: " + Thread.currentThread().getName());
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        })//被观察者
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Subscriber: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });//观察者
    }

    @Test
    public void transf() throws Exception {
        Observable.just("hello")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " world";
                    }
                })//map 将 func转换为OnSubscribe
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    @Test
    public void transfFlatMap() {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        ArrayList<String> group1 = new ArrayList<>();
        group1.add("group-1-1");
        group1.add("group-1-2");
        group1.add("group-1-3");
        group1.add("group-1-4");
        arrayLists.add(group1);
        ArrayList<String> group2 = new ArrayList<>();
        group2.add("group-2-1");
        group2.add("group-2-2");
        group2.add("group-2-3");
        group2.add("group-2-4");
        arrayLists.add(group2);

        Observable.from(arrayLists).flatMap(new Func1<ArrayList<String>, Observable<?>>() {
            @Override
            public Observable<String> call(ArrayList<String> group) {
                return Observable.from(group);
            }
        }).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });
    }


    @Test
    public void single() {
        Observable.just("hello", "world").single(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return s.equalsIgnoreCase("hello");
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = [" + s + "]");
            }
        });
    }

    @Test
    public void defer() {
        Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return null;
            }
        }).subscribe();
    }

    @Test
    public void intervel() {
        Observable.interval(1, TimeUnit.SECONDS, Schedulers.immediate()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("aLong = [" + aLong + "]");
            }
        });
    }
}
