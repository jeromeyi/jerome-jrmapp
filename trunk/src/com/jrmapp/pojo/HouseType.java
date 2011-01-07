package com.jrmapp.pojo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;




@Entity
@Table(schema="javaee",name="house_type")
@SuppressWarnings("serial") 
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  //,region="ehcache.xml"
@Proxy(lazy = false)
public class HouseType implements  Serializable {
	 private final static String table="户型";
		@Id
		
/*		 @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqid")
		 @GenericGenerator(
				 name="seqid",strategy="seqhilo",
				 parameters={
						 @Parameter(name="sequence", value="seqid")
						 }
				 )*/
		@GeneratedValue(generator = "houseTypeGenerator")   
		@GenericGenerator(name = "houseTypeGenerator", strategy = "sequence",    
		         parameters = { @Parameter(name = "sequence", value = "seq_housetype") }) 

	    @Column(nullable=false, updatable=false, length=10)
		private int housetypeid   ;//number(10)                     户型id      
		
		@Column(nullable=false, length=100,unique=true)
		@Basic(fetch = FetchType.EAGER)
		//@OneToOne(fetch = FetchType.LAZY)
		@NotEmpty
		//@Pattern(regexp="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*",message="不是邮箱的格式")
		//@Email
		private String  housetypename ;//varchar2(100)                  户型名称                      
		
		@Column(nullable=true, length=10)
		private long initarea      ;//number(10)    y                                              
		
		@Column(nullable=true, length=10)
		private long toparea       ;//number(10)    y                最高适用面积(实测M2)        
		
		@Column(nullable=false, length=1)
		@NotNull
        @Min(0)
        @Max(1)
		private int status=1        ;//number(1)              1       有效状态 （1：有效  0：无效） 
		
		@Column(nullable=true, length=32)
		private long housecatid    ;//number(32)       
		
		@Transient 
		private String test;
		//private transient String test;
		
		public String getTest() {
			return test;
		}
		public void setTest(String test) {
			this.test = test;
		}
		public int getHousetypeid() {
			return housetypeid;
		}
		public void setHousetypeid(int housetypeid) {
			this.housetypeid = housetypeid;
		}
		public String getHousetypename() {
			return housetypename;
		}
		public void setHousetypename(String housetypename) {
			this.housetypename = housetypename;
		}
		public long getInitarea() {
			return initarea;
		}
		public void setInitarea(long initarea) {
			this.initarea = initarea;
		}
		public long getToparea() {
			return toparea;
		}
		public void setToparea(long toparea) {
			this.toparea = toparea;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public long getHousecatid() {
			return housecatid;
		}
		public void setHousecatid(long housecatid) {
			this.housecatid = housecatid;
		}
	}
