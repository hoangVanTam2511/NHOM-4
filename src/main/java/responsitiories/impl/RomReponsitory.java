/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.Rom;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;

/**
 *
 * @author Home
 */
public class RomReponsitory implements IReponsitory<Rom>{

    private Session session = HibernateUtil.getSessionFactory().openSession();
    private String fromTable = "FROM Rom";
    
    @Override
    public List<Rom> findAll() {
       Query query = session.createQuery(fromTable);
        return query.getResultList();
    }

    @Override
    public Rom findOneByMa(String ma) {
       String sql = fromTable + "where ma =: id";
        Query query = session.createQuery(sql, Rom.class);
        query.setParameter("id", ma);
        return (Rom) query.getSingleResult();
    }

    @Override
    public Rom findOneByID(UUID id) {
        String sql = fromTable + "where id =: id";
        Query query = session.createQuery(sql, Rom.class);
        query.setParameter("id", id);
        return (Rom) query.getSingleResult();
    }

    @Override
    public boolean save(Rom t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean update(Rom t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean delete(Rom t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
}
