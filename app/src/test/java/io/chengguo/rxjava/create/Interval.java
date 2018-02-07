package io.chengguo.rxjava.create;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * 创建一个按固定时间间隔发射整数序列的Observable
 */
public class Interval {

    /**
     * Interval操作符返回一个Observable，它按固定的时间间隔发射一个无限递增的整数序列。
     *
     * @throws InterruptedException
     */
    @Test
    public void interval() throws InterruptedException {
        Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("subscribe = [" + aLong + "]");
                    }
                });

        Thread.sleep(5000);
    }
}
