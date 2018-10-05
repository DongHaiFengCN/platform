package com.yh.ydd.platform.entrance.register;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ydd.platfrom.R;
import com.yh.ydd.common.view.MyEditText;

public class PlatformFragment extends Fragment implements RegisterVerificationCode {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first,null);

        MyEditText companyEt = view.findViewById(R.id.companyName);
        MyEditText emailEt = view.findViewById(R.id.email);
        MyEditText password = view.findViewById(R.id.password);

        MyEditText currentPassword = view.findViewById(R.id.currentPassword);

        currentPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!password.getText().equals(s.toString())){

                    currentPassword.setError("两次密码不一致！");
                }

            }
        });



        return view;
    }

    @Override
    public boolean isSuccess() {

        return false;
    }
}
