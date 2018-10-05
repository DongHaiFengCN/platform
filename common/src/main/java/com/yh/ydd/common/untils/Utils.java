package com.yh.ydd.common.untils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Utils {
   private static final String NAME ="DOAING";
    private static final String KEY ="token";


    /**
     * 保存token到本地
     * @param context
     * @param info
     */
    public static void saveToken(Context context, String info){

        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, MODE_PRIVATE); //私有数据

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY, "Bearer " + info);

        editor.apply();

    }

    /**
     * 获取token
     * @param context
     * @return
     */
    public static String getToken(Context context){

        return context.getSharedPreferences(NAME, MODE_PRIVATE).getString(KEY, null);
    }
}
