package com.jrmapp.activemq;

import javax.jms.JMSException;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.generic.GenericBeanFactoryAccessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) throws JMSException {
		ListableBeanFactory lsb = new ClassPathXmlApplicationContext("classpath:applicationContext-activemq.xml");
		GenericBeanFactoryAccessor gba = new GenericBeanFactoryAccessor(lsb);
		TopicMessageProducer fmpa = gba.getBean("topicMessageProducer");
		QueueMessageProducer fmpb = gba.getBean("queueMessageProducer");
		int count = 1;
		while (true) {
			FooMessage fm = new FooMessage();
			fm.setId(count);
			fm.setName("测试JMS==="+fm.getId()); 
			fmpa.send(fm);
			fmpb.send(fm);
			count ++;
		}
	}
}
