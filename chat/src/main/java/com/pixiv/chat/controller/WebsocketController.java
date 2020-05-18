package com.pixiv.chat.controller;




import com.pixiv.chat.dao.mapper.UserInfoMapper;
import com.pixiv.chat.entity.UserInfo;
import com.pixiv.chat.utils.JsonUtils;
import com.pixiv.chat.utils.WebSocket;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.Message;


import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class WebsocketController {



    @Autowired
    UserInfoMapper userInfoMapper;

    @GetMapping("/send")
    public String test() {

        return JsonUtils.objectToJson(userInfoMapper.selectById(8));
    }

    /*@MessageMapping("/sendMessage")
    public String send(Message message)
    {
       return webSocket.sendOneMessage();
    }*/

   /* @PostMapping("/sendMessage")
    public String sendOneWebSocket(@RequestBody MsgInfo msgInfo) {
        String text=msgInfo.getName()+" 你好！ 这是websocket单人发送！";
        webSocket.sendOneMessage(msgInfo.getName(),msgInfo.getMessage());
        return text;
    }*/
}
