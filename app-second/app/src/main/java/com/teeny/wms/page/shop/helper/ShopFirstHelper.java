package com.teeny.wms.page.shop.helper;

import android.support.v4.util.SparseArrayCompat;

import com.teeny.wms.model.ShopGoodsEntity;
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
 * @see ShopFirstHelper
 * @since 2017/8/20
 */

public class ShopFirstHelper {

    private String mRepository;
    private String mArea;
    private String mLocationCode;
    private String mGoodsCode;
    private List<ShopGoodsEntity> mOriginalData;
    private List<ShopGoodsEntity> mDataSource;
    private SparseArrayCompat<List<ShopGoodsEntity>> mDataHolder;
    private EventBus mEventBus;

    public ShopFirstHelper() {
        mDataHolder = new SparseArrayCompat<>();
        mEventBus = EventBus.getDefault();
    }

    public void setDataSource(List<ShopGoodsEntity> dataSource) {
        this.mDataSource = dataSource;
        if (mDataSource == null) {
            mDataSource = new ArrayList<>();
        }
        mDataHolder.clear();
        this.mOriginalData = new ArrayList<>(mDataSource);
        notifyChanged();
    }

    public List<ShopGoodsEntity> getDataByType(int type) {
        if (Validator.isEmpty(mDataSource)) {
            return new ArrayList<>();
        }
        List<ShopGoodsEntity> list = mDataHolder.get(type);
        if (list == null) {
            list = findDataByType(type);
            mDataHolder.put(type, list);
        }
        return list;
    }

    private List<ShopGoodsEntity> findDataByType(int type) {
        List<ShopGoodsEntity> list = new ArrayList<>();
        for (ShopGoodsEntity entity : mDataSource) {
            if (type == entity.getStatus()) {
                list.add(entity);
            }
        }
        return list;
    }

    public List<Integer> getAchievableIds() {
        List<ShopGoodsEntity> list = getDataByType(0);
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
        List<ShopGoodsEntity> list = getDataByType(0);
        if (Validator.isEmpty(list)) {
            return;
        }
        for (ShopGoodsEntity entity : list) {
            entity.setStatus(1);
        }
        mDataHolder.clear();
        notifyChanged();
    }

    public void reverseStatus(int id) {
        Logger.e(String.valueOf(id));
        List<ShopGoodsEntity> list = getDataByType(0);
        if (Validator.isEmpty(list)) {
            return;
        }
        for (ShopGoodsEntity entity : list) {
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

    private void notifyChanged() {
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
        List<ShopGoodsEntity> values = new ArrayList<>(mOriginalData);
        List<ShopGoodsEntity> newValues;
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

    private List<ShopGoodsEntity> filterByRepository(List<ShopGoodsEntity> values) {
        if (Validator.isEmpty(mRepository)) {
            return values;
        }
        List<ShopGoodsEntity> result = new ArrayList<>();
        for (int i = 0, count = values.size(); i < count; i++) {
            ShopGoodsEntity value = values.get(i);
            if (ObjectUtils.contains(value.getRepositoryName(), mRepository)) {
                result.add(value);
            }
        }
        return result;
    }

    private List<ShopGoodsEntity> filterByArea(List<ShopGoodsEntity> values) {
        if (Validator.isEmpty(mArea)) {
            return values;
        }
        List<ShopGoodsEntity> result = new ArrayList<>();
        for (int i = 0, count = values.size(); i < count; i++) {
            ShopGoodsEntity value = values.get(i);
            if (ObjectUtils.contains(value.getAreaName(), mArea)) {
                result.add(value);
            }
        }
        return result;
    }

    private List<ShopGoodsEntity> filterByLocationCode(List<ShopGoodsEntity> values) {
        if (Validator.isEmpty(mLocationCode)) {
            return values;
        }
        List<ShopGoodsEntity> result = new ArrayList<>();
        for (int i = 0, count = values.size(); i < count; i++) {
            ShopGoodsEntity value = values.get(i);
            if (ObjectUtils.contains(value.getLocationCode(), mLocationCode)) {
                result.add(value);
            }
        }
        return result;
    }

    private List<ShopGoodsEntity> filterByGoodsCode(List<ShopGoodsEntity> values) {
        if (Validator.isEmpty(mGoodsCode)) {
            return values;
        }
        List<ShopGoodsEntity> result = new ArrayList<>();
        for (int i = 0, count = values.size(); i < count; i++) {
            ShopGoodsEntity value = values.get(i);
            if (ObjectUtils.contains(ObjectUtils.concat(value.getGoodsCode(), value.getPinyin()), mGoodsCode)) {
                result.add(value);
            }
        }
        return result;
    }

    public List<String> getRepositoryList() {
        List<ShopGoodsEntity> source = mOriginalData == null ? mDataSource : mOriginalData;
        List<String> result = new ArrayList<>();
        if (Validator.isEmpty(source)) {
            return result;
        }
        for (ShopGoodsEntity entity : source) {
            if (!result.contains(entity.getRepositoryName())) {
                result.add(entity.getRepositoryName());
            }
        }
        return result;
    }

    public List<String> getAreaList() {
        List<ShopGoodsEntity> source = mOriginalData == null ? mDataSource : mOriginalData;
        List<String> result = new ArrayList<>();
        if (Validator.isEmpty(source)) {
            return result;
        }
        for (ShopGoodsEntity entity : source) {
            if (!result.contains(entity.getAreaName())) {
                result.add(entity.getAreaName());
            }
        }
        return result;
    }
}
