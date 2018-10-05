package com.yh.ydd.platform.entrance.register;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ydd.platfrom.R;

public class RoleFragment extends Fragment implements RegisterVerificationCode {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.third,null);


        return view;
    }


    @Override
    public boolean isSuccess() {
        return false;
    }
}
