package com.jrmapp.action.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public abstract class BaseAction extends ActionSupport  implements  ServletRequestAware, ServletResponseAware{

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public BaseAction() {
		System.out.println("BaseAction---->>");
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	//-------自动生成----------//
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}
}
