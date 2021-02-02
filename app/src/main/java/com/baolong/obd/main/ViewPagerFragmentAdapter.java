package com.baolong.obd.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.baolong.obd.common.base.BaseFragment;

import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragmentList;

    public ViewPagerFragmentAdapter(FragmentManager paramFragmentManager, List<BaseFragment> paramList) {
        super(paramFragmentManager);
        this.mFragmentList = paramList;
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
}
