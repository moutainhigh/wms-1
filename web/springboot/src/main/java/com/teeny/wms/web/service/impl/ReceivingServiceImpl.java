package com.teeny.wms.web.service.impl;

import com.teeny.wms.app.model.KeyValueEntity;
import com.teeny.wms.web.model.dto.ReceivingEntity;
import com.teeny.wms.web.model.dto.ReceivingLotEntity;
import com.teeny.wms.web.model.request.ReceivingRequestEntity;
import com.teeny.wms.web.service.ReceivingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ReceivingServiceImpl
 * @since 2017/11/1
 */
@Service
public class ReceivingServiceImpl implements ReceivingService {
    @Override
    public List<KeyValueEntity> getUnitList(String account, int sId) {
        return null;
    }

    @Override
    public List<ReceivingEntity> getDetailByUnitId(String account, int unitId) {
        return null;
    }

    @Override
    public List<ReceivingEntity> getDetailByOrderNo(String account, String orderNo) {
        return null;
    }

    @Override
    public List<ReceivingLotEntity> getLotList(String account, int id) {
        return null;
    }

    @Override
    public void complete(String account, ReceivingRequestEntity recUpdateDTO, int userId) {

    }
}
