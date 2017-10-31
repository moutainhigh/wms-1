package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.core.repository.EmployessRepository;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.EmployeesDTO;
import com.teeny.wms.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lilei on 2017/7/10.
 */
@Service
public class SystemServiceImpl implements SystemService {

    private final EmployessRepository employessRepository;

    @Autowired
    public SystemServiceImpl(EmployessRepository employessRepository) {
        this.employessRepository = employessRepository;
    }

    public Employess findByUsername(String username, String account) {
        return employessRepository.findEmployerByUsername(username, account);
    }

    public EmployeesDTO findByPinyin(String username) {
        return employessRepository.findByPinyin(username);
    }

    @Override
    public List<CommonDTO> findAll(String account) {
        return employessRepository.findAll(account);
    }


}
