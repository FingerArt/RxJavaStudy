package io.chengguo.rxjava.combine;

import org.junit.Test;

import java.util.Arrays;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * 通过一个函数将多个Observables的发射物结合到一起，基于这个函数的结果为每个结合体发射单个数据项。
 */
public class Zip {

    /**
     * Zip操作符返回一个Obversable，它使用这个函数按顺序结合两个或多个Observables发射的数据项，然后它
     * 发射这个函数返回的结果。它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable
     * 一样多的数据。
     */
    @Test
    public void zip() {
        Observable
                .zip(Observable.just(1, 2, 3), Observable.just(3, 4),
                        new Func2<Integer, Integer, Integer>() {
                            @Override
                            public Integer call(Integer i1, Integer i2) {
                                System.out.println("i1 = [" + i1 + "], i2 = [" + i2 + "]");
                                return i1 + i2;
                            }
                        })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 同zip
     */
    @Test
    public void zipWith() {
        Observable
                .just(1, 2, 3)
                .zipWith(Arrays.asList(4, 5),
                        new Func2<Integer, Integer, Integer>() {
                            @Override
                            public Integer call(Integer i1, Integer i2) {
                                System.out.println("i1 = [" + i1 + "], i2 = [" + i2 + "]");
                                return i1 + i2;
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
