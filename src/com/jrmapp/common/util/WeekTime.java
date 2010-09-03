package com.jrmapp.common.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * ***��˵��***
 * @author lelem
 * @version 1.0 2008-4-14
 * @since 1.0
 * @since 1.0
 */
public class WeekTime {
	final static String dayNames[] = { "������", "����һ", "���ڶ�", "������", "������", "������", "������" };
	/**
	 * �õ������ڵ�����String
	 */
	public static String getStringWeek(Date date) { 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		int dayOfWeek =cal.get(Calendar.DAY_OF_WEEK); 
		return  dayNames [dayOfWeek - 1] ;
		}
	/**
	 * �õ��������͵�����
	 */
	public static int getIntWeek(Date date) { 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		int dayOfWeek =cal.get(Calendar.DAY_OF_WEEK); 
		return  dayOfWeek-1;//�õ����������͵����ڱ�ʵ�ʵ����ڼ�Сһ
		}
	public static int getIntWeek(Timestamp time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		int dayOfWeek =cal.get(Calendar.DAY_OF_WEEK); 
		return  dayOfWeek-1;		
	}
	
	/**
	 * ������
	 */
	 public static void main(String[] args) {
		 Date date=new Date();
		 Timestamp time=new Timestamp(System.currentTimeMillis());
		 System.out.println(getStringWeek(date));
		 System.out.println(getIntWeek(date));
		 
		 
		 System.out.println(getIntWeek(time));
	 }
}

