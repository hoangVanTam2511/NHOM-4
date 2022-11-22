/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.DongSp;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import responsitiories.IDongSPReponsitory;
import ultilities.HibernateUtil;

/**
 *
 * @author Admin
 */
public class DongSPReponsitoryImpl implements IDongSPReponsitory {

    private Session sesion = HibernateUtil.getSessionFactory().openSession();

    private String fromtable = "from dong_sp";

    @Override
    public List<DongSp> findAll() {
        Query query = sesion.createQuery(fromtable);
        return query.getResultList();
    }

    @Override
    public DongSp findOneByMa(String ma) {
        String sql = fromtable + "where ma =: ma";
        Query query = sesion.createQuery(sql, DongSp.class);
        query.setParameter("ma", ma);
        return (DongSp) query.getSingleResult();
    }

    @Override
    public DongSp findOneByID(UUID id) {
        String sql = fromtable + "where id =: id";
        Query query = sesion.createQuery(sql, DongSp.class);
        query.setParameter("id", id);
        return (DongSp) query.getSingleResult();
    }

    @Override
    public boolean save(DongSp t) {
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
    public boolean delete(DongSp t) {
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
    public boolean update(DongSp t) {
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
