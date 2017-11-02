package com.teeny.wms.web.repository;

import com.teeny.wms.app.model.KeyValueEntity;
import com.teeny.wms.web.model.dto.ReceivingEntity;
import com.teeny.wms.web.model.dto.ReceivingItemEntity;
import com.teeny.wms.web.model.dto.ReceivingLotEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ReceivingMapper
 * @since 2017/11/1
 */

@Repository
@Mapper
public interface ReceivingMapper {

    List<KeyValueEntity> getUnitList(@Param("account") String account, @Param("sId") int sId);

    List<ReceivingEntity> getDetailByUnitId(@Param("account") String account, @Param("unitId") int unitId);

    List<ReceivingEntity> getDetailByOrderNo(@Param("account") String account, @Param("orderNo") String orderNo);

    List<ReceivingLotEntity> getLotList(@Param("account") String account, @Param("id") int id);
}
