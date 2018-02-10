package io.chengguo.rxjava;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import rx.Observable;
import rx.Subscriber;

public class RxLifecycle<T> implements Observable.Transformer<T, T> {
    private final RxLifecycleOperator<T> rxLifecycle;

    RxLifecycle(LifecycleOwner lifecycle) {
        rxLifecycle = new RxLifecycleOperator<>(lifecycle);
    }

    public static <T> RxLifecycle<T> bind(LifecycleOwner lifecycle) {
        return new RxLifecycle<>(lifecycle);
    }

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable.lift(rxLifecycle);
    }

    private static class RxLifecycleOperator<T> implements Observable.Operator<T, T>, LifecycleObserver {
        private boolean destroyed;

        RxLifecycleOperator(LifecycleOwner lifecycle) {
            lifecycle.getLifecycle().addObserver(this);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy() {
            System.out.println("RxLifecycleOperator.onDestroy");
            destroyed = true;
        }

        @Override
        public Subscriber<? super T> call(final Subscriber<? super T> child) {
            return new Subscriber<T>() {
                @Override
                public void onCompleted() {
                    child.onCompleted();
                }

                @Override
                public void onError(Throwable e) {
                    child.onError(e);
                }

                @Override
                public void onNext(T t) {
                    if (destroyed) {
                        System.out.println("RxLifecycleOperator.onNext is unsubscribe");
                        unsubscribe();
                    } else {
                        child.onNext(t);
                    }
                }
            };
        }
    }
}