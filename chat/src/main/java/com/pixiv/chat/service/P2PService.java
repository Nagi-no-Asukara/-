package com.pixiv.chat.service;

import com.pixiv.chat.bean.QueenMessage;

import com.pixiv.chat.dao.mapper.UserInfoMapper;
import com.pixiv.chat.utils.Result;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;



public class P2PService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue messageQueue;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Resource
    private SimpleMessageConverter simpleMessageConverter;

    public Result send(Integer id, String content, Integer to)
    {
        ActiveMQQueue activeMQQueue=new ActiveMQQueue(to.toString());
        QueenMessage queenMessage=new QueenMessage(id.toString(),content);
        System.out.println(id.toString()+" "+content);
        jmsMessagingTemplate.convertAndSend(activeMQQueue,queenMessage);
        return Result.ok();
    }


}
