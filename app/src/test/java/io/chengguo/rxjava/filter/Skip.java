package io.chengguo.rxjava.filter;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * 抑制Observable发射的前N项数据
 */
public class Skip {

    /**
     * 使用Skip操作符，你可以忽略Observable'发射的前N项数据，只保留之后的数据。
     */
    @Test
    public void skip() {
        Observable
                .just(1, 2, 3, 4, 5, 6)
                .skip(3)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 丢弃原始Observable开始的那段时间发射的数据，时长和时间单位通过参数指定。
     */
    @Test
    public void skip2() {
        Observable
                .from(Arrays.asList(1, 2, 3, 4, 5, 6, 2, 3, 4, 5, 6, 2, 3, 4, 5, 6, 2, 3, 4, 5, 6, 2, 3, 4, 5, 6, 2, 3, 4, 5, 6))
                .skip(1, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}