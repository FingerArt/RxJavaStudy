package io.chengguo.rxjava.create;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;

/**
 * 创建一个发射特定整数序列的Observable
 */
public class Range {

    /**
     * Range操作符发射一个范围内的有序整数序列，你可以指定范围的起始和长度。
     */
    @Test
    public void range() {
        Observable
                .range(2, 4)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}
