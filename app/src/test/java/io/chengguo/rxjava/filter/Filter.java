package io.chengguo.rxjava.filter;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 只发射通过了谓词测试的数据项
 */
public class Filter {

    /**
     * Filter操作符使用你指定的一个谓词函数测试数据项，只有通过测试的数据才会被发射。
     */
    @Test
    public void filter() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 == 0;
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
     * ofType是filter操作符的一个特殊形式。它过滤一个Observable只返回指定类型的数据。
     * ofType默认不在任何特定的调度器上指定。
     */
    @Test
    public void ofType() {
        Observable.just(1, 2F, 3L, 4, 5, 6D, 7, 8)
                .ofType(Double.class)
                .subscribe(new Action1<Double>() {
                    @Override
                    public void call(Double aDouble) {
                        System.out.println("subscribe = [" + aDouble + "]");
                    }
                });
    }
}
