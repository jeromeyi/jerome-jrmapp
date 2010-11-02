package com.jrmapp.activemq;

import java.io.File;

import javax.jms.JMSException;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.generic.GenericBeanFactoryAccessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestMain {
	public static void main(String[] args) throws JMSException {
		java.io.File file=new File("activemq-data");
		System.out.println(file.exists());
       new TestMain().removeFile(file);
	/*	ListableBeanFactory lsb = new ClassPathXmlApplicationContext("classpath:applicationContext-activemq.xml");
		GenericBeanFactoryAccessor gba = new GenericBeanFactoryAccessor(lsb);
		TopicMessageProducer fmpa = gba.getBean("topicMessageProducer");
		QueueMessageProducer fmpb = gba.getBean("queueMessageProducer");
		int count = 1;
		while (true) {
			FooMessage fm = new FooMessage();
			fm.setId(count);
			fm.setName("测试JMS==="+fm.getId()); 
			//fmpa.send(fm);
			//fmpb.send(fm);
			count ++;
		}*/
	}
	 public void removeFile(String path) {   
	        this.removeFile(new File(path));   
	    }   
	  
	    public void removeFile(File path) {   
	        System.out.println("removing file " + path.getPath());   
	        if (path.isDirectory()) {   
	            File[] child = path.listFiles();   
	            if (child != null && child.length != 0) {   
	                for (int i = 0; i < child.length; i++) {   
	                    removeFile(child[i]);   
	                    child[i].delete();   
	                }   
	            }   
	        }   
	        path.delete();   
	    }  
}
