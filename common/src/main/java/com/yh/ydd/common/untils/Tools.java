package com.yh.ydd.common.untils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Tools {


    //保存token到本地
    public static void saveToken(Context context, String info){

        SharedPreferences sharedPreferences = context.getSharedPreferences("DOAING", Context.MODE_PRIVATE); //私有数据

        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器

        editor.putString("token", "Bearer " + info);

        editor.apply();//提交修改

    }

    //获取token
    public static String getToken(Context context){

        String s = context.getSharedPreferences("DOAING", Context.MODE_PRIVATE).getString("token", null);

        return s;
    }
}
