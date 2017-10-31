package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.AccountSetDTO;
import com.teeny.wms.dto.BillCountDTO;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.DocumentDTO;

import java.util.List;

/**
 * Created by bao on 2017/7/25.
 *
 */
public interface HomeService {

    BaseEntity<List<AccountSetDTO>> getAccountSet();

    BaseEntity<List<CommonDTO>> getWarehouse(String account);

    BaseEntity<BillCountDTO> getInfoByWarehouse(String account, int warehouseId);

    BaseEntity<DocumentDTO> getDocumentList(String account, int sId);
}
