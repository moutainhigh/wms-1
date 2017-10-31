package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.LocationAndCountDTO;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.TransferListDTO;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
public interface TransferService {
    BaseEntity<List<TransferListDTO>> getTransferList(String billNo, String goodsCode, int sId, int saId, String account);

    BaseEntity<String> updateAll(List<Integer> ids, String account, int userId);

    BaseEntity<String> updateOne(int id, String account, int userId);

    BaseEntity update(PutawayAddDTO putawayAddDTO, String account, int userId);

    BaseEntity<List<LocationAndCountDTO>> getLocationListById(int id, String account);

    BaseEntity<List<CommonDTO>> getBills(int saId, int sId, String account);

    BaseEntity<List<CommonDTO>> getGoodsCode(String account, int sId, int saId);
}
