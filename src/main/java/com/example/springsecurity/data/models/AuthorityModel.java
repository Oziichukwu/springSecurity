package com.example.springsecurity.data.models;

public class AuthorityModels {

    private String Authority;

    public String getAuthority() {
        return Authority;
    }

    public void setAuthority(String authority) {
        Authority = authority;
    }

    public AuthorityModels(String authority) {
        Authority = authority;
    }
}
