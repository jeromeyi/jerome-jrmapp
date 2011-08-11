package com.jrmapp.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.ibm.wsdl.Constants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：2011-6-11 上午11:15:48
 * @类说明
 */
public class ExceptionInterceptor  extends AbstractInterceptor {  
	public String intercept(ActionInvocation actioninvocation) {  
		  
        String result = null; // Action的返回值  
        try {  
            // 运行被拦截的Action,期间如果发生异常会被catch住  
            result = actioninvocation.invoke();  
            return result;  
        } catch (Exception e) {  
            /** 
             * 处理异常 
             */  
            String errorMsg = "未知错误！";  
            //通过instanceof判断到底是什么异常类型  
            if (e instanceof BaseException) {  
                BaseException be = (BaseException) e;  
                be.printStackTrace(); //开发时打印异常信息，方便调试  
                if(be.getMessage()!=null||Constants.BLANK.equals(be.getMessage().trim())){  
                    //获得错误信息  
                    errorMsg = be.getMessage().trim();  
                }  
            } else if(e instanceof RuntimeException){  
                //未知的运行时异常  
                RuntimeException re = (RuntimeException)e;  
                re.printStackTrace();  
            } else{  
                //未知的严重异常  
                e.printStackTrace();  
            }  
            //把自定义错误信息  
            HttpServletRequest request = (HttpServletRequest) actioninvocation  
                    .getInvocationContext().get(StrutsStatics.HTTP_REQUEST);  
              
            /** 
             * 发送错误消息到页面 
             */  
            request.setAttribute("errorMsg", errorMsg);  
          
            /** 
             * log4j记录日志 
             */  
            Log log = LogFactory  
                    .getLog(actioninvocation.getAction().getClass());  
            if (e.getCause() != null){  
                log.error(errorMsg, e);  
            }else{  
                log.error(errorMsg, e);  
            }  
  
            return "error";  
        }// ...end of catch  
    }  
}
