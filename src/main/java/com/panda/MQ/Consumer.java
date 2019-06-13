package com.panda.MQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    private  ConnectionFactory factory=new ActiveMQConnectionFactory(  "panda"
            ,"1314229","tcp://localhost:61616");
    private  Connection connection;
    private  Session session;
    private  Destination queue;
    final   private String check_class="class=15101";

    public void recvice() throws JMSException {
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        queue = session.createQueue("first");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new Listener());
    }
    class Listener implements MessageListener{

        public void onMessage(Message message) {
            if (message instanceof MapMessage){
                MapMessage msg=(MapMessage)message;
                try {
                    System.out.println(msg.getInt("class"));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) throws JMSException{
        //采用安全机制时   连接mq必须要账号密码
        //不采用默认的ActiveMQConnectionFactory.DEFAULT_PASSWORD
         new Consumer().recvice();
    }
}
