package com.test;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 9, 2010 2:07:24 PM
 * @类说明 该类需要设置为abstract，否则执行UT时，会报出该类没有可以执行的test方法。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//该类需要设置为abstract，否则执行UT时，会报出该类没有可以执行的test方法。
public abstract class JavenTestCase {
	@Autowired  
    private SessionFactory sessionFactory;  
      
    protected Session getSession(SessionFactory sessionFactory)  
            throws DataAccessResourceFailureException  
    {  
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);  
        session.setFlushMode(FlushMode.MANUAL);  
        return session;  
    }  
      
    @Before  
    public void setUp() throws Exception  
    {  
        Session session = getSession(sessionFactory);  
        TransactionSynchronizationManager.bindResource(sessionFactory,  
                new SessionHolder(session));  
    }  
      
    @After  
    public void tearDown() throws Exception  
    {  
        SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);  
        SessionFactoryUtils.closeSession(sessionHolder.getSession());  
    }  
      
}
