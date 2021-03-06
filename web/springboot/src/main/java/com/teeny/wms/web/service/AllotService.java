package com.teeny.wms.web.service;

import com.teeny.wms.app.model.KeyValueEntity;
import com.teeny.wms.web.model.request.AllotListRequestEntity;
import com.teeny.wms.web.model.response.AllocationEntity;
import com.teeny.wms.web.model.response.AllotEntity;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AllotService
 * @since 2017/11/7
 */
public interface AllotService {

    List<AllotEntity> getAllotList(String billNo, String goodsCode, int sId, int saId, String account);

    void updateAll(List<Integer> ids, String account, int userId);

    void updateOne(int id, String account, int userId);

    void update(AllotListRequestEntity entity, String account, int userId);

    List<AllocationEntity> getLocationsById(int id, String account);

    List<KeyValueEntity> getOrderList(int saId, int sId, String account);

    List<KeyValueEntity> getGoodsCode(String account, int sId, int saId);
}
