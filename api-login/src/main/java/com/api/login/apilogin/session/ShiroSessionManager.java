package com.api.login.apilogin.session;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class ShiroSessionManager extends DefaultWebSessionManager {

    //请求头中存储token的属性名
    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    //重写构造方法
    public ShiroSessionManager(){
        super();
        this.setDeleteInvalidSessions(true);
    }

    /**
     * 重写获取sessionId方法
     * @param request
     * @param response
     * @return
     */
    @Override
    public Serializable getSessionId(ServletRequest request, ServletResponse response){
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if (!StringUtils.isEmpty(token)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return token;
        }else {
            return super.getSessionId(request,response);
        }
    }
}
