package io.chengguo.rxjava.assist;

import org.junit.Test;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.schedulers.Timestamped;

/**
 * 给Observable发射的数据项附加一个时间戳
 */
public class Timestamp {
    @Test
    public void timestamp() {
        Observable
                .just(1, 2, 3)
                .timestamp()
                .subscribe(SubscriberPinter.<Timestamped<Integer>>create());
    }
}