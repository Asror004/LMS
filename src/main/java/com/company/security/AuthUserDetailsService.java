package com.company.security;

import com.company.domain.auth.AuthUser;
import com.company.repository.auth.AuthUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {
    private final AuthUserRepository authUserRepository;

    public AuthUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Bad Credentials"));
        return new AuthUserDetails(authUser);
    }
}
