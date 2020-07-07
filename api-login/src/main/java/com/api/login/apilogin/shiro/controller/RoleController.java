package com.api.login.apilogin.shiro.controller;


import com.api.login.apilogin.shiro.entity.Role;
import com.api.login.apilogin.shiro.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangzhilong
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/shiro/role")
@Api(value = "角色接口")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @PostMapping("addRole")
    @ApiOperation( value = "新增角色")
    public String addRole(Role role){
        boolean result = roleService.save(role);
        if (result){
            return "新增角色成功";
        }else {
            return "新增角色失败";
        }
    }
}
