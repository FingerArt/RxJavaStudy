package io.chengguo.rxjava.combine;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * 任何时候，只要在另一个Observable发射的数据定义的时间窗口内，这个Observable发射了一条数据，就结合两个
 * Observable发射的数据。
 */
public class Join {

    // TODO: 2018/2/7 不理解
    @Test
    public void join() throws InterruptedException {
        Observable
                .just(1, 2, 3)
                .join(
                        Observable.just(4, 5, 6, 7)
                        , new Func1<Integer, Observable<Integer>>() {
                            @Override
                            public Observable<Integer> call(Integer integer) {
                                return Observable.just(integer);
                            }
                        }, new Func1<Integer, Observable<Integer>>() {
                            @Override
                            public Observable<Integer> call(Integer integer) {
                                return Observable.just(integer);
                            }
                        }, new Func2<Integer, Integer, Integer>() {
                            @Override
                            public Integer call(Integer i1, Integer i2) {
                                System.out.println("i1 = [" + i1 + "], i2 = [" + i2 + "]");
                                return i1 + i2;
                            }
                        }
                )
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Join.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError = [" + e + "]");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext = [" + integer + "]");
                    }
                });

        Thread.sleep(3000);
    }
}
