package io.chengguo.rxjava;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

/**
 * Created by fingerart on 17/4/5.
 */

public class TransformOperatorTest {

    @Test
    public void buffer() {
        Observable.just("str1", "str2", "str3", "str4", "str5")
                .buffer(2)
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        System.out.println("strings = [" + strings + "]");
                    }
                });
    }

    @Test
    public void flatmap() {
        ArrayList<ArrayList> lists = createArrayLists();
        Observable.from(lists).flatMap(new Func1<ArrayList, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(ArrayList arrayList) {
                return Observable.from(arrayList);
            }
        }, 2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
    }

    @NonNull
    private ArrayList<ArrayList> createArrayLists() {
        ArrayList<ArrayList> lists = new ArrayList<>();
        ArrayList<Integer> e1 = new ArrayList();
        e1.add(11);
        e1.add(12);
        e1.add(13);
        lists.add(e1);
        ArrayList<Integer> e2 = new ArrayList();
        e2.add(21);
        e2.add(22);
        e2.add(23);
        e2.add(24);
        e2.add(25);
        e2.add(26);
        lists.add(e2);
        ArrayList<Integer> e3 = new ArrayList();
        e3.add(31);
        e3.add(32);
        e3.add(33);
        e3.add(34);
        e3.add(35);
        e3.add(36);
        lists.add(e3);
        return lists;
    }

    @Test
    public void groupBy() {
        Observable.just("hello", "world", "!", "ge").groupBy(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s.length() > 2 ? "long" : "short";
            }
        }).subscribe(new Action1<GroupedObservable<String, String>>() {
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

    @Test
    public void castMap() {
        Observable.just(1).cast(Integer.class).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer s) {
                System.out.println("s = [" + s + "]");
            }
        });
    }

    @Test
    public void scan() {
        Observable.just(1, 2, 3, 4).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer i1, Integer i2) {
                System.out.println("i1 = [" + i1 + "], i2 = [" + i2 + "]");
                return i1 + i2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
    }
}
