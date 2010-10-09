package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 9, 2010 1:47:27 PM
 * @类说明
 */
public class SpringContext {
	private static SpringContext m_instance;  
	  
    private static String[] contextFiles = new String[] { "applicationContext.xml" };  
  
    private ApplicationContext ctx;  
  
    public SpringContext() {  
        ctx = new ClassPathXmlApplicationContext(contextFiles);  
    }  
  
    public SpringContext(String[] setting) {  
        ctx = new ClassPathXmlApplicationContext(setting);  
    }  
  
    public synchronized static SpringContext getInstance() {  
        if (m_instance == null) {  
            m_instance = new SpringContext(contextFiles);  
        }  
        return m_instance;  
    }  
  
    public Object getBean(String beanId) {  
        Object o = ctx.getBean(beanId);  
        if (o instanceof TransactionProxyFactoryBean) {  
            TransactionProxyFactoryBean factoryBean = (TransactionProxyFactoryBean) o;  
            o = factoryBean.getObject();  
        }  
        return o;  
    }  
}
