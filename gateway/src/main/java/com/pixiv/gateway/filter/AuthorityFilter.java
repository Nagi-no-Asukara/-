package com.pixiv.gateway.filter;

import com.google.gson.JsonObject;
import com.pixiv.gateway.moduleService.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class AuthorityFilter implements GatewayFilter, Ordered {


    private AuthorityService authorityService;

    private Set<String> ignore;

    {
        ignore = new HashSet<>();
        ignore.add("/authority/Login");
        ignore.add("/authority/adminLogin");
        ignore.add("/collection/downloadTemplate");
        ignore.add("/userManagement/images");
        ignore.add("/notification/verification");
        ignore.add("/collection/admin/downloadFieldCollectionResult");
        ignore.add("/collection/admin/downloadZip");
        ignore.add("/calendar/downloadZip");
        ignore.add("/calendar/downloadCalendar");
        ignore.add("/calendar/downloadTemplate");
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("ServerGatewayFilter filter ");
        System.out.println("执行了过滤方法");
        String path = exchange.getRequest().getURI().getPath();
        String sid = exchange.getRequest().getHeaders().getFirst("Authorization");
        if(authorityService.checkPermission(path))
        return chain.filter( exchange );
        return authError(exchange.getResponse(), "未检测到权限信息，请登录后重试");

    }
    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 认证错误输出
     *
     * @param resp 响应对象
     * @param msg  错误信息
     */
    private Mono<Void> authError(ServerHttpResponse resp, String msg) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        JsonObject json = new JsonObject();
        json.addProperty("code", 400);
        json.addProperty("msg", msg);
        DataBuffer buffer = resp.bufferFactory().wrap(json.toString().getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

}
