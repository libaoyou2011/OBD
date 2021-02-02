package com.baolong.obd.monitor.fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;

import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.utils.Converter;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.view.CustomVideoView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import com.baolong.obd.monitor.event.UpdateJcEvent;

public class VideoFragment extends Fragment {
    private static final String TAG = VideoFragment.class.getSimpleName();
    private int pageIndex;
    private String path;
    private boolean isVideo;

    private Uri uri;
    private CustomVideoView mVideoView;
    private MediaController mediaController;
    private ProgressBar waitLoading;

    @Override
    public void setArguments(Bundle paramBundle) {
        super.setArguments(paramBundle);
        this.path = paramBundle.getString("image");
        this.pageIndex = paramBundle.getInt("index", 0);
        this.isVideo = paramBundle.getBoolean("isVideo", false);

        if (!TextUtils.isEmpty(this.path) && !path.startsWith("http")) {

            if ((!path.startsWith("profile"))) {
                path = Converter.CombineUrl("profile", path);
            }
            path = Converter.CombineUrl(BaseApplication.host, path);

        }
       /* else {

            //因为图片是内网地址, http://10.10.10.243:8015/20191026/146/180234.jpg，此处替换成外网地址
            if (path.contains(ErrorPictureHost)) {
                imagePath = path.replace(ErrorPictureHost, BaseApplication.host.substring(0,19));
            }

        }*/
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        RxBus.get().register(this);
        View view = paramLayoutInflater.inflate(R.layout.fragment_detail_video, paramViewGroup, false);
        this.mVideoView = ((CustomVideoView) view.findViewById(R.id.video_view));
        this.waitLoading = ((ProgressBar) view.findViewById(R.id.pb_loading));

        initVideoPath();
        //initBindControl();
        initListener();

        return view;
    }

    /**
     * 初始化本地或网络播放路径
     */
    private void initVideoPath() {
        LogUtil.i(TAG, "path:" + path);

        if (!TextUtils.isEmpty(this.path)) {
//            if (CommonUtils.isUrl(this.path)) {
                this.uri = Uri.parse(path);
                if (uri != null) {
                    this.mVideoView.setVideoURI(this.uri);
                    this.waitLoading.setVisibility(View.VISIBLE);
                    return;
                }
//            }
        }

        this.waitLoading.setVisibility(View.INVISIBLE);
    }

    /**
     * videoView和MediaController绑定
     */
    private void initBindControl() {
        this.mediaController = new MediaController(getContext());
        this.mVideoView.setMediaController(this.mediaController);
        this.mediaController.setMediaPlayer(this.mVideoView);
    }

    /**
     * videoView 监听器
     */
    private void initListener() {
        this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.start();// 播放
                //Toast.makeText(getContext(), "开始播放！", Toast.LENGTH_LONG).show();
            }
        });

        this.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //Toast.makeText(getActivity(), "播放完毕", Toast.LENGTH_SHORT).show();
            }
        });

        this.mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        VideoFragment.this.waitLoading.setVisibility(View.VISIBLE);
                        return false;
                    case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        VideoFragment.this.waitLoading.setVisibility(View.GONE);
                        return false;
                    default:
                        return false;
                }

            }
        });

        // 视频播放失败时停止播放，禁止"无法播放该视频"弹窗
        this.mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_ERROR_SERVER_DIED) {
                    //媒体服务器挂掉了。此时，程序必须释放MediaPlayer 对象，并重新new 一个新的。
                    //Toast.makeText(getActivity(), "视频网络服务错误", Toast.LENGTH_LONG).show();
                    LogUtil.i(TAG, "视频网络服务错误");
                } else if (what == MediaPlayer.MEDIA_ERROR_UNKNOWN) {
                    if (extra == MediaPlayer.MEDIA_ERROR_IO) {
                        //文件不存在或错误，或网络不可访问错误
                        //Toast.makeText(getActivity(), "视频网络文件错误", Toast.LENGTH_LONG).show();
                        LogUtil.i(TAG, "视频网络文件错误");
                    } else if (extra == MediaPlayer.MEDIA_ERROR_TIMED_OUT) {
                        //超时
                        //Toast.makeText(getActivity(), "视频网络超时", Toast.LENGTH_LONG).show();
                        LogUtil.i(TAG, "视频网络超时");
                    }
                }

                VideoFragment.this.waitLoading.setVisibility(View.GONE);
                mVideoView.stopPlayback(); //播放异常，则停止播放，防止弹窗使界面阻塞, 同时返回true
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            this.mVideoView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void Function(UpdateJcEvent paramUpdateJcEvent) {
        path = paramUpdateJcEvent.getModel().getsp1();
        if (!TextUtils.isEmpty(path)) {
            this.uri = Uri.parse(path);
            this.mVideoView.setVideoURI(this.uri);
            this.waitLoading.setVisibility(View.VISIBLE);
        }
    }

    public void onPause() {
        super.onPause();
        try {
            this.mVideoView.pause();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
    }

}
