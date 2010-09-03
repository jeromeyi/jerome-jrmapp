package com.jrmapp.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(schema="boloni",name="sell_series")
public class SellSeries implements  Serializable{
	@Id
	
	 @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqid")
	 @GenericGenerator(
			 name="seqid",strategy="seqhilo",
			 parameters={
					 @Parameter(name="sequence", value="seqid")
					 }
			 )

   @Column(nullable=false, updatable=false, length=10)
	private int seriesid   ;//number(10)       
	@Column(nullable=false, length=100)
	private String seriesname ;//varchar2(100)     
	@Column(nullable=true, length=10)
	private int toppoint   ;//number(10)    y      
	@Column(nullable=true, length=10)
	private int overpoint  ;//number(10)    y           
	@Column(nullable=true, length=1)
	private int status     ;//number(1)              1   
	@Column(nullable=true, length=2)
	private int seriesrate ;//number(2)     y            
	public int getSeriesid() {
		return seriesid;
	}
	public void setSeriesid(int seriesid) {
		this.seriesid = seriesid;
	}
	public String getSeriesname() {
		return seriesname;
	}
	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}
	public int getToppoint() {
		return toppoint;
	}
	public void setToppoint(int toppoint) {
		this.toppoint = toppoint;
	}
	public int getOverpoint() {
		return overpoint;
	}
	public void setOverpoint(int overpoint) {
		this.overpoint = overpoint;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSeriesrate() {
		return seriesrate;
	}
	public void setSeriesrate(int seriesrate) {
		this.seriesrate = seriesrate;
	}

}
