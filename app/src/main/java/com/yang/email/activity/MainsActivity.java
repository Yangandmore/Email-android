package com.yang.email.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.email.util.general.GenericFragment;
import com.email.util.general.GenericFragmentActivity;
import com.yang.email.R;
import com.yang.email.fragment.AgencyFragment;
import com.yang.email.fragment.ContactsFragment;
import com.yang.email.fragment.MeFragment;
import com.yang.email.fragment.MessageFragment;
import com.yang.email.util.ThemeUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainsActivity extends GenericFragmentActivity {

    @BindView(R.id.bottom_rg)
    RadioGroup bottom_rg;

    Map<Integer, GenericFragment> fragmentMap = new HashMap<>();
    GenericFragment fragment = new MessageFragment();
    GenericFragment lastFragment = fragment;

    @Override
    public void initComponent() {
        ThemeUtil.setTheme(this);
        setContentView(R.layout.activity_mains);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentMap.put(0, fragment);
        fragmentTransaction.add(R.id.content_fl, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setListener() {
        bottom_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.message_rb:
                        setFragment(0);
                        break;

                    case R.id.agency_rb:
                        setFragment(1);
                        break;

                    case R.id.contacts_rb:
                        setFragment(2);
                        break;

                    case R.id.me_rb:
                        setFragment(3);
                        break;
                }
            }
        });
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void receiverRefresh(Intent intent) {
        fragment.receiverRefresh(intent);
    }

    /**
     * 选择fragment功能列表
     */
    private void setFragment(int id) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragmentMap.containsKey(id)) {
            if (lastFragment != null)
                fragmentTransaction.hide(lastFragment);
            fragment = fragmentMap.get(id);
            lastFragment = fragment;
            fragmentTransaction.show(fragment);
        } else {
            switch (id) {
                case 0:
                    fragment = new MessageFragment();
                    break;
                case 1:
                    fragment = new AgencyFragment();
                    break;
                case 2:
                    fragment = new ContactsFragment();
                    break;
                case 3:
                    fragment = new MeFragment();
                    break;
            }
            fragmentTransaction.add(R.id.content_fl, fragment);
            fragmentMap.put(id, fragment);
            if (lastFragment != null)
                fragmentTransaction.hide(lastFragment);
            lastFragment = fragment;
        }
        fragmentTransaction.commit();
    }

}
