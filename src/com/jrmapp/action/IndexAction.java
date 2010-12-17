package com.jrmapp.action;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class IndexAction extends ActionSupport {
	private String testindex;
	
	public String getTestindex() {
		return testindex;
	}

	public void setTestindex(String testindex) {
		this.testindex = testindex;
	}

	@Override
	public String execute() throws Exception {
		String encoderString="http://192.168.8.170/Kebao/view.jsp?a=abc&b=cdf&adfd=alkkfalkjl";
		encoderString=java.net.URLEncoder.encode(encoderString,"UTF-8");
		System.out.println(encoderString);
		String decoderString=encoderString;
		decoderString=java.net.URLDecoder.decode(decoderString,"UTF-8");
		System.out.println(decoderString);
		//HttpServletRequest request=ServletActionContext.getRequest();
		//String urlString=request.getParameter("returnURL");
		//urlString=java.net.URLDecoder.decode(urlString,"UTF-8");
		//System.out.println(urlString);
		    testindex="谢毅";
			return SUCCESS;
	}
	
	public String left() throws Exception {
		  return "left";
	}
	public String right() throws Exception {
		  return "right";
	}
	public String top() throws Exception {
		testindex="谢毅";
		  return "top";
	}
}
