package com.service.feign.servicemanage.controller;

import com.service.feign.servicemanage.service.LoginFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginFeignController {

    @Autowired
    LoginFeignService loginFeignService;

    @GetMapping("/login/test")
    public String test(){
        String result = loginFeignService.nacosFeignTest();
        return result;
    }

    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,@RequestParam("password") String password){
        String result = loginFeignService.login(userName,password);
        return result;
    }
}
