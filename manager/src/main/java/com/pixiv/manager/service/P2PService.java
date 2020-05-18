package com.pixiv.manager.service;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;

@Service
public class P2PService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue messageQueue;



    public void send(String name) {
        jmsMessagingTemplate.convertAndSend(messageQueue,name);
    }


}
