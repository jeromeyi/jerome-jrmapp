package com.jrmapp.action;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.taglibs.standard.tag.common.xml.WhenTag;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;
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

@SuppressWarnings("serial")
@Controller
@Scope("prototype")

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
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
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
		user=userService.get(6);
    	System.out.println("userService===="+user.getName());
    	Set addSet=user.getAddresses();
    	Iterator iterator=addSet.iterator();
    	while(iterator.hasNext()){
    		Address address=(Address)iterator.next();
    		System.out.println("address===="+address.getAddress());
    	}
		Address  address = (Address) addressDao.get(6L);  
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
			/******************Test validator ********/
		    // 注意该处只验证了HouseType 为了说明 @Valid 注释的使用
		    ClassValidator<HouseType> classValidator = new ClassValidator<HouseType> (HouseType.class);
		    InvalidValue[] validMessages = classValidator.getInvalidValues(ht);
		    for (InvalidValue value : validMessages) {
		     /** 
		    System.out.println("InvalidValue 的长度是:" + validMessages.length
		        +" . 验证消息是: " + value.getMessage() 
		        +" . PropertyPath 是:" + value.getPropertyPath()
		        +" .\n\t PropertyName 是: " +value.getPropertyName()
		        +" Value 是: " + value.getValue()
		        +" Bean 是: "+ value.getBean()
		        +" \n\t BeanClass 是:" + value.getBeanClass());
		    }
		    **/
		    	
			    //System.out.println("验证消息是: " + value.getMessage() );
			}
		    

		    
			//houseTypeDao.save(ht);
		//System.out.println("ht1====="+ht1.getHousetypename());
		//System.out.println("ss====="+ss.getSeriesname());
	/*	List<HouseType> htList=houseTypeDao.getAll();
		for(HouseType obj:htList){
		//	System.out.println(obj.getHousetypename());
		}*/
		List<HouseType> htList1=houseTypeDao.find(" from HouseType where initarea>? and toparea>?", 0l,0l);
		for(HouseType obj:htList1){
			System.out.println(obj.getHousetypename());
		}
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
