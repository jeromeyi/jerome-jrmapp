package com.jrmapp.dao.extend; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.jrmapp.dao.support.BeanUtils;
import com.jrmapp.dao.support.Page;



/** 
 * 负责为单个Entity对象提供CRUD操作的Hibernate DAO基类. <p/>  
 * 子类只要在类定义时指定所管理Entity的Class, 
 * 即拥有对单个Entity对象的CRUD操作. 
 *  
 * @see    继承自spring的HibernateDaoSupport 
 */ 
public class HibernateEntityDao<T,PK extends Serializable> extends HibernateDaoSupport implements IEntityDao<T, PK> { 
	private static final Logger log = LoggerFactory.getLogger(HibernateEntityDao.class); 
    protected Class<T> entityClass;// DAO所管理的Entity类型. 
    public void setEntityClass(Class<T> type){ 
        this.entityClass=type; 
    } 

    /** 
     * 在构造函数中将泛型T.class赋给entityClass. 
     */ 
    public HibernateEntityDao() { 
        //entityClass = GenericsUtils.getSuperClassGenricType(getClass()); 
    } 

    /** 
     * 取得entityClass.JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。 
     */ 
    protected Class<T> getEntityClass() { 
        return entityClass; 
    } 
     
    /** 
     * 根据Serializable类型的id获取实体对象<p/> 
     * 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常. 
     * @param id 
     */ 
    @SuppressWarnings("unchecked") 
    public T get(PK id) throws Exception{ 
    	return (T) getHibernateTemplate().load(getEntityClass(), id); 
        //return (T) getHibernateTemplate().load(getEntityClass(), id,LockMode.UPGRADE); 
    	 //return (T) getHibernateTemplate().get(getEntityClass(), id); 
    } 
     
    /** 
     * 获取实体类型的全部对象 
     */ 
    @SuppressWarnings("unchecked") 
    public List<T> getAll() throws Exception{ 
    	//getHibernateTemplate().setCacheQueries(true);  
        return (List<T>)(getHibernateTemplate().loadAll(getEntityClass())); 
    } 
     
    /** 
     * 获取全部对象,带排序字段与升降序参数. 
     */ 
    @SuppressWarnings("unchecked") 
    public  List<T> getAll(String orderBy, boolean isAsc) throws Exception{ 
        Assert.hasText(orderBy); 
        if (isAsc) 
            return getHibernateTemplate().findByCriteria( 
                    DetachedCriteria.forClass(getEntityClass()).addOrder(Order.asc(orderBy))); 
        else 
            return getHibernateTemplate().findByCriteria( 
                    DetachedCriteria.forClass(getEntityClass()).addOrder(Order.desc(orderBy))); 
    } 

    /** 
     * 删除对象. 
     */ 
    public void remove(T entity) throws Exception{ 

        getHibernateTemplate().delete(entity); 
    } 

    /** 
     * 根据ID删除对象. 
     */ 
    public void removeById(PK id) throws Exception{ 
         
        remove(get(id)); 
    } 

    /** 
     * 保存对象.<br> 
     * 如果对象已在本session中持久化了,不做任何事。<br> 
     * 如果另一个seesion拥有相同的持久化标识,抛出异常。<br> 
     * 如果没有持久化标识属性,调用save()。<br> 
     * 如果持久化标识表明是一个新的实例化对象,调用save()。<br> 
     * 如果是附带版本信息的(<version>或<timestamp>)且版本属性表明为新的实例化对象就save()。<br> 
     * 否则调用update()重新关联托管对象 
     */ 
    public void save(T entity) throws Exception{ 

       getHibernateTemplate().saveOrUpdate(entity); 
    } 

    /** 
     * 在不同的session中关联修改过的托管对象 
     */ 
    public void update(T entity) throws Exception{ 

       getHibernateTemplate().update(entity); 
    } 
     
    /** 
     * 消除与 Hibernate Session 的关联 
     * @param entity 
     */ 
    public void evict(T entity) throws Exception{ 
        getHibernateTemplate().evict(entity); 
    } 
     
