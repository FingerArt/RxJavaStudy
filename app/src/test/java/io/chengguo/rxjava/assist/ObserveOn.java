package io.chengguo.rxjava.assist;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 指定一个观察者在哪个调度器上观察这个Observable
 */
public class ObserveOn {
    @Test
    public void observeOn() {
        Observable
                .just(1, 2)
                .observeOn(Schedulers.immediate())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("Thread: " + Thread.currentThread().getName());
                    }
                });
    }
}
