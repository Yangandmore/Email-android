package com.yang.email.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.email.util.models.EmailServerData;
import com.email.util.general.GenericActivity;
import com.komi.slider.SliderUtils;
import com.yang.email.R;
import com.yang.email.application.EmailApplication;
import com.yang.email.util.ThemeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginSettingActivity extends GenericActivity {

    @BindView(R.id.receiverserver_et)
    EditText receiverserver_et;
    @BindView(R.id.sendserver_et)
    EditText sendservier_et;


    EmailServerData emailServerData;


    @Override
    public void initComponent() {
        ThemeUtil.setTheme(this);
        setContentView(R.layout.activity_login_setting);
        ButterKnife.bind(this);
        SliderUtils.attachActivity(this, EmailApplication.sliderConfig);
    }

    @Override
    public void initData() {

        /**
         * 得到返回数据
         */
        Intent intent = getIntent();
        emailServerData = (EmailServerData) intent.getSerializableExtra(LoginActivity.EmailServerData_key);
        receiverserver_et.setText(TextUtils.isEmpty(emailServerData.getReceiverServerString()) ? "":emailServerData.getReceiverServerString());
        sendservier_et.setText(TextUtils.isEmpty(emailServerData.getSendServerString()) ? "":emailServerData.getSendServerString());
    }

    @Override
    public void setListener() {

    }

    @Override
    public void refreshData() {

    }

    @Override
    public void receiverRefresh(Intent intent) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(Activity.RESULT_CANCELED);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 返回按钮
     */
    public void onQuit(View view) {
        onKeyDown(4, new KeyEvent(0, 4));
    }

    /**
     * 确认按钮
     */
    public void onConfirm(View view) {
        /**
         * 退出后返回设置的服务器
         */
        Intent intent = new Intent();
        emailServerData.setSendServerString(TextUtils.isEmpty(sendservier_et.getText().toString()) ? "":sendservier_et.getText().toString());
        emailServerData.setReceiverServerString(TextUtils.isEmpty(receiverserver_et.getText().toString()) ? "":receiverserver_et.getText().toString());
        intent.putExtra(LoginActivity.EmailServerData_key, emailServerData);
        setResult(Activity.RESULT_OK, intent);
        this.finish();
    }

    /**
     * 清除按钮
     */
    public void onClear(View view) {
        switch (view.getId()) {
            case R.id.clear_receiver_iv:
                receiverserver_et.setText("");
                break;
            case R.id.clear_send_iv:
                sendservier_et.setText("");
                break;
        }
    }
}
