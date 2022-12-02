/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.Ram;
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
public class RamReponsitory implements IReponsitory<Ram>{

    private Session session = HibernateUtil.getSessionFactory().openSession();
    private String fromTable = "FROM Ram";
    
    @Override
    public List<Ram> findAll() {
        Query query = session.createQuery(fromTable);
        return query.getResultList();
    }

    @Override
    public Ram findOneByMa(String ma) {
        String sql = fromTable + " where id =: id";
        Query query = session.createQuery(sql, Ram.class);
        query.setParameter("id", ma);
        return (Ram) query.getSingleResult();
    }
    
    public Ram findOneByKichCo(int kichCo) {
        String sql = fromTable + " where kichThuoc =: id";
        Query query = session.createQuery(sql, Ram.class);
        query.setParameter("id", kichCo);
        return (Ram) query.getSingleResult();
    }
  
    @Override
    public Ram findOneByID(UUID id) {
        String sql = fromTable + " where id = :id";
        Query query = session.createQuery(sql, Ram.class);
        query.setParameter("id", id);
        return (Ram) query.getSingleResult();
    }

    @Override
    public boolean save(Ram t) {
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
    public boolean update(Ram t) {
       Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean delete(Ram t) {
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
        System.out.println(new RamReponsitory().findOneByKichCo(12));
    }
}
