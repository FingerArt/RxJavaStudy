package io.chengguo.rxjava.create;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;

/**
 * 创建一个发射指定值的Observable
 */
public class Just {

    /**
     * Just将单个数据转换为发射那个数据的Observable。
     */
    @Test
    public void just() {
        Observable
                .just(1, 2, 3)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

/**
 * [源码解析]
 *
 * 所有的just都是使用 {@link Observable#from(Object[])}
 */
}
