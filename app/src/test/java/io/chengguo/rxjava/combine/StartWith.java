package io.chengguo.rxjava.combine;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;

/**
 * 在数据序列的开头插入一条指定的项
 */
public class StartWith {
    /**
     * 如果你想要一个Observable在发射数据之前先发射一个指定的数据序列，可以使用StartWith操作符。
     */
    @Test
    public void startWidth() {
        Observable
                .just(1, 2, 3)
                .startWith(4, 5)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}
