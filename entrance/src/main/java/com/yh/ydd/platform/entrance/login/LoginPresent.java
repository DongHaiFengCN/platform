package com.yh.ydd.platform.entrance.login;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.yh.ydd.common.mvp.BasePresenter;
import com.yh.ydd.common.mvp.ILifecyclePresenter;
import com.yh.ydd.common.net.RetrofitFactory;
import com.yh.ydd.common.untils.ErrorCodeProfile;
import com.yh.ydd.common.untils.LoginResponseBody;
import com.yh.ydd.common.untils.MyObserver;
import com.yh.ydd.common.untils.Tools;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

import static com.yh.ydd.common.untils.ErrorCodeProfile.UNAUTHORIZED;


public class LoginPresent extends BasePresenter implements ILifecyclePresenter {


    String token;

    @Override
    public void onCreate() {

        //channel 7288c3ef

        token = Tools.getToken(getBaseApplication());

        // Log.e("DOAINGH",token);

    }

    @Override
    public void onDestroy() {
        Log.e("DOAING", "onDestroy");
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @SuppressLint("CheckResult")
    public void submitLogin(String name, String psw) {

        Retrofit retrofit = RetrofitFactory.getInstance(getBaseApplication());

        PersonService personService = retrofit.create(PersonService.class);
        Person person = new Person();
        person.setMobile(name);
        person.setPwd(psw);
        person.setRemember(true);

        MyObserver myObserver = new MyObserver() {
            @Override
            public void doThings(Object o) {

                LoginResponseBody loginResponseBody = (LoginResponseBody) o;
                Log.e("DOAING", loginResponseBody.getData().getChannelId());

                if ("ok".equals(loginResponseBody.getStatus())) {

                    //每次登录获取新token
                    if (true) {

                        String token = loginResponseBody.getData().getAuth().getToken();
                        Tools.saveToken(getBaseApplication(), token);
                    }
                }
            }

            @Override
            public void onError(String o, int code) {
                Toast.makeText(getBaseApplication(), o, Toast.LENGTH_SHORT).show();
            }


        };

       // personService.pushPerson(person).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(myObserver);

        personService.pushToken("7288c3ef").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MyObserver() {
            @Override
            public void doThings(Object o) {

                ResponseBody responseBody = (ResponseBody) o;
                try {
                    Log.e("DOAING", responseBody.string().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String o, int code) {

                if (UNAUTHORIZED == code) {

                    Toast.makeText(getBaseApplication(), o, Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

}
