package com.pixiv.solr.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 曾昭阳
 * @date 2019/7/26 8:31
 */
@Component
public class SpringUtil {
    private static WebApplicationContext applicationContext;

    @Autowired
    SpringUtil(WebApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    static JsonArray getAllUrl(WebApplicationContext applicationContext) {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> urlMap = mapping.getHandlerMethods();
        List<Map<String, String>> list = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : urlMap.entrySet()) {
            Map<String, String> urls = new HashMap<>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                urls.put("url", url);
            }
            urls.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            urls.put("method", method.getMethod().getName()); // 方法名
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                urls.put("type", requestMethod.toString());
            }
            list.add(urls);
        }
        return new JsonParser().parse(new Gson().toJson(list)).getAsJsonArray();
    }

    public static List<String> getUrlList() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> urlMap = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>();
        urlMap.forEach((info, handlerMethod) -> {
            PatternsRequestCondition p = info.getPatternsCondition();
            urlList.addAll(p.getPatterns());
        });
        return urlList;
    }
}
