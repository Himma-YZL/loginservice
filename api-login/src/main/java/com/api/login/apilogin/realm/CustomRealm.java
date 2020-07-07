package com.api.login.apilogin.realm;

import com.api.login.apilogin.shiro.entity.Permission;
import com.api.login.apilogin.shiro.entity.Role;
import com.api.login.apilogin.shiro.entity.User;
import com.api.login.apilogin.shiro.service.IUserService;
import com.api.login.apilogin.shiro.service.impl.PermissionServiceImpl;
import com.api.login.apilogin.shiro.service.impl.RoleServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PermissionServiceImpl permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (user == null){
            return simpleAuthorizationInfo;
        }
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("user_name",user.getUserName());
//        User user = userService.getOne(queryWrapper);
        List<Role> roleList = roleService.getRolesByUserId(user.getId());
        //添加角色和权限
        for (Role role : roleList){
            simpleAuthorizationInfo.addRole(role.getRole());
            List<Permission> permissionList = permissionService.getPermissionByRoleId(role.getId());
            for (Permission permission : permissionList){
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        //密码要用new String((char[]) ...)接收，强转String报错
        String password = new String((char[]) authenticationToken.getCredentials());
        if (StringUtils.isEmpty(userName) || "null".equals(userName)){
            throw new AuthenticationException("用户名错误");
        }else if (StringUtils.isEmpty(password) || "null".equals(password)){
            throw new AuthenticationException("密码格式错误");
        }else {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_name",userName);
            User user = userService.getOne(queryWrapper);
            if (user == null){
                throw new UnknownAccountException("用户不存在");
            }
            //传入用户信息
            SimpleAuthenticationInfo simpleAuthenticationInfo =  new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(userName + "salt"),getName());

            //验证成功开始踢人(清除缓存和Session)
//            ShiroUtil.deleteCache(user.getUserName(),true);
            return simpleAuthenticationInfo;
        }
    }
}
