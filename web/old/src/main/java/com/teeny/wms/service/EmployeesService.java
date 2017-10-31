package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;

import java.util.List;

/**
 * Created by bao on 2017/7/27.
 */
public interface EmployeesService {

    BaseEntity<List<CommonDTO>> getReviewer(String account);
}
