package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.*;
import com.teeny.wms.dto.AccountSetDTO;
import com.teeny.wms.dto.BillCountDTO;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.DocumentDTO;
import com.teeny.wms.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilei on 2017/7/25.
 */
@Service
@Transactional
public class HomeServiceImpl implements HomeService {

    private final AccountRepository accountRepository;
    private final StoragesRepository storagesRepository;
    private final TranBillRepository tranBillRepository;
    private final RecBillRepository recBillRepository;
    private final PutOnBillRepository putOnBillRepository;
    private final CheckBillRepository checkBillRepository;

    @Autowired
    public HomeServiceImpl(AccountRepository accountRepository, StoragesRepository storagesRepository, TranBillRepository tranBillRepository, RecBillRepository recBillRepository, PutOnBillRepository putOnBillRepository, CheckBillRepository checkBillRepository) {
        this.accountRepository = accountRepository;
        this.storagesRepository = storagesRepository;
        this.tranBillRepository = tranBillRepository;
        this.recBillRepository = recBillRepository;
        this.putOnBillRepository = putOnBillRepository;
        this.checkBillRepository = checkBillRepository;
    }

    @Override
    public BaseEntity<List<AccountSetDTO>> getAccountSet() {
        return new BaseEntity<>(accountRepository.getAccountSet());
    }

    @Override
    public BaseEntity<List<CommonDTO>> getWarehouse(String account) {
        List<CommonDTO> data = storagesRepository.findAll(account);
        return new BaseEntity<>(data);
    }

    @Override
    public BaseEntity<BillCountDTO> getInfoByWarehouse(String account, int sId) {
        BillCountDTO billCountDTO = new BillCountDTO();

        int tranBillCount = tranBillRepository.countByWarehousId(sId, account);
        int checkBillCount = checkBillRepository.countByWarehousId(sId, account);
        int putOnCount = putOnBillRepository.countByWarehousId(sId, account);
        int recCount = recBillRepository.countByWarehousId(sId, account);
        billCountDTO.setPutawayBillCount(putOnCount);
        billCountDTO.setTranferBillCount(tranBillCount);
        billCountDTO.setReviewBillCount(recCount);
        billCountDTO.setReviewBillCount(checkBillCount);
        return new BaseEntity<>(billCountDTO);
    }

    @Override
    public BaseEntity<DocumentDTO> getDocumentList(String account, int sId) {

        DocumentDTO result = new DocumentDTO();
        result.setAcceptanceList(recBillRepository.getBill(account, sId));
        result.setAllotList(tranBillRepository.getBill(account, sId));
        result.setPutawayList(putOnBillRepository.getBill(account, sId));
        result.setReviewList(checkBillRepository.getBill(account, sId));

        return new BaseEntity<>(result);
    }
}
