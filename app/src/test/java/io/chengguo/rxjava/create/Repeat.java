package io.chengguo.rxjava.create;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 创建一个发射特定数据重复多次的Observable
 */
public class Repeat {
    /**
     * 它不是创建一个Observable，而是重复发射原始Observable的数据序列，这个序列或者是无限的，或者通过repeat(n)指定重复次数。
     */
    @Test
    public void repeat() {
        Observable
                .just(1, 2, 3)
                .repeat(1)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 它不是缓存和重放原始Observable的数据序列，而是有条件的重新订阅和发射原来的Observable。
     */
    @Test
    public void repeatWhen() {
        // TODO: 2018/2/7 不懂
        Observable
                .just(1, 2, 3)
                .repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Void> observable) {
                        return observable;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }


}
