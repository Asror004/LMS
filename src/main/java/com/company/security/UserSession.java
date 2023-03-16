package com.company.security;

import com.company.domain.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSession {
    public AuthUser getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        var authUserDetails = authentication.getPrincipal();
        if (authUserDetails instanceof AuthUserUserDetails a)
            return a.authUser();
        return null;
    }

    public String getId() {
        AuthUser user = getUser();
        if (user != null)
            return String.valueOf(user.getId());
        return null;
    }
    public String getLanguage() {
        AuthUser user = getUser();
        if (user != null)
            return user.getLanguage().getCode();
        return null;
    }
}
