package com.jrmapp.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.jrmapp.dao.base.IBaseDao;
import com.jrmapp.pojo.Address;
import com.jrmapp.pojo.Message;
import com.jrmapp.pojo.User;
import com.jrmapp.pojo.UserIdCard;
import com.jrmapp.service.UserService;
import com.jrmapp.service.base.BaseService;
import com.jrmapp.service.base.BaseServiceImpl;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 9, 2010 2:46:25 PM
 * @类说明
 */
public class UserServiceImpl extends BaseServiceImpl implements UserService{
	@Resource(name="userDao")
	private IBaseDao<User,Long> userDao;
	@Resource(name="userIdCardDao")
	private IBaseDao<UserIdCard,Long> userIdCardDao;
	@Resource(name="messageDao")
	private IBaseDao<Message,Long> messageDao;
	@Resource(name="addressDao")
	private IBaseDao<Address,Long> addressDao;
	 public User get(long id) throws Exception{
		/* User user = new User();  
	        user.setName("test1");  
	        userDao.save(user);  
	        User user1 = new User();  
	        user1.setName("test2");  
	        userDao.save(user1);  */
		 return userDao.get(id);
	 }
	 public void save(User user) throws Exception{
	        User user1 = new User();  
	        user1.setName("test2");  
	        userDao.save(user1);  
		 userDao.save(user);
		 throw new Exception("抛出异常");
	 }
}
