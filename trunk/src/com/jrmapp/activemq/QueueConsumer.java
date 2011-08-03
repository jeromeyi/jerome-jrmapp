package com.jrmapp.activemq;

/**
 * Date: 2008-8-28
 * Time: 17:10:34
 */
public class QueueConsumer {

    public void receive(FooMessage message) throws Exception{
		System.out.println("*************************************** Queue : " + message.getId()+"  "+message.getName());
		/*if(true){
			throw new RuntimeException("接收Queue消息时出错");
        }*/
    }
    
}
