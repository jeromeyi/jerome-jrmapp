package com.jrmapp.common.util;

public class StringUtil {

	public static String convertStr(String putStr,String outStr){
		String returnStr="";
		if(putStr==null||"".equals(putStr.trim())||"null".equals(putStr.trim()))
		{
			if(outStr!=null&&!"".equals(outStr.trim()))
			returnStr= outStr;
		}else{
			 returnStr=putStr;
		}
		return returnStr;
	}
	
	public static String toString(Object putStr){
		String returnStr="";
		if(putStr!=null&&!"null".equals(String.valueOf(putStr)))
			returnStr=String.valueOf(putStr);
		return returnStr;
	}
	
}
