package com.yh.ydd.platform.entrance.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ydd.platfrom.R;
import com.yh.ydd.common.mvp.BaseMvpActivity;
import com.yh.ydd.common.mvp.BasePresenter;
import com.yh.ydd.platform.entrance.register.RegisterActivity;

public class LoginActivity extends BaseMvpActivity {

    LoginPresent loginPresent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.submit);

        loginPresent = (LoginPresent) mPresenter;

        //如果使用LifecycleObserver监听Activity生命周期
        getLifecycle().addObserver(loginPresent);

        EditText name = findViewById(R.id.name_et);
        EditText psw = findViewById(R.id.psw_et);


        button.setOnClickListener((View v) -> {

            if (name.getText().length() == 0) {
                name.setError("名字不能为空");
                return;

            } else if (psw.getText().length() == 0) {
                psw.setError("密码不能为空");
                return;
            }


            loginPresent.submitLogin(name.getText().toString(), psw.getText().toString());


        });

        TextView register = findViewById(R.id.register);

        register.setOnClickListener(v -> {

            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);

        });


    }


    @Override
    protected BasePresenter createPresenter() {
        return new LoginPresent();
    }


}
