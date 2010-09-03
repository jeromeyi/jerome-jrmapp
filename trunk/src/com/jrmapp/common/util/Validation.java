/*
 * $Id: Validation.java,v 1.1.2.9 2008/04/12 01:26:45 zengjm Exp $
 * Copyright(c) 2000-2008 HC360.COM, All Rights Reserved.
 */
package com.jrmapp.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * ***��˵��***
 * @author zenjuemin
 * @version 1.0 2008-4-10
 * @since 1.0
 * @since 1.0
 */
public class Validation {
	
	/**
	 * �ʼ���֤����
	 * @param mail
	 * @return
	 */
	public static boolean checkEmail(String mail) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mail);
		
		return m.find();
	}
	/**
	 * @author lelem
	 * �ж��ǲ�������
	 * @param number
	 * @return true ������ false ��������
	 */
	public static boolean checkNumber(String str){
		String v="0123456789";
		if(isNull(str)){
			return false;
		}
		for(int i=0;i<str.length();i++){
			char c=str.charAt(i);
			if(v.indexOf(c)<0){
				return false;
			}
		}
		return true;
	}
	 /**
	 * @author lelem
	 *  �ж��ǲ���Null
	 * @param str
	 * @return true ���ǿ� false �ǿ�
	 */
	public static boolean isNull(String str){
		 if(null==str||"".equals(str)){
			 return true;
		 }else{
			 return false;
		 }	
	 }
	 /**
	 * @author lelem
	 * �ж��ǲ��ǳ���
	 * @param str
	 * @param i
	 * @return true �ǲ�������false �ǳ���
	 */
	public static  boolean checkLength(String str,int i){
		 int len=str.length();
		 if(i<len||len<0){
			 return false;
		 }else{
			 return true;
		 } 
	 }
	public static void main(String [] args){
		System.out.print(Validation.checkNumber(new String()));
	}
}
