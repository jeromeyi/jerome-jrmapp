package com.jrmapp.activemq;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

public class TopicMessageProducer {
	@Resource
	private ActiveMQConnectionFactory  connectionFactory;
    private JmsTemplate template;
    private static Destination destination1 = null;   //消息的目的地 
    
    private static  Connection connection = null;   //JMS 客户端到JMS Provider 的连接 
    
    private static Session session = null;    //一个发送或接收消息的线程 
    
    private static  MessageProducer producer = null;  //由Session 对象创建的用来发送消息的对象 
	private Topic destination;

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}

	public void setDestination(Topic destination) {
		this.destination = destination;
	}

	 public  void   initialize() throws JMSException, Exception { 
	      
	     // System.out.println("url----------->"+GetJmsUrl.getUrl()); 
	        
	       // ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory("","",GetJmsUrl.getUrl()); 
	         
	        connection = connectionFactory.createConnection(); 
	         connection.start(); 
	         session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //不用事务了,session以自动方式接收,通用性好 
	         destination = session.createTopic("TOPIC");    
	         producer = session.createProducer(destination);    

	         producer.setDeliveryMode(DeliveryMode.PERSISTENT); //以持久的方式到队列oracle 数据库 
	         
	     }  
	 
	    
	public void send(FooMessage message) throws JMSException {
		template.convertAndSend(this.destination, message);
		/*ObjectMessage msg=session.createObjectMessage();         
		msg.setObject(message);                        
        producer.send(msg); 
		//producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		//如果不想持久化可用下面语句
		//producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
*/	}
}
