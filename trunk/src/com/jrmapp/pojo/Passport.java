package com.jrmapp.pojo;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Jan 12, 2011 10:46:45 AM
 * @类说明
 */
public class Passport implements Serializable {
	private long id;
	  @Id
	  public Long getId() { return id;}
	   private Customer customer;
	   @OneToOne(mappedBy = "passport")
	   public Customer getOwner() {
	    return customer;
	}
}
