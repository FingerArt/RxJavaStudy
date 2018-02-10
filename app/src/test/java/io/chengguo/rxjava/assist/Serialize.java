package io.chengguo.rxjava.assist;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * 强制一个Observable连续调用并保证行为正确
 * TODO: 2018/2/10 不太理解
 */
public class Serialize {

    /**
     * 一个Observable可以异步调用它的观察者的方法，可能是从不同的线程调用。这可能会让Observable行为不正确，
     * 它可能会在某一个onNext调用之前尝试调用onCompleted或onError方法，或者从两个不同的线程同时调用onNext方法。
     * 使用Serialize操作符，你可以纠正这个Observable的行为，保证它的行为是正确的且是同步的。
     */
    @Test
    public void serialize() {
        Observable
                .unsafeCreate(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(final Subscriber<? super Integer> subscriber) {
                        Schedulers.trampoline().createWorker().schedule(new Action0() {
                            @Override
                            public void call() {
                                subscriber.onNext(1);
                            }
                        });
                        Schedulers.io().createWorker().schedule(new Action0() {
                            @Override
                            public void call() {
                                subscriber.onNext(2);
                            }
                        });
                        Schedulers.computation().createWorker().schedule(new Action0() {
                            @Override
                            public void call() {
                                subscriber.onNext(2);
                            }
                        });
                        Schedulers.immediate().createWorker().schedule(new Action0() {
                            @Override
                            public void call() {
                                subscriber.onCompleted();
                            }
                        });
                    }
                })
                .serialize()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Serialize.onCompleted: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Serialize.onError: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Serialize.onNext: " + Thread.currentThread().getName());
                    }
                });
    }
}
