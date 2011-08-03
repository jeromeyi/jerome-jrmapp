package com.jrmapp.activemq;

public class TopicConsumerB {

	public void receive(FooMessage message) throws Exception {
		System.out.println("*************************************** Topic B : " + message.getId()+"  "+message.getName());
/*	    if(true){
	    	throw new RuntimeException("接收消息时出错");
	    }*/
	}
}
