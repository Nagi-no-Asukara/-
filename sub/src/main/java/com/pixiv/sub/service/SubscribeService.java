package com.pixiv.sub.service;

import ch.qos.logback.core.net.server.Client;
import com.pixiv.sub.bean.QueenMessage;
import com.pixiv.sub.bean.UserInfo;
import com.pixiv.sub.dao.mapper.UserInfoMapper;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;


import javax.jms.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Component
public class SubscribeService {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    UserInfoMapper userInfoMapper;

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认连接用户名

    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认连接密码

    private static final String BROKEURL = "tcp://192.168.30.129:61616";//默认连接地址

    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ActiveMQConnectionFactory(SubscribeService.USERNAME,
                SubscribeService.PASSWORD, SubscribeService.BROKEURL);
    }


    // 发送消息，destination是发送到的队列 是具体的类，message是待发送的消息
    public void send(Topic name, QueenMessage message){
        jmsMessagingTemplate.convertAndSend(name,message);
    }

    public void send(Destination name, QueenMessage message){
        jmsMessagingTemplate.convertAndSend(name,message);
    }


    public void Addsubscriber(String topic_id,String clientID,String name) throws JMSException {

       // UserInfo userInfo=userInfoMapper.selectByPrimaryKey(Integer.parseInt(clientID));

        //String name=userInfo.getName();

        Connection connection=null;

        Session session = null;
        MessageConsumer messageConsumer=null;
        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            connection.setClientID(clientID);
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic destination = session.createTopic(topic_id);

            //创建消息消费者
            messageConsumer = session.createDurableSubscriber(destination,name);
            messageConsumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        System.out.println("Accept msg : "
                                +((TextMessage)message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (JMSException e) {
            e.printStackTrace();
        }
        finally {
            messageConsumer.close();
            session.close();
            connection.close();
        }

    }

    public void sendMessage(String topic_name,String content) throws JMSException, InterruptedException {
         Connection connection=getConnection();
        Session session = getSession(connection);
        MessageProducer producer = session.createProducer(null);
        Destination destination = session.createTopic(topic_name);
            TextMessage msg = session.createTextMessage("我是消息内容" + content);
            // 第一个参数目标地址
            // 第二个参数 具体的数据信息
            // 第三个参数 传送数据的模式
            // 第四个参数 优先级
            // 第五个参数 消息的过期时间
            producer.send(destination, msg);
            System.out.println("发送消息：" + msg.getText());

            session.close();
            connection.close();

    }

    public void receive(String topic_name,String clientID,String sub_name) throws JMSException {
        Connection connection=null;
        Session session=null;
        MessageConsumer consumer=null;
        try {
             connection=getConnection(clientID);
             connection.start();
             session=getSession(connection);
            //订阅者要使用这种方式创建
            Topic topic = session.createTopic(topic_name);

            consumer = session.createDurableSubscriber(topic, sub_name);
            
                consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        TextMessage textMessage = (TextMessage) message;
                        String text = textMessage.getText();
                        System.out.println(" 获取消息 ---->" + text);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            });


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            assert consumer != null;
            consumer.close();
            session.close();
            connection.close();
        }


    }



    public  void delete(String topic_name,String up_id,String user_id) throws JMSException {

        Connection connection=getConnection(up_id);
        Session session=getSession(connection);
        session.createTopic(topic_name);
        session.unsubscribe(user_id);


        session.close();
        connection.close();
    }



    private Connection getConnection(String clientID) throws JMSException {

        Session session;//会话 接受或者发送消息的线程
        Connection connection=connectionFactory.createConnection();
        connection.setClientID(clientID);
        return connection;
    }

    private Connection getConnection() throws JMSException {


        return connectionFactory.createConnection();
    }

    private Session getSession(Connection connection) throws JMSException {
        //第一个参数为是否启动事务
        return  connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public   static   boolean   isOnline(HashMap sessionMap,HttpSession session){
        boolean   flag   =   true;
        if(!sessionMap.containsKey( session.getId())){
            flag=false;
            System.out.println("他下线了");
        }
        System.out.println("他仍然上线");
        return   flag;
    }




}
