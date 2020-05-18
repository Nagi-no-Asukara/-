package com.pixiv.sso.service;




import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.UUID;

@Service
public class MailService {



    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * 发送文本邮件
     */
    public String sendSimpleMail(String to, String subject) throws MessagingException {

        String token = UUID.randomUUID().toString();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);

        helper.setText("<body style=\"text-align: center;margin-left: auto;margin-right: auto;\">\n"
                + " <div id=\"welcome\" style=\"text-align: center;position: absolute;\" >\n"
                +"      <h3>\"验证链接 复制以下链接并打开即可完成注册\"</h3>\n"
                +"      <span>localhost:8085/sso/register?token="+token+"</span>"
                + "     <div style=\"text-align: center; padding: 10px\"><a style=\"text-decoration: none;\" href=\"https://localhost:8085/sso/register\" target=\"_bank\" >"
                + "           <strong></strong>提醒 有效期 仅有两个小时</a></div>\n"
                + " </div>\n" + "</body>",true);

       // message.setText("localhost:8080/register?token="+token);
        mailSender.send(message);
        return token;
    }

}
