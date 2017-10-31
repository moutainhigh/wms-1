package com.teeny.wms.service;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.EmployeesDTO;

import java.util.List;

/**
 * Created by lilei on 2017/7/10.
 *
 */
public interface SystemService {
    Employess findByUsername(String username, String account);

    EmployeesDTO findByPinyin(String username);

    List<CommonDTO> findAll(String account);
}
