package com.teeny.wms.security;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.core.domain.User;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import java.security.Principal;

/**
 * Created by lilei on 2017/7/10.
 */
public class CurrentUserWebArgumentResolver implements WebArgumentResolver {
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
        if (methodParameter.getParameterType() == Employess.class  && methodParameter.getParameterAnnotation(CurrentUser.class) != null) {
            Principal principal = webRequest.getUserPrincipal();
            WmsUser userDetails = (WmsUser) ((Authentication) principal).getPrincipal();
            return userDetails.getUser();
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }
}
