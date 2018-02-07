package io.chengguo.rxjava.create;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;

/**
 * 几个特定的Observable
 */
public class EmptyNeverThrow {

    /**
     * 创建一个不发射任何数据但是正常终止的Observable
     */
    @Test
    public void empty() {
        Observable
                .<String>empty()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("EmptyNeverThrow.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("EmptyNeverThrow.onError");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("EmptyNeverThrow.onNext");
                    }
                });
    }

    /**
     * 创建一个不发射数据也不终止的Observable
     */
    @Test
    public void never() {
        Observable
                .<String>never()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("EmptyNeverThrow.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("EmptyNeverThrow.onError");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("EmptyNeverThrow.onNext");
                    }
                });
    }

    /**
     * 创建一个不发射数据以一个错误终止的Observable
     */
    @Test
    public void _throw() {
        Observable
                .<String>error(new IllegalArgumentException())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("EmptyNeverThrow.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("EmptyNeverThrow.onError");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("EmptyNeverThrow.onNext");
                    }
                });
    }

}