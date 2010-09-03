
package com.jrmapp.common.util;


import java.io.Serializable;


public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	public String userLonginName = "";

	public String password="";
	
	public String username;

	

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userLonginName
	 */
	public String getUserLonginName() {
		return userLonginName;
	}

	/**
	 * @param userLonginName
	 *            the userLonginName to set
	 */
	public void setUserLonginName(String userLonginName) {
		this.userLonginName = userLonginName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
