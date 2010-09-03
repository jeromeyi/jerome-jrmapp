package com.jrmapp.action;


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
