package com.email.util.utils;

import android.content.Context;

import com.email.util.base.GlobalConstants;

/**
 * Created by shiq_yang on 2017/8/28.
 * 所有初始化功能
 */

public class InitUtil {

    static Context context;

    public static void init(Context context, String dbPath) {
        InitUtil.context = context;
        GlobalConstants.DbFileRoute.ROUTE_DB = dbPath;
    }

    public static Context getContext() {
        return InitUtil.context;
    }
}
