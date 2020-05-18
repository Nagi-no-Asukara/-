package com.pixiv.solr.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;


public class ConsumerService {

  /*  @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = "ImageQueue")
    public void handleMessage(String name) {
        System.out.println("成功接受Name" + name);

    }*/


}
