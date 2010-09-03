package com.jrmapp.dao.base; 

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.jrmapp.dao.extend.HibernateEntityDao;
import com.jrmapp.dao.generic.HibernateGenericDao;
import com.jrmapp.dao.support.Page;

/** 
 * �ṩhibernate dao�����в���,<br> 
 * ʵ������springע��HibernateEntityDao��HibernateGenericDao��ʵ�� 
 *  
 */ 
public class BaseDao<T,PK extends Serializable> implements IBaseDao<T,PK> { 

    protected Class<T> entityClass;// DAO�������Entity����. 
    private HibernateEntityDao<T,PK> hedao; 
    private HibernateGenericDao hgdao; 
     
    public void setHedao(HibernateEntityDao<T, PK> hedao) { 
        hedao.setEntityClass(entityClass); 
        this.hedao = hedao; 
    } 

    public void setHgdao(HibernateGenericDao hgdao) { 
        this.hgdao = hgdao; 
    } 
     
    /** 
     *��spring�ṩ���캯��ע�� 
     */ 
    public BaseDao(Class<T> type) { 
        this.entityClass = type; 
    } 
     
    public BaseDao(){} 
     
    /** 
     * ������ж��󻺴� 
     */ 
    public void clear() { 
         
        hgdao.clear(); 
    } 

    /** 
     * ����Criteria����. 
     * @param criterions �ɱ��Restrictions�����б� 
     */ 
    public Criteria createCriteria(Criterion... criterions) { 
         
        return hedao.createCriteria(criterions); 
    } 

    /** 
     * ����Criteria���󣬴������ֶ����������ֶ�. 
     */ 
    public Criteria createCriteria(String orderBy, boolean isAsc, 
            Criterion... criterions) { 
         
        return hedao.createCriteria(orderBy, isAsc, criterions); 
    } 

    /** 
     * ����Query����. ������Ҫfirst,max,fetchsize,cache,cacheRegion��������õĺ���,�����ڷ���Query����������. 
     * ���������������,���£� 
     * <pre> 
     * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list(); 
     * </pre> 
     * ���÷�ʽ���£� 
     * <pre> 
     *        dao.createQuery(hql) 
     *        dao.createQuery(hql,arg0); 
     *        dao.createQuery(hql,arg0,arg1); 
     *        dao.createQuery(hql,new Object[arg0,arg1,arg2]) 
     * </pre> 
     * 
     * @param values �ɱ����. 
     */ 
    public Query createQuery(String hql, Object... values) { 
         
        return hgdao.createQuery(hql, values); 
    } 
    public List createQueryToList(String hql, Object... values){
    	return hgdao.createQueryToList(hql, values); 
    } 
    public Iterator createQueryToIterator(String hql, Object... values){
    	return hgdao.createQueryToIterator(hql, values); 
    }
    
    /** 
     * @param hql ��ѯsql 
     * @param start ��ҳ����һ�����ݿ�ʼ 
     * @param pageSize ÿһ��ҳ��Ĵ�С 
     * @param values ��ѯ���� 
     * @return page���� 
     */ 
    public Page dataQuery(String hql, int start, int pageSize, Object... values) { 
         
        return hgdao.dataQuery(hql, start, pageSize, values); 
    } 

    /** 
     * ������ Hibernate Session �Ĺ��� 
     * @param entity 
     */ 
    public void evit(T entity) { 
         
        hedao.evict(entity); 
    } 

    /** 
     * ִ�б���sql����ñ�����ֵ�б� 
     */ 
    @SuppressWarnings("unchecked") 
    public List executeNativeSql(String sql) { 
         
        return hgdao.executeNativeSql(sql); 
    } 

    /** 
     * ����hql��ѯ,ֱ��ʹ��HibernateTemplate��find����. 
     * @param values �ɱ���� 
     */ 
    @SuppressWarnings("unchecked") 
    public List find(String hql, Object... values) { 
         
        return hgdao.find(hql, values); 
    } 

    /** 
     * ����������������ֵ��ѯ����. 
     * @return ���������Ķ����б� 
     */ 
    public List<T> findBy(String propertyName, Object value) { 
         
        return hedao.findBy(propertyName, value); 
    } 

    /** 
     * ����������������ֵ��ѯ����,���������. 
     */ 
    public List<T> findBy(String propertyName, Object value, String orderBy, 
            boolean isAsc) { 
         
        return hedao.findBy(propertyName, value, orderBy, isAsc); 
    } 

    /** 
     * ����������������ֵ��ѯΨһ����. 
     * @return ����������Ψһ���� or null if not found. 
     */ 
    public T findUniqueBy(String propertyName, Object value) { 
         
        return hedao.findUniqueBy(propertyName, value); 
    } 

