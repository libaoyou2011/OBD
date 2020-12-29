package com.baolong.obd.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baolong.obd.R;
import com.baolong.obd.common.base.Constance;
import com.baolong.obd.contract.IContract;
import com.hwangjr.rxbus.RxBus;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.base.BaseFragment;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Route(path = Constance.ACTIVITY_URL_MAIN)
public class MainActivity extends BaseActivity
        implements View.OnClickListener, ViewPager.OnPageChangeListener, IContract.View {
    private List<BaseFragment> mFragmentList;
    private TextView mTv_black_car;
    private TextView mTv_mine;
    private TextView mTv_monitor;
    private TextView mTv_work;
    private ViewPager mViewPager;
    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;

    public static Intent getIntent(Context paramContext) {
        Intent localIntent = new Intent();
        localIntent.setClass(paramContext, MainActivity.class);
        return localIntent;
    }

    private void updateBottomLinearLayoutSelect(boolean[] paramArrayOfBoolean) {
        this.mTv_monitor.setSelected(paramArrayOfBoolean[0]);
        this.mTv_black_car.setSelected(paramArrayOfBoolean[1]);
        this.mTv_work.setSelected(paramArrayOfBoolean[2]);
        this.mTv_mine.setSelected(paramArrayOfBoolean[3]);
    }

    protected void initView() {
        this.mTv_monitor = ((TextView) findViewById(R.id.tv_main_monitor));
        this.mTv_black_car = ((TextView) findViewById(R.id.tv_black_car));
        this.mTv_work = ((TextView) findViewById(R.id.tv_main_work));
        this.mTv_mine = ((TextView) findViewById(R.id.tv_main_mine));

        this.mTv_monitor.setOnClickListener(this);
        this.mTv_black_car.setOnClickListener(this);
        this.mTv_work.setOnClickListener(this);
        this.mTv_mine.setOnClickListener(this);

        this.mViewPager = ((ViewPager) findViewById(R.id.vp_main));
        initViewPager();
    }

    public void initViewPager() {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        this.mFragmentList = ViewManager.getInstance().getAllFragment();
        this.mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager, this.mFragmentList);
        this.mViewPager.setAdapter(this.mViewPagerFragmentAdapter);
        this.mViewPager.setOffscreenPageLimit(BaseApplication.MainPageSize-2);
        this.mViewPager.addOnPageChangeListener(this);
        this.mViewPager.setCurrentItem(0);
        this.mTv_monitor.setSelected(true);
    }

    private void initData() {
    }

    public void isShowTaskRed(boolean paramBoolean) {
//        ImageView localImageView;
//        if (paramBoolean) {
//            localImageView = this.redPointMianBottomTask;
//        }
//        for (int i = 0;; i = 8)
//        {
//            localImageView.setVisibility(i);
//            return;
//            localImageView = this.redPointMianBottomTask;
//        }
    }

    public void onClick(View paramView) {
        if (paramView.getId() == R.id.tv_main_monitor) {
            if (this.mViewPager.getCurrentItem() != 0) {
                this.mViewPager.setCurrentItem(0);
            }
        } else if (paramView.getId() == R.id.tv_black_car) {
            if (this.mViewPager.getCurrentItem() != 1) {
                this.mViewPager.setCurrentItem(1);
            }
        } else if (paramView.getId() == R.id.tv_main_work) {
            if (this.mViewPager.getCurrentItem() != 2) {
                this.mViewPager.setCurrentItem(2);
            }
        } else if (paramView.getId() == R.id.tv_main_mine) {
            if (this.mViewPager.getCurrentItem() != 3) {
                this.mViewPager.setCurrentItem(3);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        RxBus.get().register(this);
//        PermissionFragment.haveAll(this, getSupportFragmentManager());
    }

    @Override
    protected void onResume() {
        String str = getIntent().getStringExtra("goto");
        if (!TextUtils.isEmpty(str)) {
            str.equals("mission");
        }
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //异常：java.lang.NoClassDefFoundError: android/os/PersistableBundle
    //原因：Android 4.4 之前没有第二个参数：PersistableBundle
    /*@Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }*/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        super.onDestroy();
    }

    boolean hasOneValidExit = false;
    @Override
    public void onBackPressed() {
        Timer timer = null;

        if (!hasOneValidExit) {
            hasOneValidExit = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hasOneValidExit = false;
                }
                // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
            }, 2000);
        } else {
            // 退出并清空缓存
            //this.finish();

            ViewManager.getInstance().exitApp();
        }
    }

    @Override
    public void finish(){
//        ViewGroup view = (ViewGroup) getWindow().getDecorView();
//        view.removeAllViews();
        super.finish();
    }

    public void onPageScrollStateChanged(int paramInt) {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    }

    public void onPageSelected(int i) {
        boolean[] arrayOfBoolean = new boolean[BaseApplication.MainPageSize];
        arrayOfBoolean[i] = true;
        updateBottomLinearLayoutSelect(arrayOfBoolean);
        ((BaseFragment) this.mFragmentList.get(i)).refresh();
    }
}
