package com.email.util.general;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.email.util.inter.BaseCallBack;
import com.email.util.utils.BroadcastReceiverUtil;

/**
 * Created by shiq_yang on 2017/8/28.
 * 底层fragment
 */

public abstract class GenericFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = this.initComponent(inflater, container, savedInstanceState);
        this.setListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initData();
    }

    /**
     * 初始化控件
     */
    public abstract View initComponent(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

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

    public abstract void receiverRefresh(Intent intent);
}
