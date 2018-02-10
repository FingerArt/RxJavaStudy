package io.chengguo.rxjava.assist;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 延迟一段指定的时间再发射来自Observable的发射物
 */
public class Delay {

    /**
     * delay接受一个定义时长的参数（包括数量和单位）。每当原始Observable发射一项数据，delay就启动一个定时器
     * ，当定时器过了给定的时间段时，delay返回的Observable发射相同的数据项。
     * <p>
     * 注意：delay不会平移onError通知，它会立即将这个通知传递给订阅者，同时丢弃任何待发射的onNext通知。然而它会平移一个onCompleted通知。
     *
     * @throws InterruptedException
     */
    @Test
    public void delay() throws InterruptedException {
        Observable
                .just(1, 2)
//                .filter(new Func1<Integer, Boolean>() {
//                    @Override
//                    public Boolean call(Integer integer) {
//                        if (integer == 2) {
//                            int i = integer / 0;
//                        }
//                        return false;
//                    }
//                })
                .delay(1, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Delay.onCompleted");
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

    /**
     * 使用一个函数针对原始Observable的每一项数据返回一个Observable，它监视返回的这个Observable，当任何那样
     * 的Observable终止时，delay返回的Observable就发射关联的那项数据。
     * <p>
     * 注意：delay不会平移onError通知，它会立即将这个通知传递给订阅者，同时丢弃任何待发射的onNext通知。然而它会平移一个onCompleted通知。
     *
     * @throws InterruptedException
     */
    @Test
    public void delay2() throws InterruptedException {
        Observable
                .just(1, 2, 3)
//                .filter(new Func1<Integer, Boolean>() {
//                    @Override
//                    public Boolean call(Integer integer) {
//                        if (integer == 2) {
//                            int i = integer / 0;
//                        }
//                        return false;
//                    }
//                })
                .delay(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        return Observable.just(integer).delay(integer, TimeUnit.SECONDS);
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Delay.onCompleted");
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

        Thread.sleep(7000);
    }

    /**
     * 可以延迟订阅原始Observable。
     *
     * @throws InterruptedException
     */
    @Test
    public void delaySubscription() throws InterruptedException {
        Observable
                .just(1, 2, 3)
                .delaySubscription(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });

        Thread.sleep(3000);
    }
}
