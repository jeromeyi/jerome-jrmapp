package com.jrmapp.jws.handler;

import java.util.StringTokenizer;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.security.AuthenticatedUser;
import org.apache.axis.security.SecurityProvider;
import org.apache.axis.utils.Messages;

public class AuthenticationHandler extends BasicHandler {
	/**
	 * invoke，每一个handler都必须实现的方法。
	 */
	public void invoke(MessageContext msgContext) throws AxisFault {
		/*
		 * SecurityProvider provider =
		 * (SecurityProvider)msgContext.getProperty("securityProvider");
		 * if(provider==null) { provider= new SimpleSecurityProvider();
		 * msgContext.setProperty("securityProvider", provider); }
		 * if(provider!=null) { String userId=msgContext.getUsername(); String
		 * password=msgContext.getPassword();
		 * 
		 * //对用户进行认证，如果authUser==null，表示没有通过认证，抛出Server.Unauthenticated异常。
		 * org.apache.axis.security.AuthenticatedUser authUser =
		 * provider.authenticate(msgContext); if(authUser==null) throw new
		 * AxisFault("Server.Unauthenticated", Messages.getMessage("cantAuth01",
		 * userId), null,null); //用户通过认证，把用户的设置成认证了的用户。
		 * msgContext.setProperty("authenticatedUser", authUser); }
		 */

		AuthenticatedUser user = (AuthenticatedUser) msgContext
				.getProperty("authenticatedUser");
		if (user == null)
			throw new AxisFault("Server.NoUser",
					Messages.getMessage("needUser00"), null, null);
		String userId = user.getName();

		Handler serviceHandler = msgContext.getService();
		if (serviceHandler == null)
			throw new AxisFault(Messages.getMessage("needService00"));
		String serviceName = serviceHandler.getName();
		String allowedRoles = (String) serviceHandler.getOption("allowedRoles");
		if (allowedRoles == null) {
			return;
		}else{
			System.out.println(allowedRoles);
		}
		SecurityProvider provider = (SecurityProvider) msgContext
				.getProperty("securityProvider");
		if (provider == null)
			throw new AxisFault(Messages.getMessage("noSecurity00"));
		for (StringTokenizer st = new StringTokenizer(allowedRoles, ","); st
				.hasMoreTokens();) {
			String thisRole = st.nextToken();
			if (provider.userMatches(user, thisRole)) {
				return;// 访问授权通过。
			}
		}
		// 没有通过授权，不能访问目标服务，抛出Server.Unauthorized异常。
		throw new AxisFault("Server.Unauthorized", Messages.getMessage(
				"cantAuth02", userId, serviceName), null, null);

	}

}