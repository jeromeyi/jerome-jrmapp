package com.jrmapp.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;

import org.apache.struts2.ServletActionContext;

import com.jrmapp.action.base.BaseAction;
import com.jrmapp.vo.PeopleBean;

public class JasperAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private List<PeopleBean> myList;

    public String execute() throws Exception {
        //添加数据
        PeopleBean p1=new PeopleBean("长沙", new Integer(9), "李化", "天洒路");
        PeopleBean p2=new PeopleBean("长沙", new Integer(22), "王小样", "呆小路564");
        PeopleBean p3=new PeopleBean("南昌", new Integer(23), "王奸可", "小顺路");
        PeopleBean p4=new PeopleBean("南昌", new Integer(32), "李洒", "顺濉路");
        PeopleBean p5=new PeopleBean("武汉", new Integer(39), "张中尖", "天洒路");
        PeopleBean p6=new PeopleBean("武汉", new Integer(35), "陈主宁", "天河路５６４");
        myList = new ArrayList<PeopleBean>();
        myList.add(p1);
        myList.add(p2);
        myList.add(p3);
        myList.add(p4);
        myList.add(p5);
        myList.add(p6);

        try {
            String reportSource;
            reportSource = ServletActionContext.getServletContext()
                    .getRealPath("/jasper/jasper_template.jrxml");
            File parent = new File(reportSource).getParentFile();
            //将.jrxml模板文件编译成为.jasper文件,当然,其文件名可以指定,如果没指定,则与.jrxml文件名一样.只是后缀不同而已
            JasperCompileManager.compileReportToFile(reportSource, new File(
                    parent, "compiled_jasper_template.jasper")
                    .getAbsolutePath());
            response.setContentType("text/html;charset=UTF-8");
            //response.setCharacterEncoding("UTF-8");  
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public List getMyList() {
        return myList;
    }
}
