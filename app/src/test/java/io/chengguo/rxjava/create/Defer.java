package io.chengguo.rxjava.create;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;

/**
 * 直到有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable
 */
public class Defer {
    @Test
    public void defer() throws InterruptedException {
        Observable<Integer> defer = Observable
                .defer(new Func0<Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call() {
                        return Observable.just(1, 2, 3, 4, 5, 6);
                    }
                });

        Thread.sleep(1000);

        defer.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("subscribe = [" + integer + "]");
            }
        });
    }
}
