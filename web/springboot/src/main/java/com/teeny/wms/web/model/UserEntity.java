package com.teeny.wms.web.model;

import com.teeny.wms.app.config.security.WebSecurityConfiguration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserEntity
 * @since 2017/10/19
 */
public class UserEntity implements UserDetails {

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(WebSecurityConfiguration.ROLE_USER);
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return "123456";
    }

    @Override
    public String getUsername() {
        return "1";
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
