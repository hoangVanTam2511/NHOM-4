/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.DongSp;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class DongSpReponsitoryImpl implements IReponsitory<DongSp> {

    @Override
    public List<DongSp> findAll() {
        List<DongSp> listDongSp = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM DongSp p ";
            TypedQuery typedQuery = session.createQuery(hql);
            listDongSp = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDongSp;
    }

    @Override
    public DongSp findOneByMa(String ma) {
        DongSp dongSp = new DongSp();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM DongSp p WHERE p.ma = :id ";
            Query query = session.createQuery(hql);
            query.setParameter("id", ma);
            dongSp = (DongSp) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dongSp;
    }

    @Override
    public DongSp findOneByID(UUID id) {
        DongSp dongSp = new DongSp();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM DongSp p WHERE p.ma = :id ";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            dongSp = (DongSp) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dongSp;
    }

    @Override
    public boolean save(DongSp dongSp) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            session.save(dongSp);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(DongSp dongSp) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();// Lấy transaction tuơng ứng 
            trans.begin();
            session.delete(dongSp);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(DongSp dongSp) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.update(dongSp);
                trans.commit();
            } catch (Exception e) {
                trans.rollback();
                e.printStackTrace();
                return false;
            }
            return true;
        }

    }
    public DongSp findOneByMaAndTen(String ma, String ten) {
         DongSp dongSp = new DongSp();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM DongSp p WHERE p.ma = :id  and p.ten = :ten";
            Query query = session.createQuery(hql);
            query.setParameter("id", ma);
            query.setParameter("ten", ten);
            dongSp = (DongSp) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dongSp;
    }
}
