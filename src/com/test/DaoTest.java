package com.test;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

import com.jrmapp.dao.base.IBaseDao;
import com.jrmapp.pojo.HouseType;
import com.jrmapp.pojo.SellSeries;
import com.jrmapp.service.HouseTypeService;



/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})*/
public class DaoTest  extends JavenTestCase{
    
	@Resource(name="houseTypeDao")
	private IBaseDao<HouseType,Integer> houseTypeDao;
	@Resource(name="sellSeriesDao")
	private IBaseDao<SellSeries,Integer> sellSeriesDao;
	@Resource(name="houseTypeService")
	private HouseTypeService houseTypeService;
	
	@Test
	public void test() throws Exception{
		Vector v=new Vector();
		Collections.synchronizedList(v);
         
			//HouseType ht1=houseTypeDao.get(2923);
			//SellSeries ss=sellSeriesDao.get(3062);
/*			HouseType ht=new HouseType();
			ht.setHousetypename("测试异常");
			ht.setHousecatid(23668);
			ht.setInitarea(100);
			ht.setToparea(200);
			ht.setStatus(2);

		    // 注意该处只验证了HouseType 为了说明 @Valid 注释的使用
		    ClassValidator<HouseType> classValidator = new ClassValidator<HouseType> (HouseType.class);
		    InvalidValue[] validMessages = classValidator.getInvalidValues(ht);
		    for (InvalidValue value : validMessages) {
		     
		    System.out.println("InvalidValue 的长度是:" + validMessages.length
		        +" . 验证消息是: " + value.getMessage() 
		        +" . PropertyPath 是:" + value.getPropertyPath()
		        +" .\n\t PropertyName 是: " +value.getPropertyName()
		        +" Value 是: " + value.getValue()
		        +" Bean 是: "+ value.getBean()
		        +" \n\t BeanClass 是:" + value.getBeanClass());
		    }*/
		    
		    	
			    //System.out.println("验证消息是: " + value.getMessage() );
			//}
		    
		   // Assert.assertTrue(validMessages.length>0);
		for(int i=1;i<1000000;i++){
		HouseType ht=new HouseType();
		ht.setHousetypename("测试异常"+i);
		ht.setHousecatid(1);
		ht.setInitarea(40);
		ht.setToparea(40);
		ht.setStatus(1);
		houseTypeService.save(ht);
		}
/*		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<HouseType>> constraintViolations =
			validator.validate(ht);
		Iterator<ConstraintViolation<HouseType>> iterator=constraintViolations.iterator();
		while(iterator.hasNext()){
			ConstraintViolation<HouseType> cv=iterator.next();
			System.out.println(cv.getInvalidValue()+"---"+cv.getMessage());
		}*/
		//assertEquals(0, constraintViolations.size());
/*		HouseType ht1=new HouseType();
		ht1.setHousetypename("测试异常");
		ht1.setHousecatid(1);
		ht1.setInitarea(40);
		ht1.setToparea(40);
		ht1.setStatus(1);
		houseTypeService.save(ht);
		houseTypeService.save(ht1);*/

			
		/*//System.out.println("ht1====="+ht1.getHousetypename());
		//System.out.println("ss====="+ss.getSeriesname());
		List<HouseType> htList=houseTypeDao.getAll();
		for(HouseType obj:htList){
		//	System.out.println(obj.getHousetypename());
		}
		List<HouseType> htList1=houseTypeDao.find(" from HouseType where initarea>? and toparea>?", 0l,0l);
		for(HouseType obj:htList1){
			//System.out.println(obj.getHousetypename());
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
		}*/
	}
}
