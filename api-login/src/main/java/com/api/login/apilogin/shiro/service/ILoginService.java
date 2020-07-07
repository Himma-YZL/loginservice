package com.api.login.apilogin.shiro.service;


import com.api.login.apilogin.shiro.entity.User;

/**
 * <p>
 *  登录 服务类
 * </p>
 *
 * @author yangzhilong
 * @since 2020-06-24
 */
public interface ILoginService {

    /**
     *
     */
    String loginUser(User user);
}
