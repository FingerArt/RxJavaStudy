package io.chengguo.rxjava.connect;

import org.junit.Test;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.observables.ConnectableObservable;

/**
 * 将普通的Observable转换为可连接的Observable并可在任何时候开始发射数据
 */
public class PublishConnect {

    /**
     * 可连接的Observable (connectable Observable)与普通的Observable差不多，不过它并不会在被订阅时开始
     * 发射数据，而是直到使用了Connect操作符时才会开始。用这种方法，你可以在任何时候让一个Observable开始发射数据。
     */
    @Test
    public void publish() {
        ConnectableObservable<Integer> publish = Observable.just(1, 2, 3).publish();
        publish.subscribe(SubscriberPinter.<Integer>create());

        System.out.println("PublishConnect.publish");

        publish.connect();
    }
}
