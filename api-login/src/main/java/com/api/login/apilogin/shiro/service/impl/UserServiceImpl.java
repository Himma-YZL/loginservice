package com.api.login.apilogin.shiro.service.impl;

import com.api.login.apilogin.shiro.entity.User;
import com.api.login.apilogin.shiro.mapper.UserMapper;
import com.api.login.apilogin.shiro.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangzhilong
 * @since 2020-07-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
