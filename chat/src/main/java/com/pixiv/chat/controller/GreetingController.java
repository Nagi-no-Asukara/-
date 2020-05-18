package com.pixiv.chat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


public class GreetingController {




      @MessageMapping("/hello")
      @SendTo("/topic/greeting")
      public Message greeting(Message message){
            return  message;
      }
}
