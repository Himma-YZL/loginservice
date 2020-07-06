package com.api.login.apilogin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/nacos/test")
    public String nacosTest(){
        return "Nacos Test";
    }
}
