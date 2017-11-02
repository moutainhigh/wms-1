package com.teeny.wms.web.repository;

import com.teeny.wms.app.model.KeyValueEntity;
import com.teeny.wms.web.model.dto.ReceivingEntity;
import com.teeny.wms.web.model.dto.ReceivingLotEntity;
import com.teeny.wms.web.model.request.ReceivingRequestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ReceivingRepository
 * @since 2017/11/1
 */

@Repository
@Mapper
public interface ReceivingRepository {

    /**
     * 获取单位
     *
     * @param account 账套
     * @param sId     仓库id
     * @return List<KeyValueEntity>
     */
    List<KeyValueEntity> getUnitList(String account, int sId);

    /**
     * 通过单位获取详情
     *
     * @param account 账套
     * @param unitId  单位id
     * @return List<ReceivingEntity>
     */
    List<ReceivingEntity> getDetailByUnitId(String account, int unitId);

    /**
     * 通过单号获取详情
     *
     * @param account 账套
     * @param orderNo 单号
     * @return List<ReceivingEntity>
     */
    List<ReceivingEntity> getDetailByOrderNo(String account, String orderNo);

    /**
     * 获取批次
     *
     * @param account 账套
     * @param id      原始订单id(original_id)
     * @return List<ReceivingLotEntity>
     */
    List<ReceivingLotEntity> getLotList(String account, int id);

    /**
     * 完成
     *
     * @param account 账套
     * @param entity  提交实体
     * @param userId  用户id
     */
    void complete(String account, ReceivingRequestEntity entity, int userId);
}
