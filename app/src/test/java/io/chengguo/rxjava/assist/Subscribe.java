package io.chengguo.rxjava.assist;

import org.junit.Test;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.functions.Action1;

/**
 * 操作来自Observable的发射物和通知
 */
public class Subscribe {

    /**
     * Subscribe操作符是连接观察者和Observable的胶水。一个观察者要想看到Observable发射的数据项，或者想要
     * 从Observable获取错误和完成通知，它首先必须使用这个操作符订阅那个Observable。
     */
    @Test
    public void subscribe() {
        Observable
                .just(1, 2, 3)
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * forEach方法是简化版的subscribe，你同样可以传递一到三个函数给它，解释和传递给subscribe时一样。
     */
    @Test
    public void foreach() {
        Observable
                .just(1, 2, 3)
                .forEach(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("forEach = [" + integer + "]");
                    }
                });
    }
}
