package com.teeny.wms.web.service.impl;

import com.teeny.wms.app.model.KeyValueEntity;
import com.teeny.wms.web.model.dto.ReceivingEntity;
import com.teeny.wms.web.model.dto.ReceivingLotEntity;
import com.teeny.wms.web.model.request.ReceivingRequestEntity;
import com.teeny.wms.web.repository.ReceivingMapper;
import com.teeny.wms.web.service.ReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ReceivingMapper mReceivingMapper;

    @Autowired
    public void setReceivingMapper(ReceivingMapper receivingMapper) {
        mReceivingMapper = receivingMapper;
    }

    @Override
    public List<KeyValueEntity> getUnitList(String account, int sId) {
        return mReceivingMapper.getUnitList(account, sId);
    }

    @Override
    public List<ReceivingEntity> getDetailByUnitId(String account, int unitId) {
        return mReceivingMapper.getDetailByUnitId(account, unitId);
    }

    @Override
    public List<ReceivingEntity> getDetailByOrderNo(String account, String orderNo) {
        return mReceivingMapper.getDetailByOrderNo(account, orderNo);
    }

    @Override
    public List<ReceivingLotEntity> getLotList(String account, int id) {
        return mReceivingMapper.getLotList(account, id);
    }

    @Override
    public void complete(String account, ReceivingRequestEntity recUpdateDTO, int userId) {

    }
}
