package com.jrmapp.activemq.persist;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.generic.GenericBeanFactoryAccessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 28, 2010 6:41:57 PM
 * @类说明
 */
public class ReceiveVisitLog  implements MessageListener{ 

	  // private String user =ActiveMQConnection.DEFAULT_USER;    
	    
	  // private String password=ActiveMQConnection.DEFAULT_PASSWORD;    
	   
	   // private String url=ActiveMQConnection.DEFAULT_BROKER_URL;    
	   
	    private static String subject = "askyaya.visit.log";    
	   
	    private static Destination destination = null;    
	   
	    private static Connection connection = null;    
	   
	    private static  Session session = null;    
	   
	    private static MessageConsumer consumer = null; 
	    
	   
	    
	  //  private MessageProducer replyProducer; //反馈信息 
	    
	    //连接connection，get session 
	     private static ReceiveVisitLog receivevisitlog=null; 
	     
	     public static ReceiveVisitLog getReceiveVisitLog(){ 
	      if(receivevisitlog==null){ 
	       receivevisitlog=new  ReceiveVisitLog(); 
	       try { 
	       initialize(); 
	   } catch (Exception e) { 
	    e.printStackTrace(); 
	    // TODO: handle exception 
	   }       
	      } 
	      return receivevisitlog;      
	     } 
	    
	    
	    private static  void initialize() throws JMSException{ 
	     
	      GetJmsUrl url=new GetJmsUrl(); 
	      //ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user,password,url); 
	     System.out.println("url---M:"+url.getUrl()); 
	     
	       
	      ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory("","",url.getUrl()); 
	         System.out.println("connect to  tcp:192.168.0.245 "); 
	         Connection connection=connectionFactory.createConnection(); 
	         connection.start(); 
	         
	         session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);         
	         destination=session.createQueue(subject); 
	     //  Destination advisoryDestination = AdvisorySupport.getProducerAdvisoryTopic(destination) 
	         consumer=session.createConsumer(destination); 
	     
	    } 
	    
	    // 消费消息    
	    public void consumeMessage() throws JMSException,Exception { 
	   
	       //开始监听        
	       // MessageListenerForOrgMsg msgListener=new MessageListenerForOrgMsg();   
	       consumer.setMessageListener(this); 
	       
	       System.out.println("Consumer:->after listener message..."); 
	       
	      //如果想要去想主动的去接受消息,起用下面的        
	      //Message message=consumer.receiveNoWait();        
	      //TextMessage message=(TextMessage)consumer.receive(1000); 
	       
	  }    
	      
	  
	    // 消息处理函数(onMessage是个构造函数继承,consumerTool开始执行的时候,先要初始化它,然后才initialze()) 
	    //由 container 选择一个实例 
	public void onMessage(Message message){ 
	  try {   
	  
	     System.out.println("Consumer:->Begin receive message--->:"+message); 
	       if(message!=null){ 
	      if(message instanceof ObjectMessage){ 
	      ObjectMessage  objmsg=(ObjectMessage)message;   
	      //从消息中提取对象,转化为bean对象 
	       VisitStatInfoBean visitinfo=(VisitStatInfoBean)objmsg.getObject(); 
	     
	      //隔离对数据库的直接访问, 
	      VisitStatInfoDao vistdao=new VisitStatInfoDao(); 
	      //向数据库中插入消息 
	      vistdao.insert(visitinfo); 
	      System.out.println("Consumer:->after insert messagedb"); 
	     } 
	       } 
	    
	      
	   //消费消息中在onMessage()方法中接收producer发送过来的消息进行处理，并可以通过replyProducer反馈信息给producer 
	   /*if(message.getJMSReplyTo()!=null){    
	     replyProducer.send(message.getJMSReplyTo(),session.createTextMessage("Reply: "+message.getJMSMessageID()));   
	   }*/ 
	   
	  } catch (Exception e) { 
	   e.printStackTrace(); 
	   
	  } 
	  
	} 

	  // 关闭连接    
	    public void close() throws JMSException {    
	        System.out.println("Consumer:->Closing connection");    
	        if (consumer != null)    
	            consumer.close();    
	        if (session != null)    
	            session.close();    
	        if (connection != null)   
	            connection.close();    
	    }    
	    
	    
	    public static void main(String[] args) { 
			ListableBeanFactory lsb = new ClassPathXmlApplicationContext("classpath:applicationContext-activemq.xml");
			GenericBeanFactoryAccessor gba = new GenericBeanFactoryAccessor(lsb);
	  //取得接收信息 
	  ReceiveVisitLog receiveVisitLog = ReceiveVisitLog.getReceiveVisitLog(); 
	   GetJmsUrl url=new GetJmsUrl(); 
	   
	  //接收信息时间间隔  
	  //int sleepTime = 30*1000; 
	  int sleepTime=Integer.parseInt(url.getSleepTime()); 
	  
	  while (true){                        
	         try { 
	          System.out.println("睡眠" + sleepTime/1000 + "秒之后, 开始接收消息......"); 
	          receiveVisitLog.consumeMessage(); 

	             Thread.sleep(sleepTime); 
	             System.out.println("接收消息完毕."); 
	             
	   } catch (Exception e) { 
	    e.printStackTrace(); 
	    
	   }  
	  } 

	} 
	    
	    
	} 