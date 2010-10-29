package com.jrmapp.activemq.persist;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 28, 2010 6:44:18 PM
 * @类说明
 */
public class GetJmsUrl {
	private final static  String strUrl=null; 
	private final static String sleeptime=null; 
	  
	private final    Properties props=new Properties(); 

	private final String url="askyayaJmsConstant.properties"; 
	  
	public GetJmsUrl(){ 
	    
	}  

	public  String getUrl(){  
	  InputStream is=getClass().getResourceAsStream(url);  
	     try { 
	      if(strUrl==null){ 
	      props.load(is); 
	      } 
	  } catch (Exception e) { 
	   System.err.println("Can not read the Properties file " + url);   
	  }        
	    String strUrl= props.getProperty("hostname");         
	  return strUrl; 
	  
	} 

	public   String  getSleepTime(){  
	  InputStream is=getClass().getResourceAsStream(url); 
	  try { 
	   if(sleeptime==null){ 
	        props.load(is);        
	   }  
	  } catch (Exception e) { 
	   e.printStackTrace(); 
	   // TODO: handle exception 
	  } 
	  String   strSleepTime= props.getProperty("sleepTime"); 
	  
	  return strSleepTime; 
	  
	} 


	} 