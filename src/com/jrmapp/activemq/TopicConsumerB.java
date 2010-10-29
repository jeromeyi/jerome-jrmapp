package com.jrmapp.activemq;

public class TopicConsumerB {

	public void receive(FooMessage message) {
		System.out.println("*************************************** Topic B : " + message.getId()+"  "+message.getName());
	}
}
