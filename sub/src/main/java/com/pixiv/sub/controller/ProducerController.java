package com.pixiv.sub.controller;

import com.pixiv.sub.bean.QueenMessage;

import com.pixiv.sub.config.P2pConsumerListener;
import com.pixiv.sub.service.P2PService;

import com.pixiv.sub.service.SubscribeService;

import com.pixiv.sub.utils.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

@RestController
@CrossOrigin
public class ProducerController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue messageQueue;

    @Resource
    private Topic messageTopic;

    @Autowired
    SubscribeService topicService;

    @Autowired
    P2PService p2PService;



    @RequestMapping("/sendMessage")
    public void send() throws InterruptedException, JMSException {
        topicService.sendMessage("DurableTopic2","测试内容");
    }


    public void receive() throws JMSException {
       topicService.receive("DurableTopic2","Lison","xiangxue");
    }



}
