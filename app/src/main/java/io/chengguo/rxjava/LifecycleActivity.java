package io.chengguo.rxjava;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class LifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void hello(View view) {
        Observable
                .interval(5, TimeUnit.SECONDS)
                .compose(RxLifecycle.<Long>bind(this))
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("LifecycleActivity.onCompleted");
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError = [" + e + "]");
                    }
                    @Override
                    public void onNext(final Long aLong) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LifecycleActivity.this, aLong.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        System.out.println("onNext = [" + aLong + "]");
                    }
                });
    }

    static class RxLifecycle<T> implements Observable.Transformer<T, T> {
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

            public static RxLifecycleOperator bind(LifecycleOwner lifecycle) {
                return new RxLifecycleOperator(lifecycle);
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
}