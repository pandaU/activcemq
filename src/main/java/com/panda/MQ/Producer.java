package com.panda.MQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

public class Producer {
    public static void main(String[] args) throws JMSException, InterruptedException {
        ConnectionFactory factory=new ActiveMQConnectionFactory(  "panda"
                ,"1314229","tcp://localhost:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("first");
        MessageProducer producer = session.createProducer(null);
        MapMessage mapMessage1 = session.createMapMessage();
        mapMessage1.setInt("class",15101);
        mapMessage1.setIntProperty("class",15101);
        mapMessage1.setStringProperty("teacher","谢诗琪");
        mapMessage1.setString("teacher1","谢诗琪");
        mapMessage1.setString("teacher2","刘丽芳");
        mapMessage1.setString("teacher3","周颖");
        MapMessage mapMessage2 = session.createMapMessage();
        mapMessage2.setInt("class",15102);
        mapMessage2.setIntProperty("class",15102);
        mapMessage2.setStringProperty("teacher","周颖");
        mapMessage2.setString("teacher1","谢诗琪");
        mapMessage2.setString("teacher2","刘丽芳");
        mapMessage2.setString("teacher3","周颖");
        producer.send(queue,mapMessage1);
        producer.send(queue,mapMessage2);
        session.commit();
            //TimeUnit.SECONDS.sleep(1);
        if (connection!=null){
            connection.close();
        }
    }
}
