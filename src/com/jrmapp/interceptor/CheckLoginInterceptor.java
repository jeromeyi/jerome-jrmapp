package com.jrmapp.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 25, 2010 5:04:54 PM
 * @类说明
 */
public class CheckLoginInterceptor extends AbstractInterceptor {  
  
    private static final long serialVersionUID = 1L;  
      
  
    @Override  
    public String intercept(ActionInvocation invocation) throws Exception {  
        System.out.println("invocation"); 
        String result = invocation.invoke();  
          
        return result;  
    }  
  
}
