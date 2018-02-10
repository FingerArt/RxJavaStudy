package io.chengguo.rxjava.assist;

import org.junit.Test;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * 指定Observable自身在哪个调度器上执行
 */
public class SubscribeOn {
    @Test
    public void subscribeOn() throws InterruptedException {
        Observable
                .just(1, 2, 3)
                .subscribeOn(Schedulers.computation())
                .subscribe(SubscriberPinter.<Integer>create());

        Thread.sleep(1000);
    }
}
