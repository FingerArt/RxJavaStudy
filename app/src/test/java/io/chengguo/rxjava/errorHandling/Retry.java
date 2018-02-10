package io.chengguo.rxjava.errorHandling;

import org.junit.Test;

import io.chengguo.rxjava.create.Just;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * 如果原始Observable遇到错误，重新订阅它期望它能正常终止
 */
public class Retry {

    /**
     * Retry操作符不会将原始Observable的onError通知传递给观察者，它会订阅这个Observable，再给它一次机会无
     * 错误地完成它的数据序列。Retry总是传递onNext通知给观察者，由于重新订阅，可能会造成数据项重复
     */
    @Test
    public void retry() {
        Observable
                .error(new IllegalArgumentException("参数异常了"))
                .retry(3)//如果无参则会无限重试
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Retry.onCompleted");
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
     * retryWhen和retry类似，区别是，retryWhen将onError中的Throwable传递给一个函数，这个函数产生另一个
     * Observable，retryWhen观察它的结果再决定是不是要重新订阅原始的Observable。如果这个Observable发射了一
     * 项数据，它就重新订阅，如果这个Observable发射的是onError通知，它就将这个通知传递给观察者然后终止。
     */
    @Test
    public void retryWhen() {
        Observable
                .unsafeCreate(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        System.out.println("Retry.subscribing");
                        subscriber.onNext("call");
                        subscriber.onError(new IllegalArgumentException("参数异常啦"));
                        subscriber.onCompleted();
                    }
                })
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> observable) {
                        // TODO: 2018/2/10 不太能理解这里的操作
                        return observable;
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Retry.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = [" + e + "]");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("s = [" + s + "]");
                    }
                });
    }
}
