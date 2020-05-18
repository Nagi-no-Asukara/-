package com.pixiv.chat.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class WebsocketInterceptor implements HandshakeInterceptor {

    private static final Logger log = LoggerFactory.getLogger(WebsocketInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            // 取用户连接姓名作为参数传入
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            String userName = request.getServletRequest().getParameter("name");

            log.info("拦截请求 获取用户信息: " + userName);
            // 拿到对应的Session
            HttpSession session = request.getServletRequest().getSession(true);
            if (session != null) {
                // 唯一表示Session
                map.put("username", userName);
                log.info("获取Session: " + session.getId());
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
