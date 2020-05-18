package com.pixiv.authority.config.shiro;

import com.pixiv.authority.config.JWT.JwtToken;
import com.pixiv.authority.utils.JwtUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

//自定义sessionManager  对某些默认方法进行自定义
//像sessionManager默认是把sessionId放在cookie中的 改为放入header
public class CustomSessionManager extends DefaultWebSessionManager {

    private static String Header="Authorization";

    private static String headerPrefix="Bearer ";
    /**
     * 规定将sessionId放入请求头中 请求头为Authorization：（value）
     *  自定义获取的sessionId
     * @param request
     * @param response
     * @return
     */
    protected Serializable getSessionId(ServletRequest request, ServletResponse response){


        String id= WebUtils.toHttp(request).getHeader(Header);

        //如果是登录请求则原先没有Id
        if(StringUtils.isEmpty(id)){
            return super.getSessionId(request,response);
        }

        //id=id.replace(headerPrefix,"");

        id= (String) JwtUtils.jwtParse(id,"Miuna").get("sid");



        //请求头里有直接获取
        //session是从哪里得到的 cookie or header？
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
        //当前sessionId究竟是什么

        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        return id;

    }


}
