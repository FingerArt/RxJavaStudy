package io.chengguo.rxjava.combine;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;

/**
 * 将一个发射多个Observables的Observable转换成另一个单独的Observable，后者发射那些Observables最近发射的数据项
 */
public class Switch {

    /**
     * 它每次观察那些Observables中的一个，Switch返回的这个Observable取消订阅前一个发射数据的Observable，开始发射最近的Observable发射的数据。
     */
    @Test
    public void _switch() {
        Observable
                .switchOnNext(Observable.just(Observable.just(1, 2, 3), Observable.just(4, 5, 6)))
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }
}
