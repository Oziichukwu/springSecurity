package com.example.springsecurity.data.models;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityModel implements GrantedAuthority {

    private String Authority;

    public String getAuthority() {
        return Authority;
    }

    public void setAuthority(String authority) {
        Authority = authority;
    }

    public AuthorityModel(String authority) {
        Authority = authority;
    }
}
