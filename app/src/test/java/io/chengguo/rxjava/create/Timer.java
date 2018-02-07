package io.chengguo.rxjava.create;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * 创建一个Observable，它在一个给定的延迟后发射一个特殊的值。
 */
public class Timer {

    /**
     * Timer操作符创建一个在给定的时间段之后返回一个特殊值的Observable。
     *
     * @throws InterruptedException
     */
    @Test
    public void timer() throws InterruptedException {
        Observable
                .timer(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("subscribe = [" + aLong + "]");
                    }
                });

        Thread.sleep(2000);
    }
}
