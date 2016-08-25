package ru.dz.rmis.repository;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> entityClass;
    
    @Autowired
    private SessionFactory sessionFactory;

    public AbstractDao(Class<? extends T> targetClass) {
        entityClass = (Class<T>) targetClass;
    }

    public AbstractDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }


    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Serializable create(T pObj) {
        final Session session = getSession();
        return session.save(pObj);
    }

    public void createOrUpdate(T pObj) {
        final Session session = getSession();
        session.saveOrUpdate(pObj);
    }

    public void delete(T pObj) {
        if (pObj != null) {
            getSession().delete(pObj);
        }
    }

    public void delete(Serializable id) {
        delete(findByPK(id));
    }

    public T findByPK(Serializable id) {
        return getSession().get(entityClass, id);
    }

    public void update(T pObj) {
        getSession().saveOrUpdate(pObj);
    }

    public List<T> findAll() {
        try {
            Criteria crit = getSession().createCriteria(entityClass);
            return crit.list();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Long countAll() {
        try {
            Criteria crit = getSession().createCriteria(entityClass);
            crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            crit.setProjection(Projections.rowCount());
            return (Long) crit.uniqueResult();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
