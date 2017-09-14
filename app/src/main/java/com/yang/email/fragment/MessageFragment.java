package com.yang.email.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.email.util.general.GenericFragment;
import com.yang.email.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shiq_yang on 2017/8/28.
 * 邮件消息主页面
 */

public class MessageFragment extends GenericFragment {

    private View rootView;

    @BindView(R.id.mail_box_rv)
    RecyclerView mailBox_rv;
    @BindView(R.id.mail_user_rv)
    RecyclerView userMail_rv;
    @BindView(R.id.message_rv)
    RecyclerView mailList_rv;

    @BindView(R.id.administration_iv)
    ImageView administration_iv;
    @BindView(R.id.search_mail_iv)
    ImageView search_iv;
    @BindView(R.id.mail_new_iv)
    ImageView newMail_iv;

    @Override
    public View initComponent(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_message, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.administration_iv, R.id.search_mail_iv, R.id.mail_new_iv})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.administration_iv:
                // 打开侧边栏
                break;
            case R.id.search_mail_iv:
                // 打开搜索栏
                break;
            case R.id.mail_new_iv:
                // 打开新邮箱
                break;
        }
    }


    @Override
    public void refreshData() {

    }

    @Override
    public void receiverRefresh(Intent intent) {

    }

}
