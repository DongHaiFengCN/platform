package com.yh.ydd.common.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ydd.mylibrary.R;

public class SearchComponent extends LinearLayout {
    public SearchComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //加载视图
        LayoutInflater.from(context).inflate(R.layout.search, this);
        EditText editText = findViewById(R.id.search_et);
        Button cancelBt = findViewById(R.id.search_cancel_bt);

        ImageView cleanIm = findViewById(R.id.search_clean_im);

        cancelBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                cleanIm.setVisibility(INVISIBLE);
                //取消焦点
                editText.clearFocus();
                //关闭键盘
                hideKeyboard();
                cancelBt.setVisibility(INVISIBLE);

            }
        });

        cleanIm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                editText.setText("");
            }
        });


        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {

                    editText.setHint("");
                    cancelBt.setVisibility(VISIBLE);

                } else {

                    editText.setHint("🔍 搜索");
                    cancelBt.setVisibility(INVISIBLE);
                }

            }
        });


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {

                    if (cleanIm.getVisibility() == INVISIBLE) {

                        cleanIm.setVisibility(VISIBLE);
                    }

                    //TODO 正式发起搜索


                } else {

                    cleanIm.setVisibility(INVISIBLE);
                }

            }
        });
    }

    private void hideKeyboard() {

        Activity activity = (Activity) getContext();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


}
