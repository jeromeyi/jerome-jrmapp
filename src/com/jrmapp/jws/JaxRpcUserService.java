package com.jrmapp.jws;


import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jrmapp.pojo.User;
import com.jrmapp.service.UserService;
//@WebService
public class JaxRpcUserService extends ServletEndpointSupport {
	//@Resource(name="userService")
	private UserService userService;

	protected void onInit() { 
		    userService=(UserService) getApplicationContext().getBean("userService");
		}
	 
	public String getUserName(long id) throws Exception {
		//if (null==userService)
		   // userService=(UserService) getApplicationContext().getBean("userService");  
		User user=userService.getUser(id);
		return user.getName();
	}


}
