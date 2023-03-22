package com.company.security;

import com.company.domain.auth.AuthPermission;
import com.company.domain.auth.AuthRole;
import com.company.domain.auth.AuthUser;
import com.company.domain.basicsOfBasics.Language;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public record AuthUserUserDetails(AuthUser authUser) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authRoles = Objects.requireNonNullElse(authUser.getAuthRoles(), Collections.<AuthRole>emptySet());
        var authPermissions = Objects.requireNonNullElse(authUser.getAuthPermissions(), Collections.<AuthPermission>emptySet());
        var authorities = new ArrayList<SimpleGrantedAuthority>();
        authRoles.forEach(authRole -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authRole.getCode()));
        });

        authPermissions.forEach(authPermission -> {
            authorities.add(new SimpleGrantedAuthority(authPermission.getCode()));
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    public Integer getId() {
        return authUser.getId();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
    }
    public String getLanguage(){
        return authUser.getLanguage().getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !authUser.getStatus().equals(AuthUser.Status.BLOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return AuthUser.Status.ACTIVE.equals(authUser.getStatus());
    }
}
