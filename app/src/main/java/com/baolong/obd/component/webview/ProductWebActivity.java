package com.baolong.obd.component.webview;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baolong.obd.BuildConfig;
import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.utils.DateUtils;
import com.baolong.obd.common.utils.FileUtils;
import com.baolong.obd.common.utils.LogUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Lby on 2019/10/9.
 */
public class ProductWebActivity extends BaseActivity {

    private final String TAG = ProductWebActivity.class.getSimpleName();

    String mTitleName;
    String mUrl;

    ProgressBar webview_progress_bar;
    WebView mWebView;

    boolean isCanSavePhoto = true;
    boolean isShowPhoto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent intent = getIntent();
        mTitleName = intent.getStringExtra("title");
        mUrl = intent.getStringExtra("url");

        initTitle();
        initView();
    }

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ((TextView) findViewById(R.id.tv_title)).setText(mTitleName);
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

        CookieSyncManager.createInstance(ProductWebActivity.this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        //host: this place should add the login host address(not the login index address)
        //cookies: 是要设置的cookie字符串
        cookieManager.setCookie(BaseApplication.host, BaseApplication.cookie);
        CookieSyncManager.getInstance().sync();

        //给webview加入监听
        mWebView.setDownloadListener(new MyDownloadListener());

        mWebView.loadUrl(mUrl);
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


    class MyDownloadListener implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent,
                                    String contentDisposition, String mimetype, long contentLength) {
            //打印
//            System.out.println("url ==== >" + url);
//            System.out.println("userAgent ==== >" + userAgent);
//            System.out.println("contentDisposition ==== >" + contentDisposition);
//            System.out.println("mimetype ==== >" + mimetype);
//            System.out.println("contentLength ==== >" + contentLength);

            //打印结果
//            url ==== >data:image/png;base64,iVBORw0KGgoAAAANSUh...
//            userAgent ==== >
//            contentDisposition ==== >
//            mimetype ==== >
//            contentLength ==== >-1

            //读写SD权限
            RxPermissions rxPermissions = new RxPermissions(ProductWebActivity.this);
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe((Observer<Boolean>) new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {

                                //if (isForeground(ProductWebActivity.this)) {
                                    String filePath = exportToSdCard(url, ".png");
                                    Toast.makeText(ProductWebActivity.this, filePath, Toast.LENGTH_LONG).show();
                                //}

                                //if (isCanSavePhoto) {
                                //调用保存图片
//                                    String filePath = exportToSdCard(url, ".png");
//                                    Toast.makeText(ProductWebActivity.this, filePath, Toast.LENGTH_LONG).show();
                                //}
                                //isCanSavePhoto = !isCanSavePhoto;

                            } else {
                                Toast.makeText(ProductWebActivity.this, R.string.permission_request_denied, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    //保存图片的方法
    public String exportToSdCard(String base64Info, String fileType) {
        String[] arr = base64Info.split("base64,");

        byte[] byteArray = Base64.decode(arr[1].trim(), Base64.DEFAULT);
        InputStream inputStream = new ByteArrayInputStream(byteArray);

        String path = FileUtils.getSdRootDir(ProductWebActivity.this, FileUtils.CombinePath(BuildConfig.APPLICATION_ID, "picture"));
        //LogUtil.i(TAG, "path ==== >" + path);

        String filename = DateUtils.date2Str(new Date(), "yyyy-MM-dd_HH:mm:ss") + fileType;
        //LogUtil.i(TAG, "filename ==== >" + filename);

        File file = FileUtils.write2SDFromInput(path, filename, inputStream);
        if (file != null) {
            LogUtil.i(TAG, "file ==== >" + file.getPath());
        }

        //保存为pdf
        //String pdfPath = path + DateTime.Now.ToFileTime() + ".pdf";
        //ConvertHelper.ConvertJPGToPDF(savePath, pdfPath);

        return FileUtils.CombinePath(path, filename);
    }

    /**
     * 判断某个activity是否在前台显示
     */
    public static boolean isForeground(Activity activity) {
        return isForeground(activity, activity.getClass().getName());
    }

    /**
     * 判断某个界面是否在前台,返回true，为显示,否则不是
     */
    public static boolean isForeground(Activity context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) return true;
        }
        return false;
    }


}
