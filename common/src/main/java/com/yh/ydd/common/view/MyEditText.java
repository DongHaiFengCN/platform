package com.yh.ydd.common.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.example.ydd.mylibrary.R;


public class MyEditText extends android.support.v7.widget.AppCompatEditText {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackground(context.getApplicationContext().getDrawable(R.drawable.edittext_normal));

        setOnFocusChangeListener((v, hasFocus) -> {

            if (hasFocus) {

                MyEditText.this.setBackground(getResources().getDrawable(R.drawable.edittext_focus));

            } else {

                MyEditText.this.setBackground(getResources().getDrawable(R.drawable.edittext_normal));
            }
        });
    }


}
