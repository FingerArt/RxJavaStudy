package io.chengguo.rxjava.filter;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * 仅在过了一段指定的时间还没发射数据时才发射一个数据
 */
public class Debounce {

    /**
     * debounce操作符会过滤掉发射速率过快的数据项。
     */
    @Test
    public void debounce() {
        Observable
                .unsafeCreate(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        try {
                            subscriber.onNext(1);
                            Thread.sleep(20);
                            subscriber.onNext(2);
                            Thread.sleep(30);
                            subscriber.onNext(3);
                            Thread.sleep(10);
                            subscriber.onNext(4);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            subscriber.onCompleted();
                        }
                    }
                })
                .debounce(20, TimeUnit.MILLISECONDS)
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * throtleWithTimeout/debounce的一个变体根据你指定的时间间隔进行限流，时间单位通过TimeUnit参数指定。
     * <p>
     * 操作符默认在computation调度器上执行
     */
    @Test
    public void throttleWithTimeout() {
        Observable
                .unsafeCreate(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        try {
                            subscriber.onNext(1);
                            Thread.sleep(20);
                            subscriber.onNext(2);
                            Thread.sleep(30);
                            subscriber.onNext(3);
                            Thread.sleep(10);
                            subscriber.onNext(4);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            subscriber.onCompleted();
                        }
                    }
                })
                .throttleWithTimeout(20, TimeUnit.MILLISECONDS)
                .subscribe(SubscriberPinter.<Integer>create());
    }
}