    /** 
     * 创建Criteria对象. 
     * @param criterions 可变的Restrictions条件列表 
     */ 
    public  Criteria createCriteria(Criterion... criterions) throws Exception{ 
        Criteria criteria = getSession().createCriteria(getEntityClass()); 
        for (Criterion c : criterions) { 
            criteria.add(c); 
        } 
        return criteria; 
    } 
    /** 
     * 创建Criteria对象，带排序字段与升降序字段. 
     */ 
    public  Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) throws Exception{ 
        Assert.hasText(orderBy); 

        Criteria criteria = createCriteria(criterions); 

        if (isAsc) 
            criteria.addOrder(Order.asc(orderBy)); 
        else 
            criteria.addOrder(Order.desc(orderBy)); 

        return criteria; 
    } 
     
    /** 
     * 根据属性名和属性值查询对象. 
     * @return 符合条件的对象列表 
     */ 
    @SuppressWarnings("unchecked") 
    public  List<T> findBy(String propertyName, Object value)throws Exception { 
        Assert.hasText(propertyName); 
        return createCriteria(Restrictions.eq(propertyName, value)).list(); 
    } 
     
    /** 
     * 根据属性名和属性值查询对象,带排序参数. 
     */ 
    @SuppressWarnings("unchecked") 
    public  List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc)throws Exception { 
        Assert.hasText(propertyName); 
        Assert.hasText(orderBy); 
        return createCriteria(orderBy, isAsc, Restrictions.eq(propertyName, value)).list(); 
    } 
     
    /** 
     * 根据属性名和属性值查询唯一对象. 
     * @return 符合条件的唯一对象 or null if not found. 
     */ 
    @SuppressWarnings("unchecked") 
    public  T findUniqueBy(String propertyName, Object value) throws Exception{ 
        Assert.hasText(propertyName); 
        return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult(); 
    } 
     
    /** 
     * 分页查询函数，使用已设好查询条件与排序的<code>Criteria</code>. 
     * 
     * @param pageNo 页号,从1开始. 
     * @return 含总记录数和当前页数据的Page对象. 
     */ 
    @SuppressWarnings("unchecked") 
    public Page pagedQuery(Criteria criteria, int pageNo, int pageSize) throws Exception{ 
    	//getHibernateTemplate().setCacheQueries(true);  
        Assert.notNull(criteria); 

        if(pageNo<1)
        	pageNo=1;
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1"); 
        CriteriaImpl impl = (CriteriaImpl) criteria; 

        // 先把Projection和OrderBy条件取出来,清空两者来执行Count操作 
        Projection projection = impl.getProjection(); 
        List<CriteriaImpl.OrderEntry> orderEntries; 
        try { 
            orderEntries = (List) BeanUtils.forceGetProperty(impl, "orderEntries"); 
            BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList()); 
        } catch (Exception e) { 
            throw new InternalError(" Runtime Exception impossibility throw "); 
        } 
        // 执行查询 
        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
        if(pageSize<1)
        	pageSize=totalCount;
        if(pageNo*pageSize-totalCount>=pageSize)
        	pageNo=totalCount % pageSize == 0?totalCount/ pageSize:totalCount/ pageSize+1;
        	//return new Page(); 
        // 将之前的Projection和OrderBy条件重新设回去 
        criteria.setProjection(projection); 
        if (projection == null) { 
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY); 
        } 

        try { 
            BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries); 
        } catch (Exception e) { 
            throw new InternalError(" Runtime Exception impossibility throw "); 
        } 

        // 返回分页对象 
        if (totalCount < 1) 
            return new Page(); 

        int startIndex = Page.getStartOfPage(pageNo, pageSize);; 
        List list = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list(); 
        return new Page(startIndex, totalCount, pageSize, list); 
    } 
     
    /** 
     * 分页查询函数，根据entityClass和查询条件参数创建默认的<code>Criteria</code>. 
     * 
     * @param pageNo 页号,从1开始. 
     * @return 含总记录数和当前页数据的Page对象. 
     */ 
    public Page pagedQuery(int pageNo, int pageSize, Criterion... criterions)throws Exception { 
       
    	Criteria criteria = createCriteria(criterions); 
        return pagedQuery(criteria, pageNo, pageSize); 
    } 
     
    /** 
     * 分页查询函数，根据entityClass和查询条件参数,排序参数创建默认的<code>Criteria</code>. 
     * 
     * @param pageNo 页号,从1开始. 
     * @return 含总记录数和当前页数据的Page对象. 
     */ 
    public Page pagedQuery(int pageNo, int pageSize, String orderBy, boolean isAsc, 
                           Criterion... criterions) throws Exception{ 
        Criteria criteria = createCriteria(orderBy, isAsc, criterions); 
        return pagedQuery(criteria, pageNo, pageSize); 
    } 
     
    /** 
     * 判断对象某些属性的值在数据库中是否唯一. 
     * 
     * @param uniquePropertyNames 在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password" 
     */ 
    public boolean isUnique(T entity, String uniquePropertyNames)throws Exception { 
        Assert.hasText(uniquePropertyNames); 
        Criteria criteria = createCriteria().setProjection(Projections.rowCount()); 
        String[] nameList = uniquePropertyNames.split(","); 
        try { 
            // 循环加入唯一列 
            for (String name : nameList) { 
                criteria.add(Restrictions.eq(name, PropertyUtils.getProperty(entity, name))); 
            } 

            // 以下代码为了如果是update的情况,排除entity自身. 

            String idName = getIdName(getEntityClass()); 

            // 取得entity的主键值 
            PK id = getId(getEntityClass(), entity); 

            // 如果id!=null,说明对象已存在,该操作为update,加入排除自身的判断 
            if (id != null) 
                criteria.add(Restrictions.not(Restrictions.eq(idName, id))); 
        } catch (Exception e) { 
            ReflectionUtils.handleReflectionException(e); 
        } 
        return (Integer) criteria.uniqueResult() == 0; 
    } 
     
    /** 
     * 取得对象的主键值,辅助函数. 
     */ 
    @SuppressWarnings("unchecked") 
    public  PK getId(Class<T> entityClass, T entity) throws NoSuchMethodException, IllegalAccessException, 
            InvocationTargetException,Exception { 
        Assert.notNull(entity); 
        Assert.notNull(entityClass); 
        return (PK) PropertyUtils.getProperty(entity, getIdName(entityClass)); 
    } 

    /** 
     * 取得对象的主键名,辅助函数. 
     */ 
    public String getIdName(Class<T> clazz) throws Exception{ 
        Assert.notNull(clazz); 
        ClassMetadata meta = getSessionFactory().getClassMetadata(clazz); 
        Assert.notNull(meta, "Class " + clazz + " not define in hibernate session factory."); 
        String idName = meta.getIdentifierPropertyName(); 
        Assert.hasText(idName, clazz.getSimpleName() + " has no identifier property define."); 
        return idName; 
    } 
    
    public void logSecondLevelCacheStatisticsInfo(){ 
    	Statistics stat = getHibernateTemplate().getSessionFactory().getStatistics(); 
    	String [] queryRegionNames = stat.getSecondLevelCacheRegionNames(); 
    	String queryRegionName = null; 
    	SecondLevelCacheStatistics slcStat = null; 
    	log.info("#########################SECOND LEVEL CACHE REGION NAMES#########################"); 
    	for(int i = 0; i < queryRegionNames.length; i ++){ 
    	queryRegionName = queryRegionNames[i]; 
    	slcStat = stat.getSecondLevelCacheStatistics(queryRegionName); 
    	long memCnt = slcStat.getElementCountInMemory(); 
    	long diskCnt = slcStat.getElementCountOnDisk(); 
    	long putCnt = slcStat.getPutCount(); 
    	long hitCnt = slcStat.getHitCount(); 
    	long missCnt = slcStat.getMissCount(); 
    	long size = slcStat.getSizeInMemory(); 
    	log.info("#" + queryRegionNames[i] + "|total:" + putCnt + "|memory:" + memCnt + "|disk:" + diskCnt + 
    	"|hit:" + hitCnt + "|miss:" + missCnt + "|size:" + size ); 
    	} 
    	log.info("#################################################################################"); 

    	} 
      

} 


