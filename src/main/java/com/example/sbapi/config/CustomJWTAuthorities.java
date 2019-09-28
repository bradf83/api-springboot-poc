package com.example.sbapi.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomJWTAuthorities extends JwtAuthenticationConverter {
    @Override
    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        return super.extractAuthorities(jwt);
    }
}
