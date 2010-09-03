package com.jrmapp.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="house_type")
public class CopyOfHouseType implements  Serializable {
	/**
	  @Id @GeneratedValue(generator="system-uuid")
@GenericGenerator(name="system-uuid", strategy = "uuid")


@Id @GeneratedValue(generator="hibseq")
@GenericGenerator(name="hibseq", strategy = "seqhilo",
    parameters = {
        @Parameter(name="max_lo", value = "5"),
        @Parameter(name="sequence", value="heybabyhey")
    }
)

	 */
	@Id
	/**
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqid")
    @Column(nullable=false, updatable=false, length=10)
    */
	private int housetypeid   ;//number(10)                     ����id      
	@Column(nullable=false, length=100)
	private String  housetypename ;//varchar2(100)                  �������                      
	@Column(nullable=true, length=10)
	private long initarea      ;//number(10)    y                                              
	@Column(nullable=true, length=10)
	private long toparea       ;//number(10)    y                ����������(ʵ��M2)        
	@Column(nullable=false, length=1)
	private int status        ;//number(1)              1       ��Ч״̬ ��1����Ч  0����Ч�� 
	@Column(nullable=true, length=32)
	private long housecatid    ;//number(32)      
	
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
