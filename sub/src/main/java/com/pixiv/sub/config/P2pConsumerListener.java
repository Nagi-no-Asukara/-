package com.pixiv.sub.config;

import com.pixiv.sub.bean.QueenMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j   //p2p简称 point to point 即点对点
public class P2pConsumerListener{

    //设置一个监听器来模拟收到消息
    //@JmsListener(destination = "queue")  //queue对应队列的名称
    /*public void insertVisitLog(QueenMessage queenMessage) {
        log.info("消费者接收数据 : " + queenMessage);
    }*/




}
