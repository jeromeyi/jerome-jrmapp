package com.test;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 9, 2010 1:50:27 PM
 * @类说明
 */
public class TestBase extends TestCase { 
	private SessionFactory sessionFactory;  
	/**
	 * (空)
	 * @param name
	 */
	public TestBase(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		 super.setUp();  
	        sessionFactory = (SessionFactory) SpringContext.getInstance().getBean(  
	                "sessionFactory");//SpringContext是自己创建spring工具类  
	  
	        Session s = sessionFactory.openSession();  
	        TransactionSynchronizationManager.bindResource(sessionFactory,  
	                new SessionHolder(s));  
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	 protected void tearDown() throws Exception {  
	        super.tearDown();  
	        SessionHolder holder = (SessionHolder) TransactionSynchronizationManager  
	                .getResource(sessionFactory);  
	        Session s = holder.getSession();  
	        try {  
	            s.flush();  
	        } catch (Throwable e) {  
	            e.printStackTrace();  
	        }  
	  
	        TransactionSynchronizationManager.unbindResource(sessionFactory);  
	        SessionFactoryUtils.closeSession(s);  
	    }  
}
