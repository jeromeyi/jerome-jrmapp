package com.jrmapp.service;

import com.jrmapp.pojo.HouseType;

public interface HouseTypeService {
	public void save(HouseType houseType) throws Exception;
	public void nestedSave() throws Exception;
}
