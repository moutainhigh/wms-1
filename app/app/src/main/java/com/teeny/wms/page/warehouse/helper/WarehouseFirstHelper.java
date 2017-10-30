package com.teeny.wms.page.warehouse.helper;

import android.support.v4.util.SparseArrayCompat;

import com.teeny.wms.model.WarehouseGoodsEntity;
import com.teeny.wms.util.ObjectUtils;
import com.teeny.wms.util.Validator;
import com.teeny.wms.util.log.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see WarehouseFirstHelper
 * @since 2017/8/23
 */

public class WarehouseFirstHelper {
    private String mRepository;
    private String mArea;
    private String mLocationCode;
    private String mGoodsCode;
    private List<WarehouseGoodsEntity> mOriginalData;
    private List<WarehouseGoodsEntity> mDataSource;
    private SparseArrayCompat<List<WarehouseGoodsEntity>> mDataHolder;
    private EventBus mEventBus;

    public WarehouseFirstHelper() {
        mDataHolder = new SparseArrayCompat<>();
        mEventBus = EventBus.getDefault();
    }

    public List<WarehouseGoodsEntity> getDataSource() {
        return mDataSource;
    }

    public void setDataSource(List<WarehouseGoodsEntity> dataSource) {
        this.mDataSource = dataSource;
        if (mDataSource == null) {
            mDataSource = new ArrayList<>();
        }
        mDataHolder.clear();
        this.mOriginalData = new ArrayList<>(mDataSource);
        notifyChanged();
    }

    public List<WarehouseGoodsEntity> getDataByType(int type) {
        if (Validator.isEmpty(mDataSource)) {
            return new ArrayList<>();
        }
        List<WarehouseGoodsEntity> list = mDataHolder.get(type);
        if (list == null) {
            list = findDataByType(type);
            mDataHolder.put(type, list);
        }
        return list;
    }

    private List<WarehouseGoodsEntity> findDataByType(int type) {
        List<WarehouseGoodsEntity> list = new ArrayList<>();
        for (WarehouseGoodsEntity entity : mDataSource) {
            if (type == entity.getStatus()) {
                list.add(entity);
            }
        }
        return list;
    }

