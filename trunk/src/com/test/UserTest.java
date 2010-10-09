package com.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jrmapp.dao.base.IBaseDao;
import com.jrmapp.pojo.Address;
import com.jrmapp.pojo.Message;
import com.jrmapp.pojo.User;
import com.jrmapp.pojo.UserIdCard;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 9, 2010 1:48:41 PM
 * @类说明
 */

public class UserTest  extends TestBase{
	@Resource(name="userDao")
	private IBaseDao<User,Long> userDao;
	@Resource(name="userIdCardDao")
	private IBaseDao<UserIdCard,Long> userIdCardDao;
	@Resource(name="messageDao")
	private IBaseDao<Message,Long> messageDao;
	@Resource(name="addressDao")
	private IBaseDao<Address,Long> addressDao;

	private long userID;  
	private long addressID;  
	
	public UserTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	 public void testQuery() throws Exception{  
//	        List<Object> ls = (List) session.createQuery("from User user inner join user.userIdCard where user.name=?").setParameter(0, "AdersonJ").list();  
//	        Object[] ary = (Object[]) ls.get(0);  
//	        User user = (User) ary[0];  
	          
//	         List ls = session.createQuery("from User user where user.name=?").setParameter(0, "AdersonJ").list();  
//	         User user = (User) ls.get(0);  
//	         System.out.println(user.getName());  
	          
//	          User user = (User) session.load(User.class, this.userID);  
//	          System.out.println(user.getName());  
	          
	        //Address address = (Address) session.createQuery("from Address as address where address.address=?").setParameter(0, "Beijing city").uniqueResult();  
	       /* Address  address = (Address) addressDao.get(3l);  
	        System.out.println(address.getAddress());  */
	    	List<Address> htList1=addressDao.find(" from Address");
			for(Address obj:htList1){
				System.out.println(obj.getAddress());
				System.out.println(obj.getUser().getName());
			}
	    }  
}
