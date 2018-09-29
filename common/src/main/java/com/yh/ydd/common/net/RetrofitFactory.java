package com.yh.ydd.common.net;

import android.content.Context;

import com.yh.ydd.common.untils.Config;
import com.yh.ydd.common.untils.Utils;

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


    /**
     * 获取retrofit 实例，初始化okHttp拦截器，添加头部验证信息
     * @param context 上下文
     * @return Retrofit 实例
     */
    public static Retrofit getRetrofitInstance(final Context context) {

        if (retrofit == null) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    String token = Utils.getToken(context.getApplicationContext());

                    if (token == null) {

                        return chain.proceed(chain.request());

                    }else {

                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("Authorization", token)//加入头部校验
                                .build();
                        return chain.proceed(request);
                    }

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
