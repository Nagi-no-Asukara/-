package com.pixiv.authority.config.JWT;


import com.pixiv.authority.config.exception.CommonException;
import com.pixiv.authority.config.exception.CommonExceptionType;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 自定义拦截器
 */
@Component
public class JWTInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    JwtToken jwtToken;

    /**
     * 进入目标方法之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization =request.getHeader("Authorization");

        if(!StringUtils.isEmpty(authorization)&&authorization.startsWith("Bearer"))
        {
            String token=authorization.replace("Bearer ","");

            Claims claims= jwtToken.jwtParse(token);
            if (claims!=null){

                String apis=(String)claims.get("apis");  //api-user-update

                HandlerMethod h= (HandlerMethod) handler;
                //获取requestMapping注解 从而获取api接口的name属性
                RequestMapping requestMapping=h.getMethodAnnotation(RequestMapping.class);
                String name=requestMapping.name();
                //判断用户是否有该api权限
                if(apis.contains(name)) {
                    request.setAttribute("claims", claims);
                    return true;
                }
                else
                    throw new CommonException(CommonExceptionType.Authority_ERROR);
            }
        }
        throw new CommonException(CommonExceptionType.Authority_ERROR);
    }

    /**
     * 执行目标方法之后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }


}
