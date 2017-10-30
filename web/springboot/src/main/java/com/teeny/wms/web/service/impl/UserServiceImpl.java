package com.teeny.wms.web.service.impl;

import com.teeny.wms.web.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserServiceImpl
 * @since 2017/10/25
 */

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
