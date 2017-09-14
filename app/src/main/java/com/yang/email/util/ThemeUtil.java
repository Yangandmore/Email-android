package com.yang.email.util;

import android.app.Activity;

import com.yang.email.R;

/**
 * Created by shiq_yang on 2017/8/31.
 * 主题修改帮助类
 */

public class ThemeUtil {

    private static boolean isLight = true;

    //修改主题
    public static void setIsLight(final Activity activity) {
        ThemeUtil.isLight = !isLight;
        activity.setTheme(ThemeUtil.isLight ? R.style.ThemeLight : R.style.ThemeNight);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.recreate();
            }
        });
    }

    // 设置主题
    public static void setTheme(Activity activity) {
        activity.setTheme(ThemeUtil.isLight ? R.style.ThemeLight : R.style.ThemeNight);
    }

}
