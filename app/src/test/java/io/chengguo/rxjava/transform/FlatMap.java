package io.chengguo.rxjava.transform;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.util.ArrayList;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 将一个发射数据的Observable变换为多个Observables，然后将它们发射的数据合并后放进一个单独的Observable
 */
public class FlatMap {

    @Test
    public void flatMap() {
        Observable
                .from(createArrayLists())
                .flatMap(new Func1<ArrayList<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(ArrayList<String> arrayList) {
                        System.out.println("observable = [" + arrayList + "]");
                        return Observable.from(arrayList);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String String) {
                        System.out.println("subscribe = [" + String + "]");
                    }
                });
    }

    /**
     * flatMapIterable这个变体成对的打包数据，然后生成Iterable而不是原始数据和生成的Observables，但是
     * 处理方式是相同的。
     */
    @Test
    public void flatMapIterable() {
        Observable.from(createArrayLists())
                .flatMapIterable(new Func1<ArrayList<String>, Iterable<String>>() {
                    @Override
                    public Iterable<String> call(ArrayList<String> strings) {
                        System.out.println("observable = [" + strings + "]");
                        return strings;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String o) {
                        System.out.println("subscribe = [" + o + "]");
                    }
                });
    }

    /**
     * 它类似于最简单版本的flatMap，但是它按次序连接而不是合并那些生成的Observables，然后产生自己的数据序列。
     */
    @Test
    public void concatMap() {
        Observable.from(createArrayLists())
                .concatMap(new Func1<ArrayList<String>, Observable<?>>() {
                    @Override
                    public Observable<?> call(ArrayList<String> strings) {
                        System.out.println("observable = [" + strings + "]");
                        return Observable.from(strings);
                    }
                })
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        System.out.println("subscribe = [" + o + "]");
                    }
                });
    }

    /**
     * 它和flatMap很像，除了一点：当原始Observable发射一个新的数据（Observable）时，它将取消订阅并停止
     * 监视产生执之前那个数据的Observable，只监视当前这一个。
     */
    @Test
    public void switchMap() {
        Observable.from(createArrayLists())
                .switchMap(new Func1<ArrayList<String>, Observable<?>>() {
                    @Override
                    public Observable<?> call(ArrayList<String> strings) {
                        System.out.println("observable = [" + strings + "]");
                        return Observable.from(strings);
                    }
                })
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        System.out.println("subscribe = [" + o + "]");
                    }
                });
    }

    @NonNull
    private ArrayList<ArrayList<String>> createArrayLists() {
        ArrayList<ArrayList<String>> lists = new ArrayList<>();
        ArrayList<String> e1 = new ArrayList<>();
        e1.add("1-1");
        e1.add("1-2");
        e1.add("1-3");
        lists.add(e1);
        ArrayList<String> e2 = new ArrayList<>();
        e2.add("2-1");
        e2.add("2-2");
        e2.add("2-3");
        e2.add("2-4");
        e2.add("2-5");
        e2.add("2-6");
        lists.add(e2);
        ArrayList<String> e3 = new ArrayList<>();
        e3.add("3-1");
        e3.add("3-2");
        e3.add("3-3");
        e3.add("3-4");
        e3.add("3-5");
        e3.add("3-6");
        lists.add(e3);
        return lists;
    }
}
