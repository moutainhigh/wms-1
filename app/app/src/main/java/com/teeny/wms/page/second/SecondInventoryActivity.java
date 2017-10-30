package com.teeny.wms.page.second;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import com.teeny.wms.R;
import com.teeny.wms.base.BaseFragmentPagerAdapter;
import com.teeny.wms.base.ToolbarActivity;
import com.teeny.wms.datasouce.net.NetServiceManager;
import com.teeny.wms.datasouce.net.ResponseSubscriber;
import com.teeny.wms.datasouce.net.service.SecondInventoryService;
import com.teeny.wms.model.EmptyEntity;
import com.teeny.wms.model.KeyValueEntity;
import com.teeny.wms.model.ResponseEntity;
import com.teeny.wms.model.SecondInventoryGoodsEntity;
import com.teeny.wms.page.common.adapter.SimpleAdapter;
import com.teeny.wms.page.second.adapter.SecondInventoryGoodsAdapter;
import com.teeny.wms.page.second.fragment.SecondInventoryFragment;
import com.teeny.wms.page.second.helper.SecondInventoryHelper;
import com.teeny.wms.pop.DialogFactory;
import com.teeny.wms.pop.Toaster;
import com.teeny.wms.util.Validator;
import com.teeny.wms.widget.KeyValueTextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Class description: 复盘
 *
 * @author zp
 * @version 1.0
 * @see SecondInventoryActivity
 * @since 2017/7/16
 */

