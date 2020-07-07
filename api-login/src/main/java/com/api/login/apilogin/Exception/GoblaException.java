package com.api.login.apilogin.Exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.springboot.shiro.springbootshiro")
@Slf4j
public class GoblaException {

    /**
     * 登录异常
     */
    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public String loginException(){
        log.info("未登录");
        return "login";
    }

    /**
     * 权限异常
     * @return
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public String permissionException(){
        log.info("没有权限");
        return "/403";
    }

    @ExceptionHandler(Exception.class)
    public String exception(){
        log.info("系统异常");
        return "500";
    }
}
