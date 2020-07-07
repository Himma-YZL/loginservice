package com.api.login.apilogin.shiro.controller;


import com.api.login.apilogin.shiro.entity.User;
import com.api.login.apilogin.shiro.service.IUserService;
import com.api.login.apilogin.utils.EncryptUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/shiro/user")
@Api(value = "用户接口")
public class UserController {

    @Autowired
    IUserService userService;

    @RequiresUser
    @PostMapping("/addUser")
    @ApiOperation(value = "新增/注册用户")
    public String addUser(User user){
        //注册密码加密
        String password = EncryptUtil.md5Password(user.getUserName(),user.getPassword());
        user.setPassword(password);
        boolean result = userService.save(user);
        if (result){
            return "新增成功";
        }else {
            return "新增失败";
        }
    }

    @RequiresPermissions("add")
    @GetMapping("/getUserPermission")
    @ApiOperation(value = "校验用户是否有user-add权限接口")
    public String getUserPermission(){
        return "user-add";
    }
}
