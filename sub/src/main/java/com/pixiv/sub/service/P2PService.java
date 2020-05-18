package com.pixiv.sub.service;

import com.pixiv.sub.bean.QueenMessage;
import com.pixiv.sub.utils.Result;
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


    public Result send(Integer id, String content, Integer to)
    {
        ActiveMQQueue activeMQQueue=new ActiveMQQueue(to.toString());
        QueenMessage queenMessage=new QueenMessage(id.toString(),content);
        jmsMessagingTemplate.convertAndSend(activeMQQueue,queenMessage);
        return Result.ok();
    }

}
