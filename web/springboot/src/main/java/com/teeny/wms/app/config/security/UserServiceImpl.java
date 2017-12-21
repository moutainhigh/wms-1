package com.teeny.wms.app.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserServiceImpl
 * @since 2017/11/14
 */

@Component
public class UserServiceImpl implements UserService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
