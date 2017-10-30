package com.teeny.wms.web.service.impl;

import com.teeny.wms.web.model.UserEntity;
import com.teeny.wms.web.repository.UserRepository;
import com.teeny.wms.web.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see TestServiceImpl
 * @since 2017/10/19
 */
@Service
public class TestServiceImpl implements TestService {

    private final UserRepository mUserRepository;

    @Autowired
    public TestServiceImpl(UserRepository userRepository) {
        this.mUserRepository = userRepository;
    }

    @Override
    public UserEntity getUser(int id) {
        return mUserRepository.getUser(id);
    }
}
