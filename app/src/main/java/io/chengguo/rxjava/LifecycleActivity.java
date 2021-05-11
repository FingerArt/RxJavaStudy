package io.chengguo.rxjava;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;


public class LifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void hello(View view) {
        Observable
                .interval(5, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .compose(RxLifecycle.<Long>bind(this))
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("LifecycleActivity.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError = [" + e + "]");
                    }

                    @Override
                    public void onNext(final Long aLong) {
                        Toast.makeText(LifecycleActivity.this, aLong.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("onNext = [" + aLong + "]");
                    }
                });
    }
}