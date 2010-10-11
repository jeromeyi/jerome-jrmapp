package com.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jrmapp.dao.base.IBaseDao;
import com.jrmapp.pojo.Address;
import com.jrmapp.pojo.HouseType;
import com.jrmapp.pojo.Message;
import com.jrmapp.pojo.SellSeries;
import com.jrmapp.pojo.User;
import com.jrmapp.pojo.UserIdCard;
import com.jrmapp.service.UserService;


public class TestUser extends JavenTestCase{

	@Resource(name="userDao")
	private IBaseDao<User,Long> userDao;
	@Resource(name="userIdCardDao")
	private IBaseDao<UserIdCard,Long> userIdCardDao;
	@Resource(name="messageDao")
	private IBaseDao<Message,Long> messageDao;
	@Resource(name="addressDao")
	private IBaseDao<Address,Long> addressDao;
	@Resource(name="userService")
	private UserService userService;
	
	 private long userID;  
	    private long addressID;  
	   
	 public void testAdd() throws Exception {  
	        //Save user  
	        User user = new User();  
	        user.setName("AdersonJ");  
	        userDao.save(user);  
	          
	        //Save address  
	        Address address = new Address();  
	        address.setAddress("Beijing city");  
	        address.setUser(user);  
	        addressDao.save(address);  
	        this.addressID = address.getId();  
	        address = new Address();  
	        address.setAddress("Shanghai city");  
	        address.setUser(user);  
	        addressDao.save(address);  
	          
	        //Save message  
	        Message message = new Message();  
	        message.setContent("The weather report for tomorrow.");  
	        messageDao.save(message);  
//	        Set<User> users = new HashSet<User>();  
//	        users.add(user);  
//	        message.setUsers(users);  
//	        session.update(message);  
	        Set<Message> messages = new HashSet<Message>();  
	        messages.add(message);  
	        user.setMessages(messages);  
	        userDao.update(user);  
	          
	        //Save userIdCard  
	        UserIdCard userIdCard = new UserIdCard();  
	        userIdCard.setSeqNumber("ABCDEFG");  
	        userIdCard.setUser(user);  
	        userIdCardDao.save(userIdCard);  
	        this.userID = user.getId();  
	    }  
	      
	    @SuppressWarnings("unchecked")  
	    
	    public void testQuery() throws Exception {  
//	        List<Object> ls = (List) session.createQuery("from User user inner join user.userIdCard where user.name=?").setParameter(0, "AdersonJ").list();  
//	        Object[] ary = (Object[]) ls.get(0);  
//	        User user = (User) ary[0];  
	          
//	         List ls = session.createQuery("from User user where user.name=?").setParameter(0, "AdersonJ").list();  
//	         User user = (User) ls.get(0);  
//	         System.out.println(user.getName());  
	          
//	          User user = (User) session.load(User.class, this.userID);  
//	          System.out.println(user.getName());  
	          
	        //Address address = (Address) session.createQuery("from Address as address where address.address=?").setParameter(0, "Beijing city").uniqueResult();  
	        Address  address = addressDao.get(5l);  
	        System.out.println(address.getAddress());  
	    /*	List<Address> htList1=addressDao.find(" from Address");
			for(Address obj:htList1){
				System.out.println(obj.getAddress());
				System.out.println(obj.getUser().getName());
			}*/
	    	User user=userService.get(6);
	    	System.out.println(user.getName());
			
	    }  
	    @Test  
	    public void testAdd1() throws Exception {
	    	User user = new User();  
	        user.setName("test1");  
	        userService.save(user);  
	        
		}
	      
	      
	      
	 
	      
	      
	  
}
