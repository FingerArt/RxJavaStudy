异步

一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序库。

简洁

扩展的观察者模式

## RxJava四个基本概念：

- Observable：被观察者
- Observer：观察者
- subscribe：订阅
- Event：事件

Observable和Observer通过subscribe实现订阅与被订阅关系，从而Observable可以在需要的时候发出事件来通知Observer。

- onCompleted：事件队列完结。RxJava不仅把每个事件单独处理，还会把他们看作一个队列。当不再有新的onNext()发出时，就会触发onComplete()作为标志。
- onError：事件队列异常。在事件处理过程中出现异常时，就会触发，同时队列自动终止，不允许再有事件发出。
- 在一个正确运行的事件序列中，onComplete() 和 onError() 有且只有一个，并且是事件序列中的最后一个。onComplete()和onError二者也是互斥的，即在队列中调用了其中一个，就不会再调用另一个。

```
                      onNext(param)
                      onCompleted()
+------------+        onError(error)       +----------+
| Observable |---------------------------->| Observer |
+------------+                             +----------+
```

onNext、onComleted、onError都是一个Action
库中含有：Action0 Action1 Action

适配器模式
Observer -> Subscriber

Observer、Subscriber是同一个概念，在源码中，Observer被适配成了Subscriber

## 线程调度器-Scheduler
- Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。

- Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。

- Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。

- Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。

- AndroidSchedulers.mainThread(): Android中的主线程。

`subscribeOn` 指定被观察者发生的线程
`onserveOn` 指定观察者发生的线程

## RxJava 中的几个“对象"

OnSubscribe           被观察者-事件发出
Subscriber(Observer)  观察者-事件接收


## 转换

map           一对一转换
flatMap       平面转换
throttleFirst 防抖动过滤

## Observables 的”冷“和”热“

Observable什么时候发射数据取决于Observable的实现；
一个”热“的Observable可能已创建完就开始发射数据，因此所有后续订阅它的观察者可能从序列中间的某个位置开始接受数据（有一些数据错过了）；
一个”冷“的Observable会一直等待，直到有观察者订阅它才开始发射数据，因此这个观察者可以确保会收到整个数据队列。

在一些ReactiveX实现里，还存在一种Connectable的Observable，不管有没有观察者订阅它，都不会发射数据，除非Connect方法被调用。


