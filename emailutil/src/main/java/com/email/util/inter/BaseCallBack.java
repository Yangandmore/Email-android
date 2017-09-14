package com.email.util.inter;

import android.content.Intent;

/**
 * Created by shiq_yang on 2017/8/28.
 * 用于广播回调接口
 */

public interface BaseCallBack {

    // 广播回调
    void receiverRefresh(Intent intent);

}
