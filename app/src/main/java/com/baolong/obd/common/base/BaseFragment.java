package com.baolong.obd.common.base;

import android.content.Context;
import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import com.baolong.obd.common.utils.Utils;

public class BaseFragment extends Fragment {
//    protected BaseActivity mActivity;

    public void refresh() {
    }


    @Override
    public void onAttach(Context paramContext) {
        super.onAttach(paramContext);
        //this.mActivity = ((BaseActivity) paramContext);
    }


    protected BaseActivity getHoldingActivity() {
        return (BaseActivity) this.getActivity();
    }

    protected void addFragment(BaseFragment paramBaseFragment, @IdRes int paramInt) {
        Utils.checkNotNull(paramBaseFragment);
        getHoldingActivity().addFragment(paramBaseFragment, paramInt);
    }

    protected void popFragment() {
        getHoldingActivity().popFragment();
    }

    protected void removeFragment(BaseFragment paramBaseFragment) {
        Utils.checkNotNull(paramBaseFragment);
        getHoldingActivity().removeFragment(paramBaseFragment);
    }

    protected void replaceFragment(BaseFragment paramBaseFragment, @IdRes int paramInt) {
        Utils.checkNotNull(paramBaseFragment);
        getHoldingActivity().replaceFragment(paramBaseFragment, paramInt);
    }

    protected void showFragment(BaseFragment paramBaseFragment) {
        Utils.checkNotNull(paramBaseFragment);
        getHoldingActivity().showFragment(paramBaseFragment);
    }

    protected void hideFragment(BaseFragment paramBaseFragment) {
        Utils.checkNotNull(paramBaseFragment);
        getHoldingActivity().hideFragment(paramBaseFragment);
    }

}

