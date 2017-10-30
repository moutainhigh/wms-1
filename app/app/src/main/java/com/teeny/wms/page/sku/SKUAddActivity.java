package com.teeny.wms.page.sku;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.teeny.wms.R;
import com.teeny.wms.base.ToolbarActivity;
import com.teeny.wms.datasouce.net.NetServiceManager;
import com.teeny.wms.datasouce.net.ResponseSubscriber;
import com.teeny.wms.datasouce.net.service.SKUService;
import com.teeny.wms.model.EmptyEntity;
import com.teeny.wms.model.ResponseEntity;
import com.teeny.wms.model.SKUGoodsDetailEntity;
import com.teeny.wms.model.request.SKUAddRequestEntity;
import com.teeny.wms.pop.DialogFactory;
import com.teeny.wms.pop.Toaster;
import com.teeny.wms.util.Converter;
import com.teeny.wms.util.Validator;
import com.teeny.wms.util.WindowUtils;
import com.teeny.wms.widget.KeyValueEditView;

import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see SKUAddActivity
 * @since 2017/7/31
 */

public class SKUAddActivity extends ToolbarActivity implements DatePickerDialog.OnDateSetListener {

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SKUAddActivity.class);
        context.startActivity(intent);
    }

    private static final String DATE_FORMAT = "%1$d-%2$d-%3$d";

    private static final int INVALID_ID = -1;

    private KeyValueEditView mNameView;
    private KeyValueEditView mNumberView;
    private KeyValueEditView mSpecificationView;
    private KeyValueEditView mUnitView;
    private KeyValueEditView mManufacturerView;
    private KeyValueEditView mLotView;
    private EditText mAllocationView;
    private KeyValueEditView mAmountView;
    private TextView mDateView;
    private DatePickerDialog mDatePickerDialog;

    private EditText mFocusView;

    private int mId = INVALID_ID;

    private SKUService mService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sku_add_layout);
        mScannerHelper.openScanner(this, this::handleResult);
        initView();
    }

    private void handleResult(String msg) {
        if (mFocusView != null) {
            mFocusView.setText(msg);
            if (mFocusView.getId() == R.id.sku_add_scan) {
                obtainData(msg);
            }
        } else {
            Toaster.showToast("请先获取焦点.");
        }
    }

    @Override
    protected void onDestroy() {
        mScannerHelper.unregisterReceiver(this);
        super.onDestroy();
    }

    private void initView() {
        EditText scanTextView = (EditText) findViewById(R.id.sku_add_scan);
        scanTextView.setOnFocusChangeListener(this::onFocusChanged);
        scanTextView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                WindowUtils.hideInputSoft(v);
                obtainData(v.getText().toString());
                return true;
            }
            return false;
        });

        mNameView = (KeyValueEditView) findViewById(R.id.sku_add_name);
        mNumberView = (KeyValueEditView) findViewById(R.id.sku_add_number);
        mSpecificationView = (KeyValueEditView) findViewById(R.id.sku_add_specification);
        mUnitView = (KeyValueEditView) findViewById(R.id.sku_add_unit);
        mManufacturerView = (KeyValueEditView) findViewById(R.id.sku_add_manufacturer);

        mLotView = (KeyValueEditView) findViewById(R.id.sku_add_lot_number);
        mLotView.getValueView().setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        mLotView.getValueView().setImeOptions(EditorInfo.IME_ACTION_NEXT);
        mAllocationView = (EditText) findViewById(R.id.sku_add_allocation);
        mAllocationView.setOnFocusChangeListener(this::onFocusChanged);
        mAmountView = (KeyValueEditView) findViewById(R.id.sku_add_amount);
        mAmountView.getValueView().setInputType(InputType.TYPE_CLASS_NUMBER);
        mAmountView.getValueView().setImeOptions(EditorInfo.IME_ACTION_DONE);
        mDateView = (TextView) findViewById(R.id.sku_add_validity_date);

        mDatePickerDialog = DialogFactory.createDatePickerDialog(this, this);
        mService = NetServiceManager.getInstance().getService(SKUService.class);
    }

    private void onFocusChanged(View view, boolean hasFocus) {
        if (hasFocus) {
            this.mFocusView = (EditText) view;
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.sku_add_validity_date_click:
                mDatePickerDialog.show();
                break;
            case R.id.sku_add_complete:
                complete();
                break;
        }
    }

    private void complete() {
        if (mId <= 0) {
            Toaster.showToast("请先扫描商品.");
            return;
        }
        String lot = mLotView.getValueView().getText().toString();
        if (Validator.isEmpty(lot)) {
            Toaster.showToast("请输入批号.");
            mLotView.getValueView().requestFocus();
            return;
        }
        String allocation = mAllocationView.getText().toString();
        if (Validator.isEmpty(allocation)) {
            Toaster.showToast("请扫描货位.");
            return;
        }
        String amount = mAmountView.getValueView().getText().toString();
        if (Validator.isEmpty(amount)) {
            Toaster.showToast("请输入数量.");
            mAmountView.getValueView().requestFocus();
            return;
        }
        String date = mDateView.getText().toString();
        if (Validator.isEmpty(date)) {
            Toaster.showToast("请选择有效期.");
            return;
        }
        SKUAddRequestEntity entity = new SKUAddRequestEntity();
        entity.setpId(mId);
        entity.setAmount(Converter.toInt(amount));
        entity.setOriginalAmount(Converter.toInt(amount));
        entity.setLocationCode(allocation);
        entity.setValidateDate(date);
        entity.setLotNo(lot);
        Flowable<ResponseEntity<EmptyEntity>> flowable = mService.add(entity);
        flowable.observeOn(AndroidSchedulers.mainThread()).subscribe(new ResponseSubscriber<EmptyEntity>(this) {
            @Override
            public void doNext(EmptyEntity data) {
                getEventBus().post(new SKUCheckActivity.DataAddedFlag());
            }

            @Override
            public void doComplete() {
                finish();
            }
        });
    }

    private void obtainData(String code) {
        if (Validator.isEmpty(code)) {
            return;
        }
        Flowable<ResponseEntity<SKUGoodsDetailEntity>> flowable = mService.getGoodsDetail(code);
        flowable.observeOn(AndroidSchedulers.mainThread()).subscribe(new ResponseSubscriber<SKUGoodsDetailEntity>(this) {
            @Override
            public void doNext(SKUGoodsDetailEntity data) {
                if (data != null) {
                    mNameView.setValue(data.getGoodsName());
                    mNumberView.setValue(data.getNumber());
                    mSpecificationView.setValue(data.getStandard());
                    mUnitView.setValue(data.getUnit());
                    mManufacturerView.setValue(data.getManufacturers());
                    mId = data.getpId();
                } else {
                    Toaster.showToast("该商品码不存在.");
                }
            }

            @Override
            public void doComplete() {

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = String.format(Locale.getDefault(), DATE_FORMAT, year, month + 1, dayOfMonth);
        mDateView.setText(date);
    }
}
