package io.chengguo.rxjava.filter;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;

/**
 * 只发射第一项（或者满足某个条件的第一项）数据
 */
public class First {

    /**
     * 只发射第一个数据，使用没有参数的first操作符。
     * 如果原始Observable没有发射任何满足条件的数据，first会抛出一个NoSuchElementException
     */
    @Test
    public void first() {
        Observable
                .just(1, 2, 3, 4, 5, 6)
                .first()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 同上
     */
    @Test
    public void first2() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 9)
                .first(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 8;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 与first类似，但是在Observagle没有发射任何数据时发射一个你在参数中指定的默认值。
     */
    @Test
    public void firstOrDefault() {
        Observable
                .<Integer>empty()
                .firstOrDefault(10)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 与first类似，除了这一点：如果原始Observable没有发射任何满足条件的数据，first会抛出一个
     * NoSuchElementException，takeFist会返回一个空的Observable（不调用onNext()但是会调用onCompleted）。
     */
    @Test
    public void takeFirst() {
        Observable
                .<Integer>empty()
                .takeFirst(UtilityFunctions.alwaysTrue())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("First.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("First.onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("First.onNext");
                    }
                });
    }

    /**
     * 只允许发射一个数据
     * single操作符也与first类似，但是如果原始Observable在完成之前不是正好发射一次数据，它会抛出一个NoSuchElementException。
     */
    @Test
    public void single() {
        Observable
                .just(1)
                .single()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    /**
     * 和firstOrDefault类似，但是如果原始Observable发射超过一个的数据，会以错误通知终止。
     */
    @Test
    public void singleOrDefault() {
        Observable
                .<Integer>empty()
                .singleOrDefault(10)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }

    @Test
    public void singleOrDefault2() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 9)
                .singleOrDefault(10, new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 10;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("subscribe = [" + integer + "]");
                    }
                });
    }


}