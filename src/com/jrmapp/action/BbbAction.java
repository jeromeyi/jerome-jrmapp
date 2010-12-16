package com.jrmapp.action;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jrmapp.dao.base.IBaseDao;
import com.jrmapp.dao.support.Page;
import com.jrmapp.pojo.Address;
import com.jrmapp.pojo.HouseType;
import com.jrmapp.pojo.Message;
import com.jrmapp.pojo.SellSeries;
import com.jrmapp.pojo.User;
import com.jrmapp.pojo.UserIdCard;
import com.jrmapp.pojo.test;
import com.jrmapp.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.org.apache.bcel.internal.generic.NEW;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
//@InterceptorRef("checkLoginstack")//全局引用拦截器  
/*@InterceptorRefs({ 
    @InterceptorRef("interceptor-1"), 
    @InterceptorRef("defaultStack") 
}) */
public class BbbAction extends ActionSupport {
	@Resource(name="houseTypeDao")
	private IBaseDao<HouseType,Integer> houseTypeDao;
	@Resource(name="sellSeriesDao")
	private IBaseDao<SellSeries,Integer> sellSeriesDao;
	@Resource(name="userDao")
	private IBaseDao<User,Long> userDao;
	@Resource(name="userIdCardDao")
	private IBaseDao<UserIdCard,Long> userIdCardDao;
	@Resource(name="messageDao")
	private IBaseDao<Message,Long> messageDao;
	@Resource(name="addressDao")
	private IBaseDao<Address,Long> addressDao;
	@Resource(name="userService")
	private UserService userService;
	
	private Page testPage;
	
	private String a;
    private test test;
    private Date date;
    private long userID;  
    private long addressID;
    private User user;
    private int pageNo=1;
    private int pageSize=20;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public test getTest() {
		return test;
	}

	public Page getTestPage() {
		return testPage;
	}

	public void setTestPage(Page testPage) {
		this.testPage = testPage;
	}

	public void setTest(test test) {
		this.test = test;
	}

	@Override
	public void validate()  {
		System.out.println("验证开始。。。。。");
		Map map=this.getFieldErrors();
		this.clearFieldErrors();
		Set set=map.keySet();
		Iterator iterator=set.iterator();
		while(iterator.hasNext()){
			String key=iterator.next().toString();
			String mess=map.get(key).toString();
			if("pageSize".equals(key))
		       this.addFieldError(key, "每页记录数输入错误!");
			else if("pageNo".equals(key)){
			   this.addFieldError(key, "页数输入错误!");
			   this.addFieldError("test", "测试一条!");
			}
			
		}
		 //return   (intStr   ==   null)   ?   false   :   intStr.matches( "\\d+ "); 
		if (pageSize <=0) {
			this.addFieldError("number", "请您输入一个大于０的整数!");
		}
	}
	
	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
        System.out.println("default execute......");
		return super.execute();
	}
	//局部引用拦截器,引用多个拦截器的话则是nterceptorRefs({@InterceptorRef("interceptor-1"),@InterceptorRef("interceptor-2")})  
/*	@Action(value = "/input", results = { @Result(name = "INPUT", location = "/input.jsp"),  
	@Result(name = "login", location = "/login.html") },interceptorRefs = { @InterceptorRef("token") })  */
	@Action(value = "/bbbhello",results = { @Result(name = "hello", location = "/bbb-hello.html")
	},interceptorRefs = { @InterceptorRef("token"),@InterceptorRef("defaultStack") }) 
	//@Action(interceptorRefs=@InterceptorRef("token"))
	public String hello() throws Exception {
		// TODO Auto-generated method stub
		if(null==test){
			test=new test();
		test.setTesta("testa");
		test.setTestb("testb");
		}
		date=new Date();
		testPage=test();
		return "hello";
	}

	public Page test() throws Exception{
		
		User user1 = new User(); 
	    //if (true)
		   //throw new Exception("测试");
        user1.setName("test1"); 
		//userService.save(user1);
		user=userService.get(1);
    	System.out.println("userService===="+user.getName());
    	Set addSet=user.getAddresses();
    	Iterator iterator=addSet.iterator();
    	while(iterator.hasNext()){
    		Address address=(Address)iterator.next();
    		System.out.println("address===="+address.getAddress());
    	}
		Address  address = (Address) addressDao.get(1L);  
        System.out.println(address.getAddress());  
		Vector v=new Vector();
		Collections.synchronizedList(v);
/*		 ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");  
		 houseTypeDao= (IBaseDao)ctx.getBean("houseTypeDao");  
		 sellSeriesDao= (IBaseDao)ctx.getBean("sellSeriesDao");  */
		    System.out.println("ht----------------");
			HouseType ht1=houseTypeDao.get(1);
			System.out.println("ss----------------");
			SellSeries ss=sellSeriesDao.get(1);
			HouseType ht=new HouseType();
			ht.setHousetypename("测试");
			ht.setHousecatid(23668);
			ht.setInitarea(100);
			ht.setToparea(200);
			ht.setStatus(2);
			

		    
			//houseTypeDao.save(ht);
		//System.out.println("ht1====="+ht1.getHousetypename());
		//System.out.println("ss====="+ss.getSeriesname());
	/*	List<HouseType> htList=houseTypeDao.getAll();
		for(HouseType obj:htList){
		//	System.out.println(obj.getHousetypename());
		}*/
	/*	List<HouseType> htList1=houseTypeDao.find(" from HouseType where initarea>? and toparea>?", 0l,0l);
		for(HouseType obj:htList1){
			System.out.println(obj.getHousetypename());
		}*/
		Criterion  c1=Restrictions.ge("initarea", 0l) ;
		Criterion  c2=Restrictions.ge("toparea", 0l) ;
		Criterion c3=Restrictions.conjunction().add(
				Restrictions.disjunction()
				.add(Restrictions.eq("initarea", 30l))
				.add(Restrictions.eq("initarea", 40l))
				.add(Restrictions.eq("initarea", 0l))
				);

		Criterion c4=Restrictions.or(Restrictions.eq("initarea", 30l), Restrictions.eq("initarea", 40l));
		Page page=houseTypeDao.pagedQuery(1, 20, c1,c2,c3);
		List<HouseType> htList2=(List)page.getResult();
		for(HouseType obj:htList2){
			System.out.println(obj.getHousetypename()+"==="+obj.getInitarea());
		}
		
	/*	List list=houseTypeDao.createQueryToList(" from HouseType where initarea>? and toparea>?", 0l,0l);
		Iterator iterate= list.iterator();
		while(iterate.hasNext()){
			HouseType htit=(HouseType)iterate.next();
			System.out.println("query--"+htit.getHousetypename()+"==="+htit.getInitarea());
		}
		
		List listna=houseTypeDao.executeNativeSql("select housetypeid,housetypename from boloni.house_type");
		Iterator iteratena= listna.iterator();
		while(iteratena.hasNext()){
			Object[] objs=(Object[])iteratena.next();
			System.out.println("native--"+objs[0]+"==="+objs[1]);
		}*/
		return page;
	}
	//return "testQuery" 返回到bbb-testQuery.html  return "success" 返回 bbb.html
	//@InputConfig(methodName="query")
	@InputConfig(resultName="testQuery")
	public String testQuery() throws Exception{
		testPage=houseTypeDao.pagedQuery(pageNo, pageSize);
		return "testQuery";
	}
	
	//注意和@InputConfig结合使用
	public String query() {
		return "testQuery";
	}
	
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
