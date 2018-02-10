package io.chengguo.rxjava.assist;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * 对原始Observable的一个镜像，如果过了一个指定的时长仍没有发射数据，它会发一个错误通知
 */
public class Timeout {

    /**
     * 如果原始Observable过了指定的一段时长没有发射任何数据，Timeout操作符会以一个onError通知终止这个Observable。
     */
    @Test
    public void timeout() {
        Observable
                .just(1, 2, 3)
                .lift(new Observable.Operator<Integer, Integer>() {
                    @Override
                    public Subscriber<? super Integer> call(final Subscriber<? super Integer> subscriber) {
                        return new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(Integer integer) {
                                if (integer == 2) {
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                subscriber.onNext(integer);
                            }
                        };
                    }
                })
                .timeout(1, TimeUnit.SECONDS)
                // TODO: 2018/2/10 还有多个变体，未弄清楚
                .subscribe(SubscriberPinter.<Integer>create());
    }
}
