package com.yh.ydd.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ydd.mylibrary.R;
import com.yh.ydd.common.basedo.Search;
import com.yh.ydd.common.untils.Utils;

import java.util.HashMap;
import java.util.List;

public class SearchComponent extends LinearLayout {

    private SearchResponseListener searchResponseListener;

    private String hint ="🔍 搜索";

    /**
     * 查询方法
     */
    private Search search;



    /**
     * 查询返回值
     */
    private List<HashMap> response;

    /**
     *
     *设置查询类型
     * @param search
     */
    public void setSearchModel(Search search) {

        this.search = search;
    }


    public SearchComponent(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }
    public SearchComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //加载视图
        LayoutInflater.from(context).inflate(R.layout.search, this);
        //获取自定义属性的值
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Hint, defStyleAttr, 0);

        hint = a.getString(R.styleable.Hint_hint);

        EditText editText = findViewById(R.id.search_et);
        editText.setHint(hint);
        Button cancelBt = findViewById(R.id.search_cancel_bt);

        ImageView cleanIm = findViewById(R.id.search_clean_im);

        cancelBt.setOnClickListener(v -> {

            editText.setText("");
            cleanIm.setVisibility(INVISIBLE);
            //取消焦点
            editText.clearFocus();
            //关闭键盘
            Utils.hideKeyboard(getContext());

            cancelBt.setVisibility(INVISIBLE);

        });

        cleanIm.setOnClickListener(v -> editText.setText(""));


        editText.setOnFocusChangeListener((v, hasFocus) -> {

            if (hasFocus) {

                editText.setHint("");
                cancelBt.setVisibility(VISIBLE);

            } else {

                editText.setHint(hint);
                cancelBt.setVisibility(INVISIBLE);
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
                    //返回实际的数据
                    response = search.toSearch(s.toString());


                    searchResponseListener.searchListener(response);




                } else {

                    cleanIm.setVisibility(INVISIBLE);
                }

            }
        });
    }

    public void addSearchListener(SearchResponseListener searchResponseListener){

        this.searchResponseListener = searchResponseListener;
    }

    public interface SearchResponseListener{

        void searchListener(List<HashMap> hashMapList);
    }



}
