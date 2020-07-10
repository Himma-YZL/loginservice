package com.api.login.apilogin.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import com.api.login.apilogin.shiro.entity.ResultDTO;
import com.api.login.apilogin.shiro.entity.User;
import com.api.login.apilogin.utils.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@Api(tags = "登录接口")
public class LoginController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public ResultDTO<User> login(String userName , String password, HttpServletRequest request){
        log.info("------------------{}---------------------", port);
        JSONObject jsonObject = new JSONObject();
        ResultDTO<User> resultDTO = new ResultDTO<>();
//        Cookie[] cookies = request.getCookies();
        Subject subject = SecurityUtils.getSubject();
//        boolean remembered = subject.isRemembered();
//        if (remembered){
//
//        }
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,password);
//        usernamePasswordToken.setRememberMe(remembered);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException uae) {
//            jsonObject.put("flag","ERROR");
//            jsonObject.put("status","500");
//            jsonObject.put("MSG","未知账户");
            resultDTO.setCode("500");
            resultDTO.setMessage("未知账户");
            resultDTO.setSuccess("false");
            log.info("当前访问的端口->{}",port);
            return resultDTO;
        } catch (IncorrectCredentialsException ice) {
            jsonObject.put("flag","ERROR");
            jsonObject.put("status","500");
            jsonObject.put("MSG","密码不正确");
            resultDTO.setCode("500");
            resultDTO.setMessage("密码不正确");
            resultDTO.setSuccess("false");
            log.info("当前访问的端口->{}",port);
            return resultDTO;
//            return jsonObject;
        } catch (LockedAccountException lae) {
            jsonObject.put("flag","ERROR");
            jsonObject.put("status","500");
            jsonObject.put("MSG","账户已锁定");
            resultDTO.setCode("500");
            resultDTO.setMessage("账户已锁定");
            resultDTO.setSuccess("false");
            log.info("当前访问的端口->{}",port);
            return resultDTO;
//            return jsonObject;
        } catch (ExcessiveAttemptsException eae) {
            jsonObject.put("flag","ERROR");
            jsonObject.put("status","500");
            jsonObject.put("MSG","用户名或密码错误次数过多");
            resultDTO.setCode("500");
            resultDTO.setMessage("用户名或密码错误次数过多");
            resultDTO.setSuccess("false");
            log.info("当前访问的端口->{}",port);
            return resultDTO;
//            return jsonObject;
        } catch (AuthenticationException ae) {
            jsonObject.put("flag","ERROR");
            jsonObject.put("status","500");
            jsonObject.put("MSG","用户名或密码不正确");
            resultDTO.setCode("500");
            resultDTO.setMessage("用户名或密码不正确");
            resultDTO.setSuccess("false");
            log.info("当前访问的端口->{}",port);
            return resultDTO;
//            return jsonObject;
        }
        if (subject.isAuthenticated()){
            String sessionId = ShiroUtil.getSession().getId().toString();
            User userInfo = ShiroUtil.getUserInfo();
            jsonObject.put("flag","SUCCESS");
            jsonObject.put("status","200");
            jsonObject.put("MSG","登录成功");
            jsonObject.put("SESSIONID",sessionId);
            jsonObject.put("USERINFO",userInfo);
            redisTemplate.opsForValue().set(userName,sessionId,60, TimeUnit.SECONDS);
            resultDTO.setCode("200");
            resultDTO.setMessage("登录成功");
            resultDTO.setSuccess("true");
            resultDTO.setData(userInfo);
            log.info("当前访问的端口->{}",port);
            return resultDTO;
//            return jsonObject;
        }else {
            jsonObject.put("flag","ERROR");
            jsonObject.put("status","500");
            jsonObject.put("MSG","登录失败");
            resultDTO.setCode("500");
            resultDTO.setMessage("登录失败");
            resultDTO.setSuccess("false");
            log.info("当前访问的端口->{}",port);
            return resultDTO;
//            return jsonObject;
        }
    }

    @PostMapping("/loginOut")
    @ApiOperation(value = "退出登录")
    public String loginOut(){
        ShiroUtil.logout();
        return "退出登录";
    }

    @GetMapping("/nacos/test")
    String nacosFeignTest() {
        return "Nacos Test";
    }
}
