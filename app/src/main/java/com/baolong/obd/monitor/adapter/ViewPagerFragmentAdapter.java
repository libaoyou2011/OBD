package com.baolong.obd.monitor.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList;
    private final List<String> mTabTitles;

    public ViewPagerFragmentAdapter(FragmentManager paramFragmentManager, List<Fragment> fragmentList, List<String> titleList) {
        super(paramFragmentManager);
        this.mFragmentList = fragmentList;
        this.mTabTitles = titleList;
    }

    @Override
    public int getCount() {
        if (mFragmentList == null) {
            return 0;
        }
        return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragmentList != null && position < mFragmentList.size()) {
            return mFragmentList.get(position);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int paramInt) {
        return (CharSequence) this.mTabTitles.get(paramInt);
    }

}
