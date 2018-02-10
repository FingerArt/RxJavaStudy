package io.chengguo.rxjava.connect;

import org.junit.Test;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.observables.ConnectableObservable;

/**
 * 让一个可连接的Observable行为像普通的Observable
 */
public class RefCount {
    @Test
    public void refCount() {
        ConnectableObservable<Integer> publish = Observable.just(1, 2, 3).publish();

        //转成普通的Observable
        Observable<Integer> observable = publish.refCount();

        observable.subscribe(SubscriberPinter.<Integer>create());
    }
}
