package com.service.feign.servicemanage.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "api-login")
public interface LoginFeignService {

    @GetMapping("/nacos/test")
    String nacosFeignTest();

    @PostMapping("/login")
    String login(String userName , String password);
}
