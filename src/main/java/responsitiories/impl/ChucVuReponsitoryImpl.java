/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.ChucVu;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;

/**
 *
 * @author Admin
 */
public class ChucVuReponsitoryImpl implements IReponsitory<ChucVu> {

    @Override
    public List<ChucVu> findAll() {
        List<ChucVu> chucVus = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT c FROM ChucVu c";
            TypedQuery query = session.createQuery(hql, ChucVu.class);
            chucVus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chucVus;
    }

    @Override
    public ChucVu findOneByMa(String id) {
        ChucVu chucVu = new ChucVu();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT c FROM ChucVu c WHERE c.ma = :id";
            TypedQuery query = session.createQuery(hql, ChucVu.class);
            query.setParameter("id", id);
            chucVu = (ChucVu) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chucVu;
    }

    @Override
    public ChucVu findOneByID(UUID id) {
        ChucVu chucVu = new ChucVu();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT c FROM ChucVu c WHERE c.id = :id";
            TypedQuery query = session.createQuery(hql, ChucVu.class);
            query.setParameter("id", id);
            chucVu = (ChucVu) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chucVu;
    }

    @Override
    public boolean save(ChucVu chucVu) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(chucVu);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
            return true;
        }
    }

    @Override
    public boolean update(ChucVu chucVu) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                // cái này giống sử dụgn câu lệnh tạo giao dịch và nó có khả năng không thực hiện giao dịch khi bị sai
                session.update(chucVu);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
        }
        return true;

    }

    public boolean delete(ChucVu chucVu) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.delete(chucVu);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        ChucVu chucVu = new ChucVu();
        chucVu = new ChucVuReponsitoryImpl().findOneByMa("CV04");
        chucVu.setTen("MINH THICH CAU RAT NHIEU");
        System.out.println(chucVu);
    }
}
