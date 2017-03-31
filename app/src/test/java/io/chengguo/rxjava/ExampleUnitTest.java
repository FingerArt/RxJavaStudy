package io.chengguo.rxjava;

import org.junit.Test;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void t1() throws Exception {

        /*
         * string[]被转成OnSubscribeFromArray
         * Action1被转成ActionSubscriber
         */
        Observable.from(new String[]{"action1", "action2"}).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        Observable.from(new String[]{"action1", "action2"}).subscribe(new Observer() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });

        /*
         * 创建被观察者
         * 实现自定义的事件发送规则
         */
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("what's your name?");
                subscriber.onNext("welcome");
                subscriber.onCompleted();
            }
        });

        /*
         * 快捷创建事件队列
         */
        Observable.just("str1", "str2", "str3");
        Observable.from(new Integer[]{1,2,3});

    }
}