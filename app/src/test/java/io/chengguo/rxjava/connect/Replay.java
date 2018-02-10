package io.chengguo.rxjava.connect;

import org.junit.Test;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.observables.ConnectableObservable;

/**
 * 保证所有的观察者收到相同的数据序列，即使它们在Observable开始发射数据之后才订阅
 */
public class Replay {

    /**
     * 可连接的Observable (connectable Observable)与普通的Observable差不多，不过它并不会在被订阅时开始发射
     * 数据，而是直到使用了Connect操作符时才会开始。用这种方法，你可以在任何时候让一个Observable开始发射数据。
     * <p>
     * 如果在将一个Observable转换为可连接的Observable之前对它使用Replay操作符，产生的这个可连接Observable将
     * 总是发射完整的数据序列给任何未来的观察者，即使那些观察者在这个Observable开始给其它观察者发射数据之后才订阅。
     *
     * @throws InterruptedException
     */
    @Test
    public void replay() throws InterruptedException {
        ConnectableObservable<Integer> replay = Observable.just(1, 2, 3).replay();
        replay.connect();
        replay.subscribe(SubscriberPinter.<Integer>create());

        Thread.sleep(1000);

        replay.subscribe(SubscriberPinter.<Integer>create());
    }
}