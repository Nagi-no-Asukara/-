package com.pixiv.sub.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class MySessionListener implements HttpSessionListener {

    private static Map<String,String> sessionMap = new HashMap<>();

    private static int num=0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        num++;
        HttpSession session = se.getSession();
        String sessionId = session.getId();
        sessionMap.put(sessionId,"online");
        System.out.println(sessionId);
        System.out.println("某用户上线,现在人数为"+num);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        num--;
        HttpSession session = se.getSession();
        String sessionId = session.getId();
        System.out.println(sessionId);
        sessionMap.remove(sessionId);
        System.out.println("某用户下线,现在人数为"+num);
    }


    public static   boolean   isOnline(String id){
        System.out.println("为啥执行两次1");
        boolean   flag   =   true;
        if(!sessionMap.containsKey(id))
        {
            flag=false;
            System.out.println("他下线了");
        }
        else {
            System.out.println("他仍然上线");
        }
        return   flag;
    }



}
