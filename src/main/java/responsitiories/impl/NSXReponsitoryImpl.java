/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.NSX;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import responsitiories.INSXReponsitory;
import ultilities.HibernateUtil;

/**
 *
 * @author Admin
 */
public class NSXReponsitoryImpl implements INSXReponsitory {

    private Session sesion = HibernateUtil.getSessionFactory().openSession();

    private String fromtable = "from nsx";

    @Override
    public List<NSX> findAll() {
        Query query = sesion.createQuery(fromtable);
        return query.getResultList();
    }

    @Override
    public NSX findOneByMa(String ma) {
        String sql = fromtable + "where ma =: ma";
        Query query = sesion.createQuery(sql, NSX.class);
        query.setParameter("ma", ma);
        return (NSX) query.getSingleResult();
    }

    @Override
    public NSX findOneByID(UUID id) {
        String sql = fromtable + "where id =: id";
        Query query = sesion.createQuery(sql, NSX.class);
        query.setParameter("id", id);
        return (NSX) query.getSingleResult();
    }

    @Override
    public boolean save(NSX t) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(NSX t) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(NSX t) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
