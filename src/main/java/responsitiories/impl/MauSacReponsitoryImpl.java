/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.MauSac;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import responsitiories.IMauSacReponsitory;
import ultilities.HibernateUtil;

/**
 *
 * @author Admin
 */
public class MauSacReponsitoryImpl implements IMauSacReponsitory {

    private Session sesion = HibernateUtil.getSessionFactory().openSession();

    private String fromtable = "from MauSac";

    @Override
    public List<MauSac> findAll() {
        Query query = sesion.createQuery(fromtable);
        return query.getResultList();
    }

    @Override
    public MauSac findOneByMa(String ma) {
        String sql = fromtable + "where ma =: ma";
        Query query = sesion.createQuery(sql, MauSac.class);
        query.setParameter("ma", ma);
        return (MauSac) query.getSingleResult();
    }

    @Override
    public MauSac findOneByID(UUID id) {
        String sql = fromtable + "where id =: id";
        Query query = sesion.createQuery(sql, MauSac.class);
        query.setParameter("id", id);
        return (MauSac) query.getSingleResult();
    }

    @Override
    public boolean save(MauSac t) {
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
    public boolean delete(MauSac t) {
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
    public boolean update(MauSac t) {
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
