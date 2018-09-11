package com.yh.ydd.platform.entrance.login;

import com.yh.ydd.common.untils.LoginResponseBody;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PersonService {

    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("auth/login")
    Observable<LoginResponseBody> pushPerson(@Body Person person);



    @GET("tastes")
    Observable<ResponseBody> pushToken(@Query(" channel") String channle);

}
