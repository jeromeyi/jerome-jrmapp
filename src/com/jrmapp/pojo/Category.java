package com.jrmapp.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Dec 2, 2010 2:45:09 PM
 * @类说明
 */
@Entity
@Table(name="CATEGORY")
public class Category implements Serializable {   
  
    /** Level分层标记 */  
    public static final String LEVEL_SPLIT = "|";   
  
    @Id  
    @Column(name="ID")
	@GeneratedValue(generator = "categoryGenerator")   
	@GenericGenerator(name = "categoryGenerator", strategy = "sequence",    
	 parameters = { @Parameter(name = "sequence", value = "seq_category") }) 
   // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_category")
    private Integer id;   
       
    /** 名称 */ 
    @Column(name="name", nullable=false,length=200)  
    private String name;   
  
    /** level */
    @Column(name="nlevel",columnDefinition="number(2) default 0")  
    private int level;   
    
    @Column(nullable=false,columnDefinition="number(12,2) default 0")
    private double testcol;

    @Column(name="point",length = 20,scale = 2)
    private BigDecimal point;   
    
    /** 删除标记 */
    @Column(name="delflag")  
    private Boolean delFlag;  
    
    @Version
    @Column(name="OPTLOCK")
    private Integer version;
  
    /** 下级的类别 */  
    @OneToMany(fetch = FetchType.LAZY, mappedBy="parent")   
    @OrderBy("id")   
    private List<Category> children = new ArrayList<Category>();   
  
    /** 上级的类别 */  
    @ManyToOne  
    @JoinColumn(name = "category_id")   
    private Category parent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}   
       
    // 省略所有 getter/setter 方法...   
}  