package com.jrmapp.dao.base; 

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.jrmapp.dao.generic.HibernateGenericDao;
import com.jrmapp.dao.support.Page;




/** 
 * 提供hibernate dao的所有操作,<br> 
 * 实现类由spring注入HibernateEntityDao和HibernateGenericDao来实现 
 *  
 */ 
public interface IBaseDao<T,PK extends Serializable> { 

     
    /** 
     * 获取全部对象 
     *  
     * @see HibernateGenericDao#getAll(Class) 
     */ 
    public List<T> getAll() throws Exception; 
     
    /** 
     * 获取全部对象,带排序参数. 
     */ 
    public List<T> getAll(String orderBy, boolean isAsc) throws Exception; 
     
    /** 
     * 根据ID移除对象. 
     */ 
    public void removeById(PK id) throws Exception; 
     
    /** 
     * 取得Entity的Criteria. 
     */ 
    public Criteria createCriteria(Criterion... criterions) throws Exception; 
     
    /** 
     * 取得Entity的Criteria,带排序参数. 
     */ 
    public Criteria createCriteria(String orderBy, boolean isAsc, 
            Criterion... criterions) throws Exception; 
     
    /** 
     * 根据属性名和属性值查询对象. 
     *  
     * @return 符合条件的对象列表 
     */ 
    public List<T> findBy(String propertyName, Object value) throws Exception; 
     
    /** 
     * 根据属性名和属性值查询对象,带排序参数. 
     *  
     * @return 符合条件的对象列表 
     */ 
    public List<T> findBy(String propertyName, Object value, String orderBy, 
            boolean isAsc) throws Exception; 
     
    /** 
     * 根据属性名和属性值查询单个对象. 
     *  
     * @return 符合条件的唯一对象 or null 
     */ 
    public T findUniqueBy(String propertyName, Object value) throws Exception; 
     
    /** 
     * 判断对象某些属性的值在数据库中唯一. 
     *  
     * @param uniquePropertyNames 
     *            在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password" 
     * @see HibernateGenericDao#isUnique(Class,Object,String) 
     */ 
    public boolean isUnique(T entity, String uniquePropertyNames) throws Exception; 
     
    /** 
     * 消除与 Hibernate Session 的关联 
     *  
     */ 
    public void evit(T entity) throws Exception; 
     
     
    /** 
     * 根据ID获取对象. 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常. 
     */ 
    public T get(PK id) throws Exception; 
     
    /** 
     * 保存对象. 
     */ 
    public void save(T o) throws Exception; 
     
    /** 
     * 删除对象. 
     */ 
    public void remove(T o) throws Exception; 
     
    public void flush() throws Exception; 
     
    public void clear() throws Exception; 
     
    /** 
     * 创建Query对象. 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置. 
     * 留意可以连续设置,如下： 
     * <pre> 
     * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list(); 
     * </pre> 
     * 调用方式如下： 
     * <pre> 
     *        dao.createQuery(hql) 
     *        dao.createQuery(hql,arg0); 
     *        dao.createQuery(hql,arg0,arg1); 
     *        dao.createQuery(hql,new Object[arg0,arg1,arg2]) 
     * </pre> 
     * 
     * @param values 可变参数. 
     */ 
    public Query createQuery(String hql, Object... values) throws Exception; 
     
    public List createQueryToList(String hql, Object... values) throws Exception;  
    public Iterator createQueryToIterator(String hql, Object... values) throws Exception;  
    
    /** 
     * 根据hql查询,直接使用HibernateTemplate的find函数. 
     */ 
    @SuppressWarnings("unchecked") 
    public List find(String hql, Object... values) throws Exception; 
     

    /** 
     * 分页查询函数，使用hql. 
     * 
     * @param pageNo 页号,从1开始. 
     */ 
    public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values) throws Exception; 
     
    /** 
     * @param hql 查询sql 
     * @param start 分页从哪一条数据开始 
     * @param pageSize 每一个页面的大小 
     * @param values 查询条件 
     * @return page对象 
     */ 
    public Page dataQuery(String hql, int start, int pageSize, Object... values) throws Exception; 
     
    /** 
     * 分页查询函数，使用已设好查询条件与排序的<code>Criteria</code>. 
     * 
     * @param pageNo 页号,从1开始. 
     * @return 含总记录数和当前页数据的Page对象. 
     */ 
    public Page pagedQuery(Criteria criteria, int pageNo, int pageSize) throws Exception; 
     
    /** 
     * 分页查询函数，根据entityClass和查询条件参数创建默认的<code>Criteria</code>. 
     * 
     * @param pageNo 页号,从1开始. 
     * @return 含总记录数和当前页数据的Page对象. 
     */ 
    public Page pagedQuery(int pageNo, int pageSize, Criterion... criterions) throws Exception; 
     
    /** 
     * 分页查询函数，根据entityClass和查询条件参数,排序参数创建默认的<code>Criteria</code>. 
     * 
     * @param pageNo 页号,从1开始. 
     * @return 含总记录数和当前页数据的Page对象. 
     */ 
    public Page pagedQuery(int pageNo, int pageSize, String orderBy, boolean isAsc, 
               Criterion... criterions) throws Exception; 
     

    @SuppressWarnings("unchecked") 
    public List executeNativeSql(String sql) throws Exception; 
     
    public HibernateTemplate getHibernateTemplate() throws Exception; 
     
    /** 
     * 在不同的session中关联修改过的托管对象 
     */ 
    public void update(T entity) throws Exception; 
}

