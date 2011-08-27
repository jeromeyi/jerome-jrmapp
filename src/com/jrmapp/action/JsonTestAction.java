package com.jrmapp.action;

import javax.annotation.Resource;
import javax.persistence.Transient;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jrmapp.action.base.BaseAction;
import com.jrmapp.dao.base.IBaseDao;
import com.jrmapp.dao.support.Page;
import com.jrmapp.pojo.HouseType;
import com.jrmapp.pojo.test;
import com.jrmapp.service.HouseTypeService;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Aug 23, 2010 1:40:55 PM
 * @类说明
 */
@SuppressWarnings("serial")
//@ParentPackage("json-default")  
//@Result(type="json",name="jsontest") 
@Results( 
{@Result(type="json")}) 
@Controller
@Scope("prototype")

public class JsonTestAction extends BaseAction {
	@Resource(name="houseTypeDao")
	private IBaseDao<HouseType,Integer> houseTypeDao;
	 private int pageNo=1;
	    public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

		private int pageSize=20;
	//没有setter和getter方法的字段不会被序列化     
	//'transient'不会被序列化    
	@Transient
	private  String name = "fish119"; 
	private String email;
	
	private  test test;
	private  HouseType houseType;
	public HouseType getHouseType() {
		return houseType;
	}

	public void setHouseType(HouseType houseType) {
		this.houseType = houseType;
	}

	public test getTest() {
		return test;
	}

	public void setTest(test test) {
		this.test = test;
	}

	private Page testPage;
	
	@Resource(name="bbbAction")
	private BbbAction bbbAction;
	
	@Resource(name="houseTypeService")
	private HouseTypeService houseTypeService;


	public Page getTestPage() {
		return testPage;
	}

	public void setTestPage(Page testPage) {
		this.testPage = testPage;
	}

	public String getName() {  
        return name;  
    }  
      
    public void setName(String name) {  
        this.name = name;  
    }  
      
    //@Action(value="test",results={@Result(type="json",name="test")},interceptorRefs = { @InterceptorRef("token") })  
    public String test() throws Exception{  
        this.name += ": Test method!!";  
          
        return SUCCESS;  
    }  
    
    //@Action(value="/jsonTestObj",results={@Result(type="json",name="testObj")})  
    public String testObj() throws Exception{  
    	
    	if(null==test){
			test=new test();
		test.setTesta("testa");
		test.setTestb("testb");
		}
		//testPage=bbbAction.test();
		testPage=houseTypeDao.pagedQuery(pageNo, pageSize);
          
        return SUCCESS;  
    }  
    
    //@Action(value="ajaxForm",results={@Result(type="json",name="test")})  
    public String testAjaxForm() throws Exception{  
    	 this.name="测试ajaxSubmit";
          
    	 return SUCCESS;  
    }
    

    public String saveHouseType() throws Exception{  
    	houseTypeService.save(houseType);
    	 this.name="保存成功";    
        return SUCCESS;  
    }
      
    //@Action(results={@Result(type="json",name="success")})  
    public String execute() throws Exception{  
        this.name +=": This is the default method!";  
          
        return SUCCESS;  
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}  
}
