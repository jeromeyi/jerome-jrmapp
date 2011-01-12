package com.jrmapp.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Jan 12, 2011 10:41:51 AM
 * @类说明
 */
@Entity
public class Heart {
	/**
	 * 通过@PrimaryKeyJoinColumn 注解定义了一对一的关联关系。
	 * 关联的实体都共享同样的主键。
	 */
	private long id;
	  @Id
	  public Long getId() { return id;}
	
}
