package com.teeny.wms.page.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.teeny.wms.R;
import com.teeny.wms.base.BaseFragmentPagerAdapter;
import com.teeny.wms.base.TableViewPagerActivity;
import com.teeny.wms.page.common.helper.InventoryHelper;
import com.teeny.wms.page.shop.fragment.ShopHeaderFragment;
import com.teeny.wms.page.shop.fragment.ShopInventoryFragment;
import com.teeny.wms.pop.Toaster;
import com.teeny.wms.util.Validator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see InventoryActivity
 * @since 2017/12/27
 */

public abstract class InventoryActivity extends TableViewPagerActivity implements BaseFragmentPagerAdapter.Callback, ViewPager.OnPageChangeListener {

    private String[] mTitles;
    private SparseArrayCompat<Fragment> mFragmentHolder = new SparseArrayCompat<>();

    private InventoryHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupHeader();
        mTitles = getResources().getStringArray(R.array.pd_status);
        setupViewPager(new BaseFragmentPagerAdapter(getSupportFragmentManager(), this), this);

        registerEventBus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus();
    }

    @Override
    protected boolean onLongClick(View view) {
        Toaster.showToast("全部完成");
        return true;
    }

    @Override
    protected void onClick(View view) {

    }

    private void setupHeader() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment fragment = ShopHeaderFragment.newInstance();
        ft.replace(getHeaderLayoutId(), fragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mHelper != null) {
            setCountValue(mHelper.getNumber(position));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentHolder.get(position);
        if (Validator.isNull(fragment)) {
            fragment = createFragment(position);
            mFragmentHolder.put(position, fragment);
        }
        return fragment;
    }

    protected abstract Fragment createFragment(int position);

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNumberChanged(InventoryHelper.NumberObserver observer) {
        mHelper = observer.getHelper();
        setCountValue(mHelper.getNumber(getCurrentItem()));
    }
}
