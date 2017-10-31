package com.teeny.wms.security;

import com.teeny.wms.core.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by lilei on 2017/7/10.
 */
public class WmsAuthority implements GrantedAuthority {

    private RoleType roleType;

    public WmsAuthority(RoleType roleType) {
        super();
        this.roleType = roleType;
    }

    public String getAuthority() {
        return "ROLE_"+roleType.toString();
    }
}
