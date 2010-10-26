package com.jrmapp.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import com.jrmapp.action.user.LoginAction;
import com.jrmapp.common.util.UserSession;
import com.jrmapp.constants.AppConstants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 25, 2010 5:04:54 PM
 * @类说明
 */
public class CheckLoginInterceptor extends AbstractInterceptor {  
  
    private static final long serialVersionUID = 1L;  
      
  
    @Override  
    public String intercept(ActionInvocation actionInvocation) throws Exception {  
     /*   System.out.println("invocation"); 
        String result = invocation.invoke();  
          
        return result; */ 
    	System.out.println("begin check login interceptor!");
        // 对LoginAction不做该项拦截
        Object action = actionInvocation.getAction();
        if (action instanceof LoginAction) {
            System.out.println("exit check login, because this is login action.");
            return actionInvocation.invoke();
        }
        ActionProxy actionProxy = actionInvocation.getProxy();
		Method method = action.getClass().getMethod(actionProxy.getMethod());
		System.out.println("action====="+action.getClass().getName()+"   and   method===="+method.getName());
        // 确认Session中是否存在LOGIN
        Map session = actionInvocation.getInvocationContext().getSession();
        UserSession userSession = (UserSession) session.get(AppConstants.USERSESSION_KEY);
        if (userSession != null) {
            // 存在的情况下进行后续操作。
            System.out.println("already login!");
            return actionInvocation.invoke();
        } else {
            // 否则终止后续操作，返回LOGIN
            System.out.println("no login, forward login page!");
            return "login";
        }
    }
     
  
}
