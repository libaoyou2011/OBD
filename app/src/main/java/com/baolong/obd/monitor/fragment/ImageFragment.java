package com.baolong.obd.monitor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baolong.obd.R;
import com.hwangjr.rxbus.RxBus;
import com.baolong.obd.component.media.AppImageDisplay;

public class ImageFragment extends Fragment {
    private String path;
    private int pageIndex;
    private boolean isVideo;

    private ImageView mheaderImg;

    @Override
    public void setArguments(Bundle paramBundle) {
        super.setArguments(paramBundle);
        this.path = paramBundle.getString("image");
        this.pageIndex = paramBundle.getInt("index", 0);
        this.isVideo = paramBundle.getBoolean("isVideo", false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        RxBus.get().register(this);
        View view = paramLayoutInflater.inflate(R.layout.fragment_detail_img, paramViewGroup, false);
        this.mheaderImg = ((ImageView) view.findViewById(R.id.image));

        //glide加载图片
        AppImageDisplay.showImg("profile", path, getContext(), R.drawable.img_monitor_pic, this.mheaderImg);
        return view;
    }

    @Override
    public void onViewCreated(View paramView, @Nullable Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);
    }

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
    }

}
