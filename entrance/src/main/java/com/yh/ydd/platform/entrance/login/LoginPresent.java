package com.yh.ydd.platform.entrance.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;
import android.widget.EditText;

import com.yh.ydd.common.mvp.BasePresenter;
import com.yh.ydd.common.mvp.ILifecyclePresenter;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginPresent extends BasePresenter implements ILifecyclePresenter {


    @Override
    public void onCreate() {


    }

    @Override
    public void onDestroy() {
        Log.e("DOAING", "onDestroy");
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {

    }


    @SuppressLint("CheckResult")
    public void getNet() {

        //测试url
        String baseUrl = "http://www.wanandroid.com/tools/mockapi/9856/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        movieService.getUser().subscribeOn(Schedulers.newThread()) //设置被监听者在新的线程中
                .observeOn(Schedulers.io()).flatMap((Function<User, ObservableSource<?>>) user -> {

            Log.e("DOAING", "第一次：" + user.getFirstName());
            Log.e("DOAING", "第一次访问的线程：" + Thread.currentThread().getName());

            return movieService.getUser1();

        }).observeOn(AndroidSchedulers.mainThread()) //设置监听者的处理线程
                .subscribe(o -> {

                    User user = (User) o;
                    Log.e("DOAING", "第二次：" + user.getFirstName());
                    Log.e("DOAING", "最终结果的处理线程：" + Thread.currentThread().getName());

                });

    }
}
