/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitiories.impl;

import domainmodels.SanPham;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class SanPhamReponsitoryImpl implements IReponsitory<SanPham> {

    @Override
    public List<SanPham> findAll() {
        List<SanPham> sanPhams = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT ct FROM SanPham ct";
            TypedQuery query = session.createQuery(hql, SanPham.class);
            sanPhams = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhams;
    }

    @Override
    public SanPham findOneByMa(String ma) {
        SanPham sanPhams = new SanPham();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT ct FROM SanPham ct where ct.ma = :id";
            TypedQuery query = session.createQuery(hql, SanPham.class);
            query.setParameter("id", ma);
            sanPhams = (SanPham) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhams;

    }

    @Override
    public boolean save(SanPham t) {
         try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(t);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(SanPham t) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.update(t);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean delete(SanPham t) {
         try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.delete(t);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
        }
        return true;
    }

    @Override
    public SanPham findOneByID(UUID id) {
        SanPham sanPhams = new SanPham();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT ct FROM SanPham ct where ct.id = :id";
            TypedQuery query = session.createQuery(hql, SanPham.class);
            query.setParameter("id", id);
            sanPhams = (SanPham) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhams;
    }
}
