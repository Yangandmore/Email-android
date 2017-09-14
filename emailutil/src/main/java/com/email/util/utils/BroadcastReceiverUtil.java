package com.email.util.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.email.util.inter.BaseCallBack;
import com.email.util.models.BroadcastInfo;

/**
 * Created by shiq_yang on 2017/8/28.
 * 广播消息传递机制
 */

public class BroadcastReceiverUtil extends BroadcastReceiver {

    private Context context;
    private BaseCallBack baseCallBack;
    /**
     * 广播动作
     */
    public static final String BROAD_ACTION = "com.email.base.BroadcastReceiver.action";
    /**
     * 广播tag
     */
    public static final String BROAD_TAG = "com.email.base.BroadcastReceiver";


    public BroadcastReceiverUtil (Context context) {
        this.context = context;
        /**
         * 注册广播
         */
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROAD_ACTION);
        context.registerReceiver(this, filter);
    }

    public BroadcastReceiverUtil (Context context, BaseCallBack baseCallBack) {
        this.context = context;
        this.baseCallBack = baseCallBack;
        /**
         * 注册广播
         */
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROAD_ACTION);
        context.registerReceiver(this, filter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(BROAD_ACTION))
            // 发送数据通知界面
            if (baseCallBack != null)
                baseCallBack.receiverRefresh(intent);
            else if (context != null)
                ((BaseCallBack)context).receiverRefresh(intent);
    }

    //发送广播，需要确认消息类型
    public static void sendBroadcast(BroadcastInfo info) {
        Intent intent = new Intent(BROAD_ACTION);

        intent.putExtra(BROAD_TAG, info);

        InitUtil.getContext().sendBroadcast(intent);
    }

    // 解绑广播
    public void unregisterReceiver() {
        if (context != null)
            context.unregisterReceiver(this);
    }


}
