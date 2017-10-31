package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.QueryDocumentDTO;

import java.util.List;

/**
 * Created by lilei on 2017/7/26.
 */
public interface QueryInvoiceService {

    BaseEntity<List<QueryDocumentDTO>> getDocumentByType(int warehouseId, int type, String account);
}
