package com.ccit.dao;


import com.ccit.pojo.SearchParam;
import com.ccit.utils.Page;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDAO<T,pk extends Serializable> {
    @Inject
    private SessionFactory sessionFactory;

    private Class<?> aClass;

    public BaseDAO(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        aClass = (Class<?>) type.getActualTypeArguments()[0];
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    public void save(T entity){
        getSession().saveOrUpdate(entity);
    }
    public T findOne(pk id){
       return (T) getSession().get(aClass,id);
    }

    public Page<T> findAll(List<SearchParam> paramList,Integer p){
        Criteria criteria = getSession().createCriteria(aClass);

        for(SearchParam param:paramList){
             Object value = param.getValue();
             if("like".equalsIgnoreCase(param.getType())){
                 criteria.add(Restrictions.like(param.getPropertyName(),value.toString(), MatchMode.ANYWHERE));
             }else if("ge".equals(param.getType())){
                 criteria.add(Restrictions.ge(param.getPropertyName(),(Float)value));
             }else if("le".equals(param.getType())){
                 criteria.add(Restrictions.le(param.getPropertyName(),(Float)value));
             }
        }
        Integer count = getCount(criteria).intValue();
        Page<T> page = new Page<T>(p,count,5);
        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(page.getSize());
        page.setItems(criteria.list());

        return page;
    }
    public List<T> findAll(){
        Criteria criteria = getSession().createCriteria(aClass);
        return criteria.list();
    }
    public void delete(T entity){
        getSession().delete(entity);
    }
    public void delete(pk id){
        getSession().delete(findOne(id));
    }
    public Long getCount(Criteria criteria){
        ResultTransformer transformer = criteria.ROOT_ENTITY;
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();
        criteria.setProjection(null);
        criteria.setResultTransformer(transformer);
        return count;
    }
}
