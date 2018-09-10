package com.yh.ydd.platform.entrance.login;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {

    @GET("doaing")
    Observable<User> getUser();
    @GET("user")
    Observable<User> getUser1();
}
