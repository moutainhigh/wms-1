package com.teeny.wms.service.impl;

import com.teeny.wms.config.WmsException;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.PutOnBillRepository;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.LocationAndCountDTO;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.PutawayDTO;
import com.teeny.wms.service.CommonService;
import com.teeny.wms.service.PutOnBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by bao on 2017/8/4.
 */
@Service
@Transactional
public class PutOnBillServiceImpl implements PutOnBillService {

    private final PutOnBillRepository putOnBillRepository;
    private final CommonService commonService;

    @Autowired
    public PutOnBillServiceImpl(PutOnBillRepository putOnBillRepository, CommonService commonService) {
        this.putOnBillRepository = putOnBillRepository;
        this.commonService = commonService;
    }

    /**
     * 获取上架单详情
     * @param orderNo
     * @param account
     * @param sId
     * @return
     */
    @Override
    public BaseEntity<List<PutawayDTO>> getGoodsDetailList(String orderNo, String account, int sId) {
        List<PutawayDTO> list = putOnBillRepository.getGoodsDetailList(orderNo, account, sId);
        int id = putOnBillRepository.getBillByBillNumber(orderNo, account, sId);
        putOnBillRepository.uodateBillStatus(id, account, sId);
        return new BaseEntity<List<PutawayDTO>>(list);
    }

    /**
     * 快速上架
     * @param ids
     * @param account
     * @return
     */
    @Override
    public BaseEntity<String> putOnBillQuickly(List<Integer> ids, String account, int userId) {
        for (Integer id : ids) {
            putOnBillRepository.updateOne(id, account, userId);
        }
        int count = putOnBillRepository.countByOriginalId(ids.get(0), account);
        if (count == 0) {
            putOnBillRepository.updatePutByOriginalId(ids.get(0), account);
        }
        return new BaseEntity<String>();
    }

    /**
     * 单个上架
     * @param originalId
     * @param account
     */
    @Override
    public void putOnBillWithOne(int originalId, String account, int userId) {

        putOnBillRepository.updateOne(originalId, account, userId);
        int count = putOnBillRepository.countByOriginalId(originalId, account);
        if (count == 0) {
            putOnBillRepository.updatePutByOriginalId(originalId, account);
        }
    }


    /**
     * 更新一条数据状态
     * @param putawayAddDTO
     * @param account
     * @return
     */
    @Override
    public BaseEntity updateOne(PutawayAddDTO putawayAddDTO, String account, int userId) {

        List<Integer> ids = putOnBillRepository.getIdsByOriginalId(putawayAddDTO.getId(), account);

        if (putawayAddDTO.getLocations().size() > 0) {
            for (PutawayAddDTO.Location location : putawayAddDTO.getLocations()) {
                int locationId = commonService.getLocationIdByCode(location.getLocationCode(), account);
                if (locationId == 0) {
                    BaseEntity baseEntity = new BaseEntity<>();
                    baseEntity.setMsg("找不此货位:" + location.getLocationCode());
                    baseEntity.setResult(1);
                    throw new WmsException(baseEntity);
                }
                putOnBillRepository.copyDataByParam(putawayAddDTO.getId(), location.getAmount(), locationId, account, userId);
            }
            for (Integer id : ids) {
                putOnBillRepository.deleteBySmbId(id, putawayAddDTO.getId(), account);
            }
        }

        int count = putOnBillRepository.countByOriginalId(putawayAddDTO.getId(), account);
        if (count == 0) {
            putOnBillRepository.updatePutByOriginalId(putawayAddDTO.getId(), account);
        }
        return new BaseEntity();
    }

    @Override
    public BaseEntity<List<LocationAndCountDTO>> getLocationList(int id, String account) {
        List<LocationAndCountDTO> list = putOnBillRepository.getLocationListById(id, account);
        return new BaseEntity<>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getBills(int sId, int saId, String account) {
        List<CommonDTO> list = putOnBillRepository.getBills(sId, saId, account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getSaList(int sId, String account) {
        List<CommonDTO> list = putOnBillRepository.getSaListBysId(sId, account);
        return new BaseEntity<>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getLocations(int sId, String account) {
        List<CommonDTO> list = putOnBillRepository.getLocations(sId, account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

}
