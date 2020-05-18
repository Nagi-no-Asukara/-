package com.pixiv.gateway.moduleService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="authority")
public interface AuthorityService {

    @PostMapping(value = "/authority/ModuleService/checkPermission")
    boolean checkPermission(@RequestParam String url);
}