    public List<Integer> getAchievableIds() {
        List<WarehouseGoodsEntity> list = getDataByType(0);
        if (Validator.isEmpty(list)) {
            return null;
        }
        int count = list.size();
        List<Integer> ids = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            ids.add(list.get(i).getOriginalId());
        }
        return ids;
    }

    public void reverseAllStatus() {
        List<WarehouseGoodsEntity> list = getDataByType(0);
        if (Validator.isEmpty(list)) {
            return;
        }
        for (WarehouseGoodsEntity entity : list) {
            entity.setStatus(1);
        }
        mDataHolder.clear();
        notifyChanged();
    }

    public void reverseStatus(int id) {
        Logger.e(String.valueOf(id));
        List<WarehouseGoodsEntity> list = getDataByType(0);
        if (Validator.isEmpty(list)) {
            return;
        }
        for (WarehouseGoodsEntity entity : list) {
            if (entity.getOriginalId() == id) {
                entity.setStatus(1);
                break;
            }
        }
        mDataHolder.clear();
        notifyChanged();
    }

    public void clear() {
        mDataSource = new ArrayList<>();
        mOriginalData = null;
        notifyChanged();
    }

    public void notifyChanged() {
        mEventBus.post(this);
    }

    //2017/10/12
    public void setRepository(String repository) {
        if (!ObjectUtils.equals(mRepository, repository)) {
            mRepository = repository;
            filter();
        }
    }

    public void setArea(String area) {
        if (!ObjectUtils.equals(mArea, area)) {
            mArea = area;
            filter();
        }
    }

    public void setLocationCode(String locationCode) {
        if (!ObjectUtils.equals(mLocationCode, locationCode)) {
            mLocationCode = locationCode;
            filter();
        }
    }

    public void setGoodsCode(String goodsCode) {
        if (!ObjectUtils.equals(mGoodsCode, goodsCode)) {
            mGoodsCode = goodsCode;
            filter();
        }
    }

    private synchronized void filter() {
        if (mOriginalData == null) {
            return;
        }
        List<WarehouseGoodsEntity> values = new ArrayList<>(mOriginalData);
        List<WarehouseGoodsEntity> newValues;
        if (checkEmpty()) {
            newValues = new ArrayList<>(mOriginalData);
        } else {
            newValues = filterByRepository(values);
            newValues = filterByArea(newValues);
            newValues = filterByLocationCode(newValues);
            newValues = filterByGoodsCode(newValues);
        }
        mDataSource = newValues;
        mDataHolder.clear();
        notifyChanged();
    }

    private boolean checkEmpty() {
        return Validator.isEmpty(mRepository) && Validator.isEmpty(mArea) && Validator.isEmpty(mLocationCode) && Validator.isEmpty(mGoodsCode);
    }

    private List<WarehouseGoodsEntity> filterByRepository(List<WarehouseGoodsEntity> values) {
        if (Validator.isEmpty(mRepository)) {
            return values;
        }
        List<WarehouseGoodsEntity> result = new ArrayList<>();
        for (int i = 0, count = values.size(); i < count; i++) {
            WarehouseGoodsEntity value = values.get(i);
            if (ObjectUtils.contains(value.getRepositoryName(), mRepository)) {
                result.add(value);
            }
        }
        return result;
    }

    private List<WarehouseGoodsEntity> filterByArea(List<WarehouseGoodsEntity> values) {
        if (Validator.isEmpty(mArea)) {
            return values;
        }
        List<WarehouseGoodsEntity> result = new ArrayList<>();
        for (int i = 0, count = values.size(); i < count; i++) {
            WarehouseGoodsEntity value = values.get(i);
            if (ObjectUtils.contains(value.getAreaName(), mArea)) {
                result.add(value);
            }
        }
        return result;
    }

    private List<WarehouseGoodsEntity> filterByLocationCode(List<WarehouseGoodsEntity> values) {
        if (Validator.isEmpty(mLocationCode)) {
            return values;
        }
        List<WarehouseGoodsEntity> result = new ArrayList<>();
        for (int i = 0, count = values.size(); i < count; i++) {
            WarehouseGoodsEntity value = values.get(i);
            if (ObjectUtils.contains(value.getLocationCode(), mLocationCode)) {
                result.add(value);
            }
        }
        return result;
    }

    private List<WarehouseGoodsEntity> filterByGoodsCode(List<WarehouseGoodsEntity> values) {
        if (Validator.isEmpty(mGoodsCode)) {
            return values;
        }
        List<WarehouseGoodsEntity> result = new ArrayList<>();
        for (int i = 0, count = values.size(); i < count; i++) {
            WarehouseGoodsEntity value = values.get(i);
            if (ObjectUtils.contains(value.getGoodsCode(), mGoodsCode)) {
                result.add(value);
            }
        }
        return result;
    }

    public List<String> getRepositoryList() {
        List<WarehouseGoodsEntity> source = mOriginalData == null ? mDataSource : mOriginalData;
        List<String> result = new ArrayList<>();
        if (Validator.isEmpty(source)) {
            return result;
        }
        for (WarehouseGoodsEntity entity : source) {
            if (!result.contains(entity.getRepositoryName())) {
                result.add(entity.getRepositoryName());
            }
        }
        return result;
    }

    public List<String> getAreaList() {
        List<WarehouseGoodsEntity> source = mOriginalData == null ? mDataSource : mOriginalData;
        List<String> result = new ArrayList<>();
        if (Validator.isEmpty(source)) {
            return result;
        }
        for (WarehouseGoodsEntity entity : source) {
            if (!result.contains(entity.getAreaName())) {
                result.add(entity.getAreaName());
            }
        }
        return result;
    }
}