package com.yh.ydd.common.net;

import android.content.Context;

import com.yh.ydd.common.untils.Config;
import com.yh.ydd.common.untils.Tools;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static Retrofit retrofit;


    public static Retrofit getInstance(final Context context) {

        if (retrofit == null) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    String token = Tools.getToken(context);

                    //token 是空的做处理
                    if (token == null) {

                        //这里可以做一些事情，当前不拦截，直接返回

                        return chain.proceed(chain.request());
                    }

                    //token 不为空的时候
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Authorization", token)//加入头部校验
                            .build();
                    return chain.proceed(request);

                }
            });

            retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(Config.URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


}
