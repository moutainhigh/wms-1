package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.CheckBillRepository;
import com.teeny.wms.core.repository.PutOnBillRepository;
import com.teeny.wms.core.repository.RecBillRepository;
import com.teeny.wms.core.repository.TranBillRepository;
import com.teeny.wms.dto.QueryDocumentDTO;
import com.teeny.wms.service.QueryInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilei on 2017/7/26.
 */
@Service
@Transactional
public class QueryInvoiceServiceImpl implements QueryInvoiceService {

    @Autowired
    private RecBillRepository recBillRepository;
    @Autowired
    private PutOnBillRepository putOnBillRepository;
    @Autowired
    private CheckBillRepository checkBillRepository;
    @Autowired
    private TranBillRepository tranBillRepository;



    @Override
    public BaseEntity<List<QueryDocumentDTO>> getDocumentByType(int warehouseId, int type, String account) {

        List<QueryDocumentDTO> data = new ArrayList<QueryDocumentDTO>();

        switch (type) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
                default:
        }

        return null;
    }
}
