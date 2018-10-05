package com.yh.ydd.platform.entrance.register;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ydd.platfrom.R;
import com.tuo.customview.VerificationCodeView;

public class TelephoneFragment extends Fragment implements RegisterVerificationCode{

    private VerificationCodeView verificationCodeView;
    private boolean flag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.second, null);

        verificationCodeView = view.findViewById(R.id.icv_1);

        verificationCodeView.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                String content = verificationCodeView.getInputContent();

                if (content.length() == verificationCodeView.getEtNumber()) {

                    Log.e("DOAING", content);
                    //TODO 去验证验证码的正确性
                    flag = true;


                }



            }

            @Override
            public void deleteContent() {

            }
        });


        return view;
    }


    @Override
    public boolean isSuccess() {
        return false;
    }
}
