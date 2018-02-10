package io.chengguo.rxjava.assist;

import org.junit.Test;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;

/**
 * Materialize将数据项和事件通知都当做数据项发射，Dematerialize刚好相反。
 */
public class MaterializeDematerialize {
    /**
     * 一个合法的有限的Obversable将调用它的观察者的onNext方法零次或多次，然后调用观察者的onCompleted或onError
     * 正好一次。Materialize操作符将这一系列调用，包括原来的onNext通知和终止通知onCompleted或onError都转换为
     * 一个Observable发射的数据序列。
     */
    @Test
    public void materialize() {
        Observable
                .just(1, 2)
                .materialize()
                .subscribe(SubscriberPinter.<Notification<Integer>>create());
    }

    /**
     * Dematerialize操作符是Materialize的逆向过程，它将Materialize转换的结果还原成它原本的形式。
     * dematerialize反转这个过程，将原始Observable发射的Notification对象还原成Observable的通知。
     */
    @Test
    public void dematerialize() {
        Observable
                .just(1, 2)
                .materialize()
                .subscribe(new Subscriber<Notification<Integer>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("MaterializeDematerialize.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("MaterializeDematerialize.onError");
                    }

                    @Override
                    public void onNext(Notification<Integer> integerNotification) {
                        Observable.just(integerNotification).dematerialize().subscribe(SubscriberPinter.create());
                    }
                });
    }
}
