/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.ManHinh;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ultilities.HibernateUtil;

/**
 *
 * @author Home
 */
public class ManHinhRepository {
    private Session session = HibernateUtil.getSessionFactory().openSession();
    private String fromTable = " FROM man_hinh ";
    
    public List<ManHinh> findAll() {
        Query query = session.createQuery(fromTable);
        return query.getResultList();
    }

    public ManHinh findOneByMa(String ma) {
        String sql = fromTable + "where id =: id";
        Query query = session.createQuery(sql, ManHinh.class);
        query.setParameter("id", ma);
        return  (ManHinh) query.getSingleResult();
    }

    public ManHinh findOneByID(UUID id) {
        String sql = fromTable + "where id =: id";
        Query query = session.createQuery(sql, ManHinh.class);
        query.setParameter("id", id);
        return  (ManHinh) query.getSingleResult();  
    }

    public boolean save(ManHinh t) {
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

    public boolean update(ManHinh t) {
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

    public boolean delete(ManHinh t) {
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
        System.out.println(new CauHinhReponsitoryImpl().findAll());
    }
}
