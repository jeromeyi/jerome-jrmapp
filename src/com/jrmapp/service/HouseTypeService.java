package com.jrmapp.service;

import com.jrmapp.pojo.HouseType;

public interface HouseTypeService {
	public void save(HouseType houseType) throws Exception;
	public void nestedSave() throws Exception;
	public HouseType getHouseType(long housetypeid) throws Exception;
	public HouseType getHouseTypeForJWS(long housetypeid) throws Exception;
}
