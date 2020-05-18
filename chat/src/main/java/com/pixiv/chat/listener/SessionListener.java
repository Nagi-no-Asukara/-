package com.pixiv.chat.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer count = (Integer) context.getAttribute("online-count");
        if(count == null){
            count = 1;
        }else{
            count++;
        }
        context.setAttribute("online-count", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer count = (Integer) context.getAttribute("online-count");
        if(count > 2){
            count--;
        }else{
            count = 1;
        }
        context.setAttribute("online-count", count);
    }

}
