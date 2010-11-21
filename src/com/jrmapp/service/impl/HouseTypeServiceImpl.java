package com.jrmapp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jrmapp.dao.base.IBaseDao;
import com.jrmapp.pojo.HouseType;
import com.jrmapp.service.HouseTypeService;
import com.jrmapp.service.base.BaseServiceImpl;

@Service("houseTypeService")
public class HouseTypeServiceImpl extends BaseServiceImpl implements
		HouseTypeService {
	@Resource(name="houseTypeDao")
	private IBaseDao<HouseType,Long> houseTypeDao;
	public void save(HouseType houseType) throws Exception{
		houseTypeDao.save(houseType);
/*		try{
		nestedSave();
		}catch (Exception e) {
			// TODO: handle exception
			requiresNewSave();
		}*/
		//requiresNewSave();
		 //throw new Exception("抛出异常");
	}
	 public void nestedSave() throws Exception{
		 HouseType ht=new HouseType();
			ht.setHousetypename("测试异常nest");
			ht.setHousecatid(1);
			ht.setInitarea(40);
			ht.setToparea(40);
			ht.setStatus(1);
			houseTypeDao.save(ht);  
		 throw new RuntimeException("抛出异常");
	 }
	 public void requiresNewSave() throws Exception{
		 HouseType ht1=new HouseType();
			ht1.setHousetypename("测试异常nest");
			ht1.setHousecatid(1);
			ht1.setInitarea(40);
			ht1.setToparea(40);
			ht1.setStatus(1);
			houseTypeDao.save(ht1);  
		 //throw new Exception("抛出异常");
	 }
}
