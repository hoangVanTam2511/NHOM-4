/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.KhachHang;
import domainmodels.KhuyenMai;
import domainmodels.PhieuBaoHanh;
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
public class PhieuBaoHanhRepositoryImpl implements IReponsitory<PhieuBaoHanh> {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    private String fromTable = "FROM PhieuBaoHanh";

    @Override
    public List<PhieuBaoHanh> findAll() {
        Query query = session.createQuery(fromTable, PhieuBaoHanh.class);
        return query.getResultList();
    }

    @Override
    public PhieuBaoHanh findOneByMa(String ma) {
        String sql = "From KhachHang WHERE ma =: ma";
        Query query = session.createQuery(sql, PhieuBaoHanh.class);
        query.setParameter("ma", ma);
        return (PhieuBaoHanh) query.getSingleResult();
    }

    @Override
    public PhieuBaoHanh findOneByID(UUID id) {
        String sql = fromTable + "WHERE id =: id";
        Query query = session.createQuery(sql, PhieuBaoHanh.class);
        query.setParameter("id", id);
        return (PhieuBaoHanh) query.getSingleResult();
    }

    @Override
    public boolean save(PhieuBaoHanh t) {
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
    public boolean update(PhieuBaoHanh t) {
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
    public boolean delete(PhieuBaoHanh t) {
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

}
