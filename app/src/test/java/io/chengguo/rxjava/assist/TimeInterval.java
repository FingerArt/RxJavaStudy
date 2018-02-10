package io.chengguo.rxjava.assist;

import org.junit.Test;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 将一个发射数据的Observable转换为发射那些数据发射时间间隔的Observable
 */
public class TimeInterval {

    /**
     * TimeInterval操作符拦截原始Observable发射的数据项，替换为发射表示相邻发射物时间间隔的对象。
     */
    @Test
    public void timeInterval() {
        Observable
                .unsafeCreate(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext(1);
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext(2);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext(3);
                        subscriber.onCompleted();
                    }
                })
                .observeOn(Schedulers.computation())
                .timeInterval()
                .subscribe(SubscriberPinter.<rx.schedulers.TimeInterval<Integer>>create());
    }
}
