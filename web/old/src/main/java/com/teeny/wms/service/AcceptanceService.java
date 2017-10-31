package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.AcceptAddDTO;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.OrderDetailDTO;
import com.teeny.wms.dto.RecUpdateDTO;

import java.util.List;

/**
 * Created by lilei on 2017/7/26.
 */
public interface AcceptanceService {
    BaseEntity<List<CommonDTO>> getUnit(String account, int sId);

    BaseEntity<List<CommonDTO>> getOrderWithUnitId(int unitId, int sId, String account);

    BaseEntity<List<OrderDetailDTO>> getOrderListWithUnitId(String account, int unitId);

    BaseEntity<String> updateGoodsByOrderId(List<Integer> ids, String account);

    BaseEntity<String> updateGoodsByGoodsId(RecUpdateDTO recUpdateDTO, String account, int userId);

    BaseEntity<String> completeOne(int id, String account);

    BaseEntity<List<AcceptAddDTO>> getLotList(int id, String account);

    BaseEntity<List<OrderDetailDTO>> getBillsByBillNo(String billNo, String account, int sId);

    //测试
    void test();
}
