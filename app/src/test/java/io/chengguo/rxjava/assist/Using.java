package io.chengguo.rxjava.assist;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import io.chengguo.rxjava.SubscriberPinter;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * 创建一个只在Observable生命周期内存中的一次性资源
 */
public class Using {

    /**
     * 当一个观察者订阅using返回的Observable时，using将会使用Observable工厂函数创建观察者要观察的Observable
     * ，同时使用资源工厂函数创建一个你想要创建的资源。当观察者取消订阅这个Observable时，或者当观察者终止时
     * （无论是正常终止还是因错误而终止），using使用第三个函数释放它创建的资源。
     */
    @Test
    public void using() {
        Observable
                .using(new Func0<List<Integer>>() {
                    @Override
                    public List<Integer> call() {
                        return Arrays.asList(1, 2, 3);
                    }
                }, new Func1<List<Integer>, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(List<Integer> list) {
                        return Observable.from((Integer[]) list.toArray());
                    }
                }, new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> list) {
                        System.out.println("destroy = [" + list + "]");
                        list.clear();
                    }
                })
                .subscribe(SubscriberPinter.<Integer>create());

    }
}
