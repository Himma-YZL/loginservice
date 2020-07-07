package com.api.login.apilogin.shiro.service.impl;

import com.api.login.apilogin.shiro.entity.Permission;
import com.api.login.apilogin.shiro.mapper.PermissionMapper;
import com.api.login.apilogin.shiro.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangzhilong
 * @since 2020-07-07
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionByRoleId(Long roleId) {
        return permissionMapper.getPermissionByRoleId(roleId);
    }
}
