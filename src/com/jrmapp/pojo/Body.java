package com.jrmapp.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Jan 12, 2011 10:40:33 AM
 * @类说明
 */
@Entity
public class Body {
	 private long id;
	 private Heart heart;
	  @Id
	  public Long getId() { return id; }
	  @OneToOne(cascade = CascadeType.ALL)
	  @PrimaryKeyJoinColumn
	  public Heart getHeart() {
	     return heart;
	  }
}
