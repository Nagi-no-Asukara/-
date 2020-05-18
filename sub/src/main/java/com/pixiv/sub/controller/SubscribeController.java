package com.pixiv.sub.controller;

import com.pixiv.sub.bean.SubInfo;
import com.pixiv.sub.listener.MySessionListener;
import com.pixiv.sub.service.SubscribeService;
import com.pixiv.sub.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class SubscribeController {

    @Autowired
    SubscribeService subscribeService;

    @RequestMapping("/add")
    public void Subscribe() throws JMSException {


        subscribeService.Addsubscriber("6","1","1");
    }

    /*public Result subscribe(@RequestBody SubInfo subInfo) throws JMSException {

        subscribeService.Addsubscriber(subInfo.getUp_id(),subInfo.getUser_id());
        return  Result.ok();
    }
*/
    @RequestMapping("/delete")
    public void subscribe() throws JMSException {

        subscribeService.delete("3","1","1");

    }

    @RequestMapping("/receive")
    public void receive() throws JMSException {
        subscribeService.receive("4","1","xiangxue");
    }

    @RequestMapping("/test")
    public String test1(HttpServletRequest request){

        HttpSession session=request.getSession(true);
        return  session.getId();
    }

    @RequestMapping ("/{id}")
    public  void test(@PathVariable String id)
    {
        System.out.println("为啥执行两次2");
        MySessionListener.isOnline(id);
        return;
    }

}
