package io.chengguo.rxjava.filter;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * 只发射前面的N项数据
 */
public class Take {

    /**
     * 使用Take操作符让你可以修改Observable的行为，只返回前面的N项数据，然后发射完成通知，忽略剩余的数据。
     * 与 {@link Observable#limit(int)} 同义
     */
    @Test
    public void take() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8)
                .take(3)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * take的这个变体接受一个时长而不是数量参数。
     * 它会丢发射Observable开始的那段时间发射的数据，时长和时间单位通过参数指定。
     */
    @Test
    public void take2() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8)
                .take(1, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}