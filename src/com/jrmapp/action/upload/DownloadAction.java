package com.jrmapp.action.upload;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 25, 2010 10:54:26 AM
 * @类说明
 */
public class DownloadAction extends ActionSupport  
{  
    private String fileName;  
      
    public String getFileName()  
    {  
        String temp = fileName;  
        try  
        {  
            temp = new String(temp.getBytes("iso8859-1"),"UTF-8");  
        }   
        catch (UnsupportedEncodingException e)  
        {  
            e.printStackTrace();  
        }  
        return temp;  
    }  
  
    public void setFileName(String fileName)  
    {  
          
        this.fileName = fileName;  
    }  
  
    public InputStream getInputStream() throws Exception  
    {  
        String filePath = "/uploadfolder" + "/" + this.getFileName();  
        InputStream in = ServletActionContext  
            .getServletContext()  
            .getResourceAsStream(filePath);  
        return in;  
    }  
      
    @Override  
    public String execute() throws Exception  
    {  
        return SUCCESS;  
    }  
}  
