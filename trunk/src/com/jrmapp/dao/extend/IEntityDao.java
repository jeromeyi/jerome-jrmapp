package com.jrmapp.dao.extend; 

import java.io.Serializable; 
import java.util.List; 


/** 
 * 针对单个Entity对象的CRUD操作定义. 
 */ 
public interface IEntityDao<T,PK extends Serializable> { 

    T get(PK id) throws Exception; 

    List<T> getAll() throws Exception; 

    void save(T entity) throws Exception; 

    void remove(T entity) throws Exception; 

    void removeById(PK id) throws Exception; 
     
    void update(T entity) throws Exception; 

    /** 
     * 获取Entity对象的主键名. 
     */ 
    String getIdName(Class<T> clazz) throws Exception; 
} 



