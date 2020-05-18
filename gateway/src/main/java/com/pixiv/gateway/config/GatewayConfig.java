package com.pixiv.gateway.config;


import com.pixiv.gateway.filter.AuthorityFilter;

public class GatewayConfig {


    public AuthorityFilter authorityFilter(){
        return new AuthorityFilter();
    }
}
