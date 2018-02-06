package io.chengguo.rxjava.filter;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 定期发射Observable最近发射的数据项
 */
public class Sample {
    @Test
    public void sample() {
        // TODO: 2018/2/6  
        Observable
                .just(1, 2, 3, 4, 5)
                .sample(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}
