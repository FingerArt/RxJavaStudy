package io.chengguo.rxjava.transform;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 对Observable发射的每一项数据应用一个函数，执行变换操作
 */
public class Map {

    @Test
    public void map() {
        Observable
                .just(1, 2)
                .map(new Func1<Integer, Object>() {
                    @Override
                    public Object call(Integer integer) {
                        System.out.println("observable = [" + integer + "]");
                        return integer;
                    }
                })
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        System.out.println("subscribe = [" + o + "]");
                    }
                });
    }

    /**
     * cast操作符将原始Observable发射的每一项数据都强制转换为一个指定的类型，然后再发射数据，它是map的一个特殊版本。
     */
    @Test
    public void cast() {
        Observable.just(1, 2)
                .cast(Number.class)
                .subscribe(new Action1<Number>() {
                    @Override
                    public void call(Number s) {
                        System.out.println("s = [" + s + "]");
                    }
                });
    }
}
