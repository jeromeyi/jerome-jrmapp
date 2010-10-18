package com.jrmapp.jws;


import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import com.jrmapp.pojo.User;
import com.jrmapp.service.UserService;

public class JaxRpcUserService extends ServletEndpointSupport {

	private UserService userService;

	public String getUserName(long id) throws Exception {
		if (null==userService)
		    userService=(UserService) getApplicationContext().getBean("userService");  
		User user=userService.get(id);
		return user.getName();
	}


}
