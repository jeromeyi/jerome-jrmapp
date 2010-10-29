package com.jrmapp.activemq.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 28, 2010 6:43:26 PM
 * @类说明
 */
public class VisitStatInfoDao {
	public  void insert(VisitStatInfoBean vfBean)throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{ 
		ApplicationContext   ctx=new   FileSystemXmlApplicationContext( "applicationContext-activemq.xml "); 
		DataSource   ds   =   (DataSource)   ctx.getBean( "datasource "); 
		Connection conn= ds.getConnection(); 
		   //Connection ApplicationConn=askyaya.util.DBManager.getconn(); 
		    String strsql="insert into tbl_visit_stat_info(visitor_ip,server_id,server_ip,column_id," 
		    + "page_id,page_url, parameter, Visit_count,User_id,Visitor_type,Product_id,Seller_id," 
		    + "Info_date,visit_date,Referer_page) values(?,?,?,?,?,?,?,?,?,?,?,?,Sysdate,to_char(Sysdate,'yyyy-mm-dd'),?)"; 
		  
		    PreparedStatement pstmt = null; 
		    conn.setAutoCommit(false); 
		    try {   
		     pstmt = conn.prepareStatement(strsql);           
		   //VisitStatInfoBean vfBean=new VisitStatInfoBean(); 
		    pstmt.setString(1,vfBean.getVisitor_ip()); 
		        pstmt.setInt(2, vfBean.getServer_id()); 
		        pstmt.setString(3,vfBean.getServer_ip()); 
		        pstmt.setInt(4,vfBean.getColumn_id()); 
		        pstmt.setInt(5,vfBean.getPage_id()); 
		        pstmt.setString(6,vfBean.getPage_url()); 
		        pstmt.setString(7,vfBean.getParameter()); 
		        pstmt.setInt(8, vfBean.getVisit_count()); 
		        pstmt.setInt(9,vfBean.getUser_id()); 
		        pstmt.setInt(10,vfBean.getVisitor_type()); 
		        pstmt.setInt(11,vfBean.getProduct_id()); 
		        pstmt.setInt(12,vfBean.getSeller_id()); 
		        pstmt.setString(13,vfBean.getReferer_page());        
		        pstmt.executeUpdate(); 
		        conn.commit(); 
		       // System.out.println("insert the tbl_visit_stat_info is end "); 
		         } catch (SQLException sqle) { 
		         int errcode = sqle.getErrorCode(); 
		           // System.err.println("aq.executeQuery: " + sqle.getMessage()+":;");          
		              //违反唯一约束 
		              if(errcode == 1){}        
		              else{ 
		                // System.err.println("aq.executeQuery: " + sqle.getMessage()+":;");         
		              }                   
		              conn.rollback(); 
		         }finally { 
		     try {      
		      pstmt.close(); 
		      conn.close();     
		     } catch (Exception ex) { 
		      ex.printStackTrace(); 
		     } 
		    } 

		} 
		} 
