package com.jrmapp.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jrmapp.dao.base.IBaseDao;
import com.jrmapp.pojo.Address;
import com.jrmapp.pojo.Message;
import com.jrmapp.pojo.User;
import com.jrmapp.pojo.UserIdCard;
import com.jrmapp.service.HouseTypeService;
import com.jrmapp.service.UserService;
import com.jrmapp.service.base.BaseServiceImpl;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 9, 2010 2:46:25 PM
 * @类说明
 */
@Service("userService")
//等价于@Component("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService{
	@Resource(name="userDao")
	private IBaseDao<User,Long> userDao;
	@Resource(name="userIdCardDao")
	private IBaseDao<UserIdCard,Long> userIdCardDao;
	@Resource(name="messageDao")
	private IBaseDao<Message,Long> messageDao;
	@Resource(name="addressDao")
	private IBaseDao<Address,Long> addressDao;
	@Resource(name="houseTypeService")
	private HouseTypeService houseTypeService;
	 public User getUser(long id) throws Exception{
		/* User user = new User();  
	        user.setName("test1");  
	        userDao.save(user);  
	        User user1 = new User();  
	        user1.setName("test2");  
	        userDao.save(user1);  */
		 User user=userDao.get(id);
		 //System.out.println("runthis=="+user.getName());
		 return user;
	 }
	 public void saveUser(User user) throws Exception{
	       // User user1 = new User();  
	        //user1.setName("test2");  
	        //userDao.save(user1);  
		 userDao.save(user);
		 try{
		// houseTypeService.nestedSave();
		 }catch (Exception e) {
			// TODO: handle exception
		}
		 //throw new Exception("抛出异常");
	 }
	 
	 public String getUserName(long id)throws Exception{
		 User user=userDao.get(id);
		 System.out.println("runthis=="+user.getName());
		 return user.getName();
	 }
	 
	 public void updateUser(User user)throws Exception{
		 userDao.update(user);
		 User user1 = new User();  
	        user1.setName("test1");  
	        this.saveUser(user1);  
	 }

}
