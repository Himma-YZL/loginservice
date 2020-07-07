package com.api.login.apilogin.shiro.service;

import com.api.login.apilogin.shiro.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangzhilong
 * @since 2020-07-07
 */
public interface IRoleService extends IService<Role> {

    List<Role> getRolesByUserId(Long userId);
}
