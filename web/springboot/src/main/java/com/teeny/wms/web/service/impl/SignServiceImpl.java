package com.teeny.wms.web.service.impl;

import com.teeny.wms.app.model.StringMapEntity;
import com.teeny.wms.web.repository.SystemMapper;
import com.teeny.wms.web.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("signService")
public class SignServiceImpl implements SignService {

    private SystemMapper mSystemMapper;

    @Autowired
    public void setSystemMapper(SystemMapper systemMapper) {
        mSystemMapper = systemMapper;
    }

    @Override
    public List<StringMapEntity> getAccountSets() {
        return mSystemMapper.getAccountSets();
    }
}
