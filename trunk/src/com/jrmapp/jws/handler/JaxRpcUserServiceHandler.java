package com.jrmapp.jws.handler;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;

public class JaxRpcUserServiceHandler extends BasicHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static long COUNT=0;
	private int requestCount=0;
	
	@Override
	public void invoke(MessageContext arg0) throws AxisFault {
		// TODO Auto-generated method stub
		COUNT++;
		requestCount++;
		String status =(String) this.getOption("status");
		System.out.println("JaxPrcUserServiceHandler's status is:"+
				status+",COUNT="+COUNT+",HandlerRequestCount="+requestCount);
	}

}

