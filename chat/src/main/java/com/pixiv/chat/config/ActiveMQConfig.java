package com.pixiv.chat.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.SimpleMessageConverter;

import javax.jms.Queue;
import javax.jms.Topic;



public class ActiveMQConfig {

    //配置一个消息队列（P2P模式）

    public Queue messageQueue() {
        //这里相当于为消息队列起一个名字用于生产消费用户访问日志 在后台管理系统中的队列名称即为这个
        return new ActiveMQQueue("queue");
    }


    public Topic topic() {
        return new ActiveMQTopic("topic1");
    }


}
