package com.teeny.wms.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;

/**
 * Created by lilei on 2017/7/10.
 */
public class WmsAutoApprovalHandler implements UserApprovalHandler{
    public AuthorizationRequest updateBeforeApproval(AuthorizationRequest authorizationRequest, Authentication authentication) {
        return authorizationRequest;
    }

    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication authentication) {
        return authentication.isAuthenticated();
    }
}
