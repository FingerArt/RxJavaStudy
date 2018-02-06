package io.chengguo.rxjava.transform;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * 连续地对数据序列的每一项应用一个函数，然后连续发射结果
 */
public class Scan {

    /**
     * Scan操作符对原始Observable发射的第一项数据应用一个函数，然后将那个函数的结果作为自己的第一项数据
     * 发射。它将函数的结果同第二项数据一起填充给这个函数来产生它自己的第二项数据。它持续进行这个过程来
     * 产生剩余的数据序列。这个操作符在某些情况下被叫做accumulator。
     */
    @Test
    public void scan() {
        Observable.just(1, 2, 3, 4)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer next) {
                        System.out.println("sum = [" + sum + "], next = [" + next + "]");
                        return sum + next;
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
