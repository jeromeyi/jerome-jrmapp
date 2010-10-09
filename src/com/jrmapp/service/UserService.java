package com.jrmapp.service;

import java.io.Serializable;

import com.jrmapp.pojo.User;
import com.jrmapp.service.base.BaseService;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 9, 2010 2:46:50 PM
 * @类说明
 */
public interface UserService{
	public User get(long id) throws Exception;
	public void save(User user) throws Exception;
}
