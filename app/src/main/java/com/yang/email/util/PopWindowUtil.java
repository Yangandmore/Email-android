package com.yang.email.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.email.util.models.EmailServerData;
import com.yang.email.adapter.PopWindowRecyclerAdapter;
import com.yang.email.interfac.OnItemClickListener;

import java.util.List;

/**
 * Created by shiq_yang on 2017/9/1.
 * PopupWindow帮助类
 */

public class PopWindowUtil {


    private static PopupWindow popupWindow = null;


    /**
     * 用于打开类似登录下拉用户列表的popupwindow
     */
    public static void openPopwindow(Context context, List<EmailServerData> list, View view, final OnItemClickListener onItemClickListener) {
        RecyclerView recyclerView = new RecyclerView(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        PopWindowRecyclerAdapter adapter = new PopWindowRecyclerAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                onItemClickListener.onItemClick(view, position);
                dismiss();
            }
        });
        popupWindow = new PopupWindow(recyclerView, view.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
                popupWindow = null;
            }
        });
        popupWindow.showAsDropDown(view, 0, 0);
    }

    /**
     * 关闭功能
     */
    public static void dismiss() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

}
