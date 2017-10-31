package com.teeny.wms.service.impl;

import com.teeny.wms.config.WmsException;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.TranBillRepository;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.LocationAndCountDTO;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.TransferListDTO;
import com.teeny.wms.service.CommonService;
import com.teeny.wms.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
@Service
@Transactional
public class TransfenServiceImpl implements TransferService {

    private final TranBillRepository tranBillRepository;
    private final CommonService commonService;

    @Autowired
    public TransfenServiceImpl(TranBillRepository tranBillRepository, CommonService commonService) {
        this.tranBillRepository = tranBillRepository;
        this.commonService = commonService;
    }

    /**
     * 获取调拨单的详细数据
     * @param billNo
     * @param goodsCode
     * @param sId
     * @param saId
     * @param account
     * @return
     */
    @Override
    public BaseEntity<List<TransferListDTO>> getTransferList(String billNo, String goodsCode, int sId, int saId, String account) {
        List<TransferListDTO> list = tranBillRepository.getTransferList(billNo, goodsCode, sId, saId, account);
        tranBillRepository.updateBill(billNo, account);
        return new BaseEntity<>(list);
    }

    /**
     * 更新调拨单的数据
     * @param ids
     * @param account
     * @return
     */
    @Override
    public BaseEntity<String> updateAll(List<Integer> ids, String account, int userId) {
        if (ids.size() > 0) {
            for (Integer id : ids) {
                tranBillRepository.updateOne(id, account, userId);
            }
            int count = tranBillRepository.countByDealstatus(ids.get(0), account);
            if (count == 0) {
                tranBillRepository.updateBillStatusByOriginalId(ids.get(0), account);
            }
            return new BaseEntity<String>();
        }
        return null;
    }

    @Override
    public BaseEntity<String> updateOne(int id, String account, int userId) {
        tranBillRepository.updateOne(id, account, userId);
        int count = tranBillRepository.countByDealstatus(id, account);
        if (count == 0) {
            tranBillRepository.updateBillStatusByOriginalId(id, account);
        }
        return new BaseEntity<String>();
    }

    @Override
    public BaseEntity update(PutawayAddDTO putawayAddDTO, String account, int userId) {
        List<Integer> ids = tranBillRepository.getIdsByOriginalId(putawayAddDTO.getId(), account);
        if (putawayAddDTO.getLocations().size() > 0) {
            for (PutawayAddDTO.Location loc : putawayAddDTO.getLocations()) {
                int locationId = commonService.getLocationIdByCode(loc.getLocationCode(), account);
                if (locationId == 0) {
                    BaseEntity<String> baseEntity = new BaseEntity<String>();
                    baseEntity.setMsg("找不此货位!");
                    baseEntity.setResult(1);
                    throw new WmsException(baseEntity);
                }
                tranBillRepository.copyData(putawayAddDTO.getId(), loc.getAmount(), locationId, account, userId);
            }
        } else {
            tranBillRepository.copyData(putawayAddDTO.getId(), 0, 0, account, userId);
        }
        for (Integer i : ids) {
            tranBillRepository.deleteById(i, putawayAddDTO.getId(), account);
        }
        int count = tranBillRepository.countByDealstatus(putawayAddDTO.getId(), account);
        if (count == 0) {
            tranBillRepository.updateBillStatusByOriginalId(putawayAddDTO.getId(), account);
        }

        return new BaseEntity();
    }

    @Override
    public BaseEntity<List<LocationAndCountDTO>> getLocationListById(int id, String account) {

        List<LocationAndCountDTO> list = tranBillRepository.getLocationById(id, account);

        return new BaseEntity<List<LocationAndCountDTO>>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getBills(int saId, int sId, String account) {
        List<CommonDTO> list = tranBillRepository.getBills(saId, sId, account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getGoodsCode(String account, int sId, int saId) {
        List<CommonDTO> list = tranBillRepository.getGoodsCode(account, sId, saId);
        return new BaseEntity<>(list);
    }

}
