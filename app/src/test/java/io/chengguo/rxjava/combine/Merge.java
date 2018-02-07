package io.chengguo.rxjava.combine;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;

/**
 * 合并多个Observables的发射物
 */
public class Merge {

    /**
     * 使用Merge操作符你可以将多个Observables的输出合并，就好像它们是一个单个的Observable一样。
     */
    @Test
    public void merge() {
        Observable
                .merge(Observable.just(1, 2, 3), Observable.just(4, 5, 6))
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 同上
     */
    @Test
    public void mergeWith() {
        Observable
                .just(1, 2, 3)
                .mergeWith(Observable.just(4, 5, 6))
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}