package com.teeny.wms.page.acceptance.helper;

import android.support.v4.util.SparseArrayCompat;

import com.teeny.wms.model.KeyValueEntity;
import com.teeny.wms.model.ReceivingAcceptanceEntity;
import com.teeny.wms.model.ReceivingAcceptanceOrderEntity;
import com.teeny.wms.util.CollectionsUtils;
import com.teeny.wms.util.ObjectUtils;
import com.teeny.wms.util.Validator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AcceptanceHelper
 * @since 2017/8/17
 */

public class AcceptanceHelper {

    private List<ReceivingAcceptanceEntity> mOriginalData;
    private SparseArrayCompat<List<ReceivingAcceptanceOrderEntity>> mDataHolder;
    private EventBus mEventBus = EventBus.getDefault();

    private List<ReceivingAcceptanceEntity> mDataSource;

    public AcceptanceHelper() {
        mDataHolder = new SparseArrayCompat<>();
    }

    public void setDataSource(List<ReceivingAcceptanceEntity> dataSource) {
        mDataSource = dataSource;
        if (mDataSource == null) {
            mDataSource = new ArrayList<>();
        }
        mOriginalData = null;
        mDataHolder.clear();
        notifyChanged();
    }

    public String getStatus() {
        String status = "验收中";
//        List<ReceivingAcceptanceEntity> list;
//        if (Validator.isNotNull(mOriginalData)) {
//            list = mOriginalData;
//        } else {
//            list = mDataSource;
//        }
//        for (ReceivingAcceptanceEntity entity : list) {
//
//        }
        return status;
    }

    public String getBuyerNames() {
        if (Validator.isEmpty(mDataSource)) {
            return "";
        }
        List<String> names = new ArrayList<>();
        for (ReceivingAcceptanceEntity entity : mDataSource) {
            String name = entity.getBuyer();
            if (!names.contains(name)) {
                names.add(name);
            }
        }
        int count = CollectionsUtils.sizeOf(names);
        if (count == 1) {
            return names.get(0);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(names.get(i));
            if (i != count - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public List<KeyValueEntity> getOrderList() {
        List<KeyValueEntity> result = new ArrayList<>();
        List<ReceivingAcceptanceEntity> source;
        if (Validator.isNotNull(mOriginalData)) {
            source = mOriginalData;
        } else {
            source = mDataSource;
        }
        if (Validator.isNotEmpty(source)) {
            for (ReceivingAcceptanceEntity entity : source) {
                result.add(new KeyValueEntity(entity.getOrderId(), entity.getBillNo()));
            }
        }
        return result;
    }

    public List<Integer> getSelectOrderPosition() {
        List<Integer> result = new ArrayList<>();
        if (Validator.isNull(mOriginalData)) {
            for (int i = 0, count = CollectionsUtils.sizeOf(mDataSource); i < count; i++) {
                result.add(i);
            }
        } else {
            for (int i = 0, count = CollectionsUtils.sizeOf(mDataSource); i < count; i++) {
                result.add(mOriginalData.indexOf(mDataSource.get(i)));
            }
        }
        return result;
    }

    public void filterByOrder(long[] select) {
        if (mOriginalData == null) {
            if (mDataSource == null) {
                return;
            }
            mOriginalData = new ArrayList<>(mDataSource);
        }

        List<ReceivingAcceptanceEntity> values = new ArrayList<>(mOriginalData);
        final int count = values.size();
        final ArrayList<ReceivingAcceptanceEntity> newValues = new ArrayList<>();
        for (long id : select) {
            for (int i = 0; i < count; i++) {
                ReceivingAcceptanceEntity value = values.get(i);
                if (id == value.getOrderId()) {
                    newValues.add(value);
                    break;
                }
            }
        }
        mDataSource = newValues;
        mDataHolder.clear();
        notifyChanged();
    }

    public void clear() {
        mDataSource = new ArrayList<>();
        mOriginalData = null;
        mDataHolder.clear();
        notifyChanged();
    }

    public void notifyChanged() {
        mEventBus.post(this);
    }

    public List<ReceivingAcceptanceOrderEntity> getDataByType(int type) {
        List<ReceivingAcceptanceOrderEntity> result = mDataHolder.get(type);
        if (result == null) {
            result = findDataByType(type);
            mDataHolder.put(type, result);
        }
        return result;
    }

    private List<ReceivingAcceptanceOrderEntity> findDataByType(int type) {
        List<ReceivingAcceptanceOrderEntity> result = new ArrayList<>();
        if (Validator.isEmpty(mDataSource)) {
            return result;
        }
        for (ReceivingAcceptanceEntity entity : mDataSource) {
            List<ReceivingAcceptanceOrderEntity> list = entity.getGoodsList();
            if (Validator.isEmpty(list)) {
                continue;
            }
            for (ReceivingAcceptanceOrderEntity e : list) {
                if (e.getStatus() == type) {
                    result.add(e);
                }
            }
        }
        return result;
    }

    public ReceivingAcceptanceOrderEntity findByCode(String code) {
        if (Validator.isNotEmpty(mDataSource)) {
            List<ReceivingAcceptanceEntity> list = mDataSource;
            for (ReceivingAcceptanceEntity entity : list) {
                List<ReceivingAcceptanceOrderEntity> l = entity.getGoodsList();
                if (Validator.isNotEmpty(l)) {
                    for (ReceivingAcceptanceOrderEntity e : l) {
                        if (ObjectUtils.equals(code, e.getBarcode())) {
                            return e;
                        }
                    }
                }
            }
        }
        return null;
    }
}
