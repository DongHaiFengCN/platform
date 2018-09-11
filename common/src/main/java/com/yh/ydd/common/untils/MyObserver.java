package com.yh.ydd.common.untils;

import android.net.ParseException;


import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.yh.ydd.common.untils.ErrorCodeProfile.FILENOTFOUND;
import static com.yh.ydd.common.untils.ErrorCodeProfile.UNAUTHORIZED;

public abstract class MyObserver implements Observer {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Object o) {

        doThings(o);

    }

    @Override
    public void onError(Throwable e) {

        String error = null;
        int code = 0;
        if (e instanceof HttpException) {

            HttpException httpException = (HttpException) e;

            code = httpException.code();

            switch (code) {

                case UNAUTHORIZED:

                    error = "授权失效";

                    break;
                case FILENOTFOUND:

                    error = "无法定位到资源";

                case 500:

                    error = "服务器内部错误";

                    break;
                default:

                    break;
            }

        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {

            error = "数据解析错误";


        } else if (e instanceof ConnectException) {

            error = "网络连接失败";


        } else {

            error = "用户名或者密码错误";

        }

        onError(error,code);
    }

    @Override
    public void onComplete() {

    }

    public abstract void doThings(Object o);

    public abstract void onError(String o,int code);
}
