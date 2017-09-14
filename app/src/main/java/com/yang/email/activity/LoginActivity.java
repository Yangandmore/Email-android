package com.yang.email.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.email.util.DBUtils.EmailServerDBManager;
import com.email.util.general.GenericActivity;
import com.email.util.models.EmailServerData;
import com.yang.email.R;
import com.yang.email.adapter.PopWindowRecyclerAdapter;
import com.yang.email.application.EmailApplication;
import com.yang.email.interfac.OnItemClickListener;
import com.yang.email.util.AnimotionUtil;
import com.yang.email.util.PopWindowUtil;
import com.yang.email.util.SoftKeyboardStateUtil;
import com.yang.email.util.ThemeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yang.email.application.EmailApplication.context;

public class LoginActivity extends GenericActivity {

    public final static int REQ_CODE = 101;
    public final static String EmailServerData_key = "EmailServerData";

    @BindView(R.id.login_main_rl)
    RelativeLayout main_rl;

    @BindView(R.id.username_et)
    EditText username_et;

    @BindView(R.id.password_et)
    EditText password_et;

    @BindView(R.id.icon_iv)
    ImageView icon_iv;

    @BindView(R.id.login_rl)
    RelativeLayout login_rl;

    EmailServerData emailServerData = new EmailServerData();
    List<EmailServerData> list = new ArrayList<>();
    SoftKeyboardStateUtil softKeyboardStateUtil;


    @Override
    public void initComponent() {
        ThemeUtil.setTheme(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // 第一次进入现实图片
        if (EmailApplication.isFirst) {
            AnimotionUtil.startAppAnimotion(this, icon_iv, login_rl);
            EmailApplication.isFirst = false;
        } else {
            icon_iv.setVisibility(View.GONE);
            login_rl.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void initData() {
        softKeyboardStateUtil = new SoftKeyboardStateUtil(main_rl);
        list = EmailServerDBManager.getInstance().query();
    }

    @Override
    public void setListener() {
        softKeyboardStateUtil.addSoftKeyboardStateListener(new SoftKeyboardStateUtil.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                // 打开软键盘
                if (username_et.hasFocus()) {
                    // 打开下拉列表
                    PopWindowUtil.openPopwindow(LoginActivity.this, list, username_et, new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            // 点击事件
                            username_et.setText(list.get(position).getEmailNameString());
                        }
                    });
                } else {
                    PopWindowUtil.dismiss();
                }
            }

            @Override
            public void onSoftKeyboardClosed() {
                // 关闭软键盘
                PopWindowUtil.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE:
                // 返回服务器地址
                if (resultCode == Activity.RESULT_OK) {
                    emailServerData = (EmailServerData) data.getSerializableExtra(EmailServerData_key);
                }

                break;
        }
    }

    @Override
    public void refreshData() {

    }

    // 广播回调
    @Override
    public void receiverRefresh(Intent intent) {

    }

    /**
     * 登录
     */
    public void onLogin(View view) {
        if (TextUtils.isEmpty(username_et.getText().toString()) || TextUtils.isEmpty(password_et.getText().toString())) {
            Toast.makeText(this, "请输入邮箱及密码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(emailServerData.getSendServerString()) || TextUtils.isEmpty(emailServerData.getReceiverServerString())) {
            Toast.makeText(this, "请设置服务器地址", Toast.LENGTH_SHORT).show();
        } else {
            /**
             * TODO 登录
             */

            Intent intent = new Intent(this, MainsActivity.class);
            this.startActivity(intent);
            this.finish();

//            emailServerData.setEmailNameString(username_et.getText().toString());
//            emailServerData.setEmailPasswordString(password_et.getText().toString());
//            EmailServerDBManager.getInstance().insert(emailServerData);

        }
    }

    /**
     * 设置
     */
    public void onSetting(View view) {
        Intent intent = new Intent(this, LoginSettingActivity.class);
        intent.putExtra(EmailServerData_key, emailServerData);
        this.startActivityForResult(intent, REQ_CODE);
    }

    /**
     * 清空数据
     */
    public void onClear(View view) {
        switch (view.getId()) {
            case R.id.clear_email_iv:
                username_et.setText("");
                break;

            case R.id.clear_password_iv:
                password_et.setText("");
                break;
        }
    }

    /**
     * 修改主题
     */
    public void onSetTheme(View view) {

        ThemeUtil.setIsLight(this);
    }
}
