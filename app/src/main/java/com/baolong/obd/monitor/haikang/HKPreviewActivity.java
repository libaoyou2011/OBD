package com.baolong.obd.monitor.haikang;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.data.entity.SiteVideoInfo;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.monitor.contract.VideoSiteContract;
import com.baolong.obd.monitor.presenter.VideoSitePresenter;
import com.hikvision.netsdk.ExceptionCallBack;
import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.NET_DVR_PREVIEWINFO;
import com.hikvision.netsdk.PTZCommand;
import com.hikvision.netsdk.RealPlayCallBack;

import org.MediaPlayer.PlayM4.Player;

public class HKPreviewActivity extends BaseActivity
        implements Callback, OnTouchListener, VideoSiteContract.View {
    private final String TAG = HKPreviewActivity.class.getSimpleName();

    private SurfaceView mSurfaceView = null;
    private NET_DVR_DEVICEINFO_V30 mNetDvrDeviceInfoV30 = null;
    private int m_iLogID = -1; // 登录ID， return by NET_DVR_Login_v30
    private int m_iPlayID = -1; // return by NET_DVR_RealPlay_V30
    private int m_iPlaybackID = -1; // return by NET_DVR_PlayBackByTime
    private int m_iPort = -1; // play port
    private int m_iStartChan = 0; // start channel no
    private int m_iChanNum = 0; // channel number
    private boolean m_bNeedDecode = true;
    private boolean m_bStopPlayback = false;
    private Thread thread;
    private boolean isShow = true;
    private Button btnUp;
    private Button btnDown;
    private Button btnLeft;
    private Button btnRight;
    private Button btnZoomIn;
    private Button btnZoomOut;
    private CameraManager cameraManager;

    private TextView titleTV;

    private String dwmh;
    public String ADDRESS;
    public int PORT = 8000;
    public String USER;
    public String PSD;
//    public final String ADDRESS = "36.7.144.179";
//    public final int PORT = 8000;
//    public final String USER = "admin";
//    public final String PSD = "baolong12345";

    private VideoSitePresenter mVideoSitePresenter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haikang_preview);

        Intent intent = getIntent();
        dwmh = intent.getStringExtra("dwbh");

        initTitle();
        initView();
        initHK();
        initPresenter();

        //网络请求
        getData();

        //本地测试 安阳西点位 61.54.242.88:10018
//        ADDRESS = "61.54.242.88";
//        PORT = 10018;
//        USER ="admin";
//        PSD = "baolong12345";
//        loginHK();
//        previewVideoHK();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("m_iPort", m_iPort);
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        m_iPort = savedInstanceState.getInt("m_iPort");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Cleanup();
        m_iLogID = -1;
        // whether we have logout
        if (!HCNetSDK.getInstance().NET_DVR_Logout_V30(m_iLogID)) {
            Log.e(TAG, " NET_DVR_Logout is failed!");
            return;
        }
        stopSinglePreview();
    }

    // @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        Log.i(TAG, "surface is created" + m_iPort);
        if (-1 == m_iPort) {
            return;
        }
        Surface surface = holder.getSurface();
        if (surface.isValid()) {
            if (!Player.getInstance().setVideoWindow(m_iPort, 0, holder)) {
                Log.e(TAG, "Player setVideoWindow failed!");
            }
        }
    }

    // @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "Player setVideoWindow release!" + m_iPort);
        if (-1 == m_iPort) {
            return;
        }
        if (holder.getSurface().isValid()) {
            if (!Player.getInstance().setVideoWindow(m_iPort, 0, null)) {
                Log.e(TAG, "Player setVideoWindow failed!");
            }
        }
    }

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleTV = (TextView) findViewById(R.id.tv_title);
        titleTV.setText(getResources().getString(R.string.monitor_video_title));
    }

    // GUI init
    private void initView() {
        findViews();
        mSurfaceView.getHolder().addCallback(this);
    }

    //init 海康SDK
    private void initHK() {
        CrashUtil crashUtil = CrashUtil.getInstance();
        crashUtil.init(BaseApplication.getInstance());
        initSdk();
    }

    private void initPresenter() {
        this.mVideoSitePresenter = new VideoSitePresenter(this);
    }

    private void getData() {
        this.mVideoSitePresenter.getData(dwmh);
        //showLoading();
    }

    /**
     * @return true:success; false:fail
     *  initSdk
     *  SDK init
     */
    private boolean initSdk() {
        // init net sdk
        if (!HCNetSDK.getInstance().NET_DVR_Init()) {
            Log.e(TAG, "1.1 HCNetSDK init is failed!");
            return false;
        } else {
            Log.i(TAG, "1.1 HCNetSDK init is success!");
            HCNetSDK.getInstance().NET_DVR_SetLogToFile(3, "/mnt/sdcard/sdklog/", true);
            return true;
        }
    }

    //登录
    private void loginHK() {
        // login on the device
        m_iLogID = loginDevice();
        if (m_iLogID < 0) {
            Log.e(TAG, "2.x This device login failed!");
            return;
        } else {
            Log.i(TAG,"2.x This device login success, m_iLogID=" + m_iLogID);
        }

        // get instance of exception callback and set
        ExceptionCallBack exceptionCallBack = getExceptionCallBack();
        if (exceptionCallBack == null) {
            Log.e(TAG, "2.x2 ExceptionCallBack object is failed!");
            return;
        }
        if (!HCNetSDK.getInstance().NET_DVR_SetExceptionCallBack(exceptionCallBack)) {
            Log.e(TAG, "2.x3 NET_DVR_SetExceptionCallBack is failed!");
        }

    }

    //预览
    private void previewVideoHK() {
        //设置默认点
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                while (!Thread.currentThread().isInterrupted()) {
                SystemClock.sleep(1000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isShow)
                            startSinglePreview();
                    }
                });
            }
