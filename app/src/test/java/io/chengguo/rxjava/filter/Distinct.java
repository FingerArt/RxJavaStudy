package io.chengguo.rxjava.filter;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 抑制（过滤掉）重复的数据项
 */
public class Distinct {

    /**
     * 只允许还没有发射过的数据项通过。
     */
    @Test
    public void distinct() {
        Observable
                .just(5, 2, 3, 4, 3, 5, 7, 8)
                .distinct()
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
    public void distinct2() {
        Observable
                .just(5, 2, 3, 4, 3, 5, 7, 8)
                .distinct(new Func1<Integer, Object>() {
                    @Override
                    public Object call(Integer integer) {
                        return integer;//计算传入的值计算出一个key
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
     * 只判定一个数据和它的直接前驱是否是不同的。
     */
    @Test
    public void distinctUntilChanged() {
        Observable
                .just(5, 5, 3, 4, 3, 3, 3, 8)
                .distinctUntilChanged()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}