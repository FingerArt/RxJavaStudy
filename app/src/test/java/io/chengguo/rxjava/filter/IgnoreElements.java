package io.chengguo.rxjava.filter;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;

/**
 * 不发射任何数据，只发射Observable的终止通知
 */
public class IgnoreElements {
    @Test
    public void ignoreElements() {
        Observable
                .just(1, 2, 3)
                .ignoreElements()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("IgnoreElements.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("IgnoreElements.onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("IgnoreElements.onNext");
                    }
                });
    }
}
