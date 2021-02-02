package com.baolong.obd.execution.adapter;

import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import java.util.List;

import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;
    private List<String> mTabTitles;

    public ViewPagerFragmentAdapter(final FragmentManager fragmentManager, final List<Fragment> mList, final List<String> mTabTitles) {
        super(fragmentManager);
        this.mList = new ArrayList<Fragment>();
        this.mTabTitles = new ArrayList<String>();
        this.mList = mList;
        this.mTabTitles = mTabTitles;
    }

    @Override
    public int getCount() {
        final List<Fragment> mList = this.mList;
        int size = 0;
        if (mList != null) {
            size = this.mList.size();
        }
        return size;
    }

    @Override
    public Fragment getItem(final int n) {
        final List<Fragment> mList = this.mList;
        Fragment fragment = null;
        if (mList != null) {
            fragment = this.mList.get(n);
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(final int n) {
        return this.mTabTitles.get(n);
    }
}
