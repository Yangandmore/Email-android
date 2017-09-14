package com.email.util.general;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.email.util.inter.BaseCallBack;
import com.email.util.utils.BroadcastReceiverUtil;

/**
 * Created by shiq_yang on 2017/8/28.
 * 底层fragmentactivity
 */

public abstract class GenericFragmentActivity extends FragmentActivity implements BaseCallBack {

    protected BroadcastReceiverUtil broadcastReceiverUtil;

    /**
     * 所有fragmentactivity状态
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 广播注册
         */
        broadcastReceiverUtil = new BroadcastReceiverUtil(this);
        /**
         * 初始化
         */
        this.initComponent();
        this.setListener();
        this.initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // 解绑广播
        broadcastReceiverUtil.unregisterReceiver();
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * 初始化控件
     */
    public abstract void initComponent();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 设置事件
     */
    public abstract void setListener();

    /**
     * 刷新数据
     */
    public abstract void refreshData();

    @Override
    public abstract void receiverRefresh(Intent intent);
}
