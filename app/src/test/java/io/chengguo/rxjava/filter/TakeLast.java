package io.chengguo.rxjava.filter;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * 发射Observable发射的最后N项数据
 */
public class TakeLast {
    @Test
    public void takeLast() {
        Observable
                .just(1, 2, 3, 4, 5, 6)
                .takeLast(2)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 接受一个时长和数量参数。
     * 它会发射在原始Observable的生命周期内最后一段时间内发射的N项数据。
     */
    @Test
    public void takeLast2() {
        Observable
                .just(1, 2, 3, 4, 5, 6)
                .takeLast(2, 1, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 和takeLast类似，唯一的不同是它把所有的数据项收集到一个List再发射，而不是依次发射一个。
     */
    @Test
    public void takeLastBuffer() {
        Observable
                .just(1, 2, 3, 4, 5, 6)
                .takeLastBuffer(3)
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        System.out.println("subscribe = [" + integers + "]");
                    }
                });
    }
}