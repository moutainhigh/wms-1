package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.LocationAndCountDTO;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.PutawayDTO;

import java.util.List;


/**
 * Created by bao on 2017/8/4.
 */
public interface PutOnBillService {

    BaseEntity<List<PutawayDTO>> getGoodsDetailList(String orderNoId, String account, int sId);

    BaseEntity<String> putOnBillQuickly(List<Integer> ids, String account, int userId);

    void putOnBillWithOne(int bdId, String account, int userId);

    BaseEntity updateOne(PutawayAddDTO putawayAddDTO, String account, int userId);

    BaseEntity<List<LocationAndCountDTO>> getLocationList(int id, String account);

    BaseEntity<List<CommonDTO>> getBills(int sId, int saId, String account);

    BaseEntity<List<CommonDTO>> getSaList(int sId, String account);

    BaseEntity<List<CommonDTO>> getLocations(int sId, String account);
}
