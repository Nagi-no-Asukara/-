package com.pixiv.chat.controller;



import com.pixiv.chat.bean.ChatInfo;
import com.pixiv.chat.config.ActiveMQConfig;
import com.pixiv.chat.dao.mapper.UserInfoMapper;
import com.pixiv.chat.service.P2PService;
import com.pixiv.chat.utils.Result;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;




public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue messageQueue;

    @Resource
    private Topic messageTopic;

    @Autowired
    private P2PService p2PService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @PostMapping("/send")
    public Result send(@RequestBody ChatInfo chatInfo){

        String idstr=chatInfo.getId();
        String content=chatInfo.getContent();
        String tostr=chatInfo.getTo();
        Integer id=Integer.parseInt(idstr);
        Integer to=Integer.parseInt(tostr);

        return p2PService.send(id,content,to);

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
     public String home(Locale locale, Model model) {
                 logger.info("Welcome home! The client locale is {}.", locale);

                 Date date = new Date();
                 DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

                 String formattedDate = dateFormat.format(date);

                 model.addAttribute("serverTime", formattedDate );

                return "home";
             }

             @RequestMapping(value = "/chat", method = RequestMethod.GET)

     public String chat(Locale locale, Model model) {
                return "chat";
             }

    @GetMapping("/message/{id}")
    public Result Checkmessage(@PathVariable String id){

         return  Result.ok();
    }
}
