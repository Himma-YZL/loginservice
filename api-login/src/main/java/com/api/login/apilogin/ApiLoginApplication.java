package com.api.login.apilogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "com.api.login.apilogin.shiro.mapper")
public class ApiLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiLoginApplication.class, args);
    }

}
