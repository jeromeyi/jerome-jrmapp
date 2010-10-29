package com.jrmapp.activemq.persist;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 28, 2010 6:41:02 PM
 * @类说明
 */
public class VisitLogSender {
private static  String subject = "askyaya.visit.log";  
    
    private static Destination destination = null;   //消息的目的地 
     
    private static  Connection connection = null;   //JMS 客户端到JMS Provider 的连接 
    
    private static Session session = null;    //一个发送或接收消息的线程 
    
    private static  MessageProducer producer = null;  //由Session 对象创建的用来发送消息的对象 
   
    private static VisitLogSender visitLogSender = null; 
    
    public static VisitLogSender getVisitLogSender(){ 
      if(visitLogSender == null){ 
       visitLogSender = new VisitLogSender();       
       try{ 
        visitLogSender.initialize(); 
       }catch(Exception e) 
       {e.printStackTrace();} 
      }      
      return visitLogSender; 
     } 
     
  public  void   initialize() throws JMSException, Exception { 
      
      System.out.println("url----------->"+new GetJmsUrl().getUrl()); 
        
        ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory("","",new GetJmsUrl().getUrl()); 
         
        connection = connectionFactory.createConnection(); 
         connection.start(); 
         session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //不用事务了,session以自动方式接收,通用性好 
         destination = session.createQueue(subject);    
         producer = session.createProducer(destination);    

         producer.setDeliveryMode(DeliveryMode.PERSISTENT); //以持久的方式到队列oracle 数据库 
         
     }  
    
    
    
     //发送消息   
     public void sendMessage(VisitStatInfoBean message) throws JMSException,Exception { 
            
         ObjectMessage msg=session.createObjectMessage();         
         msg.setObject(message);                    
         System.out.println("Producer:->Sending askyaya_2007 message:"+message); 
         System.out.println("Producer:->Sending askyaya_2007 msg:"+msg);         
         producer.send(msg); 
         System.out.println("Producer:->askyaya_2007 Message sent complete!"); 
      
     }    
    
     // 关闭连接  
    public void close() throws JMSException {    
         System.out.println("Producer:->Closing connection");    
         if (producer != null)    
             producer.close();    
         if (session != null)    
             session.close();    
         if (connection != null)   
             connection.close();    
     }   

} 