package io.chengguo.rxjava.condition;

import org.junit.Test;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 条件和布尔操作
 */
public class Sample {

    /**
     * 判定是否Observable发射的所有数据都满足某个条件
     */
    @Test
    public void all() {
        Observable
                .just(1, 2, 3)
                .all(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer < 3;
                    }
                })
                .subscribe(SubscriberPinter.<Boolean>create());
    }

    /**
     * 给定两个或多个Observables，它只发射首先发射数据或通知的那个Observable的所有数据
     */
    @Test
    public void amb() {
        Observable
                .amb(Observable.just(4, 5, 6), Observable.just(1, 2, 3))
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * 判定一个Observable是否发射一个特定的值
     */
    @Test
    public void contains() {
        Observable
                .just(1, 2, 3, 4)
                .contains(3)
                .subscribe(SubscriberPinter.<Boolean>create());
    }

    /**
     * IsEmpty用于判定原始Observable是否没有发射任何数据。
     */
    @Test
    public void isEmpty() {
        Observable
                .empty()
                .isEmpty()
                .subscribe(SubscriberPinter.<Boolean>create());
    }


    /**
     * 通过一个谓词函数测试原始Observable发射的数据，只要任何一项满足条件就返回一个发射true的Observable，
     * 否则返回一个发射false的Observable。
     */
    @Test
    public void exists() {
        Observable
                .just(1, 2, 3)
                .exists(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer == 3;
                    }
                })
                .subscribe(SubscriberPinter.<Boolean>create());
    }

    /**
     * 发射来自原始Observable的值，如果原始Observable没有发射任何值，就发射一个默认值
     */
    @Test
    public void defaultIfEmpty() {
        Observable
                .empty()
                .defaultIfEmpty(4)
                .subscribe(SubscriberPinter.create());
    }

    /**
     * 判定两个Observables是否发射相同的数据序列。
     */
    @Test
    public void sequenceEqual() {
        Observable
                .sequenceEqual(Observable.just(1, 2, 3), Observable.just(1, 2, 3))
                .subscribe(SubscriberPinter.<Boolean>create());
    }

    /**
     * 丢弃原始Observable发射的数据，直到第二个Observable发射了一项数据
     */
    @Test
    public void skipUntil() {
        Observable
                .just(1, 2, 3)
                .skipUntil(Observable.unsafeCreate(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Sample.call(onNext)");
                        subscriber.onNext(10);
                    }
                }).observeOn(Schedulers.io()))
                .observeOn(Schedulers.computation())
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * 丢弃Observable发射的数据，直到一个指定的条件不成立
     */
    @Test
    public void skipWhile() {
        Observable
                .just(1, 2, 3)
                .skipWhile(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer < 2;
                    }
                })
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * TODO: 2018/2/10 没理解清除
     * 当第二个Observable发射了一项数据或者终止时，丢弃原始Observable发射的任何数据
     */
    @Test
    public void takeUntil() {
        Observable
                .unsafeCreate(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        subscriber.onNext(1);
                        subscriber.onNext(2);
                        subscriber.onNext(3);
                    }
                })
                .observeOn(Schedulers.computation())
                .takeUntil(Observable.unsafeCreate(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        try {
                            Thread.sleep(2000);
                            System.out.println("takeUntil.onNext");
                            subscriber.onNext(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).observeOn(Schedulers.io()))
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * 它使用一个谓词函数而不是第二个Observable来判定是否需要终止发射数据，它的行为类似于takeWhile。
     */
    @Test
    public void takeUntil2() {
        Observable
                .just(1, 2, 3)
                .takeUntil(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer < 2;
                    }
                })
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * 发射Observable发射的数据，直到一个指定的条件不成立
     */
    @Test
    public void takeWhile() {
        Observable
                .just(1, 2, 3)
                .takeWhile(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer < 3;
                    }
                })
                .subscribe(SubscriberPinter.<Integer>create());
    }
}
