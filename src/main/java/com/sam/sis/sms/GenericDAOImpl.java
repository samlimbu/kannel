/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.sms;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sam
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    
    private Class<T> persistClass;
            
    public GenericDAOImpl() {
        persistClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
            
            
    @Override
    public List<T> getAll() {
        session = sessionFactory.openSession();
        List<T> list = session.createCriteria(persistClass).list();
        session.close();
        return list;
    }
      

    @Override
    public void insert(T t) {
         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         session.save(t);
         transaction.commit();
         session.close();
    }

    @Override
    public void update(T t) {
         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         session.update(t);
         transaction.commit();
         session.close();
    }

    @Override
    public boolean delete(int id) {
        boolean status = false;
        T t = getbyId(id); 
        try  {
            //Step-2: Implementation
           //session.close();
            session = sessionFactory.getCurrentSession();
            System.out.println("------------------------------------------------------getcurseesio");
        }   catch (HibernateException e)      {
            //Step-3: Implementation
            System.out.println("----open session--------------------" + e.getMessage());
            session = sessionFactory.openSession();
        }
         //session = sessionFactory.openSession();
         //session = sessionFactory.getCurrentSession();
         if(t!=null){
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            session.close();
            status=true;
         }   
         return status;
    }

    @Override
    public T getbyId(int id) {
        //session = sessionFactory.getCurrentSession();
        session = sessionFactory.openSession();
        T t = (T)session.get(persistClass, id);
        session.close();
        return t;
      
         
    }
    @Override
    public void saveorupdate(T t) {
         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         session.saveOrUpdate(t);
         transaction.commit();
         session.close();
    }
}
