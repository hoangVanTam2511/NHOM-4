/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.KhachHang;
import domainmodels.KhuyenMai;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;

/**
 *
 * @author FPT Shop
 */
public class KhuyenMaiRepositoryImpl implements IReponsitory<KhuyenMai> {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    private String fromTable = "FROM KhuyenMai";

    @Override
    public List<KhuyenMai> findAll() {
        Query query = session.createQuery(fromTable, KhuyenMai.class);
        return query.getResultList();
    }

    @Override
    public KhuyenMai findOneByMa(String ma) {
        String sql = "From KhuyenMai WHERE ma =: ma";
        Query query = session.createQuery(sql, KhuyenMai.class);
        query.setParameter("ma", ma);
        return (KhuyenMai) query.getSingleResult();
    }

    @Override
    public KhuyenMai findOneByID(UUID id) {
        String sql = fromTable + "WHERE id =: id";
        Query query = session.createQuery(sql, KhuyenMai.class);
        query.setParameter("id", id);
        return (KhuyenMai) query.getSingleResult();
    }

    @Override
    public boolean save(KhuyenMai t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean update(KhuyenMai t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean delete(KhuyenMai t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new KhuyenMaiRepositoryImpl().findAll());
    }
}
