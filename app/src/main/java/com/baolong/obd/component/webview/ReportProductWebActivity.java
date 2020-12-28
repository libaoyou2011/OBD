package com.baolong.obd.component.webview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baolong.obd.BuildConfig;
import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.utils.FileUtils;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.ToastUtils;

import java.io.File;

/**
 * Created by Lby on 2019/10/9.
 */
public class ReportProductWebActivity extends BaseActivity implements ReportContract.View {

    private final String TAG = ReportProductWebActivity.class.getSimpleName();

    String mTitleName;
    // 预览url
    String mUrl;
    //纪录编号
    String mJlbh;

    ProgressBar webview_progress_bar;
    WebView mWebView;

    boolean isCanSavePhoto = true;
    boolean isShowPhoto = false;

    TextView mTopBarRightTv;
    ReportPresenter mReportPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent intent = getIntent();
        mTitleName = intent.getStringExtra("title");
        mJlbh = intent.getStringExtra("jlbh");
        mUrl = FileUtils.CombineUrl(BaseApplication.host, "/modules/overproof/previewbyjlbh", mJlbh);
        //mJlbh = "B4109270010120190714180952000_291836";

        initTitle();
        initView();

        initPresenter();
    }

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ((TextView) findViewById(R.id.tv_title)).setText(mTitleName);
        this.mTopBarRightTv = ((TextView) findViewById(R.id.tv_right_text));
        this.mTopBarRightTv.setVisibility(View.VISIBLE);
        this.mTopBarRightTv.setText("下载");
        this.mTopBarRightTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mReportPresenter != null) {
                    mReportPresenter.productOneFileInServer(mJlbh);
                }
            }
        });
    }

    private void initView() {
        webview_progress_bar = (ProgressBar) findViewById(R.id.webview_progress_bar);
        mWebView = (WebView) findViewById(R.id.webview);

        /*
         * 用WebSettings对象,用来控制WebView的属性设置来控制WebView的属性设置
         **/
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); //设置WebView属性,运行执行js脚本
        webSettings.setAppCacheEnabled(true); //设置缓存
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //设置缓存模式,一共有 4 种模式
        webSettings.setDefaultTextEncodingName("UTF-8"); //String类型的数据主要是Unicode编码的， 而WebView一般为了节省资源使用的是UTF-8编码
        webSettings.setDisplayZoomControls(false); //缩放控件隐藏掉
        webSettings.setSupportZoom(true);    //支持缩放(适配到当前屏幕)
        webSettings.setUseWideViewPort(true); //将图片调整到合适的大小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);// 支持内容重新布局,一共有 4 种方式, 默认的是NARROW_COLUMNS
        webSettings.setDefaultFontSize(14); //设置默认字体大小
        webSettings.setDefaultFixedFontSize(14); //设置默认字体大小
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        /*
         * 为WebView指定一个WebViewClient对象.WebViewClient可以辅助WebView处理各种通知,请求等事件。
         */
        mWebView.setWebViewClient(new WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                //false：重新加载时，依然时WebView控制，而不是app控制
                return true;
            }

        });

        /*
         * 为WebView指定一个WebChromeClient对象,WebChromeClient专门用来辅助WebView处理js的对话框,网站title,网站图标,加载进度条等
         */
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //定义了一个页面加载进度的progressbar，需要展示给用户的时候，可以通过如下方式获取webview内页面的加载进度
                if (newProgress == 100) {
                    webview_progress_bar.setVisibility(View.GONE);

                } else {

                    if (View.INVISIBLE == webview_progress_bar.getVisibility()) {
                        webview_progress_bar.setVisibility(View.VISIBLE);
                    }

                    webview_progress_bar.setProgress(newProgress);
                }
            }
        });

        CookieSyncManager.createInstance(ReportProductWebActivity.this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        //host: this place should add the login host address(not the login index address)
        //cookies: 是要设置的cookie字符串
        cookieManager.setCookie(BaseApplication.host, BaseApplication.cookie);
        CookieSyncManager.getInstance().sync();

        //给webview加入监听
        //mWebView.setDownloadListener(new MyDownloadListener());

        mWebView.loadUrl(mUrl);
    }

    private void initPresenter() {
        if (mReportPresenter == null) {
            mReportPresenter = new ReportPresenter(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //LogUtil.i(TAG, "----------- onPause -----------");
    }

    @Override
    public void onDestroy() {
        clearCookies();
        super.onDestroy();
    }

    //我们需要重写回退按钮的时间,当用户点击回退按钮：
    //1.webView.canGoBack()判断网页是否能后退,可以则goback()
    //2.如果不可以连续点击两次退出App,否则弹出提示Toast
    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
//            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                Toast.makeText(getApplicationContext(), "再按一次退出程序",
//                        Toast.LENGTH_SHORT).show();
//                exitTime = System.currentTimeMillis();
//            } else {
//                super.onBackPressed();
//            }
            super.onBackPressed();
        }
    }

    private void clearCookies() {
        mWebView.clearCache(true);
        CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();

        //Activity 销毁（ WebView ）的时候，先让 WebView 加载null内容，然后移除 WebView，再销毁 WebView，最后置空。
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
    }

    @Override
    public void setPresenter(ReportContract.Presenter paramT) {

    }

    @Override
    public void showLoading() {
        ((BaseActivity) ReportProductWebActivity.this).showLoading(null);
    }

    @Override
    public void showProductOneFileSuccess(String fileName) {
        if (mReportPresenter != null) {
            String path = FileUtils.getSdRootDir(ReportProductWebActivity.this, FileUtils.CombinePath(BuildConfig.APPLICATION_ID, "file"));
            mReportPresenter.downloadFile(FileUtils.CombineUrl(BaseApplication.host, "/profile/download/", fileName), path, fileName);
        }
    }

    @Override
    public void showProductTwoFileSuccess(String fileName) {
        if (mReportPresenter != null) {
            String path = FileUtils.getSdRootDir(ReportProductWebActivity.this, FileUtils.CombinePath(BuildConfig.APPLICATION_ID, "file"));
            mReportPresenter.downloadFile(FileUtils.CombineUrl(BaseApplication.host, "/profile/download/", fileName), path, fileName);
        }
    }


    @Override
    public void showDownloadFileSuccess(File file) {
        LogUtil.i(TAG, "file path:" + file.getPath());
        ToastUtils.shortToast("文件保存路径:" + file.getPath());
        if (mReportPresenter != null) {
            mReportPresenter.deleteFileInServer(file.getName());
        }
    }

    @Override
    public void showDeleteFileSuccess(String string) {
        //LogUtil.i(TAG, paramString);
    }

    @Override
    public void showFail(String paramString) {
        LogUtil.i(TAG, paramString);
    }

}
