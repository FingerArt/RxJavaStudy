package io.chengguo.rxjava.create;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import rx.Observable;
import rx.functions.Action1;

/**
 * 将其它种类的对象和数据类型转换为Observable
 */
public class From {
    @Test
    public void from() {
        Observable
                .from(Arrays.asList(1, 2, 3, 4, 5, 6))
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    @Test
    public void from2() {
        // TODO: 2018/2/7 不懂
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Observable
                .from((Future<? extends Integer>) null)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}