//            }
        });
        thread.start();

    }

    // get controller instance
    private void findViews() {
        this.mSurfaceView = (SurfaceView) findViewById(R.id.sf_VideoMonitor);

        this.btnZoomOut = (Button) findViewById(R.id.btn_ZoomOut);
        this.btnZoomIn = (Button) findViewById(R.id.btn_ZoomIn);
        this.btnRight = (Button) findViewById(R.id.btn_Right);
        this.btnLeft = (Button) findViewById(R.id.btn_Left);
        this.btnDown = (Button) findViewById(R.id.btn_Down);
        this.btnUp = (Button) findViewById(R.id.btn_Up);
        btnUp.setOnTouchListener(this);
        btnDown.setOnTouchListener(this);
        btnLeft.setOnTouchListener(this);
        btnRight.setOnTouchListener(this);
        btnZoomIn.setOnTouchListener(this);
        btnZoomOut.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        if (!NotNull.isNotNull(cameraManager)) return false;
        Log.d(TAG, "onTouch: ");
        new Thread() {
            @Override
            public void run() {
                switch (v.getId()) {
                    case R.id.btn_Up:
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            cameraManager.startMove(8, m_iLogID);
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            cameraManager.stopMove(8, m_iLogID);
                        }
                        break;
                    case R.id.btn_Left:
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            cameraManager.startMove(4, m_iLogID);
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            cameraManager.stopMove(4, m_iLogID);
                        }
                        break;
                    case R.id.btn_Right:
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            cameraManager.startMove(6, m_iLogID);
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            cameraManager.stopMove(6, m_iLogID);
                        }
                        break;
                    case R.id.btn_Down:
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            cameraManager.startMove(2, m_iLogID);
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            cameraManager.stopMove(2, m_iLogID);
                        }
                        break;
                    case R.id.btn_ZoomIn:
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            cameraManager.startZoom(1, m_iLogID);
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            cameraManager.stopZoom(1, m_iLogID);
                        }
                        break;
                    case R.id.btn_ZoomOut:
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            cameraManager.startZoom(-1, m_iLogID);
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            cameraManager.stopZoom(-1, m_iLogID);
                        }
                        break;
                    default:
                        break;
                }
            }
        }.start();
        return false;
    }

    /**
     * @return login ID
     * @fn loginDevice
     * @author zhangqing
     * @brief login on device
     */
    private int loginDevice() {
        int iLogID = -1;
        iLogID = loginNormalDevice();
        return iLogID;
    }

    /**
     * @return login ID
     * @fn loginNormalDevice
     * @author zhuzhenlei
     * @brief login on device
     */
    private int loginNormalDevice() {
        // get instance
        mNetDvrDeviceInfoV30 = new NET_DVR_DEVICEINFO_V30();
        if (null == mNetDvrDeviceInfoV30) {
            Log.e(TAG, "2.1 new HKNetDvrDeviceInfoV30 is failed!");
            return -1;
        }
        Log.i(TAG, "2.1 new HKNetDvrDeviceInfoV30 is success!");

        // call NET_DVR_Login_v30 to login on, port 8000 as default
        Log.i(TAG, "2.2 " + ADDRESS);
        Log.i(TAG, "2.2 " + PORT);
        Log.i(TAG, "2.2 " + USER);
        Log.i(TAG, "2.2 " + PSD);

        int iLogID = HCNetSDK.getInstance().NET_DVR_Login_V30(ADDRESS, PORT, USER, PSD, mNetDvrDeviceInfoV30);
        if (iLogID < 0) {
            Log.e(TAG, "2.3 NET_DVR_Login is failed! Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            return -1;
        }
        Log.i(TAG, "2.3 NET_DVR_Login is Successful!");

        if (mNetDvrDeviceInfoV30.byChanNum > 0) {
            m_iStartChan = mNetDvrDeviceInfoV30.byStartChan;
            m_iChanNum = mNetDvrDeviceInfoV30.byChanNum;
        } else if (mNetDvrDeviceInfoV30.byIPChanNum > 0) {
            m_iStartChan = mNetDvrDeviceInfoV30.byStartDChan;
            m_iChanNum = mNetDvrDeviceInfoV30.byIPChanNum + mNetDvrDeviceInfoV30.byHighDChanNum * 256;
        }
        return iLogID;
    }

    private void startSinglePreview() {
        if (m_iPlaybackID >= 0) {
            Log.i(TAG, "3.1 Please stop palyback first");
            return;
        }

        RealPlayCallBack fRealDataCallBack = getRealPlayerCbf();
        if (fRealDataCallBack == null) {
            Log.e(TAG, "3.2 fRealDataCallBack object is failed!");
            return;
        }
        Log.i(TAG, "3.2 m_iStartChan:" + m_iStartChan);

        NET_DVR_PREVIEWINFO previewInfo = new NET_DVR_PREVIEWINFO();
        previewInfo.lChannel = m_iStartChan;
        previewInfo.dwStreamType = 0; // substream
        previewInfo.bBlocked = 1;

        m_iPlayID = HCNetSDK.getInstance().NET_DVR_RealPlay_V40(m_iLogID, previewInfo, fRealDataCallBack);
        if (m_iPlayID < 0) {
            Log.e(TAG, "3.3 NET_DVR_RealPlay is failed!  Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            return;
        }
        isShow = false;
        if (NotNull.isNotNull(thread)) {
            thread.interrupt();
        }
        cameraManager = new CameraManager();
        cameraManager.setLoginId(m_iLogID);
        Intent intent = getIntent();
        if (NotNull.isNotNull(intent) && intent.getIntExtra("INDEX", -1) != -1) {
            //int point = app.preferences.getInt("POINT", 0);
            boolean b = HCNetSDK.getInstance().NET_DVR_PTZPreset(m_iPlayID, PTZCommand.GOTO_PRESET,0);
        }
    }

    /**
     * @return NULL
     * @fn stopSinglePreview
     * @author zhuzhenlei
     * @brief stop preview
     */
    private void stopSinglePreview() {
        if (m_iPlayID < 0) {
            Log.e(TAG, "m_iPlayID < 0");
            return;
        }
        // net sdk stop preview
        if (!HCNetSDK.getInstance().NET_DVR_StopRealPlay(m_iPlayID)) {
            Log.e(TAG, "StopRealPlay is failed! Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            return;
        }

        m_iPlayID = -1;
        stopSinglePlayer();
    }

    private void stopSinglePlayer() {
        Player.getInstance().stopSound();
        // player stop play
        if (!Player.getInstance().stop(m_iPort)) {
            Log.e(TAG, "stop is failed!");
            return;
        }

        if (!Player.getInstance().closeStream(m_iPort)) {
            Log.e(TAG, "closeStream is failed!");
            return;
        }
        if (!Player.getInstance().freePort(m_iPort)) {
            Log.e(TAG, "freePort is failed!" + m_iPort);
            return;
        }
        m_iPort = -1;
    }

    /**
     * @return exception instance
     * @fn getExceptiongCbf
     */
    private ExceptionCallBack getExceptionCallBack() {
        return  new ExceptionCallBack() {
            public void fExceptionCallBack(int iType, int iUserID, int iHandle) {
                Log.e(TAG, "2.x1 ExceptionCallBack object is failed!");
                Log.e(TAG, "2.x1 received exception, type:" + iType);
            }
        };
    }

    /**
     * @return callback instance
     * @fn getRealPlayerCbf
     * @brief get realplay callback instance
     */
    private RealPlayCallBack getRealPlayerCbf() {
        return new RealPlayCallBack() {
            public void fRealDataCallBack(int iRealHandle, int iDataType, byte[] pDataBuffer, int iDataSize) {
                // player channel 1
                HKPreviewActivity.this.processRealData(iDataType, pDataBuffer, iDataSize, Player.STREAM_REALTIME);
            }
        };
    }

    /**
     * @param iDataType   - data type [in]
     * @param pDataBuffer - data buffer [in]
     * @param iDataSize   - data size [in]
     * @param iStreamMode - stream mode [in]
     * @return NULL
     * @fn processRealData
     * @author zhuzhenlei
     * @brief process real data
     */
    public void processRealData(int iDataType,
                                byte[] pDataBuffer, int iDataSize, int iStreamMode) {
        if (!m_bNeedDecode) {
            // Log.i(TAG, "iPlayViewNo:" + iPlayViewNo + ",iDataType:" +
            // iDataType + ",iDataSize:" + iDataSize);
        } else {
            if (HCNetSDK.NET_DVR_SYSHEAD == iDataType) {
                if (m_iPort >= 0) {
                    return;
                }
                m_iPort = Player.getInstance().getPort();
                if (m_iPort == -1) {
                    Log.e(TAG, "getPort is failed with: "
                            + Player.getInstance().getLastError(m_iPort));
                    return;
                }
                Log.i(TAG, "getPort succ with: " + m_iPort);
                if (iDataSize > 0) {
                    // set stream mode
                    if (!Player.getInstance().setStreamOpenMode(m_iPort, iStreamMode)) {
                        Log.e(TAG, "setStreamOpenMode failed");
                        return;
                    }
                    // open stream
                    if (!Player.getInstance().openStream(m_iPort, pDataBuffer,
                            iDataSize, 2 * 1024 * 1024)) {
                        Log.e(TAG, "openStream failed");
                        return;
                    }
                    if (!Player.getInstance().play(m_iPort, mSurfaceView.getHolder())) {
                        Log.e(TAG, "play failed");
                        return;
                    }
                    if (!Player.getInstance().playSound(m_iPort)) {
                        Log.e(TAG, "playSound failed with error code:"
                                + Player.getInstance().getLastError(m_iPort));
                        return;
                    }
                }
            } else {
                if (!Player.getInstance().inputData(m_iPort, pDataBuffer,
                        iDataSize)) {
                    // Log.e(TAG, "inputData failed with: " +
                    // Player.getInstance().getLastError(m_iPort));
                    for (int i = 0; i < 4000 && m_iPlaybackID >= 0
                            && !m_bStopPlayback; i++) {
                        if (Player.getInstance().inputData(m_iPort, pDataBuffer, iDataSize)) {
                            break;
                        }

                        if (i % 100 == 0) {
                            Log.e(TAG, "inputData failed with: "
                                    + Player.getInstance().getLastError(m_iPort) + ", i:" + i);
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * @return NULL
     * @fn Cleanup
     * @author zhuzhenlei
     * @brief cleanup
     */
    public void Cleanup() {
        // release player resource

        Player.getInstance().freePort(m_iPort);
        m_iPort = -1;
        // release net SDK resource
        HCNetSDK.getInstance().NET_DVR_Cleanup();
    }

    @Override
    public void setPresenter(VideoSiteContract.Presenter paramT) {

    }

    @Override
    public void hideLoading() {
        ((BaseActivity) HKPreviewActivity.this).hideLoading();
    }

    @Override
    public void setData(SiteVideoInfo siteVideoInfo) {
        LogUtil.i(TAG, "球机名称：" + siteVideoInfo.getQjmc() );
        if (siteVideoInfo != null) {

            if (!TextUtils.isEmpty(siteVideoInfo.getQjmc())) {
                titleTV.setText(siteVideoInfo.getQjmc());
            }

            try {
                ADDRESS = siteVideoInfo.getFwip();
                if (!TextUtils.isEmpty(siteVideoInfo.getFwport())) {
                    PORT = Integer.parseInt(siteVideoInfo.getFwport()); //可能异常
                }
                USER = siteVideoInfo.getUsername();
                PSD = siteVideoInfo.getPassword();
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            if (!TextUtils.isEmpty(ADDRESS) && PORT >= 0 && !TextUtils.isEmpty(USER) && !TextUtils.isEmpty(PSD)) {
                Log.i(TAG, "2.0 ip port username password check success!");
                loginHK();
                previewVideoHK();
            } else {
                Log.i(TAG, "2.0 ip port username password check failed!");
                ToastUtils.longToast("球机信息不可用");
            }
        }
    }

    @Override
    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    @Override
    public void showLoading() {
        ((BaseActivity) HKPreviewActivity.this).showLoading(getWindow().getDecorView());
    }

}
