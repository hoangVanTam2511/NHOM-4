/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.Imei;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ultilities.HibernateUtil;
import responsitiories.ISanPhamRepository;

/**
 *
 * @author Admin
 */
public class SanPhamReponsitoryImpl implements ISanPhamRepository {

    private Session sesion = HibernateUtil.getSessionFactory().openSession();

    private String fromtable = "from SanPham";

    @Override
    public List<Imei> findAll() {
        Query query = sesion.createQuery(fromtable);
        return query.getResultList();
    }

    @Override
    public Imei findOneByLmei(String Lmei) {
        String sql = fromtable + "where soImei =: so_imei";
        Query query = sesion.createQuery(sql, Imei.class);
        query.setParameter("so_imei", Lmei);
        return (Imei) query.getSingleResult();
    }

    @Override
    public Imei findOneByID(UUID id) {
        String sql = fromtable + "where id =: id";//cái 1 lấy từ model, cái 2 từ sql
        Query query = sesion.createQuery(sql, Imei.class);
        query.setParameter("id", id);//lấy từ sql
        return (Imei) query.getSingleResult();
    }

    @Override
    public boolean save(Imei t) {
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
    public boolean delete(Imei t) {
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
    public boolean update(Imei t) {
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
