package io.chengguo.rxjava.transform;

import org.junit.Test;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * 定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个
 */
public class Buffer {

    /**
     * strings = [[str1, str2, str3]]
     * strings = [[str4, str5, str6]]
     * strings = [[str7, str8, str9]]
     */
    @Test
    public void buffer() {
        Observable.just("str1", "str2", "str3", "str4", "str5", "str6", "str7", "str8", "str9")
                .buffer(3)
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        System.out.println("strings = [" + strings + "]");
                    }
                });
    }

    /**
     * 从原始Observable的第一项数据开始创建新的缓存，此后每当收到skip项数据，用count项数据填充缓存：开头
     * 的一项和后续的count-1项，它以列表(List)的形式发射缓存，取决于count和skip的值，这些缓存可能会有重
     * 叠部分（比如skip < count时），也可能会有间隙（比如skip > count时）。
     * <p>
     * strings = [[str1, str2, str3, str4, str5, str6, str7, str8]]
     * strings = [[str3, str4, str5, str6, str7, str8, str9]]
     * strings = [[str5, str6, str7, str8, str9]]
     * strings = [[str7, str8, str9]]
     * strings = [[str9]]
     */
    @Test
    public void buffer2() {
        Observable.just("str1", "str2", "str3", "str4", "str5", "str6", "str7", "str8", "str9")
                .buffer(8, 2)
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        System.out.println("strings = [" + strings + "]");
                    }
                });
    }
}