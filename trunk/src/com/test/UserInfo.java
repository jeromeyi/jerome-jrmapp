package com.test;
/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：2011-5-6 下午04:36:23
 * @类说明
 */
public class UserInfo {

	 public UserInfo(){
	  
	 }
	    public UserInfo(java.lang.Integer userId,java.lang.String username,java.util.Date birthDate,java.lang.Integer age){
	  this.userId=userId;
	  this.username=username;
	  this.birthDate=birthDate;
	  this.age=age;
	 }
	 private java.lang.Integer userId;
	    private java.lang.String username;
	    private java.util.Date birthDate;
	    private java.lang.Integer age;
	 public java.lang.Integer getUserId() {
	  return userId;
	 }
	 public void setUserId(java.lang.Integer userId) {
	  this.userId = userId;
	 }
	 public java.lang.String getUsername() {
	  return username;
	 }
	 public void setUsername(java.lang.String username) {
	  this.username = username;
	 }
	 public java.util.Date getBirthDate() {
	  return birthDate;
	 }
	 public void setBirthDate(java.util.Date birthDate) {
	  this.birthDate = birthDate;
	 }
	 public java.lang.Integer getAge() {
	  return age;
	 }
	 public void setAge(java.lang.Integer age) {
	  this.age = age;
	 }
	    public String toString(){
	     return new StringBuffer().append(getUserId()).append(getUsername()).append(getBirthDate()).append(getAge() ).toString();
	    }
}