    /** 
     * ִ��һЩ�����sql�����ڴ��еĶ���ͬ����jdbc�������� 
     */ 
    public void flush() { 
         
        hgdao.flush(); 
    } 
     

    /** 
     * ����Serializable���͵�id��ȡʵ�����<p/> 
     * ʵ�ʵ���Hibernate��session.load()��������ʵ�����proxy����. ������󲻴��ڣ��׳��쳣. 
     * @param id 
     */ 
    public T get(PK id) { 
         
        return hedao.get(id); 
    } 

    /** 
     * ��ȡʵ�����͵�ȫ������ 
     */ 
    public List<T> getAll() { 
         
        return hedao.getAll(); 
    } 

    /** 
     * ��ȡȫ������,�������ֶ������������. 
     */ 
    public List<T> getAll(String orderBy, boolean isAsc) { 
         
        return hedao.getAll(orderBy, isAsc); 
    } 

    /** 
     * ֱ��ʹ��spring�ṩ��HibernateTemplate 
     */ 
    public HibernateTemplate getHibernateTemplate() { 
         
        return hgdao.getHibernateTemplate(); 
    } 

    /** 
     * �ж϶���ĳЩ���Ե�ֵ�����ݿ����Ƿ�Ψһ. 
     * 
     * @param uniquePropertyNames ��POJO�ﲻ���ظ��������б�,�Զ��ŷָ� ��"name,loginid,password" 
     */ 
    public boolean isUnique(T entity, String uniquePropertyNames) { 
         
        return hedao.isUnique(entity, uniquePropertyNames); 
    } 

    /** 
     * ��ҳ��ѯ������ʹ��hql. 
     * 
     * @param pageNo ҳ��,��1��ʼ. 
     */ 
    public Page pagedQuery(String hql, int pageNo, int pageSize, 
            Object... values) { 
         
        return hgdao.pagedQuery(hql, pageNo, pageSize, values); 
    } 

    /** 
     * ��ҳ��ѯ������ʹ������ò�ѯ�����������<code>Criteria</code>. 
     * 
     * @param pageNo ҳ��,��1��ʼ. 
     * @return ���ܼ�¼���͵�ǰҳ���ݵ�Page����. 
     */ 
    public Page pagedQuery(Criteria criteria, int pageNo, int pageSize) { 
         
        return hedao.pagedQuery(criteria, pageNo, pageSize); 
    } 

    /** 
     * ��ҳ��ѯ����������entityClass�Ͳ�ѯ������������Ĭ�ϵ�<code>Criteria</code>. 
     * 
     * @param pageNo ҳ��,��1��ʼ. 
     * @return ���ܼ�¼���͵�ǰҳ���ݵ�Page����. 
     */ 
    public Page pagedQuery(int pageNo, int pageSize, Criterion... criterions) { 
         
        return hedao.pagedQuery(pageNo, pageSize, criterions); 
    } 

    /** 
     * ��ҳ��ѯ����������entityClass�Ͳ�ѯ��������,�����������Ĭ�ϵ�<code>Criteria</code>. 
     * 
     * @param pageNo ҳ��,��1��ʼ. 
     * @return ���ܼ�¼���͵�ǰҳ���ݵ�Page����. 
     */ 
    public Page pagedQuery(int pageNo, int pageSize, String orderBy, 
            boolean isAsc, Criterion... criterions) { 
         
        return hedao.pagedQuery(pageNo, pageSize, orderBy, isAsc, criterions); 
    } 

    /** 
     * ɾ������. 
     */ 
    public void remove(T entity) { 
         
       hedao.remove(entity); 
    } 

    /** 
     * ����IDɾ������. 
     */ 
    public void removeById(PK id) { 
         
        hedao.removeById(id); 
    } 

    /** 
     * �������.<br> 
     * ����������ڱ�session�г־û���,�����κ��¡�<br> 
     * �����һ��seesionӵ����ͬ�ĳ־û���ʶ,�׳��쳣��<br> 
     * ���û�г־û���ʶ����,����save()��<br> 
     * ����־û���ʶ������һ���µ�ʵ��������,����save()��<br> 
     * ����Ǹ����汾��Ϣ��(<version>��<timestamp>)�Ұ汾���Ա���Ϊ�µ�ʵ���������save()��<br> 
     * �������update()���¹����йܶ��� 
     */ 
    public void save(T entity) { 

        hedao.save(entity); 
    } 
     
    /** 
     * �ڲ�ͬ��session�й����޸Ĺ����йܶ��� 
     */ 
    public void update(T entity){ 
         
        hedao.update(entity); 
    } 

} 


