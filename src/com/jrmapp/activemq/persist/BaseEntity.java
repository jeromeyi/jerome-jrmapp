package com.jrmapp.activemq.persist;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 28, 2010 6:36:46 PM
 * @类说明
 */
public class BaseEntity implements Serializable { 
	
    
	  public String toString() { 
	   return ReflectionToStringBuilder.toString(this); 
	       } 

	       public boolean equals(Object other) { 
	           if ( (this == other ) ) return true; 
	           return EqualsBuilder.reflectionEquals(this, other); 
	       } 

	       public int hashCode() { 
	        return HashCodeBuilder.reflectionHashCode(this); 
	       } 
	 
}
