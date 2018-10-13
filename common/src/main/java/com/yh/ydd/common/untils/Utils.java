package com.yh.ydd.common.untils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Utils {
   private static final String NAME ="DOAING";
    private static final String KEY ="token";



    /**
     * 保存token到本地
     * @param context 上下文
     * @param info token
     */
    public static void saveToken(Context context, String info){

        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, MODE_PRIVATE); //私有数据

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY, "Bearer " + info);

        editor.apply();

    }

    /**
     * 获取token
     * @param context 上下文
     * @return 返回token
     */
    public static String getToken(Context context){

        return context.getSharedPreferences(NAME, MODE_PRIVATE).getString(KEY, null);
    }

    /**
     * 获取系统状态栏高度
     *
     * @return 返回状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取虚拟按键的高度
     * @param context 上线文
     * @return 返回虚拟按键高度
     */
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }



    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context 上下文
     * @return 查看是否存在虚拟按键
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return 返回打开参数
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                @SuppressLint("PrivateApi") Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable ignored) {
            }
        }
        return sNavBarOverride;
    }


    /**
     * 隐藏键盘
     * @param context 上线文
     */
    public static void hideKeyboard(Context context) {

        Activity activity = (Activity) context;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        if (imm.isActive()) {
            if (Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

}
