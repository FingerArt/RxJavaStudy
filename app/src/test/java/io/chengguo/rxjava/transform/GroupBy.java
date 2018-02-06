package io.chengguo.rxjava.transform;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * 将一个Observable分拆为一些Observables集合，它们中的每一个发射原始Observable的一个子序列
 */
public class GroupBy {

    @Test
    public void groupBy() {
        Observable.just("hello", "world", "!", "ge")
                .groupBy(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s.length() > 2 ? "long" : "short";
                    }
                })
                .subscribe(new Action1<GroupedObservable<String, String>>() {
                    @Override
                    public void call(GroupedObservable<String, String> stringStringGroupedObservable) {
                        System.out.println(stringStringGroupedObservable.getKey());
                        stringStringGroupedObservable.subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                System.out.println("s = [" + s + "]");
                            }
                        });
                    }
                });
    }
}
