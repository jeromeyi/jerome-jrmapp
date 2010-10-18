package com.jrmapp.jws;

public class HelloWorldImpl implements IHelloWorld {
	private String helloStr; // Spring中需要注入的字符串

	/**
	 * 实现接口中的方法，得到Say Hello to <somebody>的字符串
	 */
	@Override
	public String getMessage(String name) {
		// TODO Auto-generated method stub
		 return helloStr+":"+name;
	}

	public String getHelloStr() {
		return helloStr;
	}

	public void setHelloStr(String helloStr) {
		this.helloStr = helloStr;
	}
}
