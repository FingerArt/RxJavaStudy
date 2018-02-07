package io.chengguo.rxjava.filter;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * 抑制Observable发射的后N项数据
 */
public class SkipLast {

    /**
     * 使用SkipLast操作符修改原始Observable，你可以忽略Observable'发射的后N项数据，只保留前面的数据。
     */
    @Test
    public void skipLast() {
        Observable
                .just(1, 2, 3, 4, 5, 6)
                .skipLast(2)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 丢弃在原始Observable的生命周期内最后一段时间内发射的数据。时长和时间单位通过参数指定。
     */
    @Test
    public void skipLast2() {
        Observable
                .just(1, 2, 3, 4, 5, 6)
                .skipLast(1, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}
