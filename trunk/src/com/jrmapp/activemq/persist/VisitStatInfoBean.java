package com.jrmapp.activemq.persist;

import java.util.Date;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 28, 2010 6:38:17 PM
 * @类说明
 */
public class VisitStatInfoBean extends BaseEntity {
	private static final long serialVersionUID = 1L; 
	String visitor_ip; 
	String server_ip; 
	String page_url; 
	String parameter; 
	String Referer_page; 
	int Visitor_type; 
	int server_id; 
	int column_id; 
	int page_id; 
	int Visit_count; 
	int User_id; 
	int Product_id; 
	int Seller_id; 
	Date info_date; 
	public Date getInfo_date() { 
	  return info_date; 
	} 
	public void setInfo_date(Date info_date) { 
	  this.info_date = info_date; 
	} 
	public int getColumn_id() { 
	  return column_id; 
	} 
	public void setColumn_id(int column_id) { 
	  this.column_id = column_id; 
	} 
	public int getPage_id() { 
	  return page_id; 
	} 
	public void setPage_id(int page_id) { 
	  this.page_id = page_id; 
	} 
	public String getPage_url() { 
	  return page_url; 
	} 
	public void setParameter(String parameter) { 
	  this.parameter = parameter; 
	} 
	public String getParameter() { 
	  return parameter; 
	} 
	public void setPage_url(String parameter) { 
	  this.parameter = parameter; 
	} 

	public int getProduct_id() { 
	  return Product_id; 
	} 
	public void setProduct_id(int product_id) { 
	  Product_id = product_id; 
	} 
	public String getReferer_page() { 
	  return Referer_page; 
	} 
	public void setReferer_page(String referer_page) { 
	  Referer_page = referer_page; 
	} 
	public int getSeller_id() { 
	  return Seller_id; 
	} 
	public void setSeller_id(int seller_id) { 
	  Seller_id = seller_id; 
	} 
	public int getServer_id() { 
	  return server_id; 
	} 
	public void setServer_id(int server_id) { 
	  this.server_id = server_id; 
	} 
	public String getServer_ip() { 
	  return server_ip; 
	} 
	public void setServer_ip(String server_ip) { 
	  this.server_ip = server_ip; 
	} 
	public int getUser_id() { 
	  return User_id; 
	} 
	public void setUser_id(int user_id) { 
	  User_id = user_id; 
	} 
	public int getVisit_count() { 
	  return Visit_count; 
	} 
	public void setVisit_count(int visit_count) { 
	  Visit_count = visit_count; 
	} 
	public String getVisitor_ip() { 
	  return visitor_ip; 
	} 
	public void setVisitor_ip(String visitor_ip) { 
	  this.visitor_ip = visitor_ip; 
	} 
	public int getVisitor_type() { 
	  return Visitor_type; 
	} 
	public void setVisitor_type(int visitor_type) { 
	  Visitor_type = visitor_type; 
	} 
	@Override 
	public int hashCode() { 
	  final int PRIME = 31; 
	  int result = super.hashCode(); 
	  result = PRIME * result + Product_id; 
	  result = PRIME * result + ((Referer_page == null) ? 0 : Referer_page.hashCode()); 
	  result = PRIME * result + Seller_id; 
	  result = PRIME * result + User_id; 
	  result = PRIME * result + Visit_count; 
	  result = PRIME * result + Visitor_type; 
	  result = PRIME * result + column_id; 
	  result = PRIME * result + ((info_date == null) ? 0 : info_date.hashCode()); 
	  result = PRIME * result + page_id; 
	  result = PRIME * result + ((page_url == null) ? 0 : page_url.hashCode()); 
	  result = PRIME * result + server_id; 
	  result = PRIME * result + ((server_ip == null) ? 0 : server_ip.hashCode()); 
	  result = PRIME * result + ((visitor_ip == null) ? 0 : visitor_ip.hashCode()); 
	  return result; 
	} 
	@Override 
	public boolean equals(Object obj) {
	  if (this == obj) 
	   return true; 
	  if (!super.equals(obj)) 
	   return false; 
	  if (getClass() != obj.getClass()) 
	   return false; 
	  final VisitStatInfoBean other = (VisitStatInfoBean) obj; 
	  if (Product_id != other.Product_id) 
	   return false; 
	  if (Referer_page == null) { 
	   if (other.Referer_page != null) 
	    return false; 
	  } else if (!Referer_page.equals(other.Referer_page)) 
	   return false; 
	  if (Seller_id != other.Seller_id) 
	   return false; 
	  if (User_id != other.User_id) 
	   return false; 
	  if (Visit_count != other.Visit_count) 
	   return false; 
	  if (Visitor_type != other.Visitor_type) 
	   return false; 
	  if (column_id != other.column_id) 
	   return false; 
	  if (info_date == null) { 
	   if (other.info_date != null) 
	    return false; 
	  } else if (!info_date.equals(other.info_date)) 
	   return false; 
	  if (page_id != other.page_id) 
	   return false; 
	  if (page_url == null) { 
	   if (other.page_url != null) 
	    return false; 
	  } else if (!page_url.equals(other.page_url)) 
	   return false; 
	  if (server_id != other.server_id) 
	   return false; 
	  if (server_ip == null) { 
	   if (other.server_ip != null) 
	    return false; 
	  } else if (!server_ip.equals(other.server_ip)) 
	   return false; 
	  if (visitor_ip == null) { 
	   if (other.visitor_ip != null) 
	    return false; 
	  } else if (!visitor_ip.equals(other.visitor_ip)) 
	   return false; 
	  return true; 
	  }
}

