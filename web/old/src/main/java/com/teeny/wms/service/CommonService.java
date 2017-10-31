package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.HistoryAllocationDTO;

import java.util.List;

/**
 * Created by lilei on 2017/8/15.
 */
public interface CommonService {
    BaseEntity<List<CommonDTO>> getSaList(String account);

    BaseEntity<List<CommonDTO>> getAreaList(String account);

    BaseEntity<List<CommonDTO>> getWarehouseList(String account);

    BaseEntity<List<CommonDTO>> getSaListBysId(int sid, String account);

    BaseEntity<List<CommonDTO>> getLocationList(int saId, String account);

    int getLocationIdByCode(String locationCode, String account);

    BaseEntity<List<HistoryAllocationDTO>> getHistoryLocation(String account, int pId);
}
