package io.chengguo.rxjava;

import org.junit.Test;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

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

}
