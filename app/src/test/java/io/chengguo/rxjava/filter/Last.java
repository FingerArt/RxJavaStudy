package io.chengguo.rxjava.filter;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 只发射最后一项（或者满足某个条件的最后一项）数据
 */
public class Last {
    @Test
    public void last() {
        Observable
                .just(1, 2, 3, 4, 5, 6)
                .last()
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
    public void last2() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 9)
                .last(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 3;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    @Test
    public void lastOrDefault() {
        Observable
                .<Integer>empty()
                .lastOrDefault(10)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}
