package com.jrmapp.action.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;

import com.jrmapp.action.base.BaseAction;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 25, 2010 10:51:00 AM
 * @类说明
 */
public class UploadAction extends BaseAction  
{  
    private static final int BUFFER_SIZE = 16 * 1024;  
    private List<File> upload;  
    private List<String> uploadFileName;  
    private List<String> uploadContentType;  
      
    public List<File> getUpload()  
    {  
        return upload;  
    }  
  
    public void setUpload(List<File> upload)  
    {  
        this.upload = upload;  
    }  
  
    public List<String> getUploadFileName()  
    {  
        return uploadFileName;  
    }  
  
    public void setUploadFileName(List<String> uploadFileName)  
    {  
        this.uploadFileName = uploadFileName;  
    }  
  
    public List<String> getUploadContentType()  
    {  
        return uploadContentType;  
    }  
  
    public void setUploadContentType(List<String> uploadContentType)  
    {  
        this.uploadContentType = uploadContentType;  
    }  
  
    private static void copy(File src, File dst)   
    {  
        InputStream in = null;  
        OutputStream out = null;  
          
        try  
        {  
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);  
            out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);  
            byte[] buffer = new byte[BUFFER_SIZE];  
            int len = 0;  
            while ((len = in.read(buffer)) > 0)   
            {  
                out.write(buffer, 0, len);  
            }  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
            if (null != in)   
            {  
                try  
                {  
                    in.close();  
                }   
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
            if (null != out)   
            {  
                try  
                {  
                    out.close();  
                }   
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
      
    @Override
    @Action(value = "/upload",results = { @Result(name = "upload", location = "/upload.html")
	},interceptorRefs = { @InterceptorRef("token") }) 
    public String execute() throws Exception  
    {  
        List<File> dstFiles = this.getUpload();  
        for (int i = 0; i < dstFiles.size(); i++)   
        {  
            String dstPath = ServletActionContext  
                .getServletContext()  
                .getRealPath("//uploadfolder") + "//" + this.getUploadFileName().get(i);  
            File dstFile = new File(dstPath);  
            copy(dstFiles.get(i),dstFile);  
        }  
        return SUCCESS;  
    }  
    
    public String goUpload() throws Exception{
    	 return SUCCESS;  
    }
}  
