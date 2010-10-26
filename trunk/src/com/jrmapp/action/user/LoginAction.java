package com.jrmapp.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.jrmapp.common.util.GenericBean;
import com.jrmapp.common.util.UserSession;
import com.jrmapp.constants.AppConstants;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Jul 28, 2010 1:38:31 PM
 * @类说明
 */
@SuppressWarnings("serial")
@Results({   
	  @Result(name="login", location="/login.html",type="redirect"),
	  @Result(name="success", location="/index.do",type="redirect")   
	})  
public class LoginAction extends ActionSupport {
private String username;
private String password;
private String verifycode;
private String loginmsg;
public String getVerifycode() {
	return verifycode;
}
public void setVerifycode(String verifycode) {
	this.verifycode = verifycode;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
@Action(value="/login",results={@Result(name = LOGIN, location = "/login.html",type="redirect")}) 
public String execute() throws Exception {
	// TODO Auto-generated method stub
	if(null!=GenericBean.getUserSession(ServletActionContext.getRequest()))
		return SUCCESS;
	return LOGIN;
}
 
	
@Action(value="/user/login",results={@Result(name = LOGIN, location = "/login.html",type="redirect")} )   
public String loginon() throws Exception{
	HttpServletRequest request=ServletActionContext.getRequest();
	if(null!=GenericBean.getUserSession(request))
		return SUCCESS;
	HttpSession session=request.getSession();
	 String imgcode=(String)session.getAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
	   if(verifycode==null||!verifycode.equals(imgcode)){
		   loginmsg="验证码错误";
		   super.addActionMessage("验证码错误");
		   return LOGIN;
	   }
	   else if("admin".equals(username)&&"admin".equals(password)){
		  UserSession userSession=new UserSession();
		   userSession.setUsername("谢毅");
		   session.setAttribute(AppConstants.USERSESSION_KEY, userSession);
		   loginmsg="登陆成功";
		   return SUCCESS;
	}
	else {
		loginmsg="用户名或者密码错误";
		return LOGIN;
	}
/*	   UserSession userSession=new UserSession();
	   userSession.setUsername("谢毅");
	   session.setAttribute(AppConstants.USERSESSION_KEY, userSession);
	   loginmsg="登陆成功";*/
	  // return SUCCESS;
}

@Action(value="/user/loginout",results={@Result(name = LOGIN, location = "/login.html",type="redirect")})   
public String loginout() throws Exception{
	HttpServletRequest request=ServletActionContext.getRequest();
	UserSession userSession =GenericBean.getUserSession(request);
	if(null!=userSession)
		request.getSession().removeAttribute(AppConstants.USERSESSION_KEY);
	return SUCCESS;

}
public String getLoginmsg() {
	return loginmsg;
}
public void setLoginmsg(String loginmsg) {
	this.loginmsg = loginmsg;
}
}
