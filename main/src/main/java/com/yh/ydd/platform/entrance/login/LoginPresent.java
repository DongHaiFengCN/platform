package com.yh.ydd.platform.entrance.login;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.yh.ydd.common.mvp.BasePresenter;
import com.yh.ydd.common.mvp.ILifecyclePresenter;
import com.yh.ydd.common.net.RetrofitFactory;
import com.yh.ydd.common.untils.ErrorCodeProfile;
import com.yh.ydd.common.untils.LoginResponseBody;
import com.yh.ydd.common.untils.MyObserver;
import com.yh.ydd.common.untils.Utils;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

import static com.yh.ydd.common.untils.ErrorCodeProfile.FILENOTFOUND;
import static com.yh.ydd.common.untils.ErrorCodeProfile.UNAUTHORIZED;


public class LoginPresent extends BasePresenter implements ILifecyclePresenter {



    @Override
    public void onCreate() {


    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onLifecycleChanged(@NonNull LifecycleOwner owner, @NonNull Lifecycle.Event event) {

    }

    @SuppressLint("CheckResult")
    public void submitLogin(String name, String psw) {



        //获取网络访问对象
        Retrofit retrofit = RetrofitFactory.getRetrofitInstance(getBaseApplication());

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

                //验证成功
                if (LoginResponseBody.SUCCESS.equals(loginResponseBody.getStatus())) {


                    //每次登录获取新token
                    if (true) {

                        String token = loginResponseBody.getData().getAuth().getToken();
                        Utils.saveToken(getBaseApplication(), token);

                    }

                    LoginActivity loginActivity = (LoginActivity) getView();
                    loginActivity.success();

                }


            }

            @Override
            public void onError(String o, int code) {
                Toast.makeText(getBaseApplication(), o, Toast.LENGTH_SHORT).show();
            }

        };

        //登录测试
        personService.pushPerson(person).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(myObserver);

        //口味返回列表token测试
   /*     personService.pushToken("7288c3ef")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver() {
            @Override
            public void doThings(Object o) {

                ResponseBody responseBody = (ResponseBody) o;
                try {
                    Log.e("DOAING", responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String o, int code) {
                if (UNAUTHORIZED == code) {

                    Toast.makeText(getBaseApplication(), o, Toast.LENGTH_SHORT).show();

                }else if(FILENOTFOUND == code){

                    Toast.makeText(getBaseApplication(), o, Toast.LENGTH_SHORT).show();
                }

            }
        });*/


    }

}
