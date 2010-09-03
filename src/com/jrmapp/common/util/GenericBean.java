
package com.jrmapp.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jrmapp.constants.AppConstants;
/**
 * 
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Jul 28, 2010 1:51:38 PM
 * @类说明
 */
public class GenericBean {
	/**
	 * 
	 */
	public GenericBean() {
	}


	public static UserSession getUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session
				.getAttribute(AppConstants.USERSESSION_KEY);
		 return userSession;
	}
	

}
