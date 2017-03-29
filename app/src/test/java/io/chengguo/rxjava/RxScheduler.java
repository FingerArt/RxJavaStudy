package io.chengguo.rxjava;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
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
}
