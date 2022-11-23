/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.CauHinh;
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
public class CauHinhReponsitory implements IReponsitory<CauHinh>{

    private Session session = HibernateUtil.getSessionFactory().openSession();
    private String fromTable = " FROM CauHinh ";
    
    @Override
    public List<CauHinh> findAll() {
        Query query = session.createQuery(fromTable);
        return query.getResultList();
    }

    @Override
    public CauHinh findOneByMa(String ma) {
        String sql = fromTable + "where id =: id";
        Query query = session.createQuery(sql, CauHinh.class);
        query.setParameter("id", ma);
        return  (CauHinh) query.getSingleResult();
    }

    @Override
    public CauHinh findOneByID(UUID id) {
        String sql = fromTable + "where id =: id";
        Query query = session.createQuery(sql, CauHinh.class);
        query.setParameter("id", id);
        return  (CauHinh) query.getSingleResult();  
    }

    @Override
    public boolean save(CauHinh t) {
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
    public boolean update(CauHinh t) {
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
    public boolean delete(CauHinh t) {
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
    public static void main(String[] args) {
        System.out.println(new CauHinhReponsitory().findAll());
    }
}
