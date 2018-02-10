package io.chengguo.rxjava;

import rx.Subscriber;

/**
 * Created by FingerArt on 2018/2/10.
 */
public class SubscriberPinter<T> extends Subscriber<T> {

    public static <T> SubscriberPinter<T> create() {
        return new SubscriberPinter<>();
    }

    @Override
    public void onCompleted() {
        System.out.println("SubscriberPinter.onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("SubscriberPinter.onError = [" + e + "]");
    }

    @Override
    public void onNext(T t) {
        System.out.println("SubscriberPinter.onNext = [" + t + "]");
    }
}
