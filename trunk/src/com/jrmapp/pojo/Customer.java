package com.jrmapp.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Jan 12, 2011 10:46:20 AM
 * @类说明
 */
public class Customer implements Serializable {
	/**其中一个实体通过外键关联到另一个实体的主键。注：一对一，则外键必须为唯一约束。
	 * 通过@JoinColumn注 解定义一对一的关联关系。如果没有@JoinColumn注解，
	 * 则系统自动处理，在主表中将创建连接列，列名为：主题的关联属性名 + 下划线 + 
	 * 被关联端的主键列名。上例为 passport_id, 因为Customer
	 *  中关联属性为 passport, Passport 的主键为 id.
	 */
	private long id;
	  @Id
	  public Long getId() { return id;}
	   private Passport passport;
	   @OneToOne(cascade = CascadeType.ALL)
	   @JoinColumn(name="passport_fk")
	   public Passport getPassport() {
	   return passport;
	}
}