public class SecondInventoryActivity extends ToolbarActivity implements BaseFragmentPagerAdapter.Callback, ViewPager.OnPageChangeListener {
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SecondInventoryActivity.class);
        context.startActivity(intent);
    }

    private SparseArrayCompat<Fragment> mFragmentHolder = new SparseArrayCompat<>();
    private String[] mTitles;

    private Spinner mSpinner;

    private AutoCompleteTextView mRepositoryTextView;    //库区
    private SimpleAdapter<String> mRepositoryAdapter;

    private AutoCompleteTextView mAreaTextView;          //区域
    private SimpleAdapter<String> mAreaAdapter;

    private EditText mLocationTextView;
    private EditText mGoodsTextView;
    private EditText mFocusView;

    private SecondInventoryService mService;
    private SecondInventoryHelper mHelper;

    private AlertDialog mSubmitDialog;

    private ViewPager mViewPager;
    private KeyValueTextView mCountView;
    private Map<SecondInventoryGoodsAdapter, AdapterDataObserver> mAdapterDataObserverHolder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_inventory_layout);

        registerEventBus();
        mScannerHelper.openScanner(this, this::handleResult);
        initView();
    }

    private void handleResult(String msg) {
        if (mFocusView != null) {
            mFocusView.setText(msg);
        } else {
            Toaster.showToast("当前没有焦点.");
        }
    }

    @Override
    protected void onDestroy() {
        unregisterEventBus();
        mScannerHelper.unregisterReceiver(this);
        for (Map.Entry<SecondInventoryGoodsAdapter, AdapterDataObserver> entry : mAdapterDataObserverHolder.entrySet()) {
            entry.getKey().unregisterAdapterDataObserver(entry.getValue());
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add:
                Object object = mSpinner.getSelectedItem();
                if (object == null) {
                    Toaster.showToast("请先选择盘点类型.");
                    return false;
                }
                int key = ((KeyValueEntity) object).getKey();
                SecondInventoryAddActivity.startActivity(this, key, mLocationTextView.getText().toString());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {

        mAdapterDataObserverHolder = new HashMap<>();

        mTitles = getResources().getStringArray(R.array.pd_status);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(), this));
        mViewPager.addOnPageChangeListener(this);
        mCountView = (KeyValueTextView) findViewById(R.id.key_value_text_view);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

        mSpinner = (Spinner) findViewById(R.id.second_inventory_type);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                obtainData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mRepositoryTextView = (AutoCompleteTextView) findViewById(R.id.second_inventory_repository);
        mRepositoryTextView.setOnFocusChangeListener(this::onRepositoryFocusChange);
        mRepositoryTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mHelper.setRepository(s.toString());
            }
        });
        mRepositoryAdapter = new SimpleAdapter<>(this);
        mRepositoryTextView.setAdapter(mRepositoryAdapter);
        mRepositoryTextView.setOnClickListener(v -> {
            if (!mRepositoryTextView.isPopupShowing()) {
                mRepositoryTextView.showDropDown();
            }
        });
        mAreaTextView = (AutoCompleteTextView) findViewById(R.id.second_inventory_area);
        mAreaTextView.setOnFocusChangeListener(this::onAreaFocusChange);
        mAreaTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mHelper.setArea(s.toString());
            }
        });
        mAreaAdapter = new SimpleAdapter<>(this);
        mAreaTextView.setAdapter(mAreaAdapter);
        mAreaTextView.setOnClickListener(v -> {
            if (!mAreaTextView.isPopupShowing()) {
                mAreaTextView.showDropDown();
            }
        });

        findViewById(R.id.second_inventory_complete).setOnLongClickListener(view -> {
            Toaster.showToast("全部完成");
            return true;
        });

        mLocationTextView = (EditText) findViewById(R.id.second_inventory_goods_allocation);
        mLocationTextView.setOnFocusChangeListener(this::onFocusChanged);
        mLocationTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mHelper.setLocationCode(s.toString());
            }
        });
        mGoodsTextView = (EditText) findViewById(R.id.second_inventory_goods);
        mGoodsTextView.setOnFocusChangeListener(this::onFocusChanged);
        mGoodsTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mHelper.setGoodsCode(s.toString());
            }
        });

        mService = NetServiceManager.getInstance().getService(SecondInventoryService.class);
        mHelper = new SecondInventoryHelper();

        mSubmitDialog = DialogFactory.createAlertDialog(this, getString(R.string.prompt_complete_all), this::onSubmit);

        obtainPdType();
    }

    private void onFocusChanged(View view, boolean hasFocus) {
        if (hasFocus) {
            mFocusView = (EditText) view;
        }
    }

    private void obtainPdType() {
        Flowable<ResponseEntity<List<KeyValueEntity>>> flowable = mService.getPdType();
        flowable.observeOn(AndroidSchedulers.mainThread()).subscribe(new ResponseSubscriber<List<KeyValueEntity>>(this) {

            @Override
            public void doNext(List<KeyValueEntity> data) {
                if (Validator.isEmpty(data)) {
                    Toaster.showToast("获取到盘点类型为空.");
                }
                ArrayAdapter<KeyValueEntity> exportAdapter = new ArrayAdapter<>(SecondInventoryActivity.this, android.R.layout.simple_spinner_item, data);
                exportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(exportAdapter);
            }

            @Override
            public void doComplete() {
                mSpinner.setSelection(0, true);
            }
        });
    }

    private void onRepositoryFocusChange(View view, boolean b) {
        if (!b) {
            mRepositoryTextView.dismissDropDown();
            return;
        }
        mRepositoryTextView.showDropDown();
    }

    private void onAreaFocusChange(View view, boolean b) {
        if (!b) {
            mAreaTextView.dismissDropDown();
            return;
        }
        mAreaTextView.showDropDown();
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.second_inventory_complete:           //完成点击
                complete();
                break;
        }
    }

    private void complete() {
        List<Integer> ids = mHelper.getAchievableIds();
        if (Validator.isEmpty(ids)) {
            Toaster.showToast("没有可以完成的商品.");
            return;
        }
        if (ids.size() > 1) {
            mSubmitDialog.show();
        } else {
            submit();
        }
    }

    private void onSubmit(DialogInterface dialog, int which) {
        submit();
    }

    private void submit() {
        List<Integer> ids = mHelper.getAchievableIds();
        Flowable<ResponseEntity<EmptyEntity>> flowable = mService.complete(ids);
        flowable.observeOn(AndroidSchedulers.mainThread()).subscribe(new ResponseSubscriber<EmptyEntity>(this) {
            @Override
            public void doNext(EmptyEntity data) {
                Toaster.showToast("全部完成操作成功.");
            }

            @Override
            public void doComplete() {
                mHelper.reverseAllStatus();
            }
        });
    }

    private void obtainData() {
        Object item = mSpinner.getSelectedItem();
        if (item == null) {
            return;
        }
        KeyValueEntity entity = (KeyValueEntity) item;
        Flowable<ResponseEntity<List<SecondInventoryGoodsEntity>>> flowable = mService.getGoodsList(entity.getKey());
        flowable.observeOn(AndroidSchedulers.mainThread()).subscribe(new ResponseSubscriber<List<SecondInventoryGoodsEntity>>(this) {

            @Override
            public void doNext(List<SecondInventoryGoodsEntity> data) {
                mHelper.setDataSource(data);
                if (Validator.isEmpty(data)) {
                    Toaster.showToast("获取到商品为空.");
                }
            }

            @Override
            public void doComplete() {
                mRepositoryAdapter.clear();
                mRepositoryAdapter.addAll(mHelper.getRepositoryList());
                mRepositoryTextView.setText("");
                mAreaAdapter.clear();
                mAreaAdapter.addAll(mHelper.getAreaList());
                mAreaTextView.setText("");
                mAreaAdapter.notifyDataSetChanged();
                mLocationTextView.setText("");
                mGoodsTextView.setText("");
            }
        });
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

    private Fragment createFragment(int position) {
        SecondInventoryFragment fragment = SecondInventoryFragment.newInstance(position);
        AdapterDataObserver observer = new AdapterDataObserver(fragment);
        SecondInventoryGoodsAdapter adapter = fragment.getAdapter();
        adapter.registerAdapterDataObserver(observer);
        mAdapterDataObserverHolder.put(adapter, observer);
        return fragment;
    }

    private class AdapterDataObserver extends RecyclerView.AdapterDataObserver {
        private SecondInventoryFragment mFragment;

        public AdapterDataObserver(SecondInventoryFragment fragment) {
            mFragment = fragment;
        }

        @Override
        public void onChanged() {
            if (mFragment == getItem(mViewPager.getCurrentItem())) {
                mCountView.setValue(String.valueOf(mFragment.getAdapter().getItemCount()));
            }
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataAdded(DataChangedFlag flag) {
        obtainData();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        SecondInventoryFragment fragment = (SecondInventoryFragment) getItem(position);
        mCountView.setValue(String.valueOf(fragment.getAdapter().getItemCount()));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static final class DataChangedFlag {
    }
}
