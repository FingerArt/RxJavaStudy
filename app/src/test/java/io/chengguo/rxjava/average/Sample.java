package io.chengguo.rxjava.average;

import org.junit.Test;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.functions.Func2;

/**
 * 算术和聚合操作
 * <p>
 * 还有部分操作符属于其它模块
 */
public class Sample {
    @Test
    public void average() {
        Observable
                .just(1, 2, 3)
                .count()
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * 不交错的发射两个或多个Observable的发射物
     * <p>
     * Concat操作符连接多个Observable的输出，就好像它们是一个Observable，第一个Observable发射的所有数据在
     * 第二个Observable发射的任何数据前面，以此类推。
     */
    @Test
    public void concat() {
        Observable
                .concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6, 7, 8))
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * 按顺序对Observable发射的每项数据应用一个函数并发射最终的值
     */
    @Test
    public void reduce() {
        Observable
                .just(1, 2, 3)
                .reduce(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer i1, Integer i2) {
                        System.out.println("i1 = [" + i1 + "], i2 = [" + i2 + "]");
                        return i1 + i2;
                    }
                })
                .subscribe(SubscriberPinter.<Integer>create());
    }


}