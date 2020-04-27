package vn.myclass.core.data.daoimpl;

import org.hibernate.*;
import vn.myclass.core.common.coreconstant.CoreConstant;
import vn.myclass.core.common.utils.HibernateUtils;
import vn.myclass.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable,T> implements GenericDao<ID,T>{
    private Class<T> persistenceClass;
    public AbstractDao()
    {
        this.persistenceClass= (Class<T>) ((ParameterizedType)getClass(). getGenericSuperclass()).getActualTypeArguments()[1];
    }
    public String getPersistenceClassName()
    {
        return persistenceClass.getSimpleName();
    }
    public List<T> findAll() {
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        List<T> list= new ArrayList<T>();
        try {
            StringBuilder sql=new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query=session.createQuery(sql.toString());
            list=query.list();
            transaction.commit();
        }catch (HibernateException e)
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return list;
    }

    public T update(T entity) {
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        T result=null;
        try {
          result= (T)session.merge(entity);
          transaction.commit();

        }catch (HibernateException e)
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

    public void save(T entity) {
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();

        try {
            session.persist(entity);
            transaction.commit();

        }catch (HibernateException e)
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }

    public T findById(ID id) {
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        T result=null;
        try {
            StringBuilder sql=new StringBuilder("from ");

            result= (T)session.get(persistenceClass,id);
            if(result==null)
            {
                throw new ObjectNotFoundException("NOT FOUND "+id,null);
            }
            transaction.commit();

        }catch (HibernateException e)
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

    public Object[] findByProperty(String property, Object value, String sortExpresstion, String sortDirection) {
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Object total=0;
        List<T> list= new ArrayList<T>();
        try {
            StringBuilder sql=new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            if(property!=null&&value !=null)
            {
                sql.append(" where ").append(property).append("=:value");
            }
            if(sortDirection!=null&&sortDirection !=null)
            {
                sql.append(" order by ").append(sortExpresstion);
                sql.append(" "+(sortDirection.equals(CoreConstant.SORT_ASC)?"asc":"desc"));
            }
            Query query=session.createQuery(sql.toString());
            if (value!=null)
            {
                query.setParameter("value",value);
            }

            StringBuilder sql1=new StringBuilder("select count(*) from ");
            sql1.append(this.getPersistenceClassName());
            if(property!=null&&value!=null)
            {
                sql1.append(" where ").append(property).append("= :value");
            }

            Query query1=session.createQuery(sql.toString());
            if(value!=null)
            {
                query1.setParameter("value",value);
            }
            total=query.list().get(0);
            list=query1.list();


            transaction.commit();

        }catch (HibernateException e)
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return new Object[] {total,list};
    }

    public Integer delete(List<ID> ids) {
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Integer count=0;
        try {
            for(ID item :ids)
            {
                T t=(T) session.get(this.persistenceClass,item);
                session.delete(t);
                count++;
            }
            transaction.commit();

        }catch (HibernateException e)
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return count;
    }
}
