package io.chengguo.rxjava.filter;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 *
 */
public class Debounce {

    /**
     * debounce操作符会过滤掉发射速率过快的数据项。
     */
    @Test
    public void debounce() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .debounce(1, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 操作符默认在computation调度器上执行
     */
    @Test
    public void throttleWithTimeout() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .throttleWithTimeout(1, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}
