package io.chengguo.rxjava.combine;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * 当两个Observables中的任何一个发射了数据时，使用一个函数结合每个Observable发射的最近数据项，并且基于
 * 这个函数的结果发射数据。
 */
public class CombineLatest {

    // TODO: 2018/2/7 看完zip再看
    @Test
    public void combineLatest() {
        Observable
                .combineLatest(Observable.just(1, 2), Observable.just(3, 4), new Func2<Integer, Integer, Object>() {
                    @Override
                    public Object call(Integer i1, Integer i2) {
                        System.out.println("i1 = [" + i1 + "], i2 = [" + i2 + "]");
                        return i1 + i2;
                    }
                })
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        System.out.println("subscribe = [" + o + "]");
                    }
                });
    }
}
