package io.chengguo.rxjava.assist;

import org.junit.Test;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Notification;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 注册一个动作作为原始Observable生命周期事件的一种占位符
 */
public class Do {

    /**
     * doOnEach操作符让你可以注册一个回调，它产生的Observable每发射一项数据就会调用它一次。
     */
    @Test
    public void doOnEach() {
        Observable
                .just(1, 2, 3)
                .doOnEach(new Action1<Notification<? super Integer>>() {
                    @Override
                    public void call(Notification<? super Integer> notification) {
                        System.out.println("notification = [" + notification.getKind() + " -> " + notification.getValue() + "]");
                    }
                })
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * doOnNext操作符类似于doOnEach(Action1)，但是它的Action不是接受一个Notification参数，而是接受发射的数据项。
     */
    @Test
    public void doOnNext() {
        Observable
                .just(1, 2, 3)
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("doOnNext = [" + integer + "]");
                    }
                })
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * 不同生命周期的
     */
    @Test
    public void doOnOthers() {
        Observable
                .just(1, 2)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("Do.doOnSubscribe");
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("Do.doOnCompleted");
                    }
                })
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("Do.doOnUnsubscribe");
                    }
                })
                .subscribe(SubscriberPinter.<Integer>create());
    }

    /**
     * doOnTerminate 操作符注册一个动作，当它产生的Observable终止之前会被调用，无论是正常还是异常终止。
     */
    @Test
    public void doOnTerminate() {
        Observable
                .error(new RuntimeException())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("Do.doOnTerminate");
                    }
                })
                .subscribe(SubscriberPinter.create());
    }
}
