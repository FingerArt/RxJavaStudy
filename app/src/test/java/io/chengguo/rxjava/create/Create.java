package io.chengguo.rxjava.create;

import org.junit.Test;

import rx.Emitter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * 使用一个函数从头开始创建一个Observable
 */
public class Create {
    @Test
    public void create() throws InterruptedException {
        Observable
                .create(new Action1<Emitter<String>>() {
                    @Override
                    public void call(Emitter<String> emitter) {
                        emitter.onNext("hello");
                        emitter.onCompleted();
                    }
                }, Emitter.BackpressureMode.BUFFER)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        System.out.println("s = [" + s + "]");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = [" + e + "]");
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Create.onCompleted");
                    }
                });
    }
}
