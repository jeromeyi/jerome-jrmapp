package com.jrmapp.common.util;

import java.text.ParseException;
import java.util.Date;

public class DateUtil {

	/**
	 * ��ת�����Ͱ�����ת��Ϊ�ַ�
	 * @param date
	 * @param conType
	 * @return
	 */
	 public static String convertDateToStr(Date date,String conType){
		 java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat(conType);
		 return sdf.format(date);
	 }
	 
	 /**
	  * ��ת�����Ͱ��ַ�ת��Ϊ����
	  * @param date
	  * @param conType
	  * @return
	  * @throws ParseException
	  */
	 public static Date convertStrToDate(String date,String conType) throws ParseException{
		 java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat(conType);
		 return sdf.parse(date);
	 }
	
}
