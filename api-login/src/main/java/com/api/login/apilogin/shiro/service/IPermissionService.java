package com.api.login.apilogin.shiro.service;

import com.api.login.apilogin.shiro.entity.Permission;
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
public interface IPermissionService extends IService<Permission> {

    List<Permission> getPermissionByRoleId(Long roleId);
}
