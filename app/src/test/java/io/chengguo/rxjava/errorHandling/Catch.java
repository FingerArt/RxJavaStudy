package io.chengguo.rxjava.errorHandling;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * 从onError通知中恢复发射数据
 */
public class Catch {

    /**
     * 拦截原始Observable的onError通知，将它替换为其它的数据项或数据序列，让产生的Observable能够正常终止或者根本不终止。
     */
    @Test
    public void onErrorResumeNext() {
        Observable
                .error(new IllegalArgumentException("参数异常啦"))
                .onErrorResumeNext(Observable.just("出错啦"))
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Catch.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError = [" + e + "]");
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext = [" + o + "]");
                    }
                });
    }

    /**
     * onErrorReturn方法返回一个镜像原有Observable行为的新Observable，后者会忽略前者的onError调用，不会将
     * 错误传递给观察者，作为替代，它会发发射一个特殊的项并调用观察者的onCompleted方法。
     */
    @Test
    public void onErrorReturn() {
        Observable
                .error(new IllegalArgumentException("参数异常啦"))
                .onErrorReturn(new Func1<Throwable, String>() {
                    @Override
                    public String call(Throwable throwable) {
                        return throwable.getMessage();
                    }
                })
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Catch.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError = [" + e + "]");
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext = [" + o + "]");
                    }
                });
    }

    /**
     * 和onErrorResumeNext类似，onExceptionResumeNext方法返回一个镜像原有Observable行为的新Observable，
     * 也使用一个备用的Observable，不同的是，如果onError收到的Throwable不是一个Exception，它会将错误传递给
     * 观察者的onError方法，不会使用备用的Observable。
     */
    @Test
    public void onExceptionResumeNext() {
        Observable
                .error(new IllegalArgumentException("参数异常啦"))
                .onExceptionResumeNext(Observable.just("继续发送"))
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Catch.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError = [" + e + "]");
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext = [" + o + "]");
                    }
                });
    }
}
