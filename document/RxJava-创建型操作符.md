[TOC]

### 创建操作符

#### Create

通过调用观察者的方法从头创建一个Observable

> 建议你在传递给create方法的函数中检查观察者的isUnsubscribed状态，以便在没有观察者的时候，让你的Observable停止发射数据或者做昂贵的运算。

#### Defer

直到有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable

#### Empty/Never/Throw
Empty: 创建一个不发射任何数据但是正常终止的Observable
Never: 创建一个不发射数据也不终止的Observable
Throw: 创建一个不发射数据以一个错误终止的Observable

这三个操作符生成的Observable行为非常特殊和受限。测试的时候很有用，有时候也用于结合其它的Observables，或者作为其它需要Observable的操作符的参数。

#### From

xxxxxxxxxxxxxxxxxx

#### Interval

创建一个按固定时间发射整数序列的Observable

默认工作在computation调度器

#### Just

创建一个发射指定值的Observable

#### Range

创建一个发射特定整数序列的Observable

``` java
rang(n, m)
```

m为0时不发射任何数据（如果设置为负数，会抛异常）；
不在任何特定调度器上执行。