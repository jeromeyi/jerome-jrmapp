package com.jrmapp.common.tool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class Log4jDBAppender extends org.apache.log4j.jdbc.JDBCAppender {  
    private BoneCP connectionPool = null;  
    private Connection connection = null;  
    private static Logger logger=Logger.getLogger(Log4jDBAppender.class);  
    public Log4jDBAppender() throws ClassNotFoundException {  
        // 设置连接池配置信息  
        BoneCPConfig config = new BoneCPConfig();  
        Class.forName("oracle.jdbc.driver.OracleDriver"); 
        try {  
            Properties P = new Properties();  
            P.load(Log4jDBAppender.class.getClassLoader().getResourceAsStream("database_conn.properties"));  
            // 数据库的JDBC URL  
            config.setJdbcUrl(P.getProperty("url"));  
            // 数据库用户名  
            config.setUsername(P.getProperty("username"));  
            // 数据库用户密码  
            config.setPassword(P.getProperty("password"));  
            // 数据库连接池的最小连接数  
            config.setMinConnectionsPerPartition(5);  
            // 数据库连接池的最大连接数  
            config.setMaxConnectionsPerPartition(10);  
            config.setPartitionCount(1);  
            // 设置数据库连接池  
            connectionPool = new BoneCP(config);  
              
          
        } catch (SQLException e) {  
            // TODO Auto-generated catch block  
            logger.error("连接池配置加载异常",e);  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            logger.error("加载配置文件IO异常",e);  
        }  
  
        // fetch a connection  
  
    }  
  
    @Override  
    protected Connection getConnection() throws SQLException {  
        if(connection==null||connection.isClosed()){  
            connection = connectionPool.getConnection();  
        }  
        return connection;  
    }  
  
    @Override  
    protected void closeConnection(Connection con) {  
        // TODO Auto-generated method stub  
        try {  
            connection.close();  
            connection=null;  
        } catch (SQLException e) {  
            // TODO Auto-generated catch block  
            logger.error("连接没正常关闭",e);  
        }  
    }  
  
}  