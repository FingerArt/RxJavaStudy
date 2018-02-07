package io.chengguo.rxjava.transform;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;

/**
 * 定期将来自原始Observable的数据分解为一个Observable窗口，发射这些窗口，而不是每次发射一项数据
 */
public class Window {

    /**
     * 这个window的变体立即打开它的第一个窗口。每当当前窗口发射了count项数据，它就关闭当前窗口并打开一个
     * 新窗口。如果从原始Observable收到了onError或onCompleted通知它也会关闭当前窗口。这种window变体发射
     * 一系列不重叠的窗口，这些窗口的数据集合与原始Observable发射的数据是一一对应的。
     */
    @Test
    public void window() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8)
                .window(3)
                .subscribe(new Action1<Observable<Integer>>() {
                    @Override
                    public void call(Observable<Integer> integerObservable) {
                        System.out.println("integerObservable = [" + integerObservable + "]");
                        integerObservable.subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                System.out.println("subscribe = [" + integer + "]");
                            }
                        });
                    }
                });
    }

    // TODO: 2018/2/7 还有很多不太理解
    @Test
    public void window2() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8)
                .window(new Func0<Observable<?>>() {
                    @Override
                    public Observable<?> call() {
                        return null;
                    }
                });
    }
}