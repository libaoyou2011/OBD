package com.baolong.obd.blackcar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList = new ArrayList();
    private List<String> mTabTitles = new ArrayList();

    public ViewPagerFragmentAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList, List<String> titleList) {
        super(fragmentManager);
        this.mList = fragmentList;
        this.mTabTitles = titleList;
    }

    public int getCount() {
        List localList = this.mList;
        int i = 0;
        if (localList != null) {
            i = this.mList.size();
        }
        return i;
    }

    public Fragment getItem(int paramInt) {
        List localList = this.mList;
        Fragment localFragment = null;
        if (localList != null) {
            localFragment = (Fragment) this.mList.get(paramInt);
        }
        return localFragment;
    }

    public CharSequence getPageTitle(int paramInt) {
        return (CharSequence) this.mTabTitles.get(paramInt);
    }
}
