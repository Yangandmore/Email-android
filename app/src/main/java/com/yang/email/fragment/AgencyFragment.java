package com.yang.email.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.email.util.general.GenericFragment;
import com.yang.email.R;

/**
 * Created by shiq_yang on 2017/9/6.
 */

public class AgencyFragment extends GenericFragment {

    private View rootView;

    @Override
    public View initComponent(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_agency, container, false);
        }
        ViewGroup vp = (ViewGroup) rootView.getParent();
        if (vp != null) {
            vp.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void initData() {

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
}
