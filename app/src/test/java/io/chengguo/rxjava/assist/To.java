package io.chengguo.rxjava.assist;

import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.functions.Func1;

/**
 * 将Observable转换为另一个对象或数据结构
 */
public class To {

    /**
     * TODO: 2018/2/10 不太理解
     *
     * @throws InterruptedException
     */
    @Test
    public void to() throws InterruptedException {
        Observable
                .just(1, 2, 3)
                .to(new Func1<Observable<Integer>, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Observable<Integer> observable) {
                        return observable;
                    }
                })
                .subscribe(SubscriberPinter.<Integer>create());
    }

    @Test
    public void toList() {
        Observable
                .just(1, 2, 3)
                .toList()
                .subscribe(SubscriberPinter.<List<Integer>>create());
    }

    @Test
    public void toMap() {
        Observable
                .just(1, 2, 3)
                .toMap(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer;
                    }
                })
                .subscribe(SubscriberPinter.<Map<Integer, Integer>>create());
    }

    @Test
    public void toMultimap() {
        Observable
                .just(1, 2, 3, 4, 5, 6)
                .toMultimap(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return integer % 2 == 0 ? "even" : "odd";
                    }
                })
                .subscribe(SubscriberPinter.<Map<String, Collection<Integer>>>create());
    }

    @Test
    public void toSortedList() {
        Observable
                .just(5, 2, 3, 1, 8, 4)
                .toSortedList()
                .subscribe(SubscriberPinter.<List<Integer>>create());
    }
}